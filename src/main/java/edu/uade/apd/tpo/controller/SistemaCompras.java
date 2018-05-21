package edu.uade.apd.tpo.controller;

import edu.uade.apd.tpo.dao.OrdenCompraDao;
import edu.uade.apd.tpo.dao.ProveedorDao;
import edu.uade.apd.tpo.entity.OrdenCompraEntity;
import edu.uade.apd.tpo.entity.ProveedorEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.EstadoCompra;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Proveedor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class SistemaCompras {

    private static SistemaCompras instance;
    private ProveedorDao proveedorDao;
    private OrdenCompraDao ordenCompraDao;
    private static final Logger logger = LoggerFactory.getLogger(SistemaCompras.class);

    private SistemaCompras() {
        this.proveedorDao = ProveedorDao.getInstance();
        this.ordenCompraDao = OrdenCompraDao.getInstance();
    }

    public static SistemaCompras getInstance() {
        if (instance == null) {
            instance = new SistemaCompras();
        }
        return instance;
    }

    public OrdenCompra generarOrdenCompra(Integer articuloId) throws BusinessException {
        Articulo art = SistemaDeposito.getInstance().buscarArticulo(articuloId);
        OrdenCompra oc = new OrdenCompra(art);
        oc.setFecha(new Date());
        return oc.guardar();
    }

    private OrdenCompra buscarOrdenCompra(Integer ordenId) throws BusinessException {
        OrdenCompraEntity entity = ordenCompraDao.findById(ordenId);
        if (entity == null) {
            throw new BusinessException("La orden de compra '" + ordenId + "' no existe!");
        }
        return OrdenCompra.fromEntity(entity);
    }

    private Proveedor buscarProveedor(Integer proveedorId) throws BusinessException {
        ProveedorEntity entity = proveedorDao.findById(proveedorId);
        if (entity == null) {
            throw new BusinessException("El proveedor con id '" + proveedorId + "' no existe!");
        }
        return Proveedor.fromEntity(entity);
    }

    //TODO Le estoy pasando el id de proveedor, debemos obtenerlo de los ultimos utilizados
    public void procesarOrdenCompra(Integer ordenId, Integer proveedorId) throws BusinessException {
        logger.info("Procesando orden de compra...");
        OrdenCompra oc = this.buscarOrdenCompra(ordenId);
        Proveedor prov = this.buscarProveedor(proveedorId);

        oc.setProveedor(prov);
        oc.ejecutar();
        Articulo art = oc.getArticulo();

      //  SistemaDeposito.getInstance().almacenar(art.getId(), oc.getArticulo().getCantCompra());
      //  art.comprar(art.getCantCompra());
      //  art.guardar();
      //  logger.info("Orden de compra procesada exitosamente!");
    }


}