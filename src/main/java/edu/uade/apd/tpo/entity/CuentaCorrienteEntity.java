package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import javax.persistence.CascadeType;
import javax.persistence.Column;
=======
import javax.persistence.Column;
import javax.persistence.Embeddable;
>>>>>>> develop
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
<<<<<<< HEAD
import javax.persistence.Transient;
import java.io.Serializable;
=======
>>>>>>> develop
import java.util.List;

@Entity
@Table(name = "cuentas_corrientes")
<<<<<<< HEAD
public class CuentaCorrienteEntity implements Serializable {
=======
public class CuentaCorrienteEntity extends BaseEntity {
>>>>>>> develop

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cuenta_corriente_id")
    private Long id;
    @Column(name = "saldo")
<<<<<<< HEAD
    private float saldo;
    @Column(name = "limite_credito")
    private float limiteCredito;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cuenta_corriente_id")
    private List<TransaccionEntity> transacciones;

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
=======
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
>>>>>>> develop
        this.limiteCredito = limiteCredito;
    }

    public List<TransaccionEntity> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionEntity> transacciones) {
        this.transacciones = transacciones;
    }
}
