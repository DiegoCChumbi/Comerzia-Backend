package pe.edu.pucp.comerzia.GestionDeAlmacen.bo;

import java.util.ArrayList;
import java.util.Date;
import pe.edu.pucp.comerzia.GestionDeAlmacen.dao.ProductoAlmacenadoDAO;
import pe.edu.pucp.comerzia.GestionDeAlmacen.daoImpl.ProductoAlmacenadoDAOImpl;
import pe.edu.pucp.comerzia.GestionDeAlmacen.model.ProductoAlmacenado;

public class ProductoAlmacenadoBO {
    private ProductoAlmacenadoDAO productoAlmacenadoDAO;

    public ProductoAlmacenadoBO() {
        this.productoAlmacenadoDAO = new ProductoAlmacenadoDAOImpl();
    }

    public Integer insertar(Integer idAlmacen, Date fechaAlmacenado, Integer stockActual, Integer idProducto) {
        ProductoBO productoBO = new ProductoBO();
        AlmacenBO almacenBO = new AlmacenBO();
        if(productoBO.obtenerPorId(idProducto)==null || almacenBO.obtenerPorId(idAlmacen)==null)return null;
        ProductoAlmacenado productoAlmacenado = new ProductoAlmacenado(idAlmacen, fechaAlmacenado, stockActual, idProducto);
        return productoAlmacenadoDAO.insertar(productoAlmacenado);
    }

    public Integer modificar(Integer idProductoAlmacenado, Integer idAlmacen, Date fechaAlmacenado, Integer stockActual, Integer idProducto) {
        ProductoAlmacenado productoAlmacenado = new ProductoAlmacenado(idProductoAlmacenado, idAlmacen, fechaAlmacenado, stockActual, idProducto);
        return productoAlmacenadoDAO.modificar(productoAlmacenado);
    }

    public Integer eliminar(ProductoAlmacenado productoAlmacenado) {
        return productoAlmacenadoDAO.eliminar(productoAlmacenado);
    }

    public ArrayList<ProductoAlmacenado> listarTodos() {
        return this.productoAlmacenadoDAO.listarTodos();
    }

    public ProductoAlmacenado obtenerPorId(Integer idProductoAlmacenado) {
        return this.productoAlmacenadoDAO.obtenerPorId(idProductoAlmacenado);
    }
}
