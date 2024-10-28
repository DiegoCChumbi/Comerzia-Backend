package pe.edu.pucp.comerzia.gestioncomercial.bo;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.gestioncomercial.dao.LineaDocumentoDAO;
import pe.edu.pucp.comerzia.gestioncomercial.daoImp.LineaDocumentoDAOImpl;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;

public class LineaDocumentoBO {
    private LineaDocumentoDAO lineaDocumentoDAO;
    
    public LineaDocumentoBO(){
        this.lineaDocumentoDAO = new LineaDocumentoDAOImpl();
    }
    
    public Integer insertar(Integer idDocumento, Integer idProducto, Integer cantidad, Double precioUnitario){
        LineaDocumento lineaDocumento = new LineaDocumento(idDocumento, idProducto, cantidad, precioUnitario);
        return lineaDocumentoDAO.insertar(lineaDocumento);
    }
    
    public Integer modificar(Integer idLinea, Integer idDocumento, Integer idProducto, Integer cantidad, Double precioUnitario){
        LineaDocumento lineaDocumento = new LineaDocumento(idLinea, idDocumento, idProducto, cantidad, precioUnitario);
        return lineaDocumentoDAO.modificar(lineaDocumento);
    }
    
    public Integer eliminar(LineaDocumento lineaDocumento){
        return lineaDocumentoDAO.eliminar(lineaDocumento);
    }
    
    public ArrayList<LineaDocumento> listarTodos() {
        return this.lineaDocumentoDAO.listarTodos();
    }
    
    public LineaDocumento obtenerPorId(Integer idLineaDocumento){
        return this.lineaDocumentoDAO.obtenerPorId(idLineaDocumento);
    }
}