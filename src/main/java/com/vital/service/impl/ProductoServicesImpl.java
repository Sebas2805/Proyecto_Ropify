package com.vital.service.impl;

import com.vital.dao.ProductoDao;
import com.vital.domain.Producto;
import com.vital.domain.ProductoDTO;
import com.vital.service.ProductoService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServicesImpl implements ProductoService
{
    

    @Autowired
    private ProductoDao productoDao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<Producto> getProductos() {
          return productoDao.findAll();
    } 
    
     @Transactional
    public void agregarProducto(Producto producto) {
        String sql = "{call pkg_productos.crear_producto(?, ?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql, 
            producto.getNombre(), 
            producto.getDescripcion(),
            producto.getPrecio().doubleValue(), 
            producto.getStock_minimo(),
            producto.getUrl_imagen(),
            producto.getId_categoria(),
            producto.getId_tipo_prenda()

        );
    }
    
      @Override
    public Producto getProducto(int idProducto) {
        return productoDao.findById(idProducto).orElse(null);  // Buscar producto por ID usando JpaRepository
    }
    
    @Transactional
    @Override
    public void actualizarProducto(Producto producto) {
        String sql = "{call pkg_productos.actualizar_producto(?, ?, ?, ?, ?, ?, ?, ?)}";  // Llamada al procedimiento almacenado

        jdbcTemplate.update(sql, 
            producto.getId_Producto(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getPrecio().doubleValue(), 
            producto.getStock_minimo(),
            producto.getUrl_imagen(),
            producto.getId_categoria(),
            producto.getId_tipo_prenda()
        );
    }
   
     @Override
    public String eliminarProducto(int idProducto) {
        try {
            productoDao.eliminarProducto(idProducto);
            return "Producto eliminado exitosamente.";
        } catch (Exception e) {
            return "Error al eliminar el producto: " + e.getMessage();
        }
    }
    
    
    
    
   
    
}
