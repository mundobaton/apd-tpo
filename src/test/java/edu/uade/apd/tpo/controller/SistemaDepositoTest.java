package edu.uade.apd.tpo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uade.apd.tpo.dao.impl.ArticuloDao;
import edu.uade.apd.tpo.model.Articulo;
import edu.uade.apd.tpo.model.ItemLote;
import edu.uade.apd.tpo.model.Lote;

public class SistemaDepositoTest {
	
	private SistemaDeposito sistemaDeposito = SistemaDeposito.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(SistemaDeposito.class);
	
	@Test
	public void testLiberarPosicion() {
		sistemaDeposito.liberarPosicion("A01010101", 10);
	}
	
	@Test
	public void testIngresarCompra() {
		Articulo art = ArticuloDao.getInstance().findById(new Long(1));
		Long ordenId = new Long(2);
		List<ItemLote> itemLotes = new ArrayList<>();
		ItemLote item = new ItemLote();
		Lote lote = new Lote();
		
		lote.setFechaElaboracion(new Date());
		lote.setArticulo(art);
		lote.setCodigo("abc080910");
		lote.setFechaVto(new Date());
		lote.setPosiciones(null);

		item.setLote(lote);
		item.setCantidad(30);
		
		itemLotes.add(item);
		
		sistemaDeposito.ingresarCompra(ordenId, itemLotes);
		
	}
	
	@Test
	public void testAlmacenar() {
		
	}
	
	@Test
	public void testObtenerLotesPorArticulo(){
		
	}
	
	@Test
	public void testBuscarArticulo(){
		
	}

}
