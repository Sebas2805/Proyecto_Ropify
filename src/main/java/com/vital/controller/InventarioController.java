package com.vital.controller;

import com.vital.domain.Inventario;
import com.vital.service.InventarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InventarioController 
{
    @Autowired
    private InventarioService inventarioService;

    @GetMapping("/inventario")
    public String verInventario(Model model) {
        List<Inventario> inventario = inventarioService.obtenerInventario();
        model.addAttribute("inventario", inventario);
        return "inventario"; // tu template en Thymeleaf
    } 
    
}
