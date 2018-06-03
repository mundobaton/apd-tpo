package edu.uade.apd.tpo.model;
/*
 * Factura A: emitida por un responsable inscripto a otro responsable inscripto. El IVA está discriminado.
 * Factura B: también emitida por un responsable inscripto pero en este caso, a un consumidor final, un sujeto exento o un monotributista 
 * (ya que al pagar el IVA por régimen simplificado no es necesario discriminar el IVA en la factura).
*/
public enum TipoFactura {

    A,
    B
    
}
