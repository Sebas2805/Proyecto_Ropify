package com.vital.service.impl;

import com.vital.dao.InventarioDao;
import com.vital.domain.Inventario;
import com.vital.service.InventarioService;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class InventarioServiceImpl implements InventarioService
{
      @Autowired
    private InventarioDao inventarioDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public List<Inventario> obtenerInventario() {
        
        /*--------Con SqlOutParameter:----------------*/
       /* 
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_INVENTARIO")      // nombre del paquete
                .withProcedureName("OBTENER_INVENTARIO")
                .declareParameters(
                        new SqlOutParameter("p_cursor", oracle.jdbc.OracleTypes.CURSOR,
                                (ResultSet rs, int rowNum) -> {
                                    Inventario dto = new Inventario();
                                    dto.setIdInventario(rs.getInt("id_inventario"));
                                    dto.setIdProducto(rs.getInt("id_producto"));
                                    dto.setNombre(rs.getString("nombre"));
                                    dto.setStockActual(rs.getInt("stock_actual"));
                                    dto.setStockMinimo(rs.getInt("stock_minimo"));
                                    dto.setEstado(rs.getString("estado")); // "Bajo Stock" o "OK"
                                    return dto;
                                })
                );

        */
        
        
        /*--------Con returningResultSet----------------*/
        
                SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_INVENTARIO")
                .withProcedureName("OBTENER_INVENTARIO")
                .returningResultSet("p_cursor", (ResultSet rs, int rowNum) -> {
                    Inventario dto = new Inventario();
                    dto.setIdInventario(rs.getInt("id_inventario"));
                    dto.setIdProducto(rs.getInt("id_producto"));
                    dto.setNombre(rs.getString("nombre"));
                    dto.setStockActual(rs.getInt("stock_actual"));
                    dto.setStockMinimo(rs.getInt("stock_minimo"));
                    dto.setEstado(rs.getString("estado"));
                    return dto;
                });
        
        
        
        
        
        
        
        
        
        
        
        
        Map<String, Object> result = jdbcCall.execute();
        return (List<Inventario>) result.get("p_cursor");
    }
    
    
}
