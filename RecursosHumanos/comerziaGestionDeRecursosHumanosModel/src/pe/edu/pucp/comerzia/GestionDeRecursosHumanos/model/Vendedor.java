package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

public class Vendedor extends Persona{

    private Integer idVendedor;
    private static Integer idCorrelativo = 1;

    private Integer idEmpleado;
    // private Empleado empleado;

    private Double ingresosVentas;
    private Double porcentajeComision;

    public Vendedor(Integer idVendedor, Integer idEmpleado, Double ingresosVentas, Double porcentajeComision) {
        this.idVendedor = idVendedor;
        this.idEmpleado = idEmpleado;
        this.ingresosVentas = ingresosVentas;
        this.porcentajeComision = porcentajeComision;
    }

    public Vendedor(Integer idEmpleado, Double ingresosVentas, Double porcentajeComision) {
        this.idVendedor = idCorrelativo++;
        this.idEmpleado = idEmpleado;
        this.ingresosVentas = ingresosVentas;
        this.porcentajeComision = porcentajeComision;
    }

    // null
    public Vendedor() {
        this.idVendedor = null;
        this.idEmpleado = null;

        // this.ingresosVentas = 0.0;
        // this.porcentajeComision = 0.0;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Double getIngresosVentas() {
        return ingresosVentas;
    }

    public void setIngresosVentas(Double ingresosVentas) {
        this.ingresosVentas = ingresosVentas;
    }

    public Double getPorcentajeComision() {
        return porcentajeComision;
    }

    public void setPorcentajeComision(Double porcentajeComision) {
        this.porcentajeComision = porcentajeComision;
    }
}
