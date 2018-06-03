package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.dao.DomicilioDao;
import edu.uade.apd.tpo.entity.DomicilioEntity;
import edu.uade.apd.tpo.repository.stub.DomicilioStub;

=======
>>>>>>> develop
public class Domicilio {

    private Long id;
    private String calle;
<<<<<<< HEAD
    private Long numero;
    private String codPostal;
    private String localidad;
    private String provincia;
    private Zona zona;
=======
    private Integer numero;
    private String codPostal;
    private String localidad;
    private String provincia;
    private ZonaEnvio zona;
>>>>>>> develop

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

<<<<<<< HEAD
    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
=======
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
>>>>>>> develop
        this.numero = numero;
    }

    public String getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

<<<<<<< HEAD
    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }

    public static Domicilio fromEntity(DomicilioEntity entity) {
        Domicilio dom = null;
        if (entity != null) {
            dom = new Domicilio();
            dom.setId(entity.getId());
            dom.setCalle(entity.getCalle());
            dom.setNumero(entity.getNumero());
            dom.setCodPostal(entity.getCodPostal());
            dom.setLocalidad(entity.getLocalidad());
            dom.setProvincia(entity.getProvincia());
            dom.setZona(entity.getZona());
        }
        return dom;
    }

    public static Domicilio fromStub(DomicilioStub stub) {
        Domicilio dom = null;
        if (stub != null) {
            dom = new Domicilio();
            dom.setId(stub.getId());
            dom.setCalle(stub.getCalle());
            dom.setNumero(stub.getNumero());
            dom.setCodPostal(stub.getCodPostal());
            dom.setLocalidad(stub.getLocalidad());
            dom.setProvincia(stub.getProvincia());
            dom.setZona(Zona.fromStub(stub.getZona()));
        }
        return dom;
    }

    public DomicilioEntity toEntity() {
        DomicilioEntity entity = new DomicilioEntity();
        entity.setId(id);
        entity.setCalle(calle);
        entity.setNumero(numero);
        entity.setCodPostal(codPostal);
        entity.setLocalidad(localidad);
        entity.setProvincia(provincia);
        entity.setZona(zona);

        return entity;
    }

    public DomicilioStub toStub() {
        DomicilioStub stub = new DomicilioStub();
        stub.setId(id);
        stub.setCalle(calle);
        stub.setNumero(numero);
        stub.setCodPostal(codPostal);
        stub.setLocalidad(localidad);
        stub.setProvincia(provincia);
        stub.setZona(zona.toStub());

        return stub;
    }

    public void guardar() {
        DomicilioDao.getInstance().save(this.toEntity());
    }


=======
    public ZonaEnvio getZona() {
        return zona;
    }

    public void setZona(ZonaEnvio zona) {
        this.zona = zona;
    }

    public Domicilio() {

    }

    public Domicilio(String calle, int numero, String codPostal, String localidad, String provincia, ZonaEnvio zona) {
        this.calle = calle;
        this.numero = numero;
        this.codPostal = codPostal;
        this.localidad = localidad;
        this.provincia = provincia;
        this.zona = zona;
    }
>>>>>>> develop
}
