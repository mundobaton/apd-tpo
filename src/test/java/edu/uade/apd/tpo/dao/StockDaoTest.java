package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.EgresoEntity;
import edu.uade.apd.tpo.entity.IngresoEntity;
import edu.uade.apd.tpo.entity.StockEntity;
import edu.uade.apd.tpo.entity.UsuarioEntity;
import edu.uade.apd.tpo.model.MotivoEgreso;
import edu.uade.apd.tpo.model.MotivoIngreso;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

public class StockDaoTest {

    private EgresoDao egresoDao;
    private UsuarioDao usuarioDao;
    private IngresoDao ingresoDao;
    private StockDao dao;

    @Before
    public void setup() {
        dao = StockDao.getInstance().getInstance();
        usuarioDao = UsuarioDao.getInstance();
        egresoDao = EgresoDao.getInstance();
        ingresoDao = IngresoDao.getInstance();
    }

    @Test
    public void testCrearEgreso() {
        UsuarioEntity usuario = usuarioDao.findByEmail("test@prueba.com");
        StockEntity stock = dao.findById(2L);

        EgresoEntity egreso = new EgresoEntity();
        egreso.setDestino("Casa");
        egreso.setFecha(new Date());
        egreso.setCantidad(100);
        egreso.setMotivo(MotivoEgreso.DETERIORO);
        egreso.setEncargado(usuario);
        egreso.setAutorizante("yo mismo");

        IngresoEntity ingreso = new IngresoEntity();
        ingreso.setFecha(new Date());
        ingreso.setCantidad(50);
        ingreso.setMotivo(MotivoIngreso.OTROS);

        if (stock.getMovimientos() == null) {
            stock.setMovimientos(new ArrayList<>());
        }
        stock.getMovimientos().add(egreso);
        stock.getMovimientos().add(ingreso);
        dao.save(stock);

    }

}
