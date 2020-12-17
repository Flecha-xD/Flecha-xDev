package proingsoftware.model;

public class Admin {
    private String id;
    private String nombre;
    private String ci;
    private String ext;
    private String phone;
    private String codigo;
    private String correo;
    private String password;
    private boolean esAdmin;

    public Admin(String id,String nombre, String ci, String ext, String phone, String codigo, String correo, String password, boolean esAdmin) {
        this.id = id;
        this.nombre = nombre;
        this.ci = ci;
        this.ext = ext;
        this.phone = phone;
        this.codigo = codigo;
        this.correo = correo;
        this.password = password;
        this.esAdmin = esAdmin;
    }

    public Admin(){
        //Necesario para firebase no borrar
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEsAdmin() {
        return esAdmin;
    }

    public void setEsAdmin(boolean esAdmin) {
        this.esAdmin = esAdmin;
    }
}
