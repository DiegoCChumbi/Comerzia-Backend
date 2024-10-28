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
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.TrabajadorDeAlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp.TrabajadorDeAlmacenDAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.TrabajadorDeAlmacen;

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
public class TrabajadorDeAlmacenBO {

    private TrabajadorDeAlmacenDAO trabajadorDeAlmacenDAO;

    public TrabajadorDeAlmacenBO() {
        this.trabajadorDeAlmacenDAO = new TrabajadorDeAlmacenDAOImpl();
    }

    // public Integer insertar(Integer idEmpleado, Integer almacenAlmacenero, Integer experiencia,
    //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa,
    //         TipoTrabajadorDeAlmacen tipoP, EstadoEmpleado estadoE) {

    //     TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();
    //     trabajadorDeAlmacen.setIdTrabajadorDeAlmacen(idTrabajadorDeAlmacen);
    //     trabajadorDeAlmacen.setDni(dni);
    //     trabajadorDeAlmacen.setNombreCompleto(nombreCompleto);
    //     trabajadorDeAlmacen.setTelefono(telefono);
    //     trabajadorDeAlmacen.setCorreo(correo);
    //     trabajadorDeAlmacen.setDireccion(direccion);
    //     trabajadorDeAlmacen.setIngresosVentas(ingresosVentas);
    //     trabajadorDeAlmacen.setPorcentajeComision(porcentajeComision);
    //     trabajadorDeAlmacen.setLicenciaMontacarga(licenciaMontacarga);
    //     trabajadorDeAlmacen.setAlmacenAlmacenero(almacenAlmacenero);
    //     trabajadorDeAlmacen.setExperiencia(experiencia);
    //     trabajadorDeAlmacen.setAlmacenAdministrador(almacenAdministrador);
    //     trabajadorDeAlmacen.setEmpresa(empresa);
    //     trabajadorDeAlmacen.setTipoP(tipoP);
    //     trabajadorDeAlmacen.setEstadoE(estadoE);

    //     return trabajadorDeAlmacenDAO.insertar(trabajadorDeAlmacen);
    // }
    public Integer insertar(Integer idEmpleado, Integer idAlmacen, Boolean licenciaMontacarga) {

        TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();
        trabajadorDeAlmacen.setIdEmpleado(idEmpleado);
        trabajadorDeAlmacen.setIdAlmacen(idAlmacen);
        trabajadorDeAlmacen.setLicenciaMontacarga(licenciaMontacarga);

        return trabajadorDeAlmacenDAO.insertar(trabajadorDeAlmacen);
    }

    // public Integer modificar(Integer idTrabajadorDeAlmacen, String dni, String nombreCompleto, String telefono,
    //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
    //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
    //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa, EstadoEmpleado estadoE) {

    //     TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();
    //     trabajadorDeAlmacen.setIdTrabajadorDeAlmacen(idTrabajadorDeAlmacen);
    //     trabajadorDeAlmacen.setDni(dni);
    //     trabajadorDeAlmacen.setNombreCompleto(nombreCompleto);
    //     trabajadorDeAlmacen.setTelefono(telefono);
    //     trabajadorDeAlmacen.setCorreo(correo);
    //     trabajadorDeAlmacen.setDireccion(direccion);
    //     trabajadorDeAlmacen.setIngresosVentas(ingresosVentas);
    //     trabajadorDeAlmacen.setPorcentajeComision(porcentajeComision);
    //     trabajadorDeAlmacen.setLicenciaMontacarga(licenciaMontacarga);
    //     trabajadorDeAlmacen.setAlmacenAlmacenero(almacenAlmacenero);
    //     trabajadorDeAlmacen.setExperiencia(experiencia);
    //     trabajadorDeAlmacen.setAlmacenAdministrador(almacenAdministrador);
    //     trabajadorDeAlmacen.setEmpresa(empresa);
    //     trabajadorDeAlmacen.setTipoP(tipoP);
    //     trabajadorDeAlmacen.setEstadoE(estadoE);

    //     return trabajadorDeAlmacenDAO.modificar(trabajadorDeAlmacen);
    // }
    public Integer modificar(Integer idTrabajadorDeAlmacen, Integer idEmpleado, Integer idAlmacen, Boolean licenciaMontacarga) {

        TrabajadorDeAlmacen trabajadorDeAlmacen = new TrabajadorDeAlmacen();
        trabajadorDeAlmacen.setIdTrabajadorDeAlmacen(idTrabajadorDeAlmacen);
        trabajadorDeAlmacen.setIdEmpleado(idEmpleado);
        trabajadorDeAlmacen.setIdAlmacen(idAlmacen);
        trabajadorDeAlmacen.setLicenciaMontacarga(licenciaMontacarga);

        return trabajadorDeAlmacenDAO.modificar(trabajadorDeAlmacen);
    }

    public Integer eliminar(TrabajadorDeAlmacen trabajadorDeAlmacen) {
        return trabajadorDeAlmacenDAO.eliminar(trabajadorDeAlmacen);
    }

    public ArrayList<TrabajadorDeAlmacen> listarTodos() {
        return trabajadorDeAlmacenDAO.listarTodos();
    }

    public TrabajadorDeAlmacen obtenerPorId(Integer id) {
        return trabajadorDeAlmacenDAO.obtenerPorId(id);
    }

}
