

package com.CyanL.LibreriaMonchos.controladores;

import com.CyanL.LibreriaMonchos.entidades.Autor;
import com.CyanL.LibreriaMonchos.services.AutorSS;
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
@RequestMapping("/autor")
public class AutorControlador {
    
    @Autowired
    private AutorSS as;
    
    @GetMapping("/lista")
    public String listautores(ModelMap m) {
        List<Autor> autores = as.listarall();
        m.addAttribute("autores", autores);
        return "ListaAutores";
    }
    
    @GetMapping("/registroautor")
    public String muestraregistro() {
        return "GuardarAutor";
    }
    
    @PostMapping("/registroautor")
    public String registro(ModelMap modelo, @RequestParam String nombre) throws Exception {
        try {
            System.out.println("Entro al tryyyy");
            as.crearAutor(nombre);
            System.out.println("Paso la creacion");
            modelo.put("exito", "Registro Exitoso");
            return "GuardarAutor";
        } catch (Exception e) {
            modelo.put("fail", "Registro Fallido(Falta de uno o mas datos)");
            return "GuardarAutor";
        }
    }
    
    @GetMapping("/modifautor/{id}")
    public String FormModAut(@PathVariable String id, ModelMap m) {
        m.put("autor", as.getOne(id));
        return "ModAutor";
    }
    
    @PostMapping("/modifautor/{id}")
    public String mod(ModelMap m, @PathVariable String id, @RequestParam String name) throws Exception {
        try {
            as.ModAutor(id, name);
            m.addAttribute("exito", "Modificacion exitosa");
            return "ModAutor";
        } catch (Exception e) {
            m.addAttribute("fail", "Modificacion Fallida(Error)");
            return "ModAutor";
        }
    }


}
