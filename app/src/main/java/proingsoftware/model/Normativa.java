package proingsoftware.model;

public class Normativa {
    private String nombre;
    private String informacion;

    public Normativa(String nombre, String informacion) {
        this.nombre = nombre;
        this.informacion = informacion;
    }

    public String getInformacion() {
        return informacion;
    }

    public String getNombre() {
        return nombre;
    }
}