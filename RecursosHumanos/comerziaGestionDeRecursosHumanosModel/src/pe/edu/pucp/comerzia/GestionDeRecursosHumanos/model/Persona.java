package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

public class Persona {

    private Integer idPersona;
    private static Integer idCorrelativo = 1;

    private String dni;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String direccion;

    public Persona(Integer idPersona, String dni, String nombreCompleto, String telefono, String correo, String direccion) {
        this.idPersona = idPersona;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Persona(String dni, String nombreCompleto, String telefono, String correo, String direccion) {
        this.idPersona = idCorrelativo++;

        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
    }

    public Persona() {
        this.idPersona = null;
        this.dni = null;
        this.nombreCompleto = null;
        this.telefono = null;
        this.correo = null;
        this.direccion = null;
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
