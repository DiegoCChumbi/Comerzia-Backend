package pe.edu.pucp.comerzia.gestioncomercial.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.gestioncomercial.dao.DocumentoDAO;
import pe.edu.pucp.comerzia.gestioncomercial.model.Documento;
import pe.edu.pucp.comerzia.gestioncomercial.model.Estado;
import pe.edu.pucp.comerzia.gestioncomercial.model.Tipo;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class DocumentoDAOImpl extends DAOImpl implements DocumentoDAO{
    private Documento documento;

    public DocumentoDAOImpl() {
        super("Documento");
        this.documento = null;
    }

    @Override
    public Boolean existeDocumento(Documento documento) {
        this.documento = documento;
        Integer idDocumento = null;
        try {
            this.abrirConexion();
            String sql = "select idDocumento from Documento where ";
            sql = sql.concat("idEmpresa=? ");
            sql = sql.concat("and estado=? ");
            sql = sql.concat("and tipo=?");
            sql = sql.concat("and idVendedor=?");
            sql = sql.concat("and idAdministrador=?");
            sql = sql.concat("and idTrabajadorDeAlmacen=?");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.documento.getIdEmpresa());
            this.incluirParametroString(2, this.documento.getEstado().toString());
            this.incluirParametroString(3, this.documento.getTipo().toString());
            this.incluirParametroInt(4, this.documento.getIdVendedor());
            this.incluirParametroInt(5, this.documento.getIdAdministrador());
            this.incluirParametroInt(6, this.documento.getIdTrabajadorDeAlmacen());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idDocumento = this.resultSet.getInt("idDocumento");
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
        this.documento.setIdDocumento(idDocumento);
        return idDocumento != null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "estado,tipo,idVendedor,idAdministrador,idTrabajadorDeAlmacen";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(2, this.documento.getEstado().toString());
        this.incluirParametroString(3, this.documento.getTipo().toString());
        this.incluirParametroInt(4, this.documento.getIdVendedor());
        this.incluirParametroInt(5, this.documento.getIdAdministrador());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "estado=?,tipo=?,idVendedor=?,idAdministrador=?,idTrabajadorDeAlmacen=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idDocumento=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(2, this.documento.getIdEmpresa());
        this.incluirParametroString(3, this.documento.getEstado().toString());
        this.incluirParametroString(4, this.documento.getTipo().toString());
        this.incluirParametroInt(5, this.documento.getIdVendedor());
        this.incluirParametroInt(6, this.documento.getIdAdministrador());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.documento.getIdDocumento());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idDocumento,idEmpresa,estado,tipo,idVendedor,idAdministrador,idTrabajadorDeAlmacen";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.documento);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.documento.getIdDocumento());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.documento = new Documento();
        this.documento.setIdDocumento(this.resultSet.getInt("idDocumento"));
        this.documento.setIdEmpresa(this.resultSet.getInt("idEmpresa"));
        this.documento.setEstado(Estado.valueOf(this.resultSet.getString("estado")));
        this.documento.setTipo(Tipo.valueOf(this.resultSet.getString("tipo")));
        this.documento.setIdVendedor(this.resultSet.getInt("idVendedor"));
        this.documento.setIdAdministrador(this.resultSet.getInt("idAdministrador"));
        this.documento.setIdTrabajadorDeAlmacen(this.resultSet.getInt("idTrabajadorDeAlmacen"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.documento=null;
    }

    @Override
    public Integer insertar(Documento documento) {
        this.documento = documento;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    public Integer modificar(Documento documento) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(documento);
    }

    @Override
    public Integer eliminar(Documento documento) {
        this.documento = documento;
        return super.eliminar();
    }

    @Override
    public ArrayList<Documento> listarTodos() {
        return (ArrayList<Documento>) super.listarTodos(null);
    }

    @Override
    public Documento obtenerPorId(Integer idDocumento) {
        this.documento = new Documento();
        this.documento.setIdDocumento(idDocumento);
        this.obtenerPorId();
        return this.documento;
    }

    @Override
    public Integer insertar(Documento documento, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(documento);
    }

    @Override
    public Integer modificar(Documento documento, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.modificar(documento);
    }

    @Override
    public Integer eliminar(Documento documento, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.eliminar(documento);
    }
}
