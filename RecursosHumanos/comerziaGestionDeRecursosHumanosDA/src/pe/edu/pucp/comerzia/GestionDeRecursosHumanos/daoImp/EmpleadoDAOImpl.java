package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class EmpleadoDAOImpl extends DAOImpl implements EmpleadoDAO {

    private Empleado empleado;

    public EmpleadoDAOImpl() {
        super("Empleado");
        this.empleado = null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idPersona,estado,nombreUsuario,contrasenha,salario,fechaContratacion";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?,?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1,this.empleado.getIdPersona());
        this.incluirParametroString(2,this.empleado.getEstado().toString());
        this.incluirParametroString(3,this.empleado.getNombreUsuario());
        this.incluirParametroString(4,this.empleado.getContrasenha());
        this.incluirParametroDouble(5,this.empleado.getSalario());
        this.incluirParametroDate(6,this.empleado.getFechaContratacion());
    }

    @Override   
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "estado=?,nombreUsuario=?,contrasenha=?,salario=?,fechaContratacion=?";
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
        this.incluirParametroString(1, this.empleado.getEstado().toString());
        this.incluirParametroString(2, this.empleado.getNombreUsuario());
        this.incluirParametroString(3, this.empleado.getContrasenha());
        this.incluirParametroDouble(4, this.empleado.getSalario());
        this.incluirParametroDate(5, this.empleado.getFechaContratacion());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1, this.empleado.getIdEmpleado());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        String sql = "per.idPersona, per.dni, per.nombreCompleto, per.telefono, per.correo, per.direccion, ";
        sql = sql.concat("em.estado, em.nombreUsuario, em.contrasenha, em.salario, em.fechaContratacion");
        return sql;
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.empleado);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.empleado.getIdEmpleado());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.empleado = new Empleado();
        this.empleado.setIdPersona(this.resultSet.getInt("idPersona"));
        this.empleado.setDni(this.resultSet.getString("dni"));
        this.empleado.setNombreCompleto(this.resultSet.getString("nombreCompleto"));
        this.empleado.setTelefono(this.resultSet.getString("telefono"));
        this.empleado.setCorreo(this.resultSet.getString("correo"));
        this.empleado.setDireccion((this.resultSet.getString("direccion")));
        this.empleado.setIdEmpleado(this.resultSet.getInt("idEmpleado"));
        this.empleado.setEstado(EstadoEmpleado.valueOf(this.resultSet.getString("estado")));
        this.empleado.setNombreUsuario(this.resultSet.getString("nombreUsuario"));
        this.empleado.setSalario(this.resultSet.getDouble("salario"));
        this.empleado.setFechaContratacion(this.resultSet.getDate("fechaContratacion"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.empleado=null;
    }

    @Override
    public Integer insertar(Empleado empleado) {
        this.empleado = empleado;
        Integer idPersona = null;
        Persona persona = new Persona();
        persona.setIdPersona(this.empleado.getIdPersona());
        persona.setDni(this.empleado.getDni());
        persona.setNombreCompleto(this.empleado.getNombreCompleto());
        persona.setTelefono(this.empleado.getTelefono());
        persona.setCorreo(this.empleado.getTelefono());
        persona.setDireccion(this.empleado.getDireccion());
        
        PersonaDAO personaDAO = new PersonaDAOImpl();
        Boolean existePersona = personaDAO.existePersona(persona);
        Boolean existeAdministrador = false;
        
        this.usarTransaccion = false;
        try{
            this.iniciarTransaccion();
            if(!existePersona){
                idPersona = personaDAO.insertar(persona, this.usarTransaccion,this.conexion);
                this.empleado.setIdPersona(idPersona);
            }else{
                idPersona = persona.getIdPersona();
                this.empleado.setIdPersona(idPersona);
                Boolean abreConexion = false;
                existeAdministrador = this.existeEmpleado(empleado, abreConexion);
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
    public Integer modificar(Empleado administrador) {
        Integer retorno = 0;
        this.empleado = administrador;
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
    public Integer eliminar(Empleado empleado) {
        Integer retorno = 0;
        this.empleado = empleado;
        Persona persona = new Persona();
        persona.setIdPersona(this.empleado.getIdPersona());        

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
    public ArrayList<Empleado> listarTodos() {
        return (ArrayList<Empleado>) super.listarTodos(null);
    }

    @Override
    public Empleado obtenerPorId(Integer idPersona) {
        this.empleado = new Empleado();
        this.empleado.setIdPersona(idPersona);
        super.obtenerPorId();
        return this.empleado;
    }

    @Override
    public Boolean existeEmpleado(Empleado administrador, Boolean abreConexion) {
        this.empleado = administrador;
        Integer idPersona = null;
        try {
            if (abreConexion) {
                this.abrirConexion();
            }
            String sql = "select idPersona from Persona where ";
            sql = sql.concat("idPersona=? ");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.empleado.getIdPersona());
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
        sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" em ");
        sql = sql.concat("join persona per on per.idPersona = em.idPersona ");
        if (limite != null && limite > 0) {
            sql = sql.concat(" limit ").concat(limite.toString());
        }
        return sql;
    }

    @Override
    public Boolean existeEmpleado(Empleado empleado) {
        Boolean abreConexion = true;
        return existeEmpleado(empleado,abreConexion);
    }
}
