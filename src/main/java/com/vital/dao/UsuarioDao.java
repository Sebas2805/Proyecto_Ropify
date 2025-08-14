package com.vital.dao;

import com.vital.DTO.ResumenUsuarioCompraFile;
import com.vital.domain.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;


public interface UsuarioDao extends JpaRepository<Usuario, Integer>{
   
      
@Modifying
@Transactional
@Query(value = "CALL pkg_usuarios.eliminar_usuario(:idUsuario)", nativeQuery = true)
void eliminarUsuario(@Param("idUsuario") int idUsuario);
    
@Modifying
@Transactional
@Query(value = "CALL PKG_USUARIO.REGISTRAR_USUARIO(:p_nombre, :p_correo, :p_contrasena, :p_direccion, :p_telefono, :p_rol)", nativeQuery = true)
void registrarUsuario(
        @Param("p_nombre") String nombre,
        @Param("p_correo") String correo,
        @Param("p_contrasena") String contrasena,
        @Param("p_direccion") String direccion,
        @Param("p_telefono") String telefono,
        @Param("p_rol") String role
      );




}
