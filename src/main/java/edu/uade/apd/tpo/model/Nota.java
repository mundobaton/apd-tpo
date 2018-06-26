package edu.uade.apd.tpo.model;

public class Nota {

    private Long id;
    private String mensaje;
    private Pedido pedido;

    public Nota() {

    }

    public Nota(String mensaje, Pedido pedido) {
        this.mensaje = mensaje;
        this.pedido = pedido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
