package proingsoftware.model;

import android.webkit.URLUtil;

import java.util.UUID;

public class ReclamoFirebase {
    private String id;
    private String nombre;
    private String ci;
    private String ext;
    private String cel;
    private String correo;
    private String dept;
    private String producto;
    private String descripcion;
    private String foto;
    private boolean fueAtendido;

    public ReclamoFirebase(){
        this.nombre = nombre;
        this.ci = ci;
        this.ext = ext;
        this.cel = cel;
        this.correo = correo;
        this.dept = dept;
        this.producto = producto;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public ReclamoFirebase(String id,String nombre, String ci, String ext, String cel, String correo, String dept, String producto, String descripcion, String foto, boolean fueAtendido) {
        this.id = id;
        this.nombre = nombre;
        this.ci = ci;
        this.ext = ext;
        this.cel = cel;
        this.correo = correo;
        this.dept = dept;
        this.producto = producto;
        this.descripcion = descripcion;
        this.foto = foto;
        this.fueAtendido = fueAtendido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getCel() {
        return cel;
    }

    public void setCel(String cel) {
        this.cel = cel;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isFueAtendido() {
        return fueAtendido;
    }

    public void setFueAtendido(boolean fueAtendido) {
        this.fueAtendido = fueAtendido;
    }
}
