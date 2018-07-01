package edu.uade.apd.tpo.boot;

import edu.uade.apd.tpo.controller.SistemaAdministracion;
import edu.uade.apd.tpo.controller.SistemaCompras;
import edu.uade.apd.tpo.controller.SistemaDeposito;
import edu.uade.apd.tpo.controller.SistemaDespacho;
import edu.uade.apd.tpo.controller.SistemaFacturacion;
import edu.uade.apd.tpo.dao.AbstractDao;
import edu.uade.apd.tpo.dao.ArticuloDao;
import edu.uade.apd.tpo.dao.SessionManager;
import edu.uade.apd.tpo.dao.UbicacionDao;
import edu.uade.apd.tpo.entity.ArticuloEntity;
import edu.uade.apd.tpo.entity.UbicacionEntity;
import edu.uade.apd.tpo.exception.BusinessException;
import edu.uade.apd.tpo.model.Rol;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class InitDatabase {

    //private final static char[] CALLES = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
    private final static char[] CALLES = {'A'};
    private final static int BLOQUES = 5;
    private final static int ESTANTES = 5;
    private final static int ESTANTERIAS = 6;
    private final static int POSICIONES = 21;

    @Test
    public void initTables() {
        SessionManager.getInstance();
    }

    @Test
    public void initAll() throws BusinessException, InterruptedException {
        //initUbicaciones();
        initArticulos();
        initCliente();
        initUsuario();
        //doFullTest();
    }


    @Test
    public void initUbicaciones() {
        for (char calle : CALLES) {
            for (int bloque = 0; bloque < BLOQUES; bloque++) {
                for (int estanteria = 0; estanteria < ESTANTERIAS; estanteria++) {
                    for (int estante = 0; estante < ESTANTES; estante++) {
                        for (int posicion = 0; posicion < POSICIONES; posicion++) {
                            UbicacionEntity ubicacion = new UbicacionEntity();
                            ubicacion.setEstado('D');
                            ubicacion.setCodigo(generarCodigoUbicacion(calle, bloque, estanteria, estante, posicion));
                            ubicacion.setCalle(calle);
                            ubicacion.setBloque(formatInt(bloque));
                            ubicacion.setEstante(formatInt(estante));
                            ubicacion.setEstanteria(formatInt(estanteria));
                            ubicacion.setPosicion(posicion);
                            UbicacionDao.getInstance().save(ubicacion);
                        }
                    }
                }

            }
        }
    }

    public void initArticulos() {
        ArticuloEntity articulo = new ArticuloEntity();
        //SALSA DE TOMATE
        articulo.setNombre("Salsa de Tomate");
        articulo.setCodigoBarras("11231234412");
        articulo.setPresentacion("Lata");
        articulo.setTamano("chico");
        articulo.setPrecio(35f);
        articulo.setUnidad("gramo");
        articulo.setCantCompra(50);
        articulo.setCantPosicion(5);
        articulo.setStock(0);

        ArticuloDao.getInstance().save(articulo);

        //FIDEOS
        articulo = new ArticuloEntity();
        articulo.setNombre("Fideos Matarazzo");
        articulo.setCodigoBarras("123134413");
        articulo.setPresentacion("Bolsa");
        articulo.setTamano("chico");
        articulo.setPrecio(49.99f);
        articulo.setUnidad("gramo");
        articulo.setCantCompra(25);
        articulo.setCantPosicion(7);
        articulo.setStock(0);

        ArticuloDao.getInstance().save(articulo);

        //QUESO RAYADO
        articulo = new ArticuloEntity();
        articulo.setNombre("Queso Rayado");
        articulo.setCodigoBarras("123134414");
        articulo.setPresentacion("Bolsa");
        articulo.setTamano("chico");
        articulo.setPrecio(20f);
        articulo.setUnidad("gramo");
        articulo.setCantCompra(10);
        articulo.setCantPosicion(3);
        articulo.setStock(0);

        ArticuloDao.getInstance().save(articulo);

    }
    private void initCliente() throws BusinessException {
        SistemaAdministracion.getInstance().crearCliente("mundobaton@gmail.com", "Agustin", 12345678L, "12345", "Fake st", 123, "Cap.Fed", "Buenos Aires", "1406", 500f, 300f);
    }

    public void initUsuario() throws BusinessException {
        SistemaAdministracion.getInstance().crearUsuario("A12345", "qwerty", Rol.ADMINISTRACION);
    }



    private void doFullTest() throws BusinessException, InterruptedException {
        SistemaAdministracion.getInstance().crearPedido(1L, "Otra direccion", 111, "Cap.Fed", "Buenos Aires", "1406");
        SistemaAdministracion.getInstance().agregarItemPedido(1L, 1L, 10);
        SistemaAdministracion.getInstance().agregarItemPedido(1L, 2L, 15);
        SistemaAdministracion.getInstance().finalizarCargaItems(1L);
        SistemaAdministracion.getInstance().aprobarPedido(1L, "un mensaje peligroso");
        SistemaCompras.getInstance().procesarOrdenCompra(1L);
        SistemaCompras.getInstance().procesarOrdenCompra(2L);
        SistemaDeposito.getInstance().reponer(1L, 50);
        SistemaDeposito.getInstance().reponer(2L, 25);
        SistemaDespacho.getInstance().despacharPedido(1L);
        SistemaFacturacion.getInstance().facturar(1L);
        SistemaFacturacion.getInstance().pagarFactura(1L, 1000f);
    }


    private String formatInt(int num) {
        if (num < 10) {
            return "0" + Integer.toString(num);
        } else {
            return Integer.toString(num);
        }
    }

    private String generarCodigoUbicacion(char calle, int bloque, int estanteria, int estante, int posicion) {
        return calle + formatInt(bloque) + formatInt(estanteria) + formatInt(estante) + formatInt(posicion);
    }
}
