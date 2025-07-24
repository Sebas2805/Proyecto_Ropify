package com.vital.controller;

import com.vital.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoClientesController {
    
    private ProductoService productoService;
       
     @Autowired
    public ProductoClientesController(ProductoService productoService) {
        this.productoService = productoService;
    }
    
    
    @GetMapping("/productosClientes")
    public String mostrarProductosClientes(Model model) {
        model.addAttribute("productos", productoService.getProductos());
        return "/productosClientes";
    }
}
