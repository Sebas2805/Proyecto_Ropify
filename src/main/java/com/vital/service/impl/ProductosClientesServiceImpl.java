package com.vital.service.impl;

import com.vital.dao.ProductoClientesDao;
import com.vital.domain.ProductoDTO;
import com.vital.service.ProductoClientesService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

@Service
public class ProductosClientesServiceImpl implements ProductoClientesService
{
     @Autowired
    private ProductoClientesDao productoClientesDao;

    @Override
    public List<ProductoDTO> obtenerProductosConStock() {
        return productoClientesDao.obtenerProductosConStock();
    }
    
     @Override
    public List<ProductoDTO> obtenerProductosFiltrados( String categoria, String tipo ) {
        return productoClientesDao.obtenerProductosConStock(categoria, tipo );
        
    }
    
  @Override
    public List<ProductoDTO> obtenerProductos(String categoria, String tipoPrenda) {
        return productoClientesDao.obtenerProductos(categoria, tipoPrenda);
    }
    
    
}
