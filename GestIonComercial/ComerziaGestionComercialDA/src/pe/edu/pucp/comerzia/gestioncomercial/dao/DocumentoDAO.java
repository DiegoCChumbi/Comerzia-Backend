package pe.edu.pucp.comerzia.gestioncomercial.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;

public interface DocumentoDAO {
    public Integer insertar(Documento documento);
    
    public Integer insertar(Documento documento, Boolean usarTransaccion, Connection conexion);
    
    public Integer modificar(Documento documento);
    
    public Integer modificar(Documento documento, Boolean usarTransaccion, Connection conexion);
    
    public Integer eliminar(Documento documento);
    
    public Integer eliminar(Documento documento, Boolean usarTransaccion, Connection conexion);
    
    public ArrayList<Documento> listarTodos();
    
    public Documento obtenerPorId(Integer idDocumento);
    
    public Boolean existeDocumento(Documento documento);
}