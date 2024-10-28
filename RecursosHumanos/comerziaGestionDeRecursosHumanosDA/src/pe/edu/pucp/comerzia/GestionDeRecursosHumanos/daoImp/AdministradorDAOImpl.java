package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.AdministradorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Administrador;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.db.DAOImpl;


/*
public class Administrador {

    private Integer idAdministrador;
    private static int idCorrelativo = 1;

    private Integer idAlmacen;
 */
public class AdministradorDAOImpl extends DAOImpl implements AdministradorDAO {

    private Administrador administrador;

    public AdministradorDAOImpl() {
        super("Administrador");
        this.administrador = null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idAlmacen";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1,this.administrador.getIdAlmacen());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "idAlmacen=?";
    }

    @Override
    protected String obtenerPredicadoParaLlavePrimaria() {
        String sql = "";
        if (this.tipo_Operacion == tipo_Operacion.MODIFICAR || this.tipo_Operacion == tipo_Operacion.ELIMINAR){
            sql = "idPersona=?";
        } else{
            sql = "per.idPersona=?";
        }
        return sql;
    }

    @Override
    protected void incluirValorDeParametrosParaModificacion() throws SQLException {
        this.incluirParametroInt(1, this.administrador.getIdAlmacen());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1,this.administrador.getIdPersona());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        String sql = "per.idPersona, per.dni, per.nombreCompleto, per.telefono, per.correo, per.direccion, ";
        sql = sql.concat("ad.idAlmacen");
        return sql;
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.administrador);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.administrador.getIdAdministrador());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.administrador = new Administrador();
        this.administrador.setIdPersona(this.resultSet.getInt("idPersona"));
        this.administrador.setDni(this.resultSet.getString("dni"));
        this.administrador.setNombreCompleto(this.resultSet.getString("nombreCompleto"));
        this.administrador.setTelefono(this.resultSet.getString("telefono"));
        this.administrador.setCorreo(this.resultSet.getString("correo"));
        this.administrador.setDireccion((this.resultSet.getString("direccion")));
        this.administrador.setIdAlmacen(this.resultSet.getInt("idAlmacen"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.administrador=null;
    }

    @Override
    public Integer insertar(Administrador administrador) {
        this.administrador = administrador;
        Integer idPersona = null;
        Persona persona = new Persona();
        persona.setIdPersona(this.administrador.getIdPersona());
        persona.setDni(this.administrador.getDni());
        persona.setNombreCompleto(this.administrador.getNombreCompleto());
        persona.setTelefono(this.administrador.getTelefono());
        persona.setCorreo(this.administrador.getTelefono());
        persona.setDireccion(this.administrador.getDireccion());
        
        PersonaDAO personaDAO = new PersonaDAOImpl();
        Boolean existePersona = personaDAO.existePersona(persona);
        Boolean existeAdministrador = false;
        
        this.usarTransaccion = false;
        try{
            this.iniciarTransaccion();
            if(!existePersona){
                idPersona = personaDAO.insertar(persona, this.usarTransaccion,this.conexion);
                this.administrador.setIdPersona(idPersona);
            }else{
                idPersona = persona.getIdPersona();
                this.administrador.setIdPersona(idPersona);
                Boolean abreConexion = false;
                existeAdministrador = this.existeAdministrador(administrador, abreConexion);
            }
            if(!existeAdministrador){
                super.insertar();
            }
            this.comitarTransaccion();            
        }catch (Exception ex){
            System.err.println("Error al intentar insertar - " + ex);
            try {
                this.rollbackTransaccion();
            } catch (SQLException ex1) {
                System.err.println("Error al intentar hacer rollback - " + ex1);
            }
        }finally{
            try {
                this.cerrarConexion();
            } catch (SQLException ex) {
                System.err.println("Error al intentar cerrar la conexion - " + ex);
            }
        }
        this.usarTransaccion = true;
        return idPersona;
    }

    @Override
    public Integer modificar(Administrador administrador) {
        Integer retorno = 0;
        this.administrador = administrador;
        Persona persona = new Persona();
        persona.setIdPersona(administrador.getIdPersona());
        persona.setCorreo(administrador.getCorreo());
        persona.setDireccion(administrador.getDireccion());
        persona.setDni(administrador.getDni());
        persona.setNombreCompleto(administrador.getNombreCompleto());
        persona.setTelefono(administrador.getTelefono());
        
        PersonaDAO personaDAO = new PersonaDAOImpl();

        this.usarTransaccion = false;
        try {
            this.iniciarTransaccion();
            personaDAO.modificar(persona, this.usarTransaccion, this.conexion);
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
    public Integer eliminar(Administrador administrador) {
        Integer retorno = 0;
        this.administrador = administrador;
        Persona persona = new Persona();
        persona.setIdPersona(this.administrador.getIdPersona());        

        PersonaDAO personaDAO = new PersonaDAOImpl();
        this.usarTransaccion = false;
        try {
            this.iniciarTransaccion();
            retorno = super.eliminar();
            personaDAO.eliminar(persona, this.usarTransaccion, this.conexion);            
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
    public ArrayList<Administrador> listarTodos() {
        return (ArrayList<Administrador>) super.listarTodos(null);
    }

    @Override
    public Administrador obtenerPorId(Integer idPersona) {
        this.administrador = new Administrador();
        this.administrador.setIdPersona(idPersona);
        super.obtenerPorId();
        return this.administrador;
    }

    @Override
    public Boolean existeAdministrador(Administrador administrador, Boolean abreConexion) {
        this.administrador = administrador;
        Integer idPersona = null;
        try {
            if (abreConexion) {
                this.abrirConexion();
            }
            String sql = "select idPersona from alumno where ";
            sql = sql.concat("idPersona=? ");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.administrador.getIdPersona());
            this.ejecutarConsultaEnBD(sql);
            if (this.resultSet.next()) {
                idPersona = this.resultSet.getInt("idPersona");
            }
        } catch (SQLException ex) {
            System.err.println("Error al consultar si existe alumno - " + ex);
        } finally {
            try {
                if (abreConexion) {
                    this.cerrarConexion();
                }
            } catch (SQLException ex) {
                System.err.println("Error al cerrar la conexión - " + ex);
            }
        }
        return idPersona != null;
    }
    
    @Override
    protected String generarSQLParaListarTodos(Integer limite) {
        String sql = "select ";
        sql = sql.concat(obtenerProyeccionParaSelect());
        sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" alu ");
        sql = sql.concat("join persona per on per.idPersona = alu.idPersona ");
        if (limite != null && limite > 0) {
            sql = sql.concat(" limit ").concat(limite.toString());
        }
        return sql;
    }

    @Override
    public Boolean existeAdministrador(Administrador administrador) {
        Boolean abreConexion = true;
        return existeAdministrador(administrador,abreConexion);
    }
}
