package pe.edu.pucp.comerzia.gestioncomercial.bo;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.gestioncomercial.dao.DocumentoDAO;
import pe.edu.pucp.comerzia.gestioncomercial.daoImp.DocumentoDAOImpl;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.Estado;
import pe.edu.pucp.comerzia.gestioncomercial.model.Tipo;

public class DocumentoBO {
    private DocumentoDAO documentoDAO;
    
    public DocumentoBO(){
        this.documentoDAO = new DocumentoDAOImpl();
    }
    
    public Integer insertar(Integer idEmpresa, Estado estado, Tipo tipo, Integer idVendedor, Integer idAdministrador, Integer idTrabajadorDeAlmacen){
        Documento documento = new Documento(idEmpresa, estado, tipo, idVendedor, idAdministrador, idTrabajadorDeAlmacen);
        return documentoDAO.insertar(documento);
    }

    public Integer modificar(Integer idDocumento, Integer idEmpresa, Estado estado, Tipo tipo, Integer idVendedor, Integer idAdministrador, Integer idTrabajadorDeAlmacen){
        Documento lineaDocumento = new Documento(idDocumento, idEmpresa, estado, tipo, idVendedor, idAdministrador, idTrabajadorDeAlmacen);
        return documentoDAO.modificar(lineaDocumento);
    }
    
    public Integer eliminar(Documento documento){
        return documentoDAO.eliminar(documento);
    }
    
    public ArrayList<Documento> listarTodos() {
        return this.documentoDAO.listarTodos();
    }
    
    public Documento obtenerPorId(Integer idDocumento){
        return this.documentoDAO.obtenerPorId(idDocumento);
    }
}
