package com.vital.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name="productos")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Producto;
    
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private Integer stock_minimo;
    private String url_imagen;
    private Integer id_categoria;
    private Integer id_tipo_prenda;

    public Producto() {
    }

    public Producto(String nombre, String descripcion, BigDecimal precio, Integer stock_minimo, String url_imagen, Integer id_categoria, Integer id_tipo_prenda) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock_minimo = stock_minimo;
        this.url_imagen = url_imagen;
        this.id_categoria = id_categoria;
        this.id_tipo_prenda = id_tipo_prenda;
    }
    
    
}