package com.vital.service;

import com.vital.domain.Producto;
import java.util.List;


public interface ProductoService
{
// Método para obtener todos los productos
List<Producto> getProductos();
    
// Método para agregar un producto nuevo
void agregarProducto(Producto producto);

// Método para obtener un producto por su id
Producto getProducto(int idProducto);

// Método para guardar un nuevo producto o actualizar uno existente
//void saveProducto(Producto producto);
 
// Método para actualizar un producto utilizando el procedimiento almacenado
void actualizarProducto( Producto producto);

String eliminarProducto(int idProducto);

}


