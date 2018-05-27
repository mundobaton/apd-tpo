package edu.uade.apd.tpo.model;

import edu.uade.apd.tpo.entity.IngresoEntity;

public class Ingreso extends Movimiento {
	
	private MotivoIngreso motivo;
	
	public MotivoIngreso getMotivo() {
		return motivo;
	}

	public void setMotivo(MotivoIngreso motivo) {
		this.motivo = motivo;
	}
	
	public void guardar() {
		
	}
	
	public int cantidad() {
		return this.cantidad;
	}

	public static Ingreso fromEntity(IngresoEntity entity) {
		Ingreso i = null;
		if(entity != null){
			i = new Ingreso();
			i.setId(entity.getId());
			i.setFecha(entity.getFecha());
			i.setCantidad(entity.getCantidad());
			i.setMotivo(entity.getMotivo());
		}
		return i;
	}

	public IngresoEntity toEntity(){
		IngresoEntity entity = new IngresoEntity();
		entity.setId(id);
		entity.setFecha(fecha);
		entity.setCantidad(cantidad);
		entity.setMotivo(motivo);

		return entity;
	}

}
