package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ProveedorDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;
import pe.edu.pucp.comerzia.db.Tipo_Operacion;

public class ProveedorDAOImpl extends DAOImpl implements ProveedorDAO {

    private Proveedor proveedor;

    public ProveedorDAOImpl() {
        super("Proveedor");
        this.proveedor = null;
    }

    @Override
    public Integer insertar(Proveedor proveedor) {
        this.proveedor = proveedor;
        Integer idEmpresa = null;
        Empresa empresa = new Empresa();
        empresa.setNombre(this.proveedor.getNombre());
        empresa.setDireccion(this.proveedor.getDireccion());
        empresa.setTelefono(this.proveedor.getTelefono());
        empresa.setEmail(this.proveedor.getEmail());
        empresa.setTipoIndustria(this.proveedor.getTipoIndustria());

        EmpresaDAO empresaDAO = new EmpresaDAOImpl();
        Boolean existeEmpresa = empresaDAO.existeEmpresa(empresa);
        Boolean existeProveedor = false;

        this.usarTransaccion = false;
        try {
            this.iniciarTransaccion();
            if (!existeEmpresa) {
                idEmpresa = empresaDAO.insertar(empresa, this.usarTransaccion, this.conexion);
                this.proveedor.setIdEmpresa(idEmpresa);
            } else {
                idEmpresa = empresa.getIdEmpresa();
                this.proveedor.setIdEmpresa(idEmpresa);
                Boolean abreConexion = false;
                existeProveedor = this.existeProveedor(this.proveedor, abreConexion);
            }
            if (!existeProveedor) {
                super.insertar();
            }
            this.comitarTransaccion();
        } catch (SQLException ex) {
            System.err.println("Error al intentar insertar - " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                System.err.println("Error al intentar hacer rollback - " + ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al intentar cerrar la conexion - " + ex);
            }
        }
        this.usarTransaccion = true;
        return idEmpresa;
    }
    
    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idEmpresa, fecha_afiliacion, RUC, razonSocial, calificacion, pais";        
    }
    
    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.proveedor.getIdEmpresa());
        this.incluirParametroDate(2, this.proveedor.getFecha_afiliacion());
        this.incluirParametroString(3, this.proveedor.getRUC());
        this.incluirParametroString(4, this.proveedor.getRazonSocial());
        this.incluirParametroDouble(5, this.proveedor.getCalificacion());
        this.incluirParametroString(6, this.proveedor.getPais());
    }

    @Override
    public Integer modificar(Proveedor proveedor) {
        Integer retorno = 0;
        this.proveedor = proveedor;
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(this.proveedor.getIdEmpresa());
        empresa.setNombre(this.proveedor.getNombre());
        empresa.setDireccion(this.proveedor.getDireccion());
        empresa.setTelefono(this.proveedor.getTelefono());
        empresa.setEmail(this.proveedor.getEmail());
        empresa.setTipoIndustria(this.proveedor.getTipoIndustria());

        EmpresaDAO empresaDAO = new EmpresaDAOImpl();

        this.usarTransaccion = false;
        try {
            this.iniciarTransaccion();
            empresaDAO.modificar(empresa, this.usarTransaccion, this.conexion);
            retorno = super.modificar();
            this.comitarTransaccion();
        } catch (SQLException ex) {
            System.err.println("Error al intentar modificar - " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                System.err.println("Error al intentar hacer rollback - " + ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al intentar cerrar la conexion - " + ex);
            }
        }
        this.usarTransaccion = true;
        return retorno;
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "fecha_afiliacion=?, RUC=?, razonSocial=?, calificacion=?, pais=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(6, this.proveedor.getIdEmpresa());
        this.incluirParametroDate(1, this.proveedor.getFecha_afiliacion());
        this.incluirParametroString(2, this.proveedor.getRUC());
        this.incluirParametroString(3, this.proveedor.getRazonSocial());
        this.incluirParametroDouble(4, this.proveedor.getCalificacion());
        this.incluirParametroString(5, this.proveedor.getPais());
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        String sql = "";
        if (this.tipo_Operacion == Tipo_Operacion.MODIFICAR || this.tipo_Operacion == Tipo_Operacion.ELIMINAR) {
            sql = "idEmpresa=?";
        } else {
            sql = "emp.idEmpresa=?";
        }
        return sql;
    }

    @Override
    public Integer eliminar(Proveedor proveedor) {
        Integer retorno = 0;
        this.proveedor = proveedor;
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(this.proveedor.getIdEmpresa());        

        EmpresaDAO empresaDAO = new EmpresaDAOImpl();
        this.usarTransaccion = false;
        try {
            this.iniciarTransaccion();
            retorno = super.eliminar();
            empresaDAO.eliminar(empresa, this.usarTransaccion, this.conexion);            
            this.comitarTransaccion();
        } catch (SQLException ex) {
            System.err.println("Error al intentar eliminar - " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                System.err.println("Error al intentar hacer rollback - " + ex1);
            }
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al intentar cerrar la conexion - " + ex);
            }
        }
        this.usarTransaccion = true;
        return retorno;
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.proveedor.getIdEmpresa());
    }

    @Override
    public ArrayList<Proveedor> listarTodos() {
        return (ArrayList<Proveedor>) super.listarTodos(null);
    }

    @Override
    protected String generarSQLParaListarTodos(Integer limite) {
        String sql = "select ";
        sql = sql.concat(obtenerProyeccionParaSelect());
        sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" pro ");
        sql = sql.concat("join empresa emp on emp.idEmpresa = pro.idEmpresa ");
        if (limite != null && limite > 0) {
            sql = sql.concat(" limit ").concat(limite.toString());
        }
        return sql;
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        String sql = "emp.idEmpresa, emp.nombre, emp.direccion, emp.telefono, ";
        sql = sql.concat("emp.email, emp.tipoIndustria, ");
        sql = sql.concat("pro.fecha_afiliacion, pro.RUC, pro.razonSocial, pro.calificacion, pro.pais");
        return sql;
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.proveedor);
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.proveedor = new Proveedor();
        this.proveedor.setIdEmpresa(this.resultSet.getInt("idEmpresa"));
        this.proveedor.setNombre(this.resultSet.getString("nombre"));
        this.proveedor.setDireccion(this.resultSet.getString("direccion"));
        this.proveedor.setTelefono(this.resultSet.getString("telefono"));
        this.proveedor.setEmail(this.resultSet.getString("email"));
        this.proveedor.setTipoIndustria(this.resultSet.getString("tipoIndustria"));
        this.proveedor.setFecha_afiliacion(this.resultSet.getTimestamp("fecha_afiliacion"));
        this.proveedor.setEmail(this.resultSet.getString("razonSocial"));
        this.proveedor.setCalificacion(this.resultSet.getDouble("calificacion"));
        this.proveedor.setPais(this.resultSet.getString("pais"));
    }

    @Override
    public Proveedor obtenerPorId(Integer idEmpresa) {
        this.proveedor = new Proveedor();
        this.proveedor.setIdEmpresa(idEmpresa);
        super.obtenerPorId();
        return this.proveedor;
    }

    @Override
    protected String generarSQLParaListarPorId() {
        String sql = "select ";
        sql = sql.concat(this.obtenerProyeccionParaSelect());
        sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" pro ");
        sql = sql.concat("join empresa emp on emp.idEmpresa = pro.idEmpresa ");
        sql = sql.concat(" where ");
        sql = sql.concat(this.obtenerPredicadoParaLlavePrimaria());
        return sql;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.proveedor.getIdEmpresa());
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.proveedor = null;
    }
    
    @Override
    public Boolean existeProveedor(Proveedor proveedor) {
        Boolean abreConexion = true;
        return existeProveedor(proveedor, abreConexion);
    }

    @Override
    public Boolean existeProveedor(Proveedor proveedor, Boolean abreConexion) {
        this.proveedor = proveedor;
        Integer idEmpresa = null;
        try {
            if (abreConexion) {
                this.abrirConexion();
            }
            String sql = "select idEmpresa from proveedor where ";
            sql = sql.concat("idEmpresa=? ");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.proveedor.getIdEmpresa());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idEmpresa = this.resultSet.getInt("idEmpresa");
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar si existe proveedor - " + ex);
        } finally {
            try {
                if (abreConexion) {
                    this.cerrarConexion();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return idEmpresa != null;
    }

}