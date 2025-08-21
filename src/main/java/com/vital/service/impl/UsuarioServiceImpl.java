package com.vital.service.impl;

import com.vital.DTO.DetalleVentaFila;
import com.vital.DTO.ResumenUsuarioCompraFile;
import com.vital.dao.UsuarioDao;
import com.vital.domain.Usuario;
import com.vital.service.UsuarioService;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Transactional
    public void agregarUsuario(Usuario usuario) {
        String sql = "{call PKG_USUARIO.REGISTRAR_USUARIO(?, ?, ?, ?, ?, ?)}";
        jdbcTemplate.update(sql,
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContrasena(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getRole()
        );
    }

    @Override
    public Usuario getUsuario(int idUsuario) {
        return usuarioDao.findById(idUsuario).orElse(null);  // Buscar producto por ID usando JpaRepository
    }

//    @Transactional
//    @Override
//    public void actualizarUsuario(Usuario usuario) {
//        String sql = "{call pkg_usuarios.actualizar_usuario(?, ?, ?, ?, ?, ?, ?)}";  // Llamada al procedimiento almacenado
//        jdbcTemplate.update(sql,
//                usuario.getId_Usuario(),
//                usuario.getNombre(),
//                usuario.getCorreo(),
//                usuario.getContrasena(),
//                usuario.getDireccion(),
//                usuario.getTelefono(),
//                usuario.getRole()
//        );
//    }
    @Override
    public String eliminarUsuario(int idUsuario) {
        try {
            usuarioDao.eliminarUsuario(idUsuario);
            return "Usuario eliminado exitosamente.";
        } catch (Exception e) {
            return "Error al eliminar el Usuario: " + e.getMessage();
        }
    }

    @Override
    public void registrarUsuario(String nombre, String correo, String contrasena, String direccion, String telefono, String role) {
        try {
            usuarioDao.registrarUsuario(nombre, correo, contrasena, direccion, telefono, role);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Usuario IniciarSesion(String correo, String contrasena) {
        return jdbcTemplate.execute(
                (CallableStatementCreator) connection -> {
                    CallableStatement cs = connection.prepareCall("{ call pkg_usuario.iniciar_sesion(?, ?, ?) }");
                    cs.setString(1, correo);
                    cs.setString(2, contrasena);
                    cs.registerOutParameter(3, oracle.jdbc.OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatementCallback<Usuario>) cs -> {
                    cs.execute();
                    try (ResultSet rs = (ResultSet) cs.getObject(3)) {
                        if (rs.next()) {
                            Usuario usuario = new Usuario();
                            usuario.setId_Usuario(rs.getInt("ID_USUARIO"));
                            usuario.setNombre(rs.getString("NOMBRE"));
                            usuario.setCorreo(rs.getString("CORREO"));
                            usuario.setContrasena(rs.getString("CONTRASENA"));
                            usuario.setDireccion(rs.getString("DIRECCION"));
                            usuario.setTelefono(rs.getString("TELEFONO"));
                            usuario.setRole(rs.getString("ROLE"));
                            return usuario;
                        }
                        return null; // credenciales invalidas
                    }
                }
        );
    }

    @Override
    public List<ResumenUsuarioCompraFile> obtenerComprasPorUsuario(Integer idUsuario) {
        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall("{ call PKG_USUARIO.Obtener_Compras_Por_Usuario(?, ?) }");
                    cs.setInt(1, idUsuario);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatementCallback<List<ResumenUsuarioCompraFile>>) cs -> {
                    cs.execute();
                    List<ResumenUsuarioCompraFile> lista = new ArrayList<>();
                    try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                        while (rs.next()) {
                            ResumenUsuarioCompraFile row = new ResumenUsuarioCompraFile();
                            row.setIdUsuario(rs.getInt("ID_USUARIO"));
                            row.setNombre(rs.getString("NOMBRE"));
                            row.setCorreo(rs.getString("CORREO"));
                            row.setIdVenta(rs.getInt("ID_VENTA"));
                            Timestamp ts = rs.getTimestamp("FECHA_VENTA");
                            row.setFechaVenta(ts != null ? ts.toLocalDateTime() : null);
                            row.setMontoTotal(rs.getBigDecimal("MONTO_TOTAL"));
                            row.setCantidadArticulos(rs.getInt("CANTIDAD_ARTICULOS"));

                            lista.add(row);
                        }
                    }
                    return lista;
                }
        );
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "{call PKG_USUARIO.ACTUALIZAR_USUARIO(?, ?, ?, ?, ?, ?)}";
        int filas = jdbcTemplate.update(
                sql,
                usuario.getId_Usuario(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContrasena(),
                usuario.getDireccion(),
                usuario.getTelefono()
        );
        return filas > 0;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DetalleVentaFila> obtenerDetalleVenta(Integer idVenta) {
        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall("{ call PKG_DETALLES_VENTAS.Obtener_Detalle_Venta(?, ?) }");
                    cs.setInt(1, idVenta);
                    cs.registerOutParameter(2, OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatementCallback<List<DetalleVentaFila>>) cs -> {
                    cs.execute();
                    List<DetalleVentaFila> lista = new ArrayList<>();
                    try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                        while (rs.next()) {
                            DetalleVentaFila row = new DetalleVentaFila();
                            row.setIdDetalle(rs.getInt("ID_DETALLE"));
                            row.setIdVenta(rs.getInt("ID_VENTA"));
                            row.setIdProducto(rs.getInt("ID_PRODUCTO"));
                            row.setProducto(rs.getString("PRODUCTO"));
                            row.setCantidad(rs.getInt("CANTIDAD"));
                            row.setPrecioUnitario(rs.getBigDecimal("PRECIO_UNITARIO"));
                            row.setSubtotal(rs.getBigDecimal("SUBTOTAL"));
                            lista.add(row);
                        }
                    }
                    return lista;
                }
        );
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario obtenerUsuarioPorIdSP(int idUsuario) {
        return jdbcTemplate.execute(
                (CallableStatementCreator) con -> {
                    CallableStatement cs = con.prepareCall("{ call PKG_USUARIO.OBTENER_USUARIO_POR_ID(?, ?) }");
                    cs.setInt(1, idUsuario);
                    cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                    return cs;
                },
                (CallableStatement cs) -> {
                    cs.execute();
                    try (ResultSet rs = (ResultSet) cs.getObject(2)) {
                        if (rs.next()) {
                            Usuario u = new Usuario();

                            u.setId_Usuario(rs.getInt("ID_USUARIO"));
                            u.setNombre(rs.getString("NOMBRE"));
                            u.setCorreo(rs.getString("CORREO"));
                            u.setContrasena(rs.getString("CONTRASENA"));
                            u.setDireccion(rs.getString("DIRECCION"));
                            u.setTelefono(rs.getString("TELEFONO"));
                            u.setRole(rs.getString("ROLE"));
                            return u;
                        }
                        return null; // no encontrado
                    }
                }
        );
    }

}
