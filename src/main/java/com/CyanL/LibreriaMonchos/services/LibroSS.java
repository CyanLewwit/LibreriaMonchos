package com.CyanL.LibreriaMonchos.services;

import com.CyanL.LibreriaMonchos.entidades.Autor;
import com.CyanL.LibreriaMonchos.entidades.Editorial;
import com.CyanL.LibreriaMonchos.entidades.Libro;
import com.CyanL.LibreriaMonchos.repositorios.LibroRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroSS {

    @Autowired
    LibroRepositorio lp;

    @Autowired
    AutorSS as;

    @Autowired
    EditorialSS edss;
    
    @Transactional
    public Libro crearLibro(String id, Long isbn, String tit, Integer a, Integer ej, Integer ejp, Integer ejr, boolean x, Autor autor, Editorial edi) throws Exception {
        Libro l = new Libro();
        l.setId(id);
        l.setIsbn(isbn);
        l.setTitulo(tit);
        l.setAnio(a);
        l.setEjemplares(ej);
        l.setEjemplaresPrestados(ejp);
        l.setEjemplaresRestantes(ejr);
        l.setAutor(autor);
        l.setEditorial(edi);
        l.setAlta(x);
        validar(l.getIsbn(), l.getTitulo(), l.getAnio(), l.getEjemplares(), l.getEjemplaresPrestados(), l.getEjemplaresRestantes(), l.isAlta(), l.getAutor(), l.getEditorial());
        return lp.save(l);
    }
    
    @Transactional
    public Libro ModLibro(Libro l) throws Exception {
        Libro o = lp.getById(l.getId());
        if (o != null) {
            validar(l.getIsbn(), l.getTitulo(), l.getAnio(), l.getEjemplares(), l.getEjemplaresPrestados(), l.getEjemplaresRestantes(), l.isAlta(), l.getAutor(), l.getEditorial());
            o.setIsbn(l.getIsbn());
            o.setTitulo(l.getTitulo());
            o.setAnio(l.getAnio());
            o.setEjemplares(l.getEjemplares());
            o.setEjemplaresPrestados(l.getEjemplaresPrestados());
            o.setEjemplaresRestantes(l.getEjemplaresRestantes());
            o.setAlta(l.isAlta());
            o.setAutor(l.getAutor());
            o.setEditorial(l.getEditorial());
            return lp.save(o);
        } else {
            throw new Exception("El libro que desea modificar no existe en la BdD");

        }
    }
    
    @Transactional
    public void EliminarLibro(Libro libro) throws Exception {
        Libro o = lp.getById(libro.getId());
        if (o != null) {
            lp.delete(o);
        } else if (o == null) {
            throw new Exception("La editorial que desea eliminar no existe en la BdD");
        }
    }


    @Transactional
    public void validar(long isbn, String t, Integer anio, Integer ej, Integer ejP, Integer ejR, boolean h, Autor autor, Editorial edi) throws Exception {
        Libro y = lp.buscarXisbn(isbn);
        if (y == null) {
            if (isbn < 0) {
                throw new Exception("El isbn no puede ser negativo");
            }
        } else {
            throw new Exception("El isbn ya existe en la BdD");
        }

        if (t == null || t.isEmpty()) {
            throw new Exception("El titulo no puede ser vacio o nulo");
        }

        if (anio == null || anio < 0) {
            throw new Exception("El anio no puede ser negativo o nulo");
        }

        if (ej == null || ej < 0) {
            throw new Exception("El numero de Ejemplares no puede ser negativo o nulo");
        }

        if (ejP == null || ejP < 0) {
            throw new Exception("El numero de Ejemplares Prestados no puede ser negativo o nulo");
        }

        if (ejR == null || ejR < 0) {
            throw new Exception("El numero de Ejemplares Restantes no puede ser negativo o nulo");
        }

        Autor x = as.ap.getById(autor.getId());
        if (x == null) {
            throw new Exception("El autor Ingresado no existe, crear antes");
        }

        Editorial b = edss.ep.getById(edi.getId());
        if (b == null) {
            throw new Exception("La Editorial Ingresada no existe, crear antes");
        }

        if (ejP > ej) {
            throw new Exception("No puede haber mas libros prestados que existentes");
        }
        if (ejR > ej) {
            throw new Exception("No puede haber mas libros restantes que existentes");
        }

        if (ej != (ejP + ejR)) {
            throw new Exception("Hay un error, la contabilidad de libros no coincide");
        }

    }

    @Transactional
    public List<Libro> libros() {
        return lp.findAll();
    }

    @Transactional
    public Libro getOnebyId(String Id) {
        return lp.getById(Id);
    }


}
