package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;

public interface ProductoDAO {
    
    public Integer insertar(Producto producto);
    
    public Integer insertar(Producto producto,Boolean usarTransaccion, Connection conexion);

    public Integer modificar(Producto producto);
    
    public Integer modificar(Producto producto,Boolean usarTransaccion, Connection conexion);

    public Integer eliminar(Producto producto);
    
    public Integer eliminar(Producto producto,Boolean usarTransaccion, Connection conexion);

    public ArrayList<Producto> listarTodos();

    public Producto obtenerPorId(Integer idProducto);
    
    public Boolean existeProducto(Producto producto);
}
