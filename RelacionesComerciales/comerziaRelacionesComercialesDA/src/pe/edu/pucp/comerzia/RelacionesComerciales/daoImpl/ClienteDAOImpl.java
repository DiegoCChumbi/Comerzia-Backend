package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.ClienteDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;
import pe.edu.pucp.comerzia.db.Tipo_Operacion;

public class ClienteDAOImpl extends DAOImpl implements ClienteDAO {

    private Cliente cliente;

    public ClienteDAOImpl() {
        super("Cliente");
        this.cliente = null;
    }

    @Override
    public Integer insertar(Cliente cliente) {
        this.cliente = cliente;
        Integer idEmpresa = null;
        Empresa empresa = new Empresa();
        empresa.setNombre(this.cliente.getNombre());
        empresa.setDireccion(this.cliente.getDireccion());
        empresa.setTelefono(this.cliente.getTelefono());
        empresa.setEmail(this.cliente.getEmail());
        empresa.setTipoIndustria(this.cliente.getTipoIndustria());

        EmpresaDAO empresaDAO = new EmpresaDAOImpl();
        Boolean existeEmpresa = empresaDAO.existeEmpresa(empresa);
        Boolean existeCliente = false;

        this.usarTransaccion = false;
        try {
            this.iniciarTransaccion();
            if (!existeEmpresa) {
                idEmpresa = empresaDAO.insertar(empresa, this.usarTransaccion, this.conexion);
                this.cliente.setIdEmpresa(idEmpresa);
            } else {
                idEmpresa = empresa.getIdEmpresa();
                this.cliente.setIdEmpresa(idEmpresa);
                Boolean abreConexion = false;
                existeCliente = this.existeCliente(this.cliente, abreConexion);
            }
            if (!existeCliente) {
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
        return "idEmpresa, fechaRegistro, fechaUltimaCompra";        
    }
    
    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.cliente.getIdEmpresa());
        this.incluirParametroDate(2, this.cliente.getFechaRegistro());
        this.incluirParametroDate(3, this.cliente.getFechaUltimaCompra());
    }

    @Override
    public Integer modificar(Cliente cliente) {
        Integer retorno = 0;
        this.cliente = cliente;
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(this.cliente.getIdEmpresa());
        empresa.setNombre(this.cliente.getNombre());
        empresa.setDireccion(this.cliente.getDireccion());
        empresa.setTelefono(this.cliente.getTelefono());
        empresa.setEmail(this.cliente.getEmail());
        empresa.setTipoIndustria(this.cliente.getTipoIndustria());

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
        return "fechaRegistro=?, fechaUltimaCompra=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(3, this.cliente.getIdEmpresa());
        this.incluirParametroDate(1, this.cliente.getFechaRegistro());
        this.incluirParametroDate(2, this.cliente.getFechaUltimaCompra());
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        String sql;
        if (this.tipo_Operacion == Tipo_Operacion.MODIFICAR || this.tipo_Operacion == Tipo_Operacion.ELIMINAR) {
            sql = "idEmpresa=?";
        } else {
            sql = "emp.idEmpresa=?";
        }
        return sql;
    }

    @Override
    public Integer eliminar(Cliente cliente) {
        Integer retorno = 0;
        this.cliente = cliente;
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(this.cliente.getIdEmpresa());        

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
        this.incluirParametroInt(1, this.cliente.getIdEmpresa());
    }

    @Override
    public ArrayList<Cliente> listarTodos() {
        return (ArrayList<Cliente>) super.listarTodos(null);
    }

    @Override
    protected String generarSQLParaListarTodos(Integer limite) {
        String sql = "select ";
        sql = sql.concat(obtenerProyeccionParaSelect());
        sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" cli ");
        sql = sql.concat("join Empresa emp on emp.idEmpresa = cli.idEmpresa ");
        if (limite != null && limite > 0) {
            sql = sql.concat(" limit ").concat(limite.toString());
        }
        return sql;
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        String sql = "emp.idEmpresa, emp.nombre, emp.direccion, emp.telefono, ";
        sql = sql.concat("emp.email, emp.tipoIndustria, ");
        sql = sql.concat("cli.fechaRegistro, cli.fechaUltimaCompra");
        return sql;
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.cliente);
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.cliente = new Cliente();
        this.cliente.setIdEmpresa(this.resultSet.getInt("idEmpresa"));
        this.cliente.setNombre(this.resultSet.getString("nombre"));
        this.cliente.setDireccion(this.resultSet.getString("direccion"));
        this.cliente.setTelefono(this.resultSet.getString("telefono"));
        this.cliente.setEmail(this.resultSet.getString("email"));
        this.cliente.setTipoIndustria(this.resultSet.getString("tipoIndustria"));
        this.cliente.setFechaRegistro(this.resultSet.getTimestamp("fechaRegistro"));
        this.cliente.setFechaUltimaCompra(this.resultSet.getTimestamp("fechaUltimaCompra"));
    }

    @Override
    public Cliente obtenerPorId(Integer idEmpresa) {
        this.cliente = new Cliente();
        this.cliente.setIdEmpresa(idEmpresa);
        super.obtenerPorId();
        return this.cliente;
    }

    @Override
    protected String generarSQLParaListarPorId() {
        String sql = "select ";
        sql = sql.concat(this.obtenerProyeccionParaSelect());
        sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" cli ");
        sql = sql.concat("join Empresa emp on emp.idEmpresa = cli.idEmpresa ");
        sql = sql.concat(" where ");
        sql = sql.concat(this.obtenerPredicadoParaLlavePrimaria());
        return sql;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.cliente.getIdEmpresa());
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.cliente = null;
    }
    
    @Override
    public Boolean existeCliente(Cliente cliente) {
        Boolean abreConexion = true;
        return existeCliente(cliente, abreConexion);
    }

    @Override
    public Boolean existeCliente(Cliente cliente, Boolean abreConexion) {
        this.cliente = cliente;
        Integer idEmpresa = null;
        try {
            if (abreConexion) {
                this.abrirConexion();
            }
            String sql = "select idEmpresa from Cliente where ";
            sql = sql.concat("idEmpresa=? ");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.cliente.getIdEmpresa());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idEmpresa = this.resultSet.getInt("idEmpresa");
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar si existe cliente - " + ex);
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