package com.vital.dao;

import com.vital.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface ProductoDao extends JpaRepository<Producto, Integer>

{
@Modifying
@Transactional
@Query(value = "CALL pkg_productos.eliminar_producto(:idProducto)", nativeQuery = true)
void eliminarProducto(@Param("idProducto") int idProducto);
}

