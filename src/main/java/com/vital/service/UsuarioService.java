
package com.vital.service;

import com.vital.DTO.ResumenUsuarioCompraFile;
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
boolean actualizarUsuario(Usuario usuario);

String eliminarUsuario(int idUsuario);

void registrarUsuario(String nombre, String correo, String contrasena, String direccion, String telefono, String role);

public Usuario IniciarSesion(String correo, String contrasena);

List<ResumenUsuarioCompraFile> obtenerComprasPorUsuario(Integer idUsuario);


}
