package edu.uade.apd.tpo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.PosicionDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.EstadoPosicion;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.ItemPedido;
import edu.uade.apd.tpo.model.ItemPosicion;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.model.MotivoEgreso;
import edu.uade.apd.tpo.model.MotivoIngreso;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Pedido;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.model.Stock;
import javafx.geometry.Pos;

public class SistemaDeposito {

    private static SistemaDeposito instance;
    private ArticuloDao articuloDao;
    private PosicionDao posicionDao;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.posicionDao = PosicionDao.getInstance();
    }

    public static SistemaDeposito getInstance() {
        if (instance == null) {
            instance = new SistemaDeposito();
        }
        return instance;
    }

    public Articulo buscarArticulo(Long articuloId) {
        ArticuloEntity art = articuloDao.getInstance().findById(articuloId);
        Articulo articulo = Articulo.fromEntity(art);
        return articulo;
    }

    public void completarPedido(Long pedidoId) {
        Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
        List<ItemPedido> items = pedido.getItems();
        for (ItemPedido itemPedido : items) {
            Articulo articulo = itemPedido.getArticulo();
            int cantidadItem = itemPedido.getCantidad();
            Stock stock = articulo.getStock();
            stock.agregarMovimientoEgreso(MotivoEgreso.VENTA, cantidadItem);
            int index = 0;
            while (cantidadItem > 0) {
                List<Posicion> posiciones = extraerArticulosPosicion(articulo);
                for (Posicion posicion : posiciones) {
                    ItemPosicion itemPosi = new ItemPosicion();
                    itemPosi.setPosicion(posicion);
                    if (cantidadItem < posicion.getCantidad()) {
                        //TODO Buscar lote por posicion
                        //itemPedido.agregarLote(posicion.getLote(), cantidadItem);
                        liberarPosicion(posicion.getCodUbicacion(), cantidadItem);
                    } else {
                        //itemPedido.agregarLote(posicion.getLote(), Posicion.getCAPACIDAD());
                        liberarPosicion(posicion.getCodUbicacion(), Posicion.getCAPACIDAD());
                    }
                    cantidadItem -= posicion.getCantidad();
                }
            }
        }
        pedido.completar();
        pedido.guardar();
    }


    private List<Posicion> extraerArticulosPosicion(Articulo articulo) {
        Date fechaVtoAnterio = articulo.getLotes().get(0).getFechaVto();
        List<Posicion> posiciones = new ArrayList();
        for (Lote lote : articulo.getLotes()) {
            if (fechaVtoAnterio.compareTo(lote.getFechaVto()) < 0) {
                fechaVtoAnterio = lote.getFechaVto();
                posiciones = lote.getPosiciones();
            }
        }
        return posiciones;
    }

    public void ingresarCompra(Long ordenId, List<ItemLote> items) throws BusinessException {
        OrdenCompra ordenCompra = SistemaCompras.getInstance().buscarOrdenCompra(ordenId);
        Articulo articulo = ordenCompra.getArticulo();
        int cantidad = articulo.getCantCompra();
        while (items != null) {
            almacenar(articulo, items, cantidad);
        }
        aceptarOrdenCompra(ordenId);
    }

    public void almacenar(Articulo articulo, List<ItemLote> itemLotes, int cantidad) throws BusinessException {
        List<Lote> lotes = articulo.getLotes();
        Stock stock = articulo.getStock();
        stock.agregarMovimientoIngreso(MotivoIngreso.COMPRA, cantidad);
        articulo.setStock(stock);

        List<PosicionEntity> entities = posicionDao.obtenerObtenerPosicionesVacias();
        List<Posicion> posiciones = new ArrayList<>();

        for (PosicionEntity entity : entities) {
            Posicion posicion = Posicion.fromEntity(entity);
            posiciones.add(posicion);
        }

        if (posiciones.size() * Posicion.getCAPACIDAD() >= cantidad) {
            for (ItemLote item : itemLotes) {
                int cantidadLote = item.getCantidad();
                lotes.add(item.getLote());
                while (cantidadLote > 0) {
                    Posicion posicion = new Posicion();
                    if (item.getLote().getPosiciones() == null) {
                        item.getLote().setPosiciones(new ArrayList<>());
                    }
                    item.getLote().getPosiciones().add(posicion);
                    if (cantidad > posicion.getCAPACIDAD()) {
                        posicion.setCantidad(21);
                        cantidadLote = cantidadLote - posicion.getCAPACIDAD();
                    } else {
                        posicion.setCantidad(cantidadLote);
                        cantidadLote = 0;
                    }
                    posicion.setEstado(EstadoPosicion.OCUPADO);
                    item.guardar();
                }
            }
            articulo.setLotes(lotes);
            articulo.guardar();
        } else {
            throw new BusinessException("No hay lugar suficiente");
        }
    }

    public void aceptarOrdenCompra(Long ordenId) throws BusinessException {
        SistemaCompras.getInstance().aceptarOrdenCompra(ordenId);
    }

    public Posicion buscarPosicionPorUbicacion(String ubicacion) {
        PosicionEntity pe = posicionDao.getInstance().findByUbicacion(ubicacion);
        Posicion pos = Posicion.fromEntity(pe);
        return pos;
    }

    public int liberarPosicion(String codUbicacion, int cantidad) {
        Posicion posicion = buscarPosicionPorUbicacion(codUbicacion);
        int result = 0;
        if (cantidad > Posicion.getCAPACIDAD()) {
            result = Posicion.getCAPACIDAD();
            posicion.liberar(result);
        } else {
            posicion.liberar(cantidad);
            result = cantidad;
        }
        posicion.guardar();
        return result;
    }

    public List<Articulo> obtenerArticulos() {
        List<ArticuloEntity> entities = articuloDao.getInstance().findAll();
        List<Articulo> articulos = new ArrayList<>();

        for (ArticuloEntity entity : entities) {
            Articulo art = Articulo.fromEntity(entity);
            articulos.add(art);
        }
        return articulos;
    }

    public Lote crearLote(String codigo, Date fechaVen, Date fechaElab, Long idArticulo) {
        Lote lote = new Lote();
        lote.setFechaElaboracion(fechaElab);
        lote.setFechaVto(fechaVen);
        lote.setCodigo(codigo);
        lote.guardar();
        return lote;
    }

    public List<Pedido> obtenerPedidosACompletar() {
        return SistemaAdministracion.getInstance().obtenerPedidosACompletar();
    }
    
    public void inicializarPosiciones() throws BusinessException {
    	List<PosicionEntity> posicionesEntity = posicionDao.getInstance().findAll();
    	char[] calles = {'A', 'B', 'C', 'D'};
    	String[] bloques = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};
    	String[] estanterias = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};
    	String[] estantes = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};
    	String[] numero = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10"};
    	if(posicionesEntity.isEmpty()) {
    		for(int i = 0; i <= 3; i++) {
    			for(int j = 0; j <= 9; j++) {
    				for(int k = 0; k <= 9; k++) {
    					for(int t = 0; t <= 9; t++) {
    						for(int h = 0; h <= 9; h++) {
    							Posicion posicion = new Posicion();
    				    		posicion.setEstado(EstadoPosicion.DISPONIBLE);
    				    		String codUbi = "" + calles[i] + bloques[j] + estanterias[k] + estantes[t] + numero[h];
    				    		posicion.setCodUbicacion(codUbi);
    				    		posicion.setCantidad(0);
    				    		posicion.setCalle(calles[i]);
    				    		posicion.setBloque(j);
    				    		posicion.setEstanteria(k);
    				    		posicion.setEstante(t);
    				    		posicion.setNumero(h);
    				    		posicion.guardar();
    						}
    					}
    				}
    			}
    		}
    	}else {
    		throw new BusinessException("Ya fueron inicializadas las posiciones");
    	}
    }
}
