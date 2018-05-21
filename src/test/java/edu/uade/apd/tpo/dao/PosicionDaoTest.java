package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.LoteEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.model.EstadoPosicion;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PosicionDaoTest {

    private PosicionDao posicionDao;
    private ArticuloDao articuloDao;
    private LoteDao loteDao;

    @Before
    public void setup() {
        posicionDao = PosicionDao.getInstance();
        articuloDao = ArticuloDao.getInstance();
        loteDao = LoteDao.getInstance();
    }

    @Test
    public void testCrearPosicion() {
        PosicionEntity pe = new PosicionEntity();
        char calle = 'A';
        int bloque = 1;
        int estante = 1;
        int estanteria = 1;
        int num = 5; //hasta 21
        int cant = 0;

        pe.setCalle(calle);
        pe.setBloque(bloque);
        pe.setEstante(estante);
        pe.setEstanteria(estanteria);
        pe.setCantidad(cant);
        pe.setNumero(num);
        pe.setEstado(EstadoPosicion.DISPONIBLE);

        pe.setCodigoUbicacion("A01010105");
        posicionDao.save(pe);
    }

    @Test
    public void testObtenerPosicionesDisponibles() {
        List<PosicionEntity> posiciones = posicionDao.findPosicionesDisponibles();
        System.out.println(posiciones.size());
    }

    @Test
    public void testAlmacenarEnPosicion() {
        ArticuloEntity art = articuloDao.findById(new Integer(1));
        PosicionEntity p = posicionDao.findById(new Integer(1));
        LoteEntity le = new LoteEntity();
        le.setArticulo(art);
        le.setCodigo("un codigo de barras");
        le.setFechaVto(new Date());
        le.setFechaElaboracion(new Date());
        le = loteDao.save(le);

        p.setLote(le);
        p.setEstado(EstadoPosicion.OCUPADO);
        p.setCantidad(10);
        posicionDao.save(p);
    }

}
