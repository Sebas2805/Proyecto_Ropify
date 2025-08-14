package com.vital.controller;

import com.vital.domain.ProductoDTO;
import com.vital.service.ProductoClientesService;
import com.vital.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductoClientesController {
    
    @Autowired
    private ProductoClientesService productoClientesService;
       
    
    @GetMapping("/productosClientes")
   public String obtenerProductosConStock(Model model, 
           @RequestParam(value = "categoria", required = false) String categoria,
           @RequestParam(value = "tipo", required = false) String tipo) {
   List<ProductoDTO> productos;
   
   // Si se recibe un filtro, se usan los parámetros para filtrar
        if ((categoria != null && !categoria.isEmpty()) || 
            (tipo != null && !tipo.isEmpty())) {
            productos = productoClientesService.obtenerProductosFiltrados(categoria, tipo);
        } else {
            // Sino, se traen todos los productos
            productos = productoClientesService.obtenerProductosConStock();
        }

        model.addAttribute("productos", productos);

        return "productosClientes"; // nombre de tu template Thymeleaf
    }

   
}
