/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ClienteDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl.ClienteDAOImpl;

/**
 *
 * @author camilo
 */
public class ClienteBO {
     // Creamos la clase.
    private ClienteDAO clienteDAO; 
    
    public ClienteBO(){
        this.clienteDAO = new ClienteDAOImpl(); // Constructor
    }
    
//    public Integer insertar(Integer idEmpresa, Date fechaRegistro, Date fechaUltimaCompra){
//        Cliente cliente = new Cliente(idEmpresa, fechaRegistro, fechaUltimaCompra);
//        return this.clienteDAO.insertar(cliente);
//    }
    
    public Integer insertar(String nombre, String direccion, String telefono, String email, String tipoIndustria, Date fechaRegistro, Date fechaUltimaCompra){
        Cliente cliente = new Cliente(nombre,direccion,telefono,email,tipoIndustria,fechaRegistro,fechaUltimaCompra);
        return this.clienteDAO.insertar(cliente);
    }
    
    public Integer modificar(Integer idEmpresa,String nombre, String direccion, String telefono, String email, String tipoIndustria, Date fechaRegistro, Date fechaUltimaCompra){
        Cliente cliente = new Cliente(idEmpresa,nombre,direccion,telefono,email,tipoIndustria,fechaRegistro,fechaUltimaCompra);
        return clienteDAO.modificar(cliente);
    }
    
//    public Integer eliminar(Cliente cliente){
//        return this.clienteDAO.eliminar(cliente);
//    }
    
    public Integer eliminar(Integer idCliente){
            Cliente cliente = new Cliente();
            cliente.setIdCliente(idCliente);
            return this.clienteDAO.eliminar(cliente);
    }
    
    public Cliente obtenerPorId(Integer idCliente){
        return this.clienteDAO.obtenerPorId(idCliente); // Pendiente
    }
    
    public ArrayList<Cliente> listarTodos(){
        return this.clienteDAO.listarTodos();
    }
    
    
}
