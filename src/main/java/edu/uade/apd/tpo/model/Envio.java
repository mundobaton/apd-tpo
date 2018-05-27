package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.EnvioDao;
import edu.uade.apd.tpo.entity.EnvioEntity;

public class Envio {

    private Long id;
    private Domicilio domicilio;
    private Transportista transportista;
    private Remito remito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public Remito getRemito() {
        return remito;
    }

    public void setRemito(Remito remito) {
        this.remito = remito;
    }

    public float calcular() {

        return domicilio.getZona().getPrecio() * transportista.getPrecio();
    }

    public static Envio fromEntity(EnvioEntity entity) {
        Envio envio = null;
        if (entity != null) {
            envio = new Envio();
            envio.setId(entity.getId());
            envio.setDomicilio(Domicilio.fromEntity(entity.getDomicilio()));
            envio.setTransportista(entity.getTransportista());
            envio.setRemito(Remito.fromEntity(entity.getRemito()));
        }
        return envio;
    }

    public EnvioEntity toEntity() {
        EnvioEntity ee = new EnvioEntity();
        ee.setId(id);
        ee.setDomicilio(domicilio != null ? domicilio.toEntity() : null);
        ee.setTransportista(transportista);
        ee.setRemito(remito != null ? remito.toEntity() : null);
        return ee;
    }

    public void guardar() {
        EnvioDao.getInstance().save(this.toEntity());
    }

}
