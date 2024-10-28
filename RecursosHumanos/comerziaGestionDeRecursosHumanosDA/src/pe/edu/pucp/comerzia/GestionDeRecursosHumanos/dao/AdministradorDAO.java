/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
/**
 *
 * @author chumbi
 */
import java.util.ArrayList;

public interface AdministradorDAO {
    public Integer insertar(Administrador administrador);
    
    public Integer modificar(Administrador administrador);
    
    public Integer eliminar(Administrador administrador);
    
    public ArrayList<Administrador> listarTodos();
    
    public Administrador obtenerPorId(Integer idAdministrador);
    
    public Boolean existeAdministrador(Administrador administrador);
    
    public Boolean existeAdministrador (Administrador administrador, Boolean abreConexion);
}
