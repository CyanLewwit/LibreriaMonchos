

package com.CyanL.LibreriaMonchos.controladores;

import com.CyanL.LibreriaMonchos.entidades.Editorial;
import com.CyanL.LibreriaMonchos.services.EditorialSS;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editorial")
public class EditorialControlador {

    @Autowired
    private EditorialSS ess;

    @GetMapping("/lista")
    public String listaeditorial(ModelMap m) {
        List<Editorial> editoriales = ess.listarall();
        m.addAttribute("editoriales", editoriales);
        return null;
    }

}
