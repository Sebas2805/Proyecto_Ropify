package com.vital.service.impl;

import com.vital.dao.UsuarioDao;
import com.vital.domain.Usuario;
import com.vital.service.UsuarioService;
import java.sql.CallableStatement;
import java.util.List;
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
        String sql = "{call pkg_usuarios.crear_usuario(?, ?, ?, ?, ?, ?)}";
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

    @Transactional
    @Override
    public void actualizarUsuario(Usuario usuario) {
        String sql = "{call pkg_usuarios.actualizar_usuario(?, ?, ?, ?, ?, ?, ?)}";  // Llamada al procedimiento almacenado
        jdbcTemplate.update(sql,
                usuario.getId_Usuario(),
                usuario.getNombre(),
                usuario.getCorreo(),
                usuario.getContrasena(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getRole()
        );
    }

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
    public Integer IniciarSesion(String correo, String contrasena) { // Return: null = error al procesar; 0 = credenciales incorrectas; otro numero = id usuario (exito);
        return jdbcTemplate.execute(
                (CallableStatementCreator) connection -> {
                    CallableStatement cs = connection.prepareCall("{ ? = call pkg_usuario.iniciar_sesion(?, ?) }");
                    cs.registerOutParameter(1, java.sql.Types.INTEGER);
                    cs.setString(2, correo);
                    cs.setString(3, contrasena);
                    return cs;
                },
                (CallableStatementCallback<Integer>) cs -> {
                    cs.execute();
                    int result = cs.getInt(1);
                    return cs.wasNull() ? null : result;
                }
        );
    }

}
