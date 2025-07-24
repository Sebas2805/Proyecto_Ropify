package com.vital.controller;

import com.vital.domain.Usuario;
import com.vital.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {
    
 private UsuarioService usuarioService;
 
  @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    
   @GetMapping("/usuarios")
    public String mostrarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.getUsuarios());
        return "/usuarios";
    }
    
    @GetMapping("/nuevoUsuario")
    public String nuevoUsuario(Model model) {
     model.addAttribute("usuario", new Usuario()); 
        return "/nuevoUsuario";
    }
        @PostMapping("/guardarUsuario")
    public String guardaUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.agregarUsuario(usuario);
        
        System.out.println("Contraseña ingresada: " + usuario.getContrasena());
        
        return "redirect:/usuarios"; // Redirige a la lista de productos
    }

     @GetMapping("/modificarUsuario/{idUsuario}")
    public String mostrarUsuarioParaModificar(@PathVariable("idUsuario") int id_Usuario, Model model) {
        // Obtener el producto por id
        Usuario usuario = usuarioService.getUsuario(id_Usuario);
        model.addAttribute("usuario", usuario);
        return "/modificarUsuario";  // Vista para modificar
    }

    @PostMapping("/actualizarUsuario")
    public String actualizarUsuario(Usuario usuario) {
        // Llamar al servicio para actualizar el producto
        usuarioService.actualizarUsuario(usuario);
        return "redirect:/usuarios";  // Redirigir a la lista de productos después de la actualización
    }
  
     @GetMapping("/eliminarUsuario/{idUsuario}")
    public String eliminarUsuario(@PathVariable("idUsuario") int id, RedirectAttributes redirectAttributes) {
        String mensaje = usuarioService.eliminarUsuario(id);
        redirectAttributes.addFlashAttribute("mensaje", mensaje);
        return "redirect:/usuarios"; //
    }
}
