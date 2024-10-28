package pe.edu.pucp.comerzia.GestionDeAlmacen.bo;

import java.util.ArrayList;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.AlmacenDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl.AlmacenDAOImpl;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.Almacen;

public class AlmacenBO {
    private AlmacenDAO almacenDAO;

    public AlmacenBO() {
        this.almacenDAO = new AlmacenDAOImpl();
    }

    public Integer insertar(String nombre, String estado, String descripcion) {
        Almacen almacen = new Almacen(nombre, estado, descripcion);
        return almacenDAO.insertar(almacen);
    }

    public Integer modificar(Integer idAlmacen, String nombre, String estado, String descripcion) {
        Almacen almacen = new Almacen(idAlmacen, nombre, estado, descripcion);
        return almacenDAO.modificar(almacen);
    }

    public Integer eliminar(Almacen almacen) {
        return almacenDAO.eliminar(almacen);
    }


    public ArrayList<Almacen> listarTodos() {
        return this.almacenDAO.listarTodos();
    }

    public Almacen obtenerPorId(Integer idAlmacen) {
        return this.almacenDAO.obtenerPorId(idAlmacen);
    }
}