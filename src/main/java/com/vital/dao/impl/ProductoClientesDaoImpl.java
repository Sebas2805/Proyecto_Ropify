package com.vital.dao.impl;

import com.vital.dao.ProductoClientesDao;
import com.vital.domain.ProductoDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoClientesDaoImpl implements ProductoClientesDao {

    private final SimpleJdbcCall jdbcCall;
    private final RowMapper<ProductoDTO> rowMapper = (rs, rowNum) -> {
        ProductoDTO p = new ProductoDTO();
        p.setId_producto(rs.getInt("id_producto"));
        p.setNombre(rs.getString("nombre"));
        p.setDescripcion(rs.getString("descripcion"));
        p.setPrecio(rs.getDouble("precio"));
        p.setUrl_imagen(rs.getString("url_imagen"));
        p.setStock_actual(rs.getInt("stock_actual"));
        p.setCategoria(rs.getString("categoria"));
        p.setTipo(rs.getString("tipo_prenda"));
        return p;
    };

    @Autowired
    public ProductoClientesDaoImpl(DataSource dataSource) {
        // Declaramos correctamente los parámetros del procedimiento
        this.jdbcCall = new SimpleJdbcCall(dataSource)
                .withCatalogName("pkg_productos")
                .withProcedureName("obtener_productos")
                .declareParameters(
                        new SqlParameter("p_categoria", Types.VARCHAR),
                        new SqlParameter("p_tipo_prenda", Types.VARCHAR),
                        new SqlOutParameter("p_cursor", Types.REF_CURSOR, rowMapper)
                )
                .withoutProcedureColumnMetaDataAccess();
    }

    @Override
    public List<ProductoDTO> obtenerProductosConStock() {
        // Pasamos null para traer todos los productos
        Map<String, Object> params = new HashMap<>();
        params.put("p_categoria", null);
        params.put("p_tipo_prenda", null);

        Map<String, Object> result = jdbcCall.execute(params);
        return (List<ProductoDTO>) result.get("p_cursor");
    }

    @Override
    public List<ProductoDTO> obtenerProductosConStock(String categoria, String tipo) {
        // Pasamos los filtros al procedimiento
        Map<String, Object> params = new HashMap<>();
        params.put("p_categoria", categoria);
        params.put("p_tipo_prenda", tipo);

        Map<String, Object> result = jdbcCall.execute(params);
        return (List<ProductoDTO>) result.get("p_cursor");
    }
    
    /*--------------------------Codigo Para los filtros------------------------*/
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<ProductoDTO> obtenerProductos(String categoria, String tipoPrenda) {
        return jdbcTemplate.execute((Connection con) -> {
            CallableStatement cs = con.prepareCall("{ call pkg_productos.obtener_productos(?, ?, ?) }");

            // Parámetro 1: categoría
            if (categoria != null && !categoria.isEmpty()) {
                cs.setString(1, categoria);
            } else {
                cs.setNull(1, java.sql.Types.VARCHAR);
            }

            // Parámetro 2: tipo prenda
            if (tipoPrenda != null && !tipoPrenda.isEmpty()) {
                cs.setString(2, tipoPrenda);
            } else {
                cs.setNull(2, java.sql.Types.VARCHAR);
            }

            // Parámetro 3: cursor de salida
            cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();

            ResultSet rs = (ResultSet) cs.getObject(3);
            List<ProductoDTO> productos = new ArrayList<>();
            while (rs.next()) {
                ProductoDTO dto = new ProductoDTO();
                dto.setId_producto(rs.getInt("id_producto"));
                dto.setNombre(rs.getString("nombre"));
                dto.setDescripcion(rs.getString("descripcion"));
                dto.setPrecio(rs.getDouble("precio"));
                dto.setUrl_imagen(rs.getString("url_imagen"));
                dto.setStock_actual(rs.getInt("stock_actual"));
                dto.setCategoria(rs.getString("categoria"));
                dto.setTipo(rs.getString("tipo_prenda"));
                productos.add(dto);
            }
            return productos;
        });
    }
    
    
}