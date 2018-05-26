package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ItemPosicionEntity;
import edu.uade.apd.tpo.entity.PosicionEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemPosicionDaoTest {
    private ItemPosicionDao dao;

    @Before
    public void setup() {
        dao = ItemPosicionDao.getInstance();
    }

    @Test
    public void testCrearItemPosicion(){
        ItemPosicionEntity ip = new ItemPosicionEntity();
        PosicionEntity pos = PosicionDao.getInstance().findByCodigo("BB012012");
        ip.setCantidad(50);
        ip.setPosicion(pos);
        dao.save(ip);

    }

    @Test
    public void testBuscarItemPosicion(){
        ItemPosicionEntity ip = dao.findById(new Long(1));
        Assert.assertNotNull(ip);
    }
}
