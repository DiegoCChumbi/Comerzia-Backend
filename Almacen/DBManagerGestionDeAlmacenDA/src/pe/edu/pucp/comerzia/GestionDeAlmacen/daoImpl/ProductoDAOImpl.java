package pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.ProductoDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class ProductoDAOImpl extends DAOImpl implements ProductoDAO {
    private Producto producto;

    public ProductoDAOImpl() {
        super("Producto");
        this.producto = null;
    }

    @Override
    public Boolean existeProducto(Producto producto) {
        this.producto = producto;
        Integer idProducto = null;
        try {
            this.abrirConexion();
            String sql = "select idProducto from Producto where ";
            sql = sql.concat("nombreProducto=? ");
            sql = sql.concat("and precio=? ");
            sql = sql.concat("and stockMinimo=? ");
            this.colocarSQLenStatement(sql);
            this.incluirParametroString(1, this.producto.getNombreProducto());
            this.incluirParametroDouble(2, this.producto.getPrecio());
            this.incluirParametroInt(3, this.producto.getStockMinimo());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idProducto = this.resultSet.getInt("idProducto");
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
        this.producto.setIdProducto(idProducto);
        return idProducto != null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "nombreProducto,precio,stockMinimo";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.producto.getNombreProducto());
        this.incluirParametroDouble(2, this.producto.getPrecio());
        this.incluirParametroInt(3, this.producto.getStockMinimo());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "nombreProducto=?,precio=?,stockMinimo=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idProducto=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.producto.getNombreProducto());
        this.incluirParametroDouble(2, this.producto.getPrecio());
        this.incluirParametroInt(3, this.producto.getStockMinimo());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.producto.getIdProducto());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idProducto,nombreProducto,precio,stockMinimo";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.producto);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.producto.getIdProducto());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.producto = new Producto();
        this.producto.setIdProducto(this.resultSet.getInt("idProducto"));
        this.producto.setNombreProducto(this.resultSet.getString("nombreProducto"));
        this.producto.setPrecio(this.resultSet.getDouble("precio"));
        this.producto.setStockMinimo(this.resultSet.getInt("stockMinimo"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.producto=null;
    }

    @Override
    public Integer insertar(Producto producto) {
        this.producto = producto;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    public Integer modificar(Producto producto) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(producto);
    }

    @Override
    public Integer eliminar(Producto producto) {
        this.producto = producto;
        return super.eliminar();
    }

    @Override
    public ArrayList<Producto> listarTodos() {
        return (ArrayList<Producto>) super.listarTodos(null);
    }

    @Override
    public Producto obtenerPorId(Integer idProducto) {
        this.producto = new Producto();
        this.producto.setIdProducto(idProducto);
        this.obtenerPorId();
        return this.producto;
    }

    @Override
    public Integer insertar(Producto producto, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(producto);
    }

    @Override
    public Integer modificar(Producto producto, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.modificar(producto);
    }

    @Override
    public Integer eliminar(Producto producto, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.eliminar(producto);
    }
}