/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerziaclientetest;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Cliente;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.ClienteBO;

public class ClienteBOTest {
    private static ClienteBO clienteBO;
    private static ArrayList<Cliente> listaClientes;
    
    public static void testClienteBO() {
        System.out.println("\ntestClienteBO");
        clienteBO = new ClienteBO();

        ArrayList<Integer> listaId = new ArrayList<>();
        testClienteBOInsertar(listaId);
        testClienteBOListarTodos();
        testClienteBOModificar(listaId);
        testClienteBOListarTodos();
        testClienteBOObtenerPorId(listaId);
        testClienteBOEliminar();
    }

    private static void testClienteBOEliminar() {
        System.out.println("\ntestClienteBOEliminar");
        for (Cliente cliente : listaClientes) {
            clienteBO.eliminar(cliente.getIdEmpresa());        
        }
    }

    private static void testClienteBOObtenerPorId(ArrayList<Integer> listaId) {
        System.out.println("\ntestClienteBOObtenerPorId");
        for (Integer id : listaId) {
            Cliente cliente = clienteBO.obtenerPorId(id);
            System.out.println("idEmpresa: " + cliente.getIdEmpresa()+ " " + cliente.getNombre()+ " " + cliente);
        }
    }

    private static void testClienteBOListarTodos() {
        System.out.println("\ntestClienteBOListarTodos");
        listaClientes = clienteBO.listarTodos();
        for (Cliente cliente : listaClientes) {
            System.out.print(cliente.getIdEmpresa().toString());
            System.out.print(", ");
            System.out.print(cliente.getNombre());
            System.out.print(", ");
            System.out.print(cliente.getDireccion());
            System.out.print(", ");
            System.out.println(cliente.getTelefono());
        }
    }

    private static void testClienteBOModificar(ArrayList<Integer> listaId) {
        System.out.println("\ntestClienteBOModificar");
        Date fechaModificacion = new Date();

        Integer resultado = clienteBO.modificar(listaId.get(0),"ElGranYUnicoAgroWorld", "Ruta Provincial 12, Campo Abierto", "+51 112-345-678", "ventas@agroworld.com", "Agricultura", fechaModificacion, fechaModificacion);
        resultado = clienteBO.modificar(listaId.get(1),"ElGranYUnicoEcoSupplies","Avenida Verde 456, Ciudad Verde", "+51 987-654-321", "info@ecosupplies.com", "Energias renovables", fechaModificacion, fechaModificacion);
    }

    private static void testClienteBOInsertar(ArrayList<Integer> listaId) {
        System.out.println("\ntestClienteBOInsertar");
        int resultado;
        Date fechaCreacion = new Date();
        
        resultado = clienteBO.insertar("AgroWorld", "Ruta Provincial 12, Campo Abierto", "+51 112-345-678", "ventas@agroworld.com", "Agricultura", fechaCreacion, fechaCreacion);
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
        
        resultado = clienteBO.insertar("EcoSupplies","Avenida Verde 456, Ciudad Verde", "+51 987-654-321", "info@ecosupplies.com", "Energias renovables", fechaCreacion, fechaCreacion);
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
    }
}
