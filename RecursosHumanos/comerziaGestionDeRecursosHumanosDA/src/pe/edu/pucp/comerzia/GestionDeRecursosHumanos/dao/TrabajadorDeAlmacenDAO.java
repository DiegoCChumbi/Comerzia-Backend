package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

public interface TrabajadorDeAlmacenDAO {
    public Integer insertar(TrabajadorDeAlmacen trabajador);
    
    public Integer modificar(TrabajadorDeAlmacen trabajador);
    
    public Integer eliminar(TrabajadorDeAlmacen trabajador);
    
    public ArrayList<TrabajadorDeAlmacen> listarTodos();
    
    public TrabajadorDeAlmacen obtenerPorId(Integer idTrabajadorDeAlmacen);

    public Boolean existeTrabajadorDeAlmacen(TrabajadorDeAlmacen trabajador);
    
    public Boolean existeTrabajadorDeAlmacen(TrabajadorDeAlmacen trbajador,Boolean abreConexion);
}
