package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.PosicionDao;
import edu.uade.apd.tpo.entity.PosicionEntity;
import edu.uade.apd.tpo.repository.stub.PosicionStub;

public class Posicion {
    private Long id;
    private String codUbicacion;
    private EstadoPosicion estado;
    private int cantidad;
    private char calle;
    private int bloque;
    private int estanteria;
    private int estante;
    private int numero;
    private static int CAPACIDAD = 100;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodUbicacion() {
        return codUbicacion;
    }

    public void setCodUbicacion(String codUbicacion) {
        this.codUbicacion = codUbicacion;
    }

    public EstadoPosicion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPosicion estado) {
        this.estado = estado;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public char getCalle() {
        return calle;
    }

    public void setCalle(char calle) {
        this.calle = calle;
    }

    public int getBloque() {
        return bloque;
    }

    public void setBloque(int bloque) {
        this.bloque = bloque;
    }

    public int getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(int estanteria) {
        this.estanteria = estanteria;
    }

    public int getEstante() {
        return estante;
    }

    public void setEstante(int estante) {
        this.estante = estante;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void guardar() {
        PosicionDao.getInstance().save(this.toEntity());
    }

    public void liberar(int cantidad) {
        this.cantidad -= cantidad;
    }

    public static int getCAPACIDAD() {
        return CAPACIDAD;
    }

    public static void setCAPACIDAD(int cAPACIDAD) {
        CAPACIDAD = cAPACIDAD;
    }

    public static Posicion fromEntity(PosicionEntity entity) {
        Posicion p = null;
        if (entity != null) {
            p = new Posicion();
            p.setId(entity.getId());
            p.setBloque(entity.getBloque());
            p.setCalle(entity.getCalle());
            p.setCantidad(entity.getCantidad());
            p.setCodUbicacion(entity.getCodUbicacion());
            p.setEstante(entity.getEstante());
            p.setEstado(entity.getEstado());
            p.setEstanteria(entity.getEstanteria());
            p.setNumero(entity.getNumero());
        }
        return p;
    }

    public static Posicion fromStub(PosicionStub stub) {
        Posicion p = null;
        if (stub != null) {
            p = new Posicion();
            p.setId(stub.getId());
            p.setBloque(stub.getBloque());
            p.setCalle(stub.getCalle());
            p.setCantidad(stub.getCantidad());
            p.setCodUbicacion(stub.getCodUbicacion());
            p.setEstante(stub.getEstante());
            p.setEstado(EstadoPosicion.fromStub(stub.getEstado()));
            p.setEstanteria(stub.getEstanteria());
            p.setNumero(stub.getNumero());
        }
        return p;
    }

    public PosicionEntity toEntity() {
        PosicionEntity entity = new PosicionEntity();
        entity.setId(id);
        entity.setBloque(bloque);
        entity.setCalle(calle);
        entity.setCantidad(cantidad);
        entity.setCodUbicacion(codUbicacion);
        entity.setEstante(estante);
        entity.setEstado(estado);
        entity.setEstanteria(estanteria);
        entity.setNumero(numero);
        return entity;

    }

    public PosicionStub toStub() {
        PosicionStub stub = new PosicionStub();
        stub.setId(id);
        stub.setBloque(bloque);
        stub.setCalle(calle);
        stub.setCantidad(cantidad);
        stub.setCodUbicacion(codUbicacion);
        stub.setEstante(estante);
        stub.setEstado(estado.toStub());
        stub.setEstanteria(estanteria);
        stub.setNumero(numero);
        return stub;

    }
}
