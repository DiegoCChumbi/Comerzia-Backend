/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.RelacionesComerciales.bo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.VisitaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl.VisitaDAOImpl;

import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;

public class VisitaBO {
    
    // Creamos la clase.
    private VisitaDAO visitaDAO; 
    
    public VisitaBO(){
        this.visitaDAO = new VisitaDAOImpl(); // Constructor
    }
    
    public Integer insertar(Date fecha, Double duracion, Integer idCliente, Integer idVendedor){
        Visita visita = new Visita(fecha, duracion, idCliente, idVendedor);
        return this.visitaDAO.insertar(visita);
    }
    
    public Integer modificar(Integer idVisita, Date fecha, Double duracion, Integer idCliente, Integer idVendedor){
        Visita visita = new Visita();
        visita.setIdVisita(idVisita);
        visita.setFecha(fecha);
        visita.setDuracion(duracion);
        visita.setIdCliente(idCliente);
        visita.setIdVendedor(idVendedor);
        return visitaDAO.modificar(visita);
        
    }
    
    public Integer eliminar(Integer idVisita){
        Visita visita = new Visita();
        visita.setIdVisita(idVisita);
        return this.visitaDAO.eliminar(visita);
    }
    
    public Visita obtenerPorId(Integer idVisita){
        return this.visitaDAO.obtenerPorId(idVisita); // Pendiente
    }
    
    public ArrayList<Visita> listarTodos(){
        return this.visitaDAO.listarTodos();
    }
    
    
    
}