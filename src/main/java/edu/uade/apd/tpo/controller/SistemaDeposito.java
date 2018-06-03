package edu.uade.apd.tpo.controller;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.PosicionDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.*;

public class SistemaDeposito {

    private static SistemaDeposito instance;
    private ArticuloDao articuloDao;
    private PosicionDao posicionDao;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.posicionDao = PosicionDao.getInstance();
    }

=======

import edu.uade.apd.tpo.dao.impl.ArticuloDao;
import edu.uade.apd.tpo.dao.impl.ItemLoteDao;
import edu.uade.apd.tpo.dao.impl.OrdenCompraDao;
import edu.uade.apd.tpo.dao.impl.PosicionDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.Lote;
import edu.uade.apd.tpo.model.MotivoIngreso;
import edu.uade.apd.tpo.model.OrdenCompra;
import edu.uade.apd.tpo.model.Posicion;
import edu.uade.apd.tpo.model.Stock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SistemaDeposito {

    private ArticuloDao articuloDao;
    private ItemLoteDao itemLoteDao;
    private PosicionDao posicionDao;
    private OrdenCompraDao ordenCompraDao;

    private static SistemaDeposito instance;

    private SistemaDeposito() {
        this.articuloDao = ArticuloDao.getInstance();
        this.itemLoteDao = ItemLoteDao.getInstance();
        this.posicionDao = PosicionDao.getInstance();
        this.ordenCompraDao = OrdenCompraDao.getInstance();
    }
    
>>>>>>> develop
    public static SistemaDeposito getInstance() {
        if (instance == null) {
            instance = new SistemaDeposito();
        }
        return instance;
    }
<<<<<<< HEAD

    public Articulo buscarArticulo(Long articuloId) {
        ArticuloEntity art = articuloDao.findById(articuloId);
        Articulo articulo = Articulo.fromEntity(art);
        return articulo;
    }

    public void completarPedido(Long pedidoId) throws BusinessException {
        Cliente cli = SistemaAdministracion.getInstance().obtenerClientePorPedido(pedidoId);
        if (cli == null) throw new BusinessException("No existe ningún cliente asociado al pedido.");
        Pedido pedido = cli.obtenerPedido(pedidoId);
        if (pedido == null) throw new BusinessException("Pedido no encontrado.");
        List<ItemPedido> items = pedido.getItems();
        if (items == null) throw new BusinessException("Pedido vacio.");
        for (ItemPedido itemPedido : items) {
            Articulo articulo = itemPedido.getArticulo();
            int cantidadItem = itemPedido.getCantidad();
            Stock stock = articulo.getStock();
            stock.agregarMovimientoEgreso(MotivoEgreso.VENTA, cantidadItem);
            int index = 0;
            boolean continuar = true;
            while (cantidadItem > 0 && continuar) {
                continuar = false;
                Lote lote = extraerLoteMenorFechaVencimiento(articulo);
                if (lote.getPosiciones() != null) {
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
                        continuar = true;
                        cantidadItem -= posicion.getCantidad();
                    }
                }
            }
        }
        pedido.completar();
        cli.guardar();
    }

    //Me da el lote del articulo con fecha de vencimiento mas proxima
    private Lote extraerLoteMenorFechaVencimiento(Articulo articulo) throws BusinessException {
        if (articulo.getLotes().isEmpty()) throw new BusinessException("No hay lotes cargados para el articulo");
        Date fechaVtoAnterior = articulo.getLotes().get(0).getFechaVto();
        Lote loteMenorFecha = new Lote();
        for (Lote lote : articulo.getLotes()) {
            if (fechaVtoAnterior.compareTo(lote.getFechaVto()) < 0) {
                fechaVtoAnterior = lote.getFechaVto();
                loteMenorFecha = lote;
            }
        }
        return loteMenorFecha;
    }

    public void ingresarCompra(Long ordenId, List<ItemLote> lotesRecibidos) throws BusinessException {
        OrdenCompra ordenCompra = SistemaCompras.getInstance().buscarOrdenCompra(ordenId);
        if(ordenCompra == null) throw new BusinessException("No se ha encontrado la orden de compra.");
        almacenar(ordenCompra.getArticulo().getId(), lotesRecibidos, ordenCompra.getArticulo().getCantCompra());
        aceptarOrdenCompra(ordenId);
        //Disparar la verificacion de pedidos pendientes
        SistemaAdministracion.getInstance().procesarPedidosPendientesCompraIngresada();
    }

    public List<Posicion> obtenerPosicionesVacias(int cantidad) {
        return posicionDao.obtenerObtenerPosicionesVacias(cantidad).parallelStream().map(po -> Posicion.fromEntity(po)).collect(Collectors.toList());
    }

    public void almacenar(Long articuloId, List<ItemLote> lotesRecibidos, int cantidad) throws BusinessException {

        Articulo art = buscarArticulo(articuloId);
        if(art == null) throw new BusinessException("Articulo no existente en el deposito...");

        Stock stock = art.getStock();
        stock.agregarMovimientoIngreso(MotivoIngreso.COMPRA, cantidad);
        art.setStock(stock);

        List<Lote> lotes = new ArrayList<>();
        int cantidadDePosiciones = (cantidad <= Posicion.getCAPACIDAD()) ? 1 : (cantidad / Posicion.getCAPACIDAD()) + 1;

        List<Posicion> posiciones = obtenerPosicionesVacias(cantidadDePosiciones);
        if(posiciones == null)throw new BusinessException("No hay posiciones vacias.");

        int index = 0;
        if (posiciones.size() * Posicion.getCAPACIDAD() >= cantidad) {
            for (ItemLote item : lotesRecibidos) {
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
            art.getLotes().addAll(lotes);
            art.guardar();
        } else {
            throw new BusinessException("No hay lugar suficiente.");
        }
    }

    public void aceptarOrdenCompra(Long ordenId) throws BusinessException {
        SistemaCompras.getInstance().aceptarOrdenCompra(ordenId);
    }

    public Posicion buscarPosicionPorUbicacion(String ubicacion) {
        PosicionEntity pe = posicionDao.findByUbicacion(ubicacion);
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
        List<ArticuloEntity> entities = articuloDao.findAll();
        List<Articulo> articulos = new ArrayList<>();

        for (ArticuloEntity entity : entities) {
            Articulo art = Articulo.fromEntity(entity);
            articulos.add(art);
        }
        return articulos;
    }

    public Lote crearLote(String codigo, Date fechaVen, Date fechaElab, Long idArticulo) {
        ArticuloEntity ae = ArticuloDao.getInstance().findById(idArticulo);
        Articulo articulo = Articulo.fromEntity(ae);
        Lote lote = new Lote();
        lote.setFechaElaboracion(fechaElab);
        lote.setFechaVto(fechaVen);
        lote.setCodigo(codigo);
        if (articulo.getLotes() == null) {
            articulo.setLotes(new ArrayList<>());
        }
        articulo.getLotes().add(lote);
        articulo.guardar();
        return lote;
    }

    public List<Pedido> obtenerPedidosACompletar() {
        return SistemaAdministracion.getInstance().obtenerPedidosACompletar();
    }

    public void inicializarPosiciones() throws BusinessException {
        List<PosicionEntity> posicionesEntity = posicionDao.findAll();
        char[] calles = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        String[] bloques = {"01", "02", "03"};
        String[] estanterias = {"01", "02", "03", "04", "05"};
        String[] estantes = {"01", "02", "03", "04", "05", "06"};
        String[] numero = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"};
        if (posicionesEntity.isEmpty()) {
            for (int i = 0; i <= 6; i++) {
                for (int j = 0; j <= 2; j++) {
                    for (int k = 0; k <= 4; k++) {
                        for (int t = 0; t <= 5; t++) {
                            for (int h = 0; h <= 20; h++) {
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
        } else {
            throw new BusinessException("Ya fueron inicializadas las posiciones");
        }
    }
=======
    
    public void liberarPosicion(String codigoUbicacion, int cantidad) {
    	Posicion posicion = posicionDao.findById(codigoUbicacion);
    	posicion.liberar(cantidad);
    	posicion.guardar();
    }
    
    public void ingresarCompra(Long ordenId, List<ItemLote> itemLotes) {
    	OrdenCompra orden = SistemaCompras.getInstance().buscarOrdenCompra(ordenId);
    	Articulo articulo = orden.getArticulo();
    	int cantCompra = orden.getArticulo().getCantCompra();
    	almacenar(articulo, itemLotes, cantCompra);
    	SistemaCompras.getInstance().aceptarOrdenCompra(orden.getId());
    }
    
    public void almacenar(Articulo articulo, List<ItemLote> itemLotes, int cantidad) {
    	List<Lote> lotes = articulo.getLotes();
    	Stock stock = articulo.getStock();
    	stock.agregarMovimientoIngreso(MotivoIngreso.COMPRA, cantidad);
    	articulo.setStock(stock);
    	List<Posicion> posiciones = posicionDao.obtenerObtenerPosicionesVacias();
    	if(posiciones.size() * 21 >= cantidad) {
	    	for (ItemLote item : itemLotes) {
		    	int cantidadLote = item.getCantidad(); 
		    	lotes.add(item.getLote());
	    		while(cantidadLote > 0) {
		        	Posicion posicion = new Posicion();
		        	posicion.setLote(item.getLote());
		    		if(cantidad > 21) {
		    			posicion.setCantidad(21);
		    			cantidadLote = cantidadLote - 21;
		    		}else {
		    			posicion.setCantidad(cantidadLote);
		    	    	cantidadLote = 0;
		    		}
	    	    	posicion.guardar();
		    	}
	    	}
	    	articulo.setLotes(lotes);
	    	articulo.guardar();
    	}else {
    		//TODO Arrojar exception porque no hay lugar suficiente
    	}
    }

    /**
     * Obtiene la menor cantidad de lotes necesarios
     * @param articuloId
     * @param cant
     * @return
     */
    public List<ItemLote> obtenerLotesPorArticulo(Long articuloId, int cant) {
        List<ItemLote> result = new ArrayList<>();
        int cantidadParcial = 0;
        List<ItemLote> itemLotes = itemLoteDao.findByArticuloId(articuloId);
        Iterator<ItemLote> it = itemLotes.iterator();
        while (cantidadParcial < cant && it.hasNext()) {
            ItemLote il = it.next();
            cantidadParcial += il.getCantidad();
            result.add(il);
        }
        return result;
    }

    public Articulo buscarArticulo(Long articuloId) {
        return articuloDao.findById(articuloId);
    }

>>>>>>> develop
}
