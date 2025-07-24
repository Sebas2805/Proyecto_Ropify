package com.vital.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="usuarios")
public class Usuario implements Serializable
     
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Usuario;
    
    private String nombre;
    private String correo;
    private String contrasena;
    private String direccion;
    private String telefono;
    private String role;

    public Usuario() {
    }

    public Usuario(int id_Usuario, String nombre, String correo, String contrasena, String direccion, String telefono, String role) {
        this.id_Usuario = id_Usuario;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.telefono = telefono;
        this.role = role;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id_Usuario=" + id_Usuario + ", nombre=" + nombre + ", correo=" + correo + ", contrasena=" + contrasena + ", direccion=" + direccion + ", telefono=" + telefono + ", role=" + role + '}';
    }

   
           
    
}
