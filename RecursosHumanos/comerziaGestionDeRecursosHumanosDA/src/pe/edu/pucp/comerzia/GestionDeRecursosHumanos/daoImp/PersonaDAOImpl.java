package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.db.DAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;

public class PersonaDAOImpl extends DAOImpl implements PersonaDAO {

    private Persona persona;

    public PersonaDAOImpl() {
        super("Persona");
        this.persona = null;
    }
    
    @Override
    public Boolean existePersona(Persona persona) {
        this.persona = persona;
        Integer idPersona = null;
        try {
            this.abrirConexion();
            String sql = "select idPersona from Persona where ";
            sql = sql.concat("dni=? ");
            sql = sql.concat("and nombreCompleto=? ");
            sql = sql.concat("and telefono=? ");
            sql = sql.concat("and correo=?");
            sql = sql.concat("and direccion=?");
            this.colocarSQLenStatement(sql);
            this.incluirParametroString(1, this.persona.getDni());
            this.incluirParametroString(2, this.persona.getNombreCompleto());
            this.incluirParametroString(3, this.persona.getTelefono());
            this.incluirParametroString(4, this.persona.getCorreo());
            this.incluirParametroString(5, this.persona.getDireccion());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idPersona = this.resultSet.getInt("idPersona");
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
        this.persona.setIdPersona(idPersona);
        return idPersona != null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "dni,nombreCompleto,telefono,correo,direccion";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroString(1, this.persona.getDni());
        this.incluirParametroString(2, this.persona.getNombreCompleto());
        this.incluirParametroString(3, this.persona.getTelefono());
        this.incluirParametroString(4, this.persona.getCorreo());
        this.incluirParametroString(5, this.persona.getDireccion());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "dni=?,nombreCompleto=?,telefono=?,correo=?,direccion=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        return "idPersona=?";
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroString(1, this.persona.getDni());
        this.incluirParametroString(2, this.persona.getNombreCompleto());
        this.incluirParametroString(3, this.persona.getTelefono());
        this.incluirParametroString(4, this.persona.getCorreo());
        this.incluirParametroString(5, this.persona.getDireccion());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.persona.getIdPersona());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        return "idPersona,dni,nombreCompleto,telefono,correo,direccion";
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.persona);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.persona.getIdPersona());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.persona = new Persona();
        this.persona.setIdPersona(this.resultSet.getInt("idPersona"));
        this.persona.setDni(this.resultSet.getString("dni"));
        this.persona.setNombreCompleto(this.resultSet.getString("nombreCompleto"));
        this.persona.setTelefono(this.resultSet.getString("telefono"));
        this.persona.setCorreo(this.resultSet.getString("correo"));
        this.persona.setDireccion(this.resultSet.getString("direccion"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.persona=null;
    }

    @Override
    public Integer insertar(Persona persona) {
        this.persona = persona;
        this.retornarLlavePrimaria = true;
        Integer id = super.insertar();
        this.retornarLlavePrimaria = false;
        return id;
    }

    @Override
    public Integer modificar(Persona persona) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(persona);
    }

    @Override
    public Integer eliminar(Persona persona) {
        this.persona = persona;
        return super.eliminar();
    }

    @Override
    public ArrayList<Persona> listarTodos() {
        return (ArrayList<Persona>) super.listarTodos(null);
    }

    @Override
    public Persona obtenerPorId(Integer idPersona) {
        this.persona = new Persona();
        this.persona.setIdPersona(idPersona);
        this.obtenerPorId();
        return this.persona;
    }

    @Override
    public Integer insertar(Persona persona, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.insertar(persona);
    }

    @Override
    public Integer modificar(Persona persona, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.modificar(persona);
    }

    @Override
    public Integer eliminar(Persona persona, Boolean usarTransaccion, Connection conexion) {
        this.usarTransaccion = usarTransaccion;
        this.conexion = conexion;
        return this.eliminar(persona);
    }
}
