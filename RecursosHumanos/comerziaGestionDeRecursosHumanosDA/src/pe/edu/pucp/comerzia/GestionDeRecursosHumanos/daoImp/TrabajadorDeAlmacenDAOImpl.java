package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.TrabajadorDeAlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;
import pe.edu.pucp.comerzia.db.DAOImpl;

/*
public class TrabajadorDeAlmacen {

    private Integer idTrabajadorDeAlmacen;
    private static Integer idCorrelativo = 1;

    private Integer idEmpleado;
    // private Empleado empleado;
    private Integer idAlmacen;
    // private Almacen almacenDeTrabajo;

    private boolean licenciaMontacarga;
 */
public class TrabajadorDeAlmacenDAOImpl extends DAOImpl implements TrabajadorDeAlmacenDAO {

    private TrabajadorDeAlmacen trabajador;

    public TrabajadorDeAlmacenDAOImpl() {
        super("TrabajadorDeAlmacen");
        this.trabajador = null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idEmpleado,idAlmacen,licenciaMontacarga";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1,this.trabajador.getIdEmpleado());
        this.incluirParametroInt(2,this.trabajador.getIdAlmacen());
        this.incluirParametroBoolean(3, this.trabajador.isLicenciaMontacarga());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "idEmpleado=?,idAlmacen=?,licenciaMontacarga=?";
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
        this.incluirParametroInt(1, this.trabajador.getIdTrabajadorDeAlmacen());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1,this.trabajador.getIdPersona());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        String sql = "per.idPersona, per.dni, per.nombreCompleto, per.telefono, per.correo, per.direccion, ";
        sql = sql.concat("tra.idTrabajadorDeAlmacen, tra.idEmpleado, tra.idAlmacen, tra.licenciaMontacarga");
        return sql;
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.trabajador);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.trabajador.getIdTrabajadorDeAlmacen());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.trabajador = new TrabajadorDeAlmacen();
        this.trabajador.setIdPersona(this.resultSet.getInt("idPersona"));
        this.trabajador.setDni(this.resultSet.getString("dni"));
        this.trabajador.setNombreCompleto(this.resultSet.getString("nombreCompleto"));
        this.trabajador.setTelefono(this.resultSet.getString("telefono"));
        this.trabajador.setCorreo(this.resultSet.getString("correo"));
        this.trabajador.setDireccion((this.resultSet.getString("direccion")));
        this.trabajador.setIdTrabajadorDeAlmacen(this.resultSet.getInt("idTrabajadorDeAlmacen"));
        this.trabajador.setIdEmpleado(this.resultSet.getInt("idEmpleado"));
        this.trabajador.setIdAlmacen(this.resultSet.getInt("idAlmacen"));
        this.trabajador.setLicenciaMontacarga(this.resultSet.getBoolean("licenciaMontacarga"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.trabajador=null;
    }

    @Override
    public Integer insertar(TrabajadorDeAlmacen trabajador) {
        this.trabajador = trabajador;
        Integer idPersona = null;
        Persona persona = new Persona();
        persona.setIdPersona(this.trabajador.getIdPersona());
        persona.setDni(this.trabajador.getDni());
        persona.setNombreCompleto(this.trabajador.getNombreCompleto());
        persona.setTelefono(this.trabajador.getTelefono());
        persona.setCorreo(this.trabajador.getTelefono());
        persona.setDireccion(this.trabajador.getDireccion());
        
        PersonaDAO personaDAO = new PersonaDAOImpl();
        Boolean existePersona = personaDAO.existePersona(persona);
        Boolean existeTrabajador = false;
        
        this.usarTransaccion = false;
        try{
            this.iniciarTransaccion();
            if(!existePersona){
                idPersona = personaDAO.insertar(persona, this.usarTransaccion,this.conexion);
                this.trabajador.setIdPersona(idPersona);
            }else{
                idPersona = persona.getIdPersona();
                this.trabajador.setIdPersona(idPersona);
                Boolean abreConexion = false;
                existeTrabajador = this.existeTrabajadorDeAlmacen(trabajador, abreConexion);
            }
            if(!existeTrabajador){
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
    public Integer modificar(TrabajadorDeAlmacen trabajador) {
        Integer retorno = 0;
        this.trabajador = trabajador;
        Persona persona = new Persona();
        persona.setIdPersona(trabajador.getIdPersona());
        persona.setCorreo(trabajador.getCorreo());
        persona.setDireccion(trabajador.getDireccion());
        persona.setDni(trabajador.getDni());
        persona.setNombreCompleto(trabajador.getNombreCompleto());
        persona.setTelefono(trabajador.getTelefono());
        
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
    public Integer eliminar(TrabajadorDeAlmacen trabajador) {
        Integer retorno = 0;
        this.trabajador = trabajador;
        Persona persona = new Persona();
        persona.setIdPersona(this.trabajador.getIdPersona());        

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
    public ArrayList<TrabajadorDeAlmacen> listarTodos() {
        return (ArrayList<TrabajadorDeAlmacen>) super.listarTodos(null);
    }

    @Override
    public TrabajadorDeAlmacen obtenerPorId(Integer idPersona) {
        this.trabajador = new TrabajadorDeAlmacen();
        this.trabajador.setIdPersona(idPersona);
        super.obtenerPorId();
        return this.trabajador;
    }

    @Override
    public Boolean existeTrabajadorDeAlmacen(TrabajadorDeAlmacen trabajador, Boolean abreConexion) {
        this.trabajador = trabajador;
        Integer idPersona = null;
        try {
            if (abreConexion) {
                this.abrirConexion();
            }
            String sql = "select idPersona from alumno where ";
            sql = sql.concat("idPersona=? ");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.trabajador.getIdPersona());
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
        sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" tra ");
        sql = sql.concat("join persona per on per.idPersona = tra.idPersona ");
        if (limite != null && limite > 0) {
            sql = sql.concat(" limit ").concat(limite.toString());
        }
        return sql;
    }

    @Override
    public Boolean existeTrabajadorDeAlmacen(TrabajadorDeAlmacen administrador) {
        Boolean abreConexion = true;
        return existeTrabajadorDeAlmacen(trabajador,abreConexion);
    }
}
