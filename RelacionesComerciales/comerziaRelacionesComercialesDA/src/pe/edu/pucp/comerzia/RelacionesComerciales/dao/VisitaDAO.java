package pe.edu.pucp.comerzia.RelacionesComerciales.dao;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;


public interface VisitaDAO {  
    
    public Integer insertar(Visita visita);
    
    public Integer modificar(Visita visita);
    
    public Integer eliminar(Visita visita);
    
    public ArrayList<Visita> listarTodos();
    
    public Visita obtenerPorId(Integer idVisita);
    
}