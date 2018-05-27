package edu.uade.apd.tpo.transform;

import edu.uade.apd.tpo.dao.FacturaDao;
import edu.uade.apd.tpo.entity.FacturaEntity;
import edu.uade.apd.tpo.model.Factura;
import edu.uade.apd.tpo.model.Transaccion;
import org.junit.Test;

public class FacturaMappingTest {

    @Test
    public void testMapFactura(){
        FacturaEntity entity = FacturaDao.getInstance().findById(2L);
        Factura factura = Factura.fromEntity(entity, Transaccion.fromEntity(entity.getTransaccion()));
        System.out.print(factura);
    }
}
