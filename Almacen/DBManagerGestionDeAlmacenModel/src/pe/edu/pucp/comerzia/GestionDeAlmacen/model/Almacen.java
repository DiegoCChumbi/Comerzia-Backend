package pe.edu.pucp.comerzia.GestionDeAlmacen.model;

public class Almacen {
    private Integer idAlmacen;
    private static Integer correlativo = 1;
    private String nombre;
    private String estado;
    private String descripcion;

    public Almacen(String nombre, String estado, String descripcion) {
        this.idAlmacen = correlativo++;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public Almacen(Integer idAlmacen, String nombre, String estado, String descripcion) {
        this.idAlmacen = idAlmacen;
        this.nombre = nombre;
        this.estado = estado;
        this.descripcion = descripcion;
    }
    
    public Almacen(){
        this.idAlmacen = null;
        this.nombre = null;
        this.estado = null;
        this.descripcion = null;
    }
    
    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
 
}