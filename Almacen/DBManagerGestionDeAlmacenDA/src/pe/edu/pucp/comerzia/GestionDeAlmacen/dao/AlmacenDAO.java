package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;

public interface AlmacenDAO {

    public Integer insertar(Almacen almacen);
    
    public Integer insertar(Almacen almacen,Boolean usarTransaccion, Connection conexion);

    public Integer modificar(Almacen almacen);
    
    public Integer modificar(Almacen almacen,Boolean usarTransaccion, Connection conexion);

    public Integer eliminar(Almacen almacen);
    
    public Integer eliminar(Almacen almacen,Boolean usarTransaccion, Connection conexion);

    public ArrayList<Almacen> listarTodos();

    public Almacen obtenerPorId(Integer idAlmacen);
    
    public Boolean existeAlmacen(Almacen almacen);
}