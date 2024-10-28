package pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.AlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.db.DAOImpl;


public class AlmacenDAOImpl extends DAOImpl implements AlmacenDAO {
    private Almacen almacen;

    public AlmacenDAOImpl() {
        super("Almacen");
        this.almacen = null;
    }

    @Override
    public Boolean existeAlmacen(Almacen almacen) {
        this.almacen = almacen;
        Integer idAlmacen = null;
        try {
            this.abrirConexion();
            String sql = "select idAlmacen from Almacen where ";
            sql = sql.concat("nombre=? ");
            sql = sql.concat("and estado=? ");
            sql = sql.concat("and descripcion=? ");
            this.colocarSQLenStatement(sql);
            this.incluirParametroString(1, this.almacen.getNombre());
            this.incluirParametroString(2, this.almacen.getEstado());
            this.incluirParametroString(3, this.almacen.getDescripcion());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idAlmacen = this.resultSet.getInt("idAlmacen");
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar si existe persona - " + ex);
        } finally {
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        this.almacen.setIdAlmacen(idAlmacen);
        return idAlmacen != null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "nombre,estado,descripcion";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.almacen.getNombre());
        this.incluirParametroString(2, this.almacen.getEstado());
        this.incluirParametroString(3, this.almacen.getDescripcion());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "nombre=?,estado=?,descripcion=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idAlmacen=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.almacen.getNombre());
        this.incluirParametroString(2, this.almacen.getEstado());
        this.incluirParametroString(3, this.almacen.getDescripcion());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.almacen.getIdAlmacen());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idAlmacen,nombre,estado,descripcion";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.almacen);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.almacen.getIdAlmacen());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.almacen = new Almacen();
        this.almacen.setIdAlmacen(this.resultSet.getInt("idAlmacen"));
        this.almacen.setNombre(this.resultSet.getString("nombre"));
        this.almacen.setEstado(this.resultSet.getString("estado"));
        this.almacen.setDescripcion(this.resultSet.getString("descripcion"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.almacen=null;
    }

    @Override
    public Integer insertar(Almacen almacen) {
        this.almacen = almacen;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    public Integer modificar(Almacen almacen) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(almacen);
    }

    @Override
    public Integer eliminar(Almacen almacen) {
        this.almacen = almacen;
        return super.eliminar();
    }

    @Override
    public ArrayList<Almacen> listarTodos() {
        return (ArrayList<Almacen>) super.listarTodos(null);
    }

    @Override
    public Almacen obtenerPorId(Integer idPersona) {
        this.almacen = new Almacen();
        this.almacen.setIdAlmacen(idPersona);
        this.obtenerPorId();
        return this.almacen;
    }

    @Override
    public Integer insertar(Almacen almacen, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(almacen);
    }

    @Override
    public Integer modificar(Almacen almacen, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.modificar(almacen);
    }

    @Override
    public Integer eliminar(Almacen almacen, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.eliminar(almacen);
    }
}