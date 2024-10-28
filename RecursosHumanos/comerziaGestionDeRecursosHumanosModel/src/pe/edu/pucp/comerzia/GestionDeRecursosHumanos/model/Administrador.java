/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model;

import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
/**
 * s
 *
 * @author chumbi
 */
public class Administrador extends Persona{

    private Integer idAdministrador;
    private static int idCorrelativo = 1;

    private Integer idAlmacen;
    
    public Administrador(Integer idPersona, String dni, String nombreCompleto,String telefono, String correo, String direccion,Integer idAlmacen){
        super(idPersona,dni,nombreCompleto,telefono,correo,direccion);
        this.idAdministrador = idCorrelativo++;
        this.idAlmacen=idAlmacen;
    }

    public Administrador(Integer idAdministrador, Integer idAlmacen) {
        super();
        this.idAdministrador = idAdministrador;
        this.idAlmacen = idAlmacen;
    }

    public Administrador(Integer idAlmacen) {
        super();
        this.idAdministrador = idCorrelativo++;
        this.idAlmacen = idAlmacen;
    }

    // null
    public Administrador() {
        super();
        this.idAdministrador = null;
        this.idAlmacen = null;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public Integer getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(Integer idAlmacen) {
        this.idAlmacen = idAlmacen;
    }
}
