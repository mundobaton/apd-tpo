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

    public void completarPedido(Long pedidoId) throws BusinessException{
        Pedido pedido = SistemaAdministracion.getInstance().buscarPedido(pedidoId);
        List<ItemPedido> items = pedido.getItems();
        for (ItemPedido itemPedido : items) {
            Articulo articulo = itemPedido.getArticulo();
            int cantidadItem = itemPedido.getCantidad();
            Stock stock = articulo.getStock();
            stock.agregarMovimientoEgreso(MotivoEgreso.VENTA, cantidadItem);
            int index = 0;
            while (cantidadItem > 0) {
                Lote lote = extraerLoteMenorFechaVencimiento(articulo);
                for (Posicion posicion : lote.getPosiciones()) {
                    ItemPosicion itemPosi = new ItemPosicion();
                    itemPosi.setPosicion(posicion);
                    if (cantidadItem < posicion.getCantidad()) {
                        itemPedido.agregarLote(lote, cantidadItem);
                        liberarPosicion(posicion.getCodUbicacion(), cantidadItem);
                    } else {
                        itemPedido.agregarLote(lote, Posicion.getCAPACIDAD());
                        liberarPosicion(posicion.getCodUbicacion(), Posicion.getCAPACIDAD());
                    }
                    cantidadItem -= posicion.getCantidad();
                }
            }
        }
        pedido.completar();
        pedido.guardar();
    }

    //Me da el lote del articulo con fecha de vencimiento mas proxima
    private Lote extraerLoteMenorFechaVencimiento(Articulo articulo) throws BusinessException{
        if(articulo.getLotes().isEmpty()) throw new BusinessException("No hay lotes cargados para el articulo");
        Date fechaVtoAnterio = articulo.getLotes().get(0).getFechaVto();
        Lote loteMenorFecha = new Lote();
        for (Lote lote : articulo.getLotes()) {
            if (fechaVtoAnterio.compareTo(lote.getFechaVto()) < 0) {
                fechaVtoAnterio = lote.getFechaVto();
                loteMenorFecha = lote;
            }
        }
        return loteMenorFecha;
    }

    public void ingresarCompra(Long ordenId, List<ItemLote> items) throws BusinessException {
        OrdenCompra ordenCompra = SistemaCompras.getInstance().buscarOrdenCompra(ordenId);
        Articulo articulo = ordenCompra.getArticulo();
        int cantidad = articulo.getCantCompra();
        almacenar(articulo, items, cantidad);
        aceptarOrdenCompra(ordenId);
    }
    
    public List<PosicionEntity> obtenerPosicionesVacias(int cantidad){
    	return posicionDao.obtenerObtenerPosicionesVacias(cantidad);
    }

    public void almacenar(Articulo articulo, List<ItemLote> itemLotes, int cantidad) throws BusinessException {
        List<Lote> lotes = articulo.getLotes();
        Stock stock = articulo.getStock();
        stock.agregarMovimientoIngreso(MotivoIngreso.COMPRA, cantidad);
        articulo.setStock(stock);
        
        int cantidadDePosiciones = (cantidad / Posicion.getCAPACIDAD()) + 1;
        List<PosicionEntity> entities = obtenerPosicionesVacias(cantidadDePosiciones);
        
        List<Posicion> posiciones = new ArrayList<>();

        for (PosicionEntity entity : entities) {
            Posicion posicion = Posicion.fromEntity(entity);
            posiciones.add(posicion);
        }
        int index = 0;
        if (posiciones.size() * Posicion.getCAPACIDAD() >= cantidad) {
            for (ItemLote item : itemLotes) {
            	int cantidadLote = item.getCantidad();
                lotes.add(item.getLote());
                while (cantidadLote > 0) {
                    Posicion pos = posiciones.get(index);
                    if (item.getLote().getPosiciones() == null) {
                        item.getLote().setPosiciones(new ArrayList<>());
                    }
                    if (cantidadLote > Posicion.getCAPACIDAD()) {
                    	pos.setCantidad(Posicion.getCAPACIDAD());
                        cantidadLote = cantidadLote - Posicion.getCAPACIDAD();
                    } else {
                        pos.setCantidad(cantidadLote);
                        cantidadLote = 0;
                    }
                    pos.setEstado(EstadoPosicion.OCUPADO);
                    pos.guardar();
                    item.getLote().getPosiciones().add(pos);
                    item.guardar();
                    index++;
                }
                index = 0;
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
    	char[] calles = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    	String[] bloques = {"01", "02", "03"};
    	String[] estanterias = {"01", "02", "03", "04", "05"};
    	String[] estantes = {"01", "02", "03", "04", "05", "06"};
    	String[] numero = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
    	if(posicionesEntity.isEmpty()) {
    		for(int i = 0; i <= 6; i++) {
    			for(int j = 0; j <= 2; j++) {
    				for(int k = 0; k <= 4; k++) {
    					for(int t = 0; t <= 5; t++) {
    						for(int h = 0; h <= 20; h++) {
    							Posicion posicion = new Posicion();
    				    		posicion.setEstado(EstadoPosicion.DISPONIBLE);
    				    		String codUbi = "" + calles[i] + bloques[j] + estanterias[k] + estantes[t] + numero[h];
    				    		posicion.setCodUbicacion(codUbi);
    				    		posicion.setCantidad(0);
    				    		posicion.setCalle(calles[i]);
    				    		posicion.setBloque(j + 1);
    				    		posicion.setEstanteria(k + 1);
    				    		posicion.setEstante(t + 1);
    				    		posicion.setNumero(h + 1);
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
