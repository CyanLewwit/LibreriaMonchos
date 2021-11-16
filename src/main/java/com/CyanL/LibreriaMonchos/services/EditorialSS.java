

package com.CyanL.LibreriaMonchos.services;

import com.CyanL.LibreriaMonchos.entidades.Editorial;
import com.CyanL.LibreriaMonchos.repositorios.EditorialRepositorio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialSS {
    
    @Autowired
    EditorialRepositorio ep;
    
    @Transactional
    public Editorial crearEditorial(String id, String name) throws Exception {
        Editorial edi = new Editorial();
        edi.setId(id);
        edi.setNombre(name);
        edi.setAlta(true);
        validacion(edi.getNombre(), edi.getId(), edi.isAlta());
        return ep.save(edi);
    }
    
    @Transactional
    public Editorial ModEditorial(Editorial edi) throws Exception {
        Editorial d = ep.getById(edi.getId());
        if (d != null) {
            validacion(edi.getNombre(), edi.getId(), edi.isAlta());
            d.setNombre(edi.getNombre());
            d.setAlta(edi.isAlta());
            return ep.save(d);
        } else {
            throw new Exception("La editorial que desea modificar no existe en la BdD");
        }
    }
    
    @Transactional
    public void Eliminareditorial(Editorial edi) throws Exception {
        Editorial d = ep.getById(edi.getId());
        if (d != null) {
            ep.delete(d);
        } else if (d == null) {
            throw new Exception("La editorial que desea eliminar no existe en la BdD");
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

        Editorial x = ep.getById(id);
        if (x != null) {
            throw new Exception("Ya existe una Editorial con ese id en la BdD");
        }

    }
    
    @Transactional
    public List<Editorial> listarall() {
        return ep.findAll();
    }

    @Transactional
    public Editorial getOne(String id) {
        return ep.getById(id);
    }
}

    


