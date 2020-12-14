package proingsoftware.model;

import java.net.URI;

public class ProductoFirebase {
    private String id;
    private String nombre;
    private String descripcion;
    private String codigo;
    private URI foto;
    private String precio;

    public ProductoFirebase(String id,String nombre, String descripcion, String precio, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.codigo = codigo;
        //this.foto = foto;
    }
    public ProductoFirebase(){
        //Necesario para firebase no borrar
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public URI getFoto() {
        return foto;
    }
    public String getPrecio() {
        return precio;
    }
}
