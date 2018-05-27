package edu.uade.apd.tpo.transform;

import edu.uade.apd.tpo.dao.PedidoDao;
import edu.uade.apd.tpo.entity.PedidoEntity;
import edu.uade.apd.tpo.model.Cliente;
import edu.uade.apd.tpo.model.Pedido;
import org.junit.Test;

public class PedidoMappingTest {

    @Test
    public void testMapPedido(){
        PedidoEntity entity = PedidoDao.getInstance().findById(1L);
        Pedido pedido = Pedido.fromEntity(entity, Cliente.fromEntity(entity.getCliente()));
        System.out.print(pedido);
    }
}
