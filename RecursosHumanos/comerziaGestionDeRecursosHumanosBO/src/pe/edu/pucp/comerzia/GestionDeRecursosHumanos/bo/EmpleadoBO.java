/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo;

/**
 *
 * @author chumbi
 */
import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.EmpleadoDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp.EmpleadoDAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;

/*

public class Empleado {

    private Integer idEmpleado;
    private static Integer idCorrelativo = 1;

    private Integer idPersona;
    private EstadoEmpleado estado;
    private String nombreUsuario;
    private String contrasenha;
    private Double salario;
    private Date fechaContratacion;
*/
public class EmpleadoBO {

    private EmpleadoDAO empleadoDAO;

    public EmpleadoBO() {
        this.empleadoDAO = new EmpleadoDAOImpl();
    }

    // public Integer insertar(Integer idEmpleado, String dni, String nombreCompleto, String telefono,
    //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
    //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
    //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa,
    //         TipoEmpleado tipoP, EstadoEmpleado estadoE) {

    //     Empleado empleado = new Empleado();
    //     empleado.setIdEmpleado(idEmpleado);
    //     empleado.setDni(dni);
    //     empleado.setNombreCompleto(nombreCompleto);
    //     empleado.setTelefono(telefono);
    //     empleado.setCorreo(correo);
    //     empleado.setDireccion(direccion);
    //     empleado.setIngresosVentas(ingresosVentas);
    //     empleado.setPorcentajeComision(porcentajeComision);
    //     empleado.setLicenciaMontacarga(licenciaMontacarga);
    //     empleado.setAlmacenAlmacenero(almacenAlmacenero);
    //     empleado.setExperiencia(experiencia);
    //     empleado.setAlmacenAdministrador(almacenAdministrador);
    //     empleado.setEmpresa(empresa);
    //     empleado.setTipoP(tipoP);
    //     empleado.setEstadoE(estadoE);

    //     return empleadoDAO.insertar(empleado);
    // }
    public Integer insertar(Integer idPersona, EstadoEmpleado estado, String nombreUsuario, String contrasenha, Double salario, Date fechaContratacion) {

        Empleado empleado = new Empleado();
        empleado.setIdPersona(idPersona);
        empleado.setEstado(estado);
        empleado.setNombreUsuario(nombreUsuario);
        empleado.setContrasenha(contrasenha);
        empleado.setSalario(salario);
        empleado.setFechaContratacion(fechaContratacion);

        return empleadoDAO.insertar(empleado);
    }

    // public Integer modificar(Integer idEmpleado, String dni, String nombreCompleto, String telefono,
    //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
    //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
    //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa, EstadoEmpleado estadoE) {

    //     Empleado empleado = new Empleado();
    //     empleado.setIdEmpleado(idEmpleado);
    //     empleado.setDni(dni);
    //     empleado.setNombreCompleto(nombreCompleto);
    //     empleado.setTelefono(telefono);
    //     empleado.setCorreo(correo);
    //     empleado.setDireccion(direccion);
    //     empleado.setIngresosVentas(ingresosVentas);
    //     empleado.setPorcentajeComision(porcentajeComision);
    //     empleado.setLicenciaMontacarga(licenciaMontacarga);
    //     empleado.setAlmacenAlmacenero(almacenAlmacenero);
    //     empleado.setExperiencia(experiencia);
    //     empleado.setAlmacenAdministrador(almacenAdministrador);
    //     empleado.setEmpresa(empresa);
    //     empleado.setTipoP(tipoP);
    //     empleado.setEstadoE(estadoE);

    //     return empleadoDAO.modificar(empleado);
    // }
    public Integer modificar(Integer idEmpleado, Integer idPersona, EstadoEmpleado estado, String nombreUsuario, String contrasenha, Double salario, Date fechaContratacion) {

        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        empleado.setIdPersona(idPersona);
        empleado.setEstado(estado);
        empleado.setNombreUsuario(nombreUsuario);
        empleado.setContrasenha(contrasenha);
        empleado.setSalario(salario);
        empleado.setFechaContratacion(fechaContratacion);

        return empleadoDAO.modificar(empleado);
    }

    public Integer eliminar(Empleado empleado) {
        return empleadoDAO.eliminar(empleado);
    }

    public ArrayList<Empleado> listarTodos() {
        return empleadoDAO.listarTodos();
    }

    public Empleado obtenerPorId(Integer id) {
        return empleadoDAO.obtenerPorId(id);
    }

}
