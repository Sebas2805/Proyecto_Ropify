package com.vital.dao;

import com.vital.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface UsuarioDao extends JpaRepository<Usuario, Integer>
{
@Modifying
@Transactional
@Query(value = "CALL pkg_usuarios.eliminar_usuario(:idUsuario)", nativeQuery = true)
void eliminarUsuario(@Param("idUsuario") int idUsuario);
    
    
}
