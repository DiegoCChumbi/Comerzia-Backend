/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package comerziavisitatest;

import java.util.ArrayList;
import java.util.Date;
import java.time.Duration;
import pe.edu.pucp.comerzia.RelacionesComerciales.Model.Visita;
import pe.edu.pucp.comerzia.RelacionesComerciales.bo.VisitaBO;

public class VisitaBOTest {
    private static VisitaBO visitaBO;
    private static ArrayList<Visita> listaVisitaes;
    
    public static void testVisitaBO() {
        System.out.println("\ntestVisitaBO");
        visitaBO = new VisitaBO();

        ArrayList<Integer> listaId = new ArrayList<>();
        testVisitaBOInsertar(listaId);
        testVisitaBOListarTodos();
        testVisitaBOModificar(listaId);
        testVisitaBOListarTodos();
        testVisitaBOObtenerPorId(listaId);
        testVisitaBOEliminar();
    }

    private static void testVisitaBOEliminar() {
        System.out.println("\ntestVisitaBOEliminar");
        for (Visita visita : listaVisitaes) {
            visitaBO.eliminar(visita.getIdVisita());        
        }
    }

    private static void testVisitaBOObtenerPorId(ArrayList<Integer> listaId) {
        System.out.println("\ntestVisitaBOObtenerPorId");
        for (Integer id : listaId) {
            Visita visita = visitaBO.obtenerPorId(id);
            System.out.println("idVisita: " + visita.getIdVisita()+ " " + visita.getIdCliente()+ " " + visita.getIdVendedor());
        }
    }

    private static void testVisitaBOListarTodos() {
        System.out.println("\ntestVisitaBOListarTodos");
        listaVisitaes = visitaBO.listarTodos();
        for (Visita visita : listaVisitaes) {
            System.out.print(visita.getIdVisita().toString());
            System.out.print(", ");
            System.out.print(visita.getDuracion());
            System.out.print(", ");
            System.out.print(visita.getIdCliente());
            System.out.print(", ");
            System.out.println(visita.getIdVendedor());
        }
    }

    private static void testVisitaBOModificar(ArrayList<Integer> listaId) {
        System.out.println("\ntestVisitaBOModificar");
        Date fechaModificacion = new Date();
        //Duration duracion = Duration.ofHours(1).plusMinutes(30);
        Double duracion = 1.5;
        Integer resultado = visitaBO.modificar(listaId.get(0),fechaModificacion,duracion,9,1);
        resultado = visitaBO.modificar(listaId.get(1),fechaModificacion,duracion,10,1);
    }

    private static void testVisitaBOInsertar(ArrayList<Integer> listaId) {
        System.out.println("\ntestVisitaBOInsertar");
        int resultado;
        Date fechaCreacion = new Date();
        //Duration duracion = Duration.ofHours(2).plusMinutes(30);
        Double duracion = 2.5;
        
        resultado = visitaBO.insertar(fechaCreacion,duracion,51,51);
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
        
        resultado = visitaBO.insertar(fechaCreacion,duracion,51,51);
        System.out.println("Llave primaria insertada: " + resultado);
        listaId.add(resultado);
    }
}
