

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
    public Editorial crearEditorial(String name) throws Exception {
        validarName(name);
        Editorial s=new Editorial();
        s.setNombre(name);
        s.setAlta(true);
        return ep.save(s);
    }
    
    @Transactional
    public Editorial ModEditorial(String id,String name) throws Exception {
        Editorial edi = getOne(id);
        if (edi != null) {
            validarName(edi.getNombre());
            edi.setNombre(name);
            return ep.save(edi);
        } else {
            throw new Exception("La Editorial que desea Modificar no existe en la BdD");
        }
    }
    
    @Transactional
    public void Eliminareditorial(String id) throws Exception {
        Editorial d = getOne(id);
        if (d != null) {
            ep.delete(d);
        } else if (d == null) {
            throw new Exception("La editorial que desea eliminar no existe en la BdD");
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
    
    @Transactional
    public void validarName(String x) throws Exception {
        if (x == null || x.isEmpty()) {
            throw new Exception("Nombre no puede ser vacio o nulo");
        }
    }
    
      @Transactional
    public void validarId(String id) throws Exception{
        Editorial x = getOne(id);
        if (x != null) {
            throw new Exception("Ya existe un Autor con ese id en la BdD");
        }
    }
}

    


