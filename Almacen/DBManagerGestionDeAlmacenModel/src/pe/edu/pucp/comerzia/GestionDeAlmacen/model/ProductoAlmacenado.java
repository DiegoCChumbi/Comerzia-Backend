package pe.edu.pucp.comerzia.GestionDeAlmacen.model;

import java.util.Date;

public class ProductoAlmacenado  {
    private Integer idProductoAlmacenado;
    private static Integer correlativo = 1;
    private Integer idAlmacen;     
    private Date fechaAlmacenado;
    private Integer stockActual;
    private Integer idProducto;

    public ProductoAlmacenado() {
        this.idProductoAlmacenado = null;
        this.idAlmacen = null;
        this.fechaAlmacenado = null;
        this.stockActual = 0;
        this.idProducto = null;
    }

    public ProductoAlmacenado(Integer idAlmacen, Date fechaAlmacenado, Integer stockActual, Integer idProducto) {
        this.idProductoAlmacenado = correlativo++;
        this.idAlmacen = idAlmacen;
        this.fechaAlmacenado = fechaAlmacenado;
        this.stockActual = stockActual;
        this.idProducto = idProducto;
    }

    public ProductoAlmacenado(Integer idProductoAlmacenado, Integer idAlmacen, Date fechaAlmacenado, Integer stockActual, Integer idProducto) {
        this.idProductoAlmacenado = idProductoAlmacenado;
        this.idAlmacen = idAlmacen;
        this.fechaAlmacenado = fechaAlmacenado;
        this.stockActual = stockActual;
        this.idProducto = idProducto;
    }

    public Integer getIdProductoAlmacenado() {
        return idProductoAlmacenado;
    }

    public void setIdProductoAlmacenado(Integer idProductoAlmacenado) {
        this.idProductoAlmacenado = idProductoAlmacenado;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public Date getFechaAlmacenado() {
        return fechaAlmacenado;
    }

    public void setFechaAlmacenado(Date fechaAlmacenado) {
        this.fechaAlmacenado = fechaAlmacenado;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

}
