package com.vital.dao;

import com.vital.domain.ProductoDTO;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoClientesDao 
{
  List<ProductoDTO> obtenerProductosConStock();
    
    List<ProductoDTO> obtenerProductosConStock(String categoriaNombre, String tipoNombre );
    
    List<ProductoDTO> obtenerProductos(String categoria, String tipoPrenda);
}
