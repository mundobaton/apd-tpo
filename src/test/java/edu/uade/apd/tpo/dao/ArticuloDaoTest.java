package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.StockEntity;
import org.junit.Before;
import org.junit.Test;

public class ArticuloDaoTest {

    private ArticuloDao dao;

    @Before
    public void setup() {
        dao = ArticuloDao.getInstance();
    }

    @Test
    public void testCrearArticulo() {
        ArticuloEntity articuloEntity = new ArticuloEntity();
        articuloEntity.setCodBarras("4123");
        articuloEntity.setDescripcion("Un articulo test");
        articuloEntity.setPresentacion("una linda presentacion");
        articuloEntity.setUnidad("una bolsa grande");
        articuloEntity.setCantCompra(1500);
        articuloEntity.setStock(new StockEntity());
        articuloEntity.setVolumen(70);
        articuloEntity.setPrecio(49.90f);
        dao.save(articuloEntity);
    }
}
