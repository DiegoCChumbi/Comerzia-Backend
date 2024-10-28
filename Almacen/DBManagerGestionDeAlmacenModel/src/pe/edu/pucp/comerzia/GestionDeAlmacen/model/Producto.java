package pe.edu.pucp.comerzia.GestionDeAlmacen.model;

public class Producto {

    private Integer idProducto;
    private static Integer correlativo = 1;
    private String nombreProducto;
    private Double precio;
    private Integer stockMinimo;

    public Producto() {
        this.idProducto = null;
        this.nombreProducto = null;
        this.precio = null;
        this.stockMinimo = 0;
    }

    public Producto(String nombreProducto, Double precio, Integer stockMinimo) {
        this.idProducto = correlativo;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
        correlativo++;

    }

    public Producto(Integer idProducto, String nombreProducto, Double precio, Integer stockMinimo) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stockMinimo = stockMinimo;
       
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }
 
}
