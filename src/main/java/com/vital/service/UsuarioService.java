
package com.vital.service;

import com.vital.domain.Usuario;
import java.util.List;


public interface UsuarioService {
    // Método para obtener todos los productos
List<Usuario> getUsuarios();
 

// Método para agregar un producto nuevo
void agregarUsuario(Usuario usuario);

// Método para obtener un producto por su id
Usuario getUsuario(int idUsuario);
 
// Método para actualizar un producto utilizando el procedimiento almacenado
void actualizarUsuario(Usuario usuario);

String eliminarUsuario(int idUsuario);




}
