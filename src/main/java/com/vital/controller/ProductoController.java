package com.vital.controller;

import com.vital.domain.Producto;
import com.vital.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProductoController 
{
   private ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/productos")
    public String mostrarProductos(Model model) {
        model.addAttribute("productos", productoService.getProductos());
        return "/productos";
    }
    
     @GetMapping("/nuevoProducto")
    public String nuevoProducto(Model model) {
     model.addAttribute("producto", new Producto()); 
        return "/nuevoProducto";
    }
        @PostMapping("/guardarProducto")
    public String guardaProducto(@ModelAttribute Producto producto) {
        productoService.agregarProducto(producto);
        return "redirect:/productos"; // Redirige a la lista de productos
    }

     @GetMapping("/modificarProducto/{idProducto}")
    public String mostrarProductoParaModificar(@PathVariable("idProducto") int id_Producto, Model model) {
        // Obtener el producto por id
        Producto producto = productoService.getProducto(id_Producto);
        model.addAttribute("producto", producto);
        return "/modificarProducto";  // Vista para modificar
    }

    @PostMapping("/actualizarProducto")
    public String actualizarProducto(Producto producto) {
        // Llamar al servicio para actualizar el producto
        productoService.actualizarProducto(producto);
        return "redirect:/productos";  // Redirigir a la lista de productos después de la actualización
    }
  
     @GetMapping("/eliminarProducto/{idProducto}")
    public String eliminarProducto(@PathVariable("idProducto") int id, RedirectAttributes redirectAttributes) {
        String mensaje = productoService.eliminarProducto(id);
        redirectAttributes.addFlashAttribute("mensaje", mensaje);
        return "redirect:/productos"; //
    }
    
}
 