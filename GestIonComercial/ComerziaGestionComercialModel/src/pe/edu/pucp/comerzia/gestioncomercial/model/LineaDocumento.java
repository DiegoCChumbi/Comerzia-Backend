package pe.edu.pucp.comerzia.gestioncomercial.model;

public class LineaDocumento {
    private Integer idLinea;
    private static Integer idLineaDocumentoCorrelativo = 0;
    private Integer idDocumento;
    private Integer idProducto;
    private Integer cantidad;
    private Double precioUnitario;

    public LineaDocumento(Integer idDocumento, Integer idProducto, Integer cantidad, Double precioUnitario) {
        this.idLinea = idLineaDocumentoCorrelativo++;
        this.idDocumento = idDocumento;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public LineaDocumento(Integer idLinea, Integer idDocumento, Integer idProducto, Integer cantidad, Double precioUnitario) {
        this.idLinea = idLinea;
        this.idDocumento = idDocumento;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public LineaDocumento() {
        this.idLinea = null;
        this.idDocumento = null;
        this.idProducto = null;
        this.cantidad = null;
        this.precioUnitario = null;
    }

    public Integer getIdLinea() {
        return idLinea;
    }

    public void setIdLinea(Integer idLinea) {
        this.idLinea = idLinea;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    
}
