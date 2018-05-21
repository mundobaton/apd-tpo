package edu.uade.apd.tpo.dao;

import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.LoteEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticuloDaoTest {

    private ArticuloDao dao;

    @Before
    public void setup() {
        dao = ArticuloDao.getInstance();
    }

    @Test
    public void testSaveArticulo() {
        ArticuloEntity entity = new ArticuloEntity();
        entity.setCantCompra(10);
        entity.setCodigoBarras("ASD123");
        entity.setDescripcion("Una descripcion");
        entity.setPrecio(new Float(150.50));
        entity.setPresentacion("una presentacion");
        entity.setUnidad("bolsita");
        entity.setVolumen(150);

        LoteEntity le = new LoteEntity();
        le.setArticulo(entity);
        le.setCodigo("cxzv");
        le.setFechaElaboracion(new Date());
        le.setFechaVto(new Date());
        List<LoteEntity> lotes = new ArrayList<>();
        entity.setLotes(lotes);

        dao.save(entity);



    }

}
