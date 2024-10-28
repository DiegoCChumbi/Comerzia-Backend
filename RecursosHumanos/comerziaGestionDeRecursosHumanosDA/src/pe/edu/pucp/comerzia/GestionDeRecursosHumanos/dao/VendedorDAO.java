package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;

public interface VendedorDAO {
    public Integer insertar(Vendedor vendedor);
    
    public Integer modificar(Vendedor vendedor);
    
    public Integer eliminar(Vendedor vendedor);
    
    public ArrayList<Vendedor> listarTodos();
    
    public Vendedor obtenerPorId(Integer idVendedor);
    
    public Boolean existeVendedor(Vendedor vendedor);
    
    public Boolean existeVendedor(Vendedor vendedor, Boolean abreConexion);
}
