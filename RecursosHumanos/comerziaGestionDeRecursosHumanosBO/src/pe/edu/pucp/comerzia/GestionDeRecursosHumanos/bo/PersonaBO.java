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
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.daoImp.PersonaDAOImpl;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.dao.PersonaDAO;

/*
public class Persona {

    private Integer idPersona;
    private static Integer idCorrelativo = 1;

    private String dni;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private String direccion;
 */
public class PersonaBO {

    private PersonaDAO personaDAO;

    public PersonaBO() {
        this.personaDAO = new PersonaDAOImpl();
    }

    // public Integer insertar(Integer idPersona, String dni, String nombreCompleto, String telefono,
    //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
    //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
    //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa,
    //         TipoPersona tipoP, EstadoEmpleado estadoE) {
    //     Persona persona = new Persona();
    //     persona.setIdPersona(idPersona);
    //     persona.setDni(dni);
    //     persona.setNombreCompleto(nombreCompleto);
    //     persona.setTelefono(telefono);
    //     persona.setCorreo(correo);
    //     persona.setDireccion(direccion);
    //     persona.setIngresosVentas(ingresosVentas);
    //     persona.setPorcentajeComision(porcentajeComision);
    //     persona.setLicenciaMontacarga(licenciaMontacarga);
    //     persona.setAlmacenAlmacenero(almacenAlmacenero);
    //     persona.setExperiencia(experiencia);
    //     persona.setAlmacenAdministrador(almacenAdministrador);
    //     persona.setEmpresa(empresa);
    //     persona.setTipoP(tipoP);
    //     persona.setEstadoE(estadoE);
    //     return personaDAO.insertar(persona);
    // }
    public Integer insertar(String dni, String nombreCompleto, String telefono,
            String correo, String direccion) {
        Persona persona = new Persona();
        persona.setDni(dni);
        persona.setNombreCompleto(nombreCompleto);
        persona.setTelefono(telefono);
        persona.setCorreo(correo);
        persona.setDireccion(direccion);
        return personaDAO.insertar(persona);
    }

    // public Integer modificar(Integer idPersona, String dni, String nombreCompleto, String telefono,
    //         String correo, String direccion, Double ingresosVentas, Double porcentajeComision,
    //         Boolean licenciaMontacarga, Integer almacenAlmacenero, Integer experiencia,
    //         Integer almacenAdministrador, Integer IdAlmacendministrador, Integer empresa, EstadoEmpleado estadoE) {

    //     Persona persona = new Persona();
    //     persona.setIdPersona(idPersona);
    //     persona.setDni(dni);
    //     persona.setNombreCompleto(nombreCompleto);
    //     persona.setTelefono(telefono);
    //     persona.setCorreo(correo);
    //     persona.setDireccion(direccion);
    //     persona.setIngresosVentas(ingresosVentas);
    //     persona.setPorcentajeComision(porcentajeComision);
    //     persona.setLicenciaMontacarga(licenciaMontacarga);
    //     persona.setAlmacenAlmacenero(almacenAlmacenero);
    //     persona.setExperiencia(experiencia);
    //     persona.setAlmacenAdministrador(almacenAdministrador);
    //     persona.setEmpresa(empresa);
    //     persona.setTipoP(tipoP);
    //     persona.setEstadoE(estadoE);

    //     return personaDAO.modificar(persona);
    // }
    public Integer modificar(Integer idPersona, String dni, String nombreCompleto, String telefono,
            String correo, String direccion) {

        Persona persona = new Persona();
        persona.setIdPersona(idPersona);
        persona.setDni(dni);
        persona.setNombreCompleto(nombreCompleto);
        persona.setTelefono(telefono);
        persona.setCorreo(correo);
        persona.setDireccion(direccion);

        return personaDAO.modificar(persona);
    }

    public Integer eliminar(Persona persona) {
        return personaDAO.eliminar(persona);
    }

    public ArrayList<Persona> listarTodos() {
        return personaDAO.listarTodos();
    }

    public Persona obtenerPorId(Integer id) {
        return personaDAO.obtenerPorId(id);
    }

}
