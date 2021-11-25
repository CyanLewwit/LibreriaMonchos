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
    public Libro crearLibro(Integer isbn, String tit, Integer a, Integer ej, Integer ejp, String autor, String edi) throws Exception {
        validar(isbn, tit, a, ej, ejp, autor, edi);
        System.out.println("Se validaron los datos");
        Libro l = new Libro();
        l.setIsbn(isbn);
        System.out.println("Se seteo el isbn");
        l.setTitulo(tit);
        System.out.println("Se seteo el titulo");
        l.setAnio(a);
        System.out.println("Se seteo el anio");
        l.setEjemplares(ej);
        System.out.println("Se setearon los ejemplares");
        l.setEjemplaresPrestados(ejp);
        System.out.println("Los otros ejemplares");
        l.setAutor(as.buscarxname(autor));
        System.out.println("Se seteo el Autor");
        l.setEditorial(edss.buscxname(edi));
        System.out.println("Se seteo la Editorial");
        l.setAlta(true);
        l.setEjemplaresRestantes(ej-ejp);
        System.out.println("Los otros de los otros ejemplares");
        return lp.save(l);
    }
    
    @Transactional
    public Libro ModLibro(String id,Integer isbn, String tit, Integer a, Integer ej, Integer ejp, String autor, String edi) throws Exception {
        validar(isbn,tit,a,ej,ejp,autor,edi);
        System.out.println("Se validaron los datos");
        Libro o=getOnebyId(id);
        if (o != null) {
            o.setAnio(a);
            System.out.println("Se seteo el anio");
            o.setEjemplares(ej);
            System.out.println("Se setearon los ejemplares");
            o.setEjemplaresPrestados(ejp);
            System.out.println("Los otros ejemplares");
            o.setEjemplaresRestantes(ej-ejp);
            System.out.println("Los otros de los otros ejemplares");
            o.setIsbn(isbn);
            System.out.println("Se seteo el isbn");
            o.setTitulo(tit);
            System.out.println("Se seteo el titulo");
            o.setAutor(as.buscarxname(autor));
            System.out.println("Se seteo el Autor");
            o.setEditorial(edss.buscxname(edi));
            System.out.println("Se seteo la Editorial");
            return lp.save(o);
        } else {
            throw new Exception("El libro que desea modificar no existe en la BdD");

        }
    }
    
    @Transactional
    public void EliminarLibro(String id) throws Exception {
        Libro o = lp.getById(id);
        if (o != null) {
            o.setAutor(null);
            o.setEditorial(null);
            lp.delete(o);
        } else if (o == null) {
            throw new Exception("La editorial que desea eliminar no existe en la BdD");
        }
    }


    @Transactional
    public void validar(Integer isbn, String t, Integer anio, Integer ej, Integer ejP,String autor, String edi) throws Exception {
   
        
       if (isbn < 0) {
            throw new Exception("El isbn no puede ser negativo");
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
         if (ejP > ej) {
            throw new Exception("No puede haber mas libros prestados que existentes");
        }
       

        Autor x = as.buscarxname(autor);
        if (x == null) {
            System.out.println("No habia autor, se creo");
            as.crearAutor(autor);
        }

        Editorial b = edss.buscxname(edi);
        if (b == null) {
            System.out.println("No habia editorial se creo");
            edss.crearEditorial(edi);
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
