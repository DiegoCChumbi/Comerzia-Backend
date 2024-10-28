/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl.EmpresaDAOImpl;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;

/**
 *
 * @author camilo
 */
public class EmpresaBO {
    
    // Creamos la clase.
    private EmpresaDAO empresaDAO; 
    
    public EmpresaBO(){
        this.empresaDAO = new EmpresaDAOImpl(); // Constructor
    }
    
    public Integer insertar(String nombre, String direccion, String telefono, String email, String tipoIndustria){
        Empresa empresa;
        empresa = new Empresa(nombre, direccion, telefono, email, tipoIndustria);
        return empresaDAO.insertar(empresa);
        
        
    }
    
    public Integer modificar(Integer idEmpresa,String nombre, String direccion, String telefono, String email, String tipoIndustria){
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(idEmpresa);
        empresa.setNombre(nombre);
        empresa.setDireccion(direccion);
        empresa.setTelefono(telefono);
        empresa.setEmail(email);
        empresa.setTipoIndustria(tipoIndustria);
        return this.empresaDAO.modificar(empresa);
        
    }
    
    public Integer eliminar(Integer idEmpresa){
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(idEmpresa);
        return this.empresaDAO.eliminar(empresa);
    }
    
    public Empresa obtenerPorId(Integer idEmpresa){
        return this.empresaDAO.obtenerPorId(idEmpresa); // Pendiente
    }
    
    public ArrayList<Empresa> listarTodos(){
        return this.empresaDAO.listarTodos();
    }
    
}