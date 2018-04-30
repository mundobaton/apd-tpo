package edu.uade.apd.tpo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "cuentas_corrientes")
public class CuentaCorrienteEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_corriente_id")
    private Long id;
    @Column(name = "saldo")
    private Float saldo;
    @Column(name = "limite_credito")
    private Float limiteCredito;
    @OneToMany
    @JoinColumn(name = "cuenta_corriente_id")
    private List<TransaccionEntity> transacciones;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Float getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(Float limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    public List<TransaccionEntity> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionEntity> transacciones) {
        this.transacciones = transacciones;
    }
}
