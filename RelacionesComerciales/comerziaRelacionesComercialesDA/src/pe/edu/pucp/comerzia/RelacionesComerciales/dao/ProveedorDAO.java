package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;

public interface ProveedorDAO {
    
    public Integer insertar(Proveedor proveedor);
    
    public Integer modificar(Proveedor proveedor);
    
    public Integer eliminar(Proveedor proveedor);
    
    public ArrayList<Proveedor> listarTodos();
    
    public Proveedor obtenerPorId(Integer idProveedor);
    
    public Boolean existeProveedor(Proveedor proveedor);
    
    public Boolean existeProveedor(Proveedor proveedor, Boolean abreConexion);
}
