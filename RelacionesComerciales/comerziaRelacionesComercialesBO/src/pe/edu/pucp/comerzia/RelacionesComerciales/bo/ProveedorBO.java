/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ProveedorDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl.ProveedorDAOImpl;

/**
 *
 * @author camilo
 */
public class ProveedorBO {
    
    // Creamos la clase.
    private ProveedorDAO proveedorDAO; 
    
    public ProveedorBO(){
        this.proveedorDAO = new ProveedorDAOImpl(); // Constructor
    }
    
//    public Integer insertar(Integer idEmpresa, Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais){
//        Proveedor proveedor;
//        proveedor = new Proveedor(idEmpresa, fecha_afiliacion, RUC, razonSocial, calificacion, pais);
//        return proveedorDAO.insertar(proveedor);
//    }
    
    public Integer insertar(String nombre, String direccion, String telefono, String email, String tipoIndustria,
            Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais){
        Proveedor proveedor = new Proveedor(nombre,direccion,telefono,email,tipoIndustria,fecha_afiliacion,RUC,razonSocial,calificacion,pais);
        return this.proveedorDAO.insertar(proveedor);
    }
    
    public Integer modificar(Integer idEmpresa,String nombre, String direccion, String telefono, String email, String tipoIndustria,
            Date fecha_afiliacion, String RUC, String razonSocial, Double calificacion, String pais){
        Proveedor proveedor = new Proveedor(idEmpresa,nombre,direccion,telefono,email,tipoIndustria,fecha_afiliacion,RUC,razonSocial,calificacion,pais);
        return this.proveedorDAO.modificar(proveedor);
        
    }
    
    public Integer eliminar(Integer idProveedor){
        Proveedor proveedor = new Proveedor();
        proveedor.setIdProveedor(idProveedor);
        return this.proveedorDAO.eliminar(proveedor);
    }
    
    public Proveedor obtenerPorId(Integer idProveedor){
        return this.proveedorDAO.obtenerPorId(idProveedor); // Pendiente
    }
    
    public ArrayList<Proveedor> listarTodos(){
        return this.proveedorDAO.listarTodos();
    }
}
