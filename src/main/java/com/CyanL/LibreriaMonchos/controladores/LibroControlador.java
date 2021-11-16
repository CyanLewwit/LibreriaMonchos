

package com.CyanL.LibreriaMonchos.controladores;

import com.CyanL.LibreriaMonchos.services.LibroSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroSS lss;

    @GetMapping("/lista")
    public String listalibros(ModelMap m) {

        return null;
    }


}
