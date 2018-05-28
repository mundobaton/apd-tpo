package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.dao.CuentaCorrienteDao;
import edu.uade.apd.tpo.entity.CuentaCorrienteEntity;
import edu.uade.apd.tpo.entity.TransaccionEntity;
import edu.uade.apd.tpo.repository.stub.CuentaCorrienteStub;
import edu.uade.apd.tpo.repository.stub.TransaccionStub;

import java.util.ArrayList;
import java.util.List;

public class CuentaCorriente {

    private Long id;
    private float saldo;
    private float limiteCredito;
    private List<Transaccion> transacciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public static CuentaCorriente fromEntity(CuentaCorrienteEntity entity) {
        CuentaCorriente cuentaCorriente = null;
        if (entity != null) {
            cuentaCorriente = new CuentaCorriente();
            cuentaCorriente.setId(entity.getId());
            cuentaCorriente.setSaldo(entity.getSaldo());
            cuentaCorriente.setLimiteCredito(entity.getLimiteCredito());
            if (entity.getTransacciones() != null) {
                cuentaCorriente.setTransacciones(new ArrayList<>());
                for (TransaccionEntity te : entity.getTransacciones()) {
                    cuentaCorriente.getTransacciones().add(Transaccion.fromEntity(te));
                }
            }
        }
        return cuentaCorriente;
    }

    public static CuentaCorriente fromStub(CuentaCorrienteStub stub) {
        CuentaCorriente cuentaCorriente = null;
        if (stub != null) {
            cuentaCorriente = new CuentaCorriente();
            cuentaCorriente.setId(stub.getId());
            cuentaCorriente.setSaldo(stub.getSaldo());
            cuentaCorriente.setLimiteCredito(stub.getLimiteCredito());
            if (stub.getTransacciones() != null) {
                cuentaCorriente.setTransacciones(new ArrayList<>());
                for (TransaccionStub te : stub.getTransacciones()) {
                    cuentaCorriente.getTransacciones().add(Transaccion.fromStub(te));
                }
            }
        }
        return cuentaCorriente;
    }

    public CuentaCorrienteEntity toEntity() {
        CuentaCorrienteEntity entity = new CuentaCorrienteEntity();
        entity.setId(id);
        entity.setSaldo(saldo);
        entity.setLimiteCredito(limiteCredito);
        if (transacciones != null) {
            entity.setTransacciones(new ArrayList<>());
            for (Transaccion t : transacciones) {
                entity.getTransacciones().add(t.toEntity());
            }
        }
        return entity;
    }

    public CuentaCorrienteStub toStub() {
        CuentaCorrienteStub stub = new CuentaCorrienteStub();
        stub.setId(id);
        stub.setSaldo(saldo);
        stub.setLimiteCredito(limiteCredito);
        if (transacciones != null) {
            stub.setTransacciones(new ArrayList<>());
            for (Transaccion t : transacciones) {
                stub.getTransacciones().add(t.toStub());
            }
        }
        return stub;
    }

    public void guardar() {
        CuentaCorrienteDao.getInstance().save(this.toEntity());
    }

}
