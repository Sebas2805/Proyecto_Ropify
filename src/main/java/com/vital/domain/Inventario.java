package com.vital.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Inventario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInventario;
    
    private Integer idProducto;
    private String nombre;
    private int stockActual;
    private int stockMinimo;
    private String estado; // OK o ALERTA

    public Inventario() {
    }

    public Inventario(Integer idInventario, Integer idProducto, String nombre, int stockActual, int stockMinimo, String estado) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
    }

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Inventario{" + "idInventario=" + idInventario + ", idProducto=" + idProducto + ", nombre=" + nombre + ", stockActual=" + stockActual + ", stockMinimo=" + stockMinimo + ", estado=" + estado + '}';
    }
    
    

}