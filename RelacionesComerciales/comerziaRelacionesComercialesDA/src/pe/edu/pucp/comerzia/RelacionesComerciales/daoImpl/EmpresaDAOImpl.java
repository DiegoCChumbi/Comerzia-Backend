package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Empresa;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.EmpresaDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class EmpresaDAOImpl extends DAOImpl implements EmpresaDAO {

    private Empresa empresa;

    public EmpresaDAOImpl() {
        super("Empresa");
        this.empresa = null;
    }

    @Override
    public Integer insertar(Empresa empresa) {
        this.empresa = empresa;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }
    
    @Override
    public Integer insertar(Empresa empresa, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(empresa);
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "nombre,direccion,telefono,email,tipoIndustria";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.empresa.getNombre());
        this.incluirParametroString(2, this.empresa.getDireccion());
        this.incluirParametroString(3, this.empresa.getTelefono());
        this.incluirParametroString(4, this.empresa.getEmail());
        this.incluirParametroString(5, this.empresa.getTipoIndustria());
    }        

    @Override
    public Integer modificar(Empresa empresa) {
        this.empresa = empresa;
        return super.modificar();
    }
    
    @Override
    public Integer modificar(Empresa empresa, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.modificar(empresa);
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idEmpresa=?";
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "nombre=?, direccion=?, telefono=?, email=?, tipoIndustria=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(6, this.empresa.getIdEmpresa());
        this.incluirParametroString(1, this.empresa.getNombre());
        this.incluirParametroString(2, this.empresa.getDireccion());
        this.incluirParametroString(3, this.empresa.getTelefono());
        this.incluirParametroString(4, this.empresa.getEmail());
        this.incluirParametroString(5, this.empresa.getTipoIndustria());
    }

    @Override
    public Integer eliminar(Empresa empresa) {
        this.empresa = empresa;
        return super.eliminar();
    }
    
    @Override
    public Integer eliminar(Empresa empresa, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.eliminar(empresa);
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.empresa.getIdEmpresa());
    }

    @Override
    public ArrayList<Empresa> listarTodos() {
        return (ArrayList<Empresa>) super.listarTodos(null);
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idEmpresa,nombre,direccion,telefono,email,tipoIndustria";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.empresa);
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.empresa = new Empresa();
        this.empresa.setIdEmpresa(this.resultSet.getInt("idEmpresa"));
        this.empresa.setNombre(this.resultSet.getString("nombre"));
        this.empresa.setDireccion(this.resultSet.getString("direccion"));
        this.empresa.setTelefono(this.resultSet.getString("telefono"));
        this.empresa.setEmail(this.resultSet.getString("email"));
        this.empresa.setTipoIndustria(this.resultSet.getString("tipoIndustria"));
    }

    @Override
    public Empresa obtenerPorId(Integer idEmpresa) {
        this.empresa = new Empresa();
        this.empresa.setIdEmpresa(idEmpresa);
        this.obtenerPorId();
        return this.empresa;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.empresa.getIdEmpresa());
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.empresa = null;
    }
    
    @Override
    public Boolean existeEmpresa(Empresa empresa) {
        this.empresa = empresa;
        Integer idEmpresa = null;
        try {
            this.abrirConexion();
            String sql = "select idEmpresa from Empresa where ";
            sql = sql.concat("nombre=? ");
            sql = sql.concat("and tipoIndustria=? ");
            sql = sql.concat("and email=?");
            this.colocarSQLenStatement(sql);
            this.incluirParametroString(1, this.empresa.getNombre());
            this.incluirParametroString(2, this.empresa.getTipoIndustria());
            this.incluirParametroString(3, this.empresa.getEmail());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idEmpresa = this.resultSet.getInt("idEmpresa");
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar si existe empresa - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        this.empresa.setIdEmpresa(idEmpresa);
        return idEmpresa != null;
    }
}
