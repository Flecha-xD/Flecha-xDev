
package com.model;

public class Producto {
    private String nombre;
    private String descripcion;
    private int foto;
    private int precio;

    public Libro(String nombre, String descripcion, int foto, int precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFoto() {
        return foto;
    }
    public int getPrecio() {
        return precio;
    }
}