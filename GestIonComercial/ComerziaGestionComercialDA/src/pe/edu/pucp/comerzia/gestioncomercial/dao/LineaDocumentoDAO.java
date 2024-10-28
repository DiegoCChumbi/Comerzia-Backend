package pe.edu.pucp.comerzia.gestioncomercial.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;

public interface LineaDocumentoDAO {
    public Integer insertar(LineaDocumento lineaDocumento);
    
    public Integer insertar(LineaDocumento lineaDocumento, Boolean usarTransaccion, Connection conexion);
    
    public Integer modificar(LineaDocumento lineaDocumento);
    
    public Integer modificar(LineaDocumento lineaDocumento, Boolean usarTransaccion, Connection conexion);
    
    public Integer eliminar(LineaDocumento lineaDocumento);
    
    public Integer eliminar(LineaDocumento lineaDocumento, Boolean usarTransaccion, Connection conexion);
    
    public ArrayList<LineaDocumento> listarTodos();
    
    public LineaDocumento obtenerPorId(Integer idLineaDocumento);
    
    public Boolean existeLineaDocumento(LineaDocumento lineaDocumento);
}
