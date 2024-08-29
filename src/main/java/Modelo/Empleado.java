package Modelo;

public class Empleado {
    private int id;
    private String cedula;
    private String nombres;
    private String telefono;
    private String user;

    
    public Empleado() {
    }

    
    public Empleado(int id, String cedula, String nombres, String telefono, String user) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.telefono = telefono;
        this.user = user;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //                                                             nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
