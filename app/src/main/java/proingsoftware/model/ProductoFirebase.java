package proingsoftware.model;

import java.net.URI;

public class ProductoFirebase {
    //private String id;
    private String nombre;
    private String descripcion;
    private String codigo;
    private URI foto;
    private String precio;

//    String id
    public ProductoFirebase(String nombre, String descripcion, String precio, String codigo) {
        //this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.codigo = codigo;
        //this.foto = foto;
    }
    public ProductoFirebase(){
        //Necesario para firebase no borrar
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public URI getFoto() {
        return foto;
    }

    public void setFoto(URI foto) {
        this.foto = foto;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
