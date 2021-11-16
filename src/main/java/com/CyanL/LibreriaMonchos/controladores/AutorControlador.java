

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
        return null;
    }
    
    @GetMapping("/registroautor")
    public String muestraregistro() {
        return null;
    }
    
    @PostMapping("/registroautor")
    public String registro(ModelMap modelo, @RequestParam String name, @RequestParam String alta) throws Exception {
        try {
            as.crearAutor(name);
            modelo.put("exito", "Registro Exitoso");
            return null;
        } catch (Exception e) {
            modelo.put("fail", "Registro Fallido(Falta de uno o mas datos)");
            return null;
        }
    }
    
    @GetMapping("/modifautor/{id}")
    public String FormModAut(@PathVariable String id, ModelMap m) {
        m.put("autor", as.getOne(id));
        return null;
    }
    
        @PostMapping("/modifautor/{id}")
    public String mod(ModelMap m, @PathVariable String id, @RequestParam String name, @RequestParam String a) throws Exception {
        boolean x;
        if (a.equalsIgnoreCase("si")) {
            x = true;
        } else if (a.equalsIgnoreCase("no")) {
            x = false;
        } else {
            throw new Exception("Error en el valor de alta");
        }
        try {
            as.ModAutor(id, name, x);
            m.addAttribute("extito", "Modificacion exitosa");
            return null;
        } catch (Exception e) {
            m.addAttribute("fail", "Modificacion Fallida(Error)");
            return null;
        }
    }


}
