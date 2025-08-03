package com.vital.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public Usuario(String nombre, String correo, String contrasena, String direccion, String telefono, String role) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.telefono = telefono;
        this.role = role;
    }
    
    
}