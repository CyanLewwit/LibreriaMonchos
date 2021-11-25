

package com.CyanL.LibreriaMonchos.controladores;

import com.CyanL.LibreriaMonchos.entidades.Libro;
import com.CyanL.LibreriaMonchos.services.LibroSS;
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
@RequestMapping("/libro")
public class LibroControlador {
    
    @Autowired
    private LibroSS ls;

    @GetMapping("/lista")
    public String listalibros(ModelMap m) {
        List<Libro> libros=ls.libros();
        m.addAttribute("libros", libros);
        return "ListaLibro";
    }
    
    @GetMapping("/registrolibro")
    public String muestraregistro(){
        return "GuardarLibro";
    }
    
    @PostMapping("/registrolibro")
    public String registro(ModelMap m, @RequestParam String titulo, @RequestParam Integer isbn, @RequestParam Integer anio,@RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam String autor, @RequestParam String editorial) throws Exception{
        System.out.println("Entro al metodo registro");
        try{
            ls.crearLibro(isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
            System.out.println("Se guardo el BOOK");
            m.put("exito", "Registro Exitoso");
            return "GuardarLibro";
        } catch (Exception e){
            m.put("fail", "Registro Fallido(Falta de uno o mas datos)");
            return "GuardarLibro";
        }
    }
    
    @GetMapping("/modlibro/{id}")
    public String modificar(ModelMap m, @PathVariable String id){
        m.put("libro", ls.getOnebyId(id));
        return "ModLibro";
    }
    
    @PostMapping("/modlibro/{id}")
    public String modlibro(ModelMap m, @PathVariable String id,@RequestParam String titulo, @RequestParam Integer isbn, @RequestParam Integer anio,@RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam String autor, @RequestParam String editorial) throws Exception{
        System.out.println("Entro al metodo modificar");
        try{
            ls.ModLibro(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, autor, editorial);
            return "redirect:/libro/lista";
        } catch (Exception e){
            m.put("fail", "Modificacion Fallida(Error)");
            return "ModLibro";
        }
    }
    
    @GetMapping("/eliminarlibro/{id}")
    public String delete(@PathVariable String id){
        try{
            ls.EliminarLibro(id);
            return "redirect:/libro/lista";
        } catch (Exception e){
            return "ListaLibro";
        }
    }
    
    


}
