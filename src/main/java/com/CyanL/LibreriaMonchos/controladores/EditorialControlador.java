

package com.CyanL.LibreriaMonchos.controladores;

import com.CyanL.LibreriaMonchos.entidades.Editorial;
import com.CyanL.LibreriaMonchos.services.EditorialSS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialSS ess;
    
    @GetMapping("/lista")
    public String listautores(ModelMap m) {
        List<Editorial> editoriales = ess.listarall();
        m.addAttribute("editoriales", editoriales);
        return "ListaEditoriales";
    }
    
    @GetMapping("/registroedi")
    public String muestraregistro() {
        return "GuardarEditorial";
    }
    
    @PostMapping("/registroedi")
    public String registro(ModelMap modelo, @RequestParam String nombre) throws Exception {
        try {
            ess.crearEditorial(nombre);
            modelo.put("exito", "Registro Exitoso");
            return "GuardarEditorial";
        } catch (Exception e) {
            modelo.put("fail", "Registro Fallido(Falta de uno o mas datos)");
            return "GuardarEditorial";
        }
    }
    
    @GetMapping("/modedi/{id}")
    public String FormModAut(@PathVariable String id, ModelMap m) {
        m.put("editorial", ess.getOne(id));
        return "ModEditorial";
    }
    
    @PostMapping("/modedi/{id}")
    public String mod(ModelMap m, @PathVariable String id, @RequestParam String nombre) throws Exception {
        try {
            ess.ModEditorial(id, nombre);
            return "redirect:/editorial/lista";
        } catch (Exception e) {
            m.put("fail", "Modificacion Fallida(Error)");
            return "ModEditorial";
        }
    }

    
    @GetMapping("/eliminareditorial/{id}")
    public String eliminar(@PathVariable String id){
        try{
            ess.Eliminareditorial(id);
            return "redirect:/editorial/lista";
        } catch (Exception e){
            return "ListaAutores";
        }
    }


  

}
