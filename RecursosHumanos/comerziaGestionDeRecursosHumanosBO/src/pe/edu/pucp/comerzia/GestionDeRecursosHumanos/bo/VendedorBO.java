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
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.VendedorDAO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp.VendedorDAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Vendedor;

/*

public class Vendedor {

    private Integer idVendedor;
    private static Integer idCorrelativo = 1;

    private Integer idEmpleado;
    // private Empleado empleado;

    private Double ingresosVentas;
    private Double porcentajeComision;
 */
public class VendedorBO {

    private VendedorDAO vendedorDAO;

    public VendedorBO() {
        this.vendedorDAO = new VendedorDAOImpl();
    }

    // public Integer insertar(Integer idVendedor, String dni, String nombreCompleto, String telefono,
    //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
    //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
    //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa,
    //         TipoVendedor tipoP, EstadoEmpleado estadoE) {

    //     Vendedor vendedor = new Vendedor();
    //     vendedor.setIdVendedor(idVendedor);
    //     vendedor.setDni(dni);
    //     vendedor.setNombreCompleto(nombreCompleto);
    //     vendedor.setTelefono(telefono);
    //     vendedor.setCorreo(correo);
    //     vendedor.setDireccion(direccion);
    //     vendedor.setIngresosVentas(ingresosVentas);
    //     vendedor.setPorcentajeComision(porcentajeComision);
    //     vendedor.setLicenciaMontacarga(licenciaMontacarga);
    //     vendedor.setAlmacenAlmacenero(almacenAlmacenero);
    //     vendedor.setExperiencia(experiencia);
    //     vendedor.setAlmacenAdministrador(almacenAdministrador);
    //     vendedor.setEmpresa(empresa);
    //     vendedor.setTipoP(tipoP);
    //     vendedor.setEstadoE(estadoE);

    //     return vendedorDAO.insertar(vendedor);
    // }
    public Integer insertar(Integer idEmpleado, Double ingresosVentas, Double porcentajeComision) {

        Vendedor vendedor = new Vendedor();
        vendedor.setIdEmpleado(idEmpleado);
        vendedor.setIngresosVentas(ingresosVentas);
        vendedor.setPorcentajeComision(porcentajeComision);

        return vendedorDAO.insertar(vendedor);
    }

    // public Integer modificar(Integer idVendedor, String dni, String nombreCompleto, String telefono,
    //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
    //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
    //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa, EstadoEmpleado estadoE) {

    //     Vendedor vendedor = new Vendedor();
    //     vendedor.setIdVendedor(idVendedor);
    //     vendedor.setDni(dni);
    //     vendedor.setNombreCompleto(nombreCompleto);
    //     vendedor.setTelefono(telefono);
    //     vendedor.setCorreo(correo);
    //     vendedor.setDireccion(direccion);
    //     vendedor.setIngresosVentas(ingresosVentas);
    //     vendedor.setPorcentajeComision(porcentajeComision);
    //     vendedor.setLicenciaMontacarga(licenciaMontacarga);
    //     vendedor.setAlmacenAlmacenero(almacenAlmacenero);
    //     vendedor.setExperiencia(experiencia);
    //     vendedor.setAlmacenAdministrador(almacenAdministrador);
    //     vendedor.setEmpresa(empresa);
    //     vendedor.setTipoP(tipoP);
    //     vendedor.setEstadoE(estadoE);

    //     return vendedorDAO.modificar(vendedor);
    // }
    public Integer modificar(Integer idVendedor, Integer idEmpleado, Double ingresosVentas, Double porcentajeComision) {

        Vendedor vendedor = new Vendedor();
        vendedor.setIdVendedor(idVendedor);
        vendedor.setIdEmpleado(idEmpleado);
        vendedor.setIngresosVentas(ingresosVentas);
        vendedor.setPorcentajeComision(porcentajeComision);

        return vendedorDAO.modificar(vendedor);
    }

    public Integer eliminar(Vendedor vendedor) {
        return vendedorDAO.eliminar(vendedor);
    }

    public ArrayList<Vendedor> listarTodos() {
        return vendedorDAO.listarTodos();
    }

    public Vendedor obtenerPorId(Integer id) {
        return vendedorDAO.obtenerPorId(id);
    }

}
