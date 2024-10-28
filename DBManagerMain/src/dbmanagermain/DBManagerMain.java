package dbmanagermain;

import pe.edu.pucp.comerzia.config.DBManager;
import java.util.ArrayList;
import java.sql.Date;

// Importar las clases BO correspondientes
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.AlmacenBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.bo.ProductoAlmacenadoBO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Producto;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.AdministradorBO;

// Importar otras clases BO según sea necesario
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.PersonaBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Persona;

import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.EmpleadoBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.TrabajadorDeAlmacenBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.bo.VendedorBO;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.Empleado;
import pe.edu.pucp.comerzia.GestionDeRecursosHumanos.model.EstadoEmpleado;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;

import pe.edu.pucp.comerzia.RelacionesComerciales.bo.EmpresaBO;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.ClienteBO;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.ProveedorBO;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.VisitaBO;
import pe.edu.pucp.comerzia.gestioncomercial.bo.DocumentoBO;
import pe.edu.pucp.comerzia.gestioncomercial.bo.LineaDocumentoBO;
import pe.edu.pucp.comerzia.gestioncomercial.model.Estado;
import pe.edu.pucp.comerzia.gestioncomercial.model.Tipo;

import comerziaproveedortest.ProveedorBOTest;
import comerziaclientetest.ClienteBOTest;
import comerziavisitatest.VisitaBOTest;

public class DBManagerMain {

    public static void main(String[] args) {
        DBManager dbManager = DBManager.getInstance();
        System.out.println("Conexión DBManager: " + dbManager);
        ProveedorBOTest.testProveedorBO();
//        ClienteBOTest.testClienteBO();
        //VisitaBOTest.testVisitaBO();
    }
}
