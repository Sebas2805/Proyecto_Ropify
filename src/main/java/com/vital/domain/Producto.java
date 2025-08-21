package com.vital.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;



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

    public int getId_Producto() {
        return id_Producto;
    }

    public void setId_Producto(int id_Producto) {
        this.id_Producto = id_Producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock_minimo() {
        return stock_minimo;
    }

    public void setStock_minimo(Integer stock_minimo) {
        this.stock_minimo = stock_minimo;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public Integer getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    public Integer getId_tipo_prenda() {
        return id_tipo_prenda;
    }

    public void setId_tipo_prenda(Integer id_tipo_prenda) {
        this.id_tipo_prenda = id_tipo_prenda;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producto{");
        sb.append("id_Producto=").append(id_Producto);
        sb.append(", nombre=").append(nombre);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", precio=").append(precio);
        sb.append(", stock_minimo=").append(stock_minimo);
        sb.append(", url_imagen=").append(url_imagen);
        sb.append(", id_categoria=").append(id_categoria);
        sb.append(", id_tipo_prenda=").append(id_tipo_prenda);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}