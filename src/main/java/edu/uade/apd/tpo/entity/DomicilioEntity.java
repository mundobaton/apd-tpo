package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import java.io.Serializable;

import edu.uade.apd.tpo.model.Zona;

import javax.persistence.Column;
=======
import edu.uade.apd.tpo.model.CondIva;
import edu.uade.apd.tpo.model.ZonaEnvio;

import javax.persistence.Column;
import javax.persistence.Embeddable;
>>>>>>> develop
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "domicilios")
<<<<<<< HEAD
public class DomicilioEntity implements Serializable {
=======
public class DomicilioEntity extends BaseEntity {
>>>>>>> develop

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domicilio_id")
    private Long id;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero")
<<<<<<< HEAD
    private Long numero;
    @Column(name = "cod_postal")
=======
    private Integer numero;
    @Column(name = "codigo_postal")
>>>>>>> develop
    private String codPostal;
    @Column(name = "localidad")
    private String localidad;
    @Column(name = "provincia")
    private String provincia;
    @Enumerated(EnumType.ORDINAL)
<<<<<<< HEAD
    @Column(name = "zona_id")
    private Zona zona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
=======
    @Column(name = "zona")
    private ZonaEnvio zona;

    public long getId() {
        return id;
    }

    public void setId(long id) {
>>>>>>> develop
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


=======
    public ZonaEnvio getZona() {
        return zona;
    }

    public void setZona(ZonaEnvio zona) {
        this.zona = zona;
    }
>>>>>>> develop
}
