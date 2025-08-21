package com.vital.service;

import com.vital.domain.ProductoDTO;
import java.util.List;


public interface ProductoClientesService 
{
    List<ProductoDTO> obtenerProductosConStock();
    
    List<ProductoDTO> obtenerProductosFiltrados(String categoria, String tipo);
      
    List<ProductoDTO> obtenerProductos(String categoria, String tipoPrenda);
}
