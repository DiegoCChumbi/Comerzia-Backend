package pe.edu.pucp.comerzia.gestioncomercial.model;

public class Documento{
    private Integer idDocumento;
    private static Integer idDocumentoCorrelativo = 0;
    private Integer idEmpresa;
    private Estado estado;
    private Tipo tipo;
    private Integer idVendedor;
    private Integer idAdministrador;
    private Integer idTrabajadorDeAlmacen;

    public Documento(Integer idEmpresa, Estado estado, Tipo tipo, Integer idVendedor, Integer idAdministrador, Integer idTrabajadorDeAlmacen) {
        this.idDocumento = idDocumentoCorrelativo++;
        this.idEmpresa = idEmpresa;
        this.estado = estado;
        this.tipo = tipo;
        this.idVendedor = idVendedor;
        this.idAdministrador = idAdministrador;
        this.idTrabajadorDeAlmacen = idTrabajadorDeAlmacen;
    }
   

    public Documento(Integer idDocumento, Integer idEmpresa, Estado estado, Tipo tipo, Integer idVendedor, Integer idAdministrador, Integer idTrabajadorDeAlmacen) {
        this.idDocumento = idDocumento;
        this.idEmpresa = idEmpresa;
        this.estado = estado;
        this.tipo = tipo;
        this.idVendedor = idVendedor;
        this.idAdministrador = idAdministrador;
        this.idTrabajadorDeAlmacen = idTrabajadorDeAlmacen;
    }

    public Documento() {
        this.idDocumento = null;
        this.idEmpresa = null;
        this.estado = null;
        this.tipo = null;
        this.idVendedor = null;
        this.idAdministrador = null;
        this.idTrabajadorDeAlmacen = null;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Integer getIdTrabajadorDeAlmacen() {
        return idTrabajadorDeAlmacen;
    }

    public void setIdTrabajadorDeAlmacen(Integer idTrabajadorDeAlmacen) {
        this.idTrabajadorDeAlmacen = idTrabajadorDeAlmacen;
    }
  
}
