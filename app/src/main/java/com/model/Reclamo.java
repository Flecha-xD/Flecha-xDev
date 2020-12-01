
package com.model;

public class Reclamo {
    private String nombre;
    private String prodserv;
    private String descripcion;
    private int foto;

    public Reclamo(String nombre, String descripcion, String prodserv , int foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.prodserv = prodserv;
        this.foto = foto;
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