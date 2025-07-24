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

@Controller
public class AdminController 
{
 

    @Autowired
    public AdminController() {
       
    }

    @GetMapping("/admin")
    public String mostrarAdmin() {
        return "/admin";
    }
    
  
}
 