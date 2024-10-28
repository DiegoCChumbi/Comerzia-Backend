/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerziaproveedortest;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Proveedor;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.ProveedorBO;

public class ProveedorBOTest {
    private static ProveedorBO proveedorBO;
    private static ArrayList<Proveedor> listaProveedores;
    
    public static void testProveedorBO() {
        System.out.println("\ntestProveedorBO");
        proveedorBO = new ProveedorBO();

        ArrayList<Integer> listaId = new ArrayList<>();
        testProveedorBOInsertar(listaId);
        testProveedorBOListarTodos();
        testProveedorBOModificar(listaId);
        testProveedorBOListarTodos();
        testProveedorBOObtenerPorId(listaId);
        testProveedorBOEliminar();
    }

    private static void testProveedorBOEliminar() {
        System.out.println("\ntestProveedorBOEliminar");
        for (Proveedor proveedor : listaProveedores) {
            proveedorBO.eliminar(proveedor.getIdEmpresa());        
        }
    }

    private static void testProveedorBOObtenerPorId(ArrayList<Integer> listaId) {
        System.out.println("\ntestProveedorBOObtenerPorId");
        for (Integer id : listaId) {
            Proveedor proveedor = proveedorBO.obtenerPorId(id);
            System.out.println("idEmpresa: " + proveedor.getIdEmpresa()+ " " + proveedor.getNombre()+ " " + proveedor);
        }
    }

    private static void testProveedorBOListarTodos() {
        System.out.println("\ntestProveedorBOListarTodos");
        listaProveedores = proveedorBO.listarTodos();
        for (Proveedor proveedor : listaProveedores) {
            System.out.print(proveedor.getIdEmpresa().toString());
            System.out.print(", ");
            System.out.print(proveedor.getNombre());
            System.out.print(", ");
            System.out.print(proveedor.getDireccion());
            System.out.print(", ");
            System.out.print(proveedor.getTelefono());
            System.out.print(", ");
            System.out.print(proveedor.getPais());
            System.out.print(", ");
            System.out.println(proveedor.getCalificacion());
        }
    }

    private static void testProveedorBOModificar(ArrayList<Integer> listaId) {
        System.out.println("\ntestProveedorBOModificar");
        Date fechaModificacion = new Date();

        Integer resultado = proveedorBO.modificar(listaId.get(0),"ElTechSolutions", "Calle Innovación 123, Ciudad de la Tecnología", "+51 123-456-789", "contacto@techsolutions.com", "Tecnología", fechaModificacion, "12345678901", "TechSolutions S.A.", 5.0, "Peru");
        resultado = proveedorBO.modificar(listaId.get(1),"ElEcoSupplies","Avenida Verde 456, Ciudad Verde", "+51 987-654-321", "info@ecosupplies.com", "Energias renovables", fechaModificacion, "10987654321", "EcoSupplies Ltd.", 5.0, "Peru");
    }

    private static void testProveedorBOInsertar(ArrayList<Integer> listaId) {
        System.out.println("\ntestProveedorBOInsertar");
        int resultado;
        Date fechaCreacion = new Date();
        
        resultado = proveedorBO.insertar("TechSolutions", "Calle Innovación 123, Ciudad de la Tecnología", "+51 123-456-789", "contacto@techsolutions.com", "Tecnología", fechaCreacion, "12345678901", "TechSolutions S.A.", 4.5, "Peru");
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
        
        resultado = proveedorBO.insertar("EcoSupplies","Avenida Verde 456, Ciudad Verde", "+51 987-654-321", "info@ecosupplies.com", "Energias renovables", fechaCreacion, "10987654321", "EcoSupplies Ltd.", 4.8, "Peru");
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
    }
}
