
package com.model;

public class Reclamo {
    private String nombre;
    private String prodserv;
    private String descripcion;
    private int foto;

    public Libro(String nombre, String descripcion, int foto, String prodserv) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.prodserv = prodserv;
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
    public String getProdServ() {
        return prodserv;
    }
}