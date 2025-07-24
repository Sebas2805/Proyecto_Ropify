package com.vital.service.impl;

import com.vital.dao.UsuarioDao;
import com.vital.domain.Usuario;
import com.vital.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    
    
}
