package edu.uade.apd.tpo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "cuentas_corrientes")
public class CuentaCorrienteEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_corriente_id")
    private Long id;
    @Column(name = "saldo")
    private float saldo;
    @Column(name = "credito")
    private float credito;
    @OneToMany
    @JoinColumn(name = "cuenta_corriente_id")
    private List<NotaEntity> notas;

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

    public float getCredito() {
        return credito;
    }

    public void setCredito(float credito) {
        this.credito = credito;
    }

    public List<NotaEntity> getNotas() {
        return notas;
    }

    public void setNotas(List<NotaEntity> notas) {
        this.notas = notas;
    }
}
