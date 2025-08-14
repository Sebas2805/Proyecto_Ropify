package com.vital.domain;


public class ProductoDTO {
    
    private Integer id_producto;
    private String nombre;
    private String descripcion;
    private double precio;
    private String url_imagen;
    private int stock_actual;
    private String categoria;
    private String tipo;
    
    public ProductoDTO() {
    }

    public ProductoDTO(Integer id_producto, String nombre, String descripcion, double precio, String url_imagen, int stock_actual, String categoria, String tipo) {
        this.id_producto = id_producto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.url_imagen = url_imagen;
        this.stock_actual = stock_actual;
        this.categoria = categoria;
        this.tipo = tipo;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getUrl_imagen() {
        return url_imagen;
    }

    public void setUrl_imagen(String url_imagen) {
        this.url_imagen = url_imagen;
    }

    public int getStock_actual() {
        return stock_actual;
    }

    public void setStock_actual(int stock_actual) {
        this.stock_actual = stock_actual;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "id_producto=" + id_producto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", url_imagen=" + url_imagen + ", stock_actual=" + stock_actual + ", categoria=" + categoria + ", tipo=" + tipo + '}';
    }

  
  
  
 

    
    
    
    
    
    
    
    
    
    
    
}
