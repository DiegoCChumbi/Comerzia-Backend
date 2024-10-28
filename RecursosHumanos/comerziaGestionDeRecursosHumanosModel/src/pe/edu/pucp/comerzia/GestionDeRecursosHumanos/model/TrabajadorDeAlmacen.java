/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

/**
 *
 * @author chumbi
 */
public class TrabajadorDeAlmacen extends Persona{

    private Integer idTrabajadorDeAlmacen;
    private static Integer idCorrelativo = 1;

    private Integer idEmpleado;
    // private Empleado empleado;
    private Integer idAlmacen;
    // private Almacen almacenDeTrabajo;
    private boolean licenciaMontacarga;

    public TrabajadorDeAlmacen(Integer idTrabajadorDeAlmacen, Integer idEmpleado, boolean licenciaMontacarga, Integer idAlmacen) {
        this.idTrabajadorDeAlmacen = idTrabajadorDeAlmacen;

        this.idEmpleado = idEmpleado;
        this.idAlmacen = idAlmacen;

        this.licenciaMontacarga = licenciaMontacarga;
    }

    public TrabajadorDeAlmacen(Integer idEmpleado, boolean licenciaMontacarga, Integer idAlmacen) {
        this.idTrabajadorDeAlmacen = idCorrelativo++;

        this.idEmpleado = idEmpleado;
        this.idAlmacen = idAlmacen;

        this.licenciaMontacarga = licenciaMontacarga;
    }

    // null
    public TrabajadorDeAlmacen() {
        this.idTrabajadorDeAlmacen = null;
        this.idEmpleado = null;
        this.idAlmacen = null;

        // this.licenciaMontacarga = false;
    }

    public Integer getIdTrabajadorDeAlmacen() {
        return idTrabajadorDeAlmacen;
    }

    public void setIdTrabajadorDeAlmacen(Integer idTrabajadorDeAlmacen) {
        this.idTrabajadorDeAlmacen = idTrabajadorDeAlmacen;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public boolean isLicenciaMontacarga() {
        return licenciaMontacarga;
    }

    public void setLicenciaMontacarga(boolean licenciaMontacarga) {
        this.licenciaMontacarga = licenciaMontacarga;
    }
}
