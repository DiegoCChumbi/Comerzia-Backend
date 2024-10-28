package pe.edu.pucp.comerzia.RelacionesComerciales.dao;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import java.sql.Connection;

public interface EmpresaDAO {
    
    public Integer insertar(Empresa empresa);
    
    public Integer insertar(Empresa empresa, Boolean usarTransaccion, Connection conexion);
    
    public Integer modificar(Empresa empresa);
    
    public Integer modificar(Empresa empresa, Boolean usarTransaccion, Connection conexion);
    
    public Integer eliminar(Empresa empresa);
    
    public Integer eliminar(Empresa empresa, Boolean usarTransaccion, Connection conexion);
    
    public ArrayList<Empresa> listarTodos();
    
    public Empresa obtenerPorId(Integer idEmpresa);
    
    public Boolean existeEmpresa(Empresa empresa);
    
}