package pe.edu.pucp.comerzia.RelacionesComerciales.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.comerzia.db.DAOImpl;
import pe.edu.pucp.comerzia.RelacionesComerciales.dao.VisitaDAO;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;

public class VisitaDAOImpl extends DAOImpl implements VisitaDAO {

    private Visita visita;

    public VisitaDAOImpl() {
        super("Visita");
        this.visita = null;
    }

    @Override
    public Integer insertar(Visita visita) {
        this.visita = visita;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "fecha, duracion, idCliente, idVendedor";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?, ?, ?, ?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroDate(1, this.visita.getFecha());
        this.incluirParametroDouble(2, this.visita.getDuracion());
        this.incluirParametroInt(3, this.visita.getIdCliente());
        this.incluirParametroInt(4, this.visita.getIdVendedor());
    }

    @Override
    public Integer modificar(Visita visita) {
        this.visita = visita;
        return super.modificar();
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "fecha=?, duracion=?, idCliente=?, idVendedor=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idVisita=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(5, this.visita.getIdVisita());
        this.incluirParametroDate(1, this.visita.getFecha());
        this.incluirParametroDouble(2, this.visita.getDuracion());
        this.incluirParametroInt(3, this.visita.getIdCliente());
        this.incluirParametroInt(4, this.visita.getIdVendedor());
    }

    @Override
    public Integer eliminar(Visita visita) {
        this.visita = visita;
        return super.eliminar();
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.visita.getIdVisita());
    }

    @Override
    public ArrayList<Visita> listarTodos() {
        return (ArrayList<Visita>) super.listarTodos(null);
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idVisita, fecha, duracion, idCliente, idVendedor";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        this.instanciarObjetoDelResultSet();
        lista.add(this.visita);
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.visita = new Visita();
        visita.setIdVisita(this.resultSet.getInt("idVisita"));
        visita.setFecha(this.resultSet.getTimestamp("fecha"));
        visita.setDuracion(this.resultSet.getDouble("duracion"));
        visita.setIdCliente(this.resultSet.getInt("idCliente"));
        visita.setIdVendedor(this.resultSet.getInt("idVendedor"));
    }

    @Override
    public Visita obtenerPorId(Integer idVisita) {
        this.visita = new Visita();
        this.visita.setIdVisita(idVisita);
        super.obtenerPorId();
        return this.visita;
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.visita.getIdVisita());
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.visita = null;
    }
}