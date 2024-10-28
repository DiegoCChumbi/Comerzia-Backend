package pe.edu.pucp.comerzia.gestioncomercial.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.gestioncomercial.model.LineaDocumento;
import pe.edu.pucp.comerzia.gestioncomercial.dao.LineaDocumentoDAO;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class LineaDocumentoDAOImpl extends DAOImpl implements LineaDocumentoDAO{
    private LineaDocumento linea;

    public LineaDocumentoDAOImpl() {
        super("LineaDocumento");
        this.linea = null;
    }

    @Override
    public Boolean existeLineaDocumento(LineaDocumento linea) {
        this.linea = linea;
        Integer idLinea = null;
        try {
            this.abrirConexion();
            String sql = "select idLineaDocumento from LinaDocumento where ";
            sql = sql.concat("idDocumento=? ");
            sql = sql.concat("and idProducto=? ");
            sql = sql.concat("and cantidad=? ");
            sql = sql.concat("and precioUnitario=?");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.linea.getIdDocumento());
            this.incluirParametroInt(2, this.linea.getIdProducto());
            this.incluirParametroInt(3, this.linea.getCantidad());
            this.incluirParametroDouble(4, this.linea.getPrecioUnitario());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idLinea = this.resultSet.getInt("idDocumento");
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
        this.linea.setIdLinea(idLinea);
        return idLinea != null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idDocumento,idProducto,cantidad,precioUnitario";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1, this.linea.getIdDocumento());
        this.incluirParametroInt(2, this.linea.getIdProducto());
        this.incluirParametroInt(3, this.linea.getCantidad());
        this.incluirParametroDouble(4, this.linea.getPrecioUnitario());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "idDocumento=?,idProducto=?,cantidad=?,precioUnitario=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idLinea=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(1, this.linea.getIdLinea());
        this.incluirParametroInt(2, this.linea.getIdDocumento());
        this.incluirParametroInt(3, this.linea.getIdProducto());
        this.incluirParametroInt(4, this.linea.getCantidad());
        this.incluirParametroDouble(5, this.linea.getPrecioUnitario());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.linea.getIdLinea());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idLinea,idDocumento,idProducto,cantidad,precioUnitario";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.linea);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.linea.getIdLinea());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.linea = new LineaDocumento();
        this.linea.setIdLinea(this.resultSet.getInt("idLinea"));
        this.linea.setIdDocumento(this.resultSet.getInt("idDocumento"));
        this.linea.setIdProducto(this.resultSet.getInt("idProducto"));
        this.linea.setCantidad(this.resultSet.getInt("cantidad"));
        this.linea.setPrecioUnitario(this.resultSet.getDouble("precioUnitario"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.linea=null;
    }

    @Override
    public Integer insertar(LineaDocumento linea) {
        this.linea = linea;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    public Integer modificar(LineaDocumento linea) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(linea);
    }

    @Override
    public Integer eliminar(LineaDocumento linea) {
        this.linea = linea;
        return super.eliminar();
    }

    @Override
    public ArrayList<LineaDocumento> listarTodos() {
        return (ArrayList<LineaDocumento>) super.listarTodos(null);
    }

    @Override
    public LineaDocumento obtenerPorId(Integer idLinea) {
        this.linea = new LineaDocumento();
        this.linea.setIdLinea(idLinea);
        this.obtenerPorId();
        return this.linea;
    }

    @Override
    public Integer insertar(LineaDocumento linea, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(linea);
    }

    @Override
    public Integer modificar(LineaDocumento linea, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.modificar(linea);
    }

    @Override
    public Integer eliminar(LineaDocumento linea, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.eliminar(linea);
    }
}
