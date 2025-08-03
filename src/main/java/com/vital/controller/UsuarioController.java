package com.vital.controller;

import com.vital.domain.Usuario;
import com.vital.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    /// Codigo nueno para Inicio de sesion y Registro

@GetMapping("/registrar")
    public String nuevoRegistro(Usuario usuario) {
        return "/usuario/registro";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario) {
        usuarioService.registrarUsuario(usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContrasena(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getRole()
        );
        return "redirect:/admin"; // redirige a la lista    
    }

    @GetMapping("/nuevoInicioSesion")
    public String nuevoInicioSesion(Usuario usuario) {
        return "/usuario/InicioSesion";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
            @RequestParam String contrasena,
            HttpSession session,
            Model model) {
        Integer idUsuario = usuarioService.IniciarSesion(correo, contrasena);

        if (idUsuario != null) {
            session.setAttribute("usuarioId", idUsuario);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "/usuario/InicioSesion";
        }
    }

    /// Fin nuevo codigo
    ///

    
    
    
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
