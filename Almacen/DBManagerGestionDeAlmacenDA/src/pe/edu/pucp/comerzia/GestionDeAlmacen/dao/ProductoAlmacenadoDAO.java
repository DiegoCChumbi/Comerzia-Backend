package pe.edu.pucp.comerzia.GestionDeAlmacen.dao;

import java.sql.Connection;
import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;

public interface ProductoAlmacenadoDAO {
    
    public Integer insertar(ProductoAlmacenado productoAlmacenado);
    
    public Integer insertar(ProductoAlmacenado productoAlmacenado,Boolean usarTransaccion, Connection conexion);

    public Integer modificar(ProductoAlmacenado productoAlmacenado);
    
    public Integer modificar(ProductoAlmacenado productoAlmacenado,Boolean usarTransaccion, Connection conexion);

    public Integer eliminar(ProductoAlmacenado productoAlmacenado);
    
    public Integer eliminar(ProductoAlmacenado productoAlmacenado,Boolean usarTransaccion, Connection conexion);

    public ArrayList<ProductoAlmacenado> listarTodos();

    public ProductoAlmacenado obtenerPorId(Integer idProductoAlmacenado);
    
    public Boolean existeProductoAlmacenado(ProductoAlmacenado productoAlmacenado);
}
