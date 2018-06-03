package edu.uade.apd.tpo.entity;

<<<<<<< HEAD
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "proveedores")
public class ProveedorEntity implements Serializable {
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "proveedores")
public class ProveedorEntity extends BaseEntity {

>>>>>>> develop
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proveedor_id")
    private Long id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cuit")
    private String cuit;
    @Column(name = "telefono")
    private String telefono;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
<<<<<<< HEAD


=======
>>>>>>> develop
}
