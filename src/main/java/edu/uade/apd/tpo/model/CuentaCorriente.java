package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;

import java.util.ArrayList;
import java.util.List;

public class CuentaCorriente {

    private Integer id;
    private Float saldo;
    private Float limiteCredito;
    private List<Transaccion> transacciones;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public Float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public CuentaCorrienteEntity toEntity() {
        CuentaCorrienteEntity entity = new CuentaCorrienteEntity();
        entity.setId(id);
        entity.setSaldo(saldo);
        entity.setLimiteCredito(limiteCredito);
        if (transacciones != null && !transacciones.isEmpty()) {
            List<TransaccionEntity> transaccionEntities = new ArrayList<>();
            for (Transaccion t : transacciones) {
                transaccionEntities.add(t.toEntity());
            }
            entity.setTransacciones(transaccionEntities);
        }
        return entity;
    }

    public static CuentaCorriente fromEntity(CuentaCorrienteEntity entity) {
        CuentaCorriente cc = null;
        if(entity != null) {
            cc = new CuentaCorriente();
            cc.setId(entity.getId());
            cc.setSaldo(entity.getSaldo());
            cc.setLimiteCredito(entity.getLimiteCredito());
            if (entity.getTransacciones() != null) {
                List<Transaccion> transacciones = new ArrayList<>();
                for (TransaccionEntity t : entity.getTransacciones()) {
                    transacciones.add(Transaccion.fromEntity(t));
                }
                cc.setTransacciones(transacciones);
            }
        }

        return cc;
    }
}
