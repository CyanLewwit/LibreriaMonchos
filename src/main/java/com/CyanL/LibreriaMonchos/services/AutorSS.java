

package com.CyanL.LibreriaMonchos.services;

import com.CyanL.LibreriaMonchos.entidades.Autor;
import com.CyanL.LibreriaMonchos.repositorios.AutorRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorSS {
    
    @Autowired
    AutorRepositorio ap;
    
    @Transactional
    public Autor crearAutor(String name) throws Exception {
        validarName(name);
        System.out.println("Valido el nombre");
        Autor autor = new Autor();
        System.out.println("Creo el autor");
        autor.setNombre(name);
        System.out.println("Seteo el name en el autor");
        autor.setAlta(true);
        System.out.println("Seteo el boolean");
        return ap.save(autor);
    }
    
    @Transactional
    public Autor ModAutor(String id, String n) throws Exception {
        Autor r = ap.getById(id);
        if (r != null) {
            validarName(r.getNombre());
            r.setNombre(n);
            return ap.save(r);
        } else {
            throw new Exception("El auto que desea Modificar no existe en la BdD");
        }
    }
    
    
    @Transactional
    public void ElimAutor(Autor autor) throws Exception {
        Autor r = ap.getById(autor.getId());
        if (r != null) {
            ap.delete(r);
        } else if (r == null) {
            throw new Exception("El auto que desea eliminar no existe en la BdD");
        }
    }
    
    @Transactional
    public void validacion(String name, String id, boolean a) throws Exception {
        if (name == null || name.isEmpty()) {
            throw new Exception("Nombre no puede ser vacio o nulo");
        }
        if (id == null || id.isEmpty()) {
            throw new Exception("Id no puede ser vacio o nulo");
        }

        Autor x = ap.getById(id);
        if (x != null) {
            throw new Exception("Ya existe un Autor con ese id en la BdD");
        }

    }

    
    @Transactional
    public List<Autor> listarall() {
        return ap.findAll();
    }

    @Transactional
    public Autor getOne(String id) {
        return ap.getById(id);
    }
    
    @Transactional
    public void validarName(String x) throws Exception{
        if (x == null || x.isEmpty()) {
            throw new Exception("Nombre no puede ser vacio o nulo");
        }
    }
    
    @Transactional
    public void validarId(String id) throws Exception{
        Autor x = ap.getById(id);
        if (x != null) {
            throw new Exception("Ya existe un Autor con ese id en la BdD");
        }
    }
}
