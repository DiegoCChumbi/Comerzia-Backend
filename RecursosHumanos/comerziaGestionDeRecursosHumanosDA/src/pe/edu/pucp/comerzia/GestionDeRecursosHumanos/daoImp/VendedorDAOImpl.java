package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.VendedorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;
import pe.edu.pucp.comerzia.db.DAOImpl;

public class VendedorDAOImpl extends DAOImpl implements VendedorDAO {

    private Vendedor vendedor;

    public VendedorDAOImpl() {
        super("Vendedor");
        this.vendedor = null;
    }

    @Override
    protected String obtenerListaDeAtributosParaInsercion() {
        return "idEmpleado,ingresosVentas,porcentajeComision";
    }

    @Override
    protected String incluirListaDeParametrosParaInsercion() {
        return "?,?,?";
    }

    @Override
    protected void incluirValorDeParametrosParaInsercion() throws SQLException {
        this.incluirParametroInt(1,this.vendedor.getIdEmpleado());
        this.incluirParametroDouble(2,this.vendedor.getIngresosVentas());
        this.incluirParametroDouble(3,this.vendedor.getPorcentajeComision());
    }

    @Override
    protected String obtenerListaDeValoresYAtributosParaModificacion() {
        return "idEmpleado=?,ingresosVentas=?,porcentajeComision=?";
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
        this.incluirParametroInt(1, this.vendedor.getIdVendedor());
    }

    @Override
    protected void incluirValorDeParametrosParaEliminacion() throws SQLException {
        this.incluirParametroInt(1,this.vendedor.getIdVendedor());
    }

    @Override
    protected String obtenerProyeccionParaSelect() {
        String sql = "per.idPersona, per.dni, per.nombreCompleto, per.telefono, per.correo, per.direccion, ";
        sql = sql.concat("ve.idVendedor,ve.idEmpleado,ve.ingresosVentas,ve.porcentajeComision");
        return sql;
    }

    @Override
    protected void agregarObjetoALaLista(List lista, ResultSet resultSet) throws SQLException {
        instanciarObjetoDelResultSet();
        lista.add(this.vendedor);
    }

    @Override
    protected void incluirValorDeParametrosParaObtenerPorId() throws SQLException {
        this.incluirParametroInt(1, this.vendedor.getIdVendedor());
    }

    @Override
    protected void instanciarObjetoDelResultSet() throws SQLException {
        this.vendedor = new Vendedor();
        this.vendedor.setIdPersona(this.resultSet.getInt("idPersona"));
        this.vendedor.setDni(this.resultSet.getString("dni"));
        this.vendedor.setNombreCompleto(this.resultSet.getString("nombreCompleto"));
        this.vendedor.setTelefono(this.resultSet.getString("telefono"));
        this.vendedor.setCorreo(this.resultSet.getString("correo"));
        this.vendedor.setDireccion((this.resultSet.getString("direccion")));
        this.vendedor.setIdVendedor(this.resultSet.getInt("idVendedor"));
        this.vendedor.setIdEmpleado(this.resultSet.getInt("idEmpleado"));
        this.vendedor.setIngresosVentas(this.resultSet.getDouble("ingresosVentas"));
        this.vendedor.setPorcentajeComision(this.resultSet.getDouble("porcentajeComision"));
    }

    @Override
    protected void limpiarObjetoDelResultSet() {
        this.vendedor=null;
    }

    @Override
    public Integer insertar(Vendedor vendedor) {
        this.vendedor = vendedor;
        Integer idPersona = null;
        Persona persona = new Persona();
        persona.setIdPersona(this.vendedor.getIdPersona());
        persona.setDni(this.vendedor.getDni());
        persona.setNombreCompleto(this.vendedor.getNombreCompleto());
        persona.setTelefono(this.vendedor.getTelefono());
        persona.setCorreo(this.vendedor.getTelefono());
        persona.setDireccion(this.vendedor.getDireccion());
        
        PersonaDAO personaDAO = new PersonaDAOImpl();
        Boolean existePersona = personaDAO.existePersona(persona);
        Boolean existeVendedor = false;
        
        this.usarTransaccion = false;
        try{
            this.iniciarTransaccion();
            if(!existePersona){
                idPersona = personaDAO.insertar(persona, this.usarTransaccion,this.conexion);
                this.vendedor.setIdPersona(idPersona);
            }else{
                idPersona = persona.getIdPersona();
                this.vendedor.setIdPersona(idPersona);
                Boolean abreConexion = false;
                existeVendedor = this.existeVendedor(vendedor, abreConexion);
            }
            if(!existeVendedor){
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
    public Integer modificar(Vendedor vendedor) {
        Integer retorno = 0;
        this.vendedor = vendedor;
        Persona persona = new Persona();
        persona.setIdPersona(vendedor.getIdPersona());
        persona.setCorreo(vendedor.getCorreo());
        persona.setDireccion(vendedor.getDireccion());
        persona.setDni(vendedor.getDni());
        persona.setNombreCompleto(vendedor.getNombreCompleto());
        persona.setTelefono(vendedor.getTelefono());
        
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
    public Integer eliminar(Vendedor vendedor) {
        Integer retorno = 0;
        this.vendedor = vendedor;
        Persona persona = new Persona();
        persona.setIdPersona(this.vendedor.getIdPersona());        

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
    public ArrayList<Vendedor> listarTodos() {
        return (ArrayList<Vendedor>) super.listarTodos(null);
    }

    @Override
    public Vendedor obtenerPorId(Integer idPersona) {
        this.vendedor = new Vendedor();
        this.vendedor.setIdPersona(idPersona);
        super.obtenerPorId();
        return this.vendedor;
    }

    @Override
    public Boolean existeVendedor(Vendedor vendedor, Boolean abreConexion) {
        this.vendedor = vendedor;
        Integer idPersona = null;
        try {
            if (abreConexion) {
                this.abrirConexion();
            }
            String sql = "select idPersona from alumno where ";
            sql = sql.concat("idPersona=? ");
            this.colocarSQLenStatement(sql);
            this.incluirParametroInt(1, this.vendedor.getIdPersona());
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
        sql = sql.concat(" from ").concat(this.nombre_tabla).concat(" ve ");
        sql = sql.concat("join persona per on per.idPersona = ve.idPersona ");
        if (limite != null && limite > 0) {
            sql = sql.concat(" limit ").concat(limite.toString());
        }
        return sql;
    }

    @Override
    public Boolean existeVendedor(Vendedor administrador) {
        Boolean abreConexion = true;
        return existeVendedor(administrador,abreConexion);
    }
}
