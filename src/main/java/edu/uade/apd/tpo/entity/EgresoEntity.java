package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import java.io.Serializable;

import edu.uade.apd.tpo.model.MotivoEgreso;

import javax.persistence.CascadeType;
=======
import edu.uade.apd.tpo.model.MotivoEgreso;

>>>>>>> develop
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "egresos")
@PrimaryKeyJoinColumn(name = "egreso_id")
public class EgresoEntity extends MovimientoEntity {

<<<<<<< HEAD
    @Column(name = "motivo_id")
    @Enumerated(EnumType.ORDINAL)
    private MotivoEgreso motivo;
    @OneToOne(cascade = CascadeType.ALL)
=======
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "motivo")
    private MotivoEgreso motivoEgreso;
    @OneToOne
>>>>>>> develop
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity encargado;
    @Column(name = "autorizante")
    private String autorizante;
    @Column(name = "destino")
    private String destino;

<<<<<<< HEAD
    public MotivoEgreso getMotivo() {
        return motivo;
    }

    public void setMotivo(MotivoEgreso motivo) {
        this.motivo = motivo;
=======
    public MotivoEgreso getMotivoEgreso() {
        return motivoEgreso;
    }

    public void setMotivoEgreso(MotivoEgreso motivoEgreso) {
        this.motivoEgreso = motivoEgreso;
>>>>>>> develop
    }

    public UsuarioEntity getEncargado() {
        return encargado;
    }

    public void setEncargado(UsuarioEntity encargado) {
        this.encargado = encargado;
    }

    public String getAutorizante() {
        return autorizante;
    }

    public void setAutorizante(String autorizante) {
        this.autorizante = autorizante;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
<<<<<<< HEAD


=======
>>>>>>> develop
}
