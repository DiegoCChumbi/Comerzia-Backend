package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import java.util.ArrayList;
import java.sql.Connection;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;

public interface PersonaDAO {
    public Integer insertar(Persona persona);
    
    public Integer insertar(Persona persona, Boolean usarTransaccion, Connection conexion);
    
    public Integer modificar(Persona persona);
    
    public Integer modificar(Persona persona, Boolean usarTransaccion, Connection conexion);
    
    public Integer eliminar(Persona persona);
    
    public Integer eliminar(Persona persona, Boolean usarTransaccion, Connection conexion);
    
    public ArrayList<Persona> listarTodos();
    
    public Persona obtenerPorId(Integer idPersona);
    
    public Boolean existePersona(Persona persona);
}
