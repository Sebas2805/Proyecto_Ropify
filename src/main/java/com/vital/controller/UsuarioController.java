package com.vital.controller;

import com.vital.DTO.DetalleVentaFila;
import com.vital.DTO.ResumenUsuarioCompraFile;
import com.vital.domain.Usuario;
import com.vital.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;
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
        Usuario usuario = usuarioService.IniciarSesion(correo, contrasena);

        if (usuario != null) {
            session.setAttribute("usuario", usuario);
            return "redirect:/";
        } else {
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "/usuario/InicioSesion";
        }
    }
    
    @GetMapping("/menu")
    public String mostrarMenuUsuarios(HttpSession session,Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/usuario/menu";
    }
    
    @GetMapping("/compras")
    public String mostrarCompras(HttpSession session,Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null) {
            return "redirect:/usuario/login";
        }

        List<ResumenUsuarioCompraFile> ventas = usuarioService.obtenerComprasPorUsuario(usuario.getId_Usuario());
        model.addAttribute("ventas", ventas);
        model.addAttribute("usuarioNombre", usuario.getNombre());
        return "/usuario/compras";
    }
    
    @GetMapping("/listadoId")
    public String mostrarListadoId(HttpSession session,Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        model.addAttribute("usuario", usuario);
        return "/usuario/listado_usuario_id";
    }
    
    @GetMapping("/actualizar/{idUsuario}")
    public String mostrarFormularioActualizar(@PathVariable("idUsuario") int idUsuario, Model model) {
        Usuario usuario = usuarioService.getUsuario(idUsuario); // Lo obtienes de DB
        if (usuario == null) {

            return "redirect:/usuarios";
        }
        model.addAttribute("usuario", usuario); 
        return "/usuario/actualizar"; 
    }
    
    @PostMapping("/actualizar/aplicar")
    public String actualizar(@ModelAttribute("usuario") Usuario usuario, RedirectAttributes ra) {
        boolean exito = usuarioService.actualizarUsuario(usuario);
        if (exito) {
            ra.addFlashAttribute("msg", "Usuario actualizado correctamente!");
        } else {
            ra.addFlashAttribute("error", "No se encontró el usuario a actualizar");
        }
        return "redirect:/usuario/usuarios";
    }
    
    @GetMapping("/ventas/{idVenta}")
    public String verCompraDetalle(@PathVariable Integer idVenta,
                                   HttpSession session,
                                   Model model) {
        // (Opcional) validar que haya sesión iniciada:
        // Usuario u = (Usuario) session.getAttribute("usuario");
        // if (u == null) return "redirect:/usuario/nuevoInicioSesion";

        List<DetalleVentaFila> detalle = usuarioService.obtenerDetalleVenta(idVenta);

        int totalItems = detalle.stream().mapToInt(DetalleVentaFila::getCantidad).sum();
        BigDecimal totalMonto = detalle.stream()
                .map(DetalleVentaFila::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("idVenta", idVenta);
        model.addAttribute("detalle", detalle);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalMonto", totalMonto);

        return "/usuario/venta_detalle";
    }
    
    
    // Formulario de busqueda Usuario por ID
    
    // Muestra el formulario vacío
    @GetMapping("/buscar")
    public String buscarUsuarioForm(Model model) {
        model.addAttribute("usuarioEncontrado", null);
        return "/usuario/buscar"; // templates/usuario/buscar.html
    }

    // Procesa la búsqueda por ID (desde el form)
    @PostMapping("/buscar")
    public String buscarUsuarioSubmit(@RequestParam("idUsuario") int idUsuario, Model model) {
        Usuario usuario = usuarioService.obtenerUsuarioPorIdSP(idUsuario);
        model.addAttribute("usuarioEncontrado", usuario);
        model.addAttribute("idBuscado", idUsuario);
        return "/usuario/buscar";
    }
    
    ///
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
