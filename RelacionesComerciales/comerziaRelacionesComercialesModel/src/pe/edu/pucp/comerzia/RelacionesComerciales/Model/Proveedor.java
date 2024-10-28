package pe.edu.pucp.comerzia.RelacionesComerciales.Model;

import java.util.Date;

public class Proveedor extends Empresa{

    private Integer idProveedor;
    private static Integer id_correlativo = 1;
    private Date fecha_afiliacion;
    private String RUC;
    private String razonSocial;
    private Double calificacion;
    private String pais;

    public Proveedor(Integer idEmpresa, Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais) {
        //this.idEmpresa = idEmpresa;
        this.fecha_afiliacion = fecha_afiliacion;
        this.RUC = RUC;
        this.razonSocial = razonSocial;
        this.calificacion = calificacion;
        this.pais = pais;
    }
    
    public Proveedor(String nombre, String direccion, String telefono, String email, String tipoIndustria,
            Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais){
        setNombre(nombre);
        setDireccion(direccion);
        setEmail(email);
        setTipoIndustria(tipoIndustria);
        this.fecha_afiliacion=fecha_afiliacion;
        this.RUC=RUC;
        this.razonSocial=razonSocial;
        this.calificacion=calificacion;
        this.pais=pais;
    }
    
    public Proveedor(Integer idEmpresa,String nombre, String direccion, String telefono, String email, String tipoIndustria,
            Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais){
        setIdEmpresa(idEmpresa);
        setNombre(nombre);
        setDireccion(direccion);
        setEmail(email);
        setTipoIndustria(tipoIndustria);
        this.fecha_afiliacion=fecha_afiliacion;
        this.RUC=RUC;
        this.razonSocial=razonSocial;
        this.calificacion=calificacion;
        this.pais=pais;
    }

    public Proveedor(Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais) {
        //this.idEmpresa = id_correlativo;
        this.fecha_afiliacion = fecha_afiliacion;
        this.RUC = RUC;
        this.razonSocial = razonSocial;
        this.calificacion = calificacion;
        this.pais = pais;
        id_correlativo++;
    }

    // null
    public Proveedor() {
        //this.idEmpresa = null;
        this.fecha_afiliacion = null;
        this.RUC = null;
        this.razonSocial = null;
        this.calificacion = null;
        this.pais = null;
    }

    // Getters y Setters
//    public Integer getIdEmpresa() {
//        return idEmpresa;
//    }
//
//    public void setIdEmpresa(Integer idEmpresa) {
//        this.idEmpresa = idEmpresa;
//    }

    public Date getFecha_afiliacion() {
        return fecha_afiliacion;
    }

    public void setFecha_afiliacion(Date fecha_afiliacion) {
        this.fecha_afiliacion = fecha_afiliacion;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC(String RUC) {
        this.RUC = RUC;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Integer getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Integer idProveedor) {
        this.idProveedor = idProveedor;
    }
}
