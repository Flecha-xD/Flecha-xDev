
package proingsoftware.model;

public class Producto {
    private String nombre;
    private String descripcion;
    private int foto;
    private String precio;

    public Producto(String nombre, String descripcion, String precio, int foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
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
    public String getPrecio() {
        return precio;
    }
}