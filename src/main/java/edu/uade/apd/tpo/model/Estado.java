package edu.uade.apd.tpo.model;

<<<<<<< HEAD
import edu.uade.apd.tpo.dao.EstadoDao;
import edu.uade.apd.tpo.entity.EstadoEntity;
import edu.uade.apd.tpo.repository.stub.EstadoStub;

=======
>>>>>>> develop
import java.util.Date;

public class Estado {

    private Long id;
<<<<<<< HEAD
    private EstadoPedido estado;
    private Date fecha;
    private String motivo;
=======
    private Date fecha;
    private EstadoPedido estado;
    private String motivo;
    private Pedido pedido;
>>>>>>> develop

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

<<<<<<< HEAD
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

=======
>>>>>>> develop
    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

<<<<<<< HEAD
    public static Estado fromEntity(EstadoEntity entity) {
        Estado estado = null;
        if (entity != null) {
            estado = new Estado();
            estado.setId(entity.getId());
            estado.setEstado(entity.getEstado());
            estado.setFecha(entity.getFecha());
            estado.setMotivo(entity.getMotivo());
        }

        return estado;
    }

    public static Estado fromStub(EstadoStub stub) {
        Estado estado = null;
        if (stub != null) {
            estado = new Estado();
            estado.setId(stub.getId());
            estado.setEstado(EstadoPedido.fromStub(stub.getEstado()));
            estado.setFecha(stub.getFecha());
            estado.setMotivo(stub.getMotivo());
        }

        return estado;
    }

    public EstadoEntity toEntity() {
        EstadoEntity ee = new EstadoEntity();
        ee.setId(id);
        ee.setEstado(estado);
        ee.setFecha(fecha);
        ee.setMotivo(motivo);

        return ee;
    }

    public EstadoStub toStub() {
        EstadoStub ee = new EstadoStub();
        ee.setId(id);
        ee.setEstado(estado.toStub());
        ee.setFecha(fecha);
        ee.setMotivo(motivo);

        return ee;
    }

    public void guardar() {
        EstadoDao.getInstance().save(this.toEntity());
    }

=======
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
>>>>>>> develop
}
