package com.vital.service;

import com.vital.domain.Inventario;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface InventarioService
{
    List<Inventario> obtenerInventario();
    
}
