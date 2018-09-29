package uniandes.isis2304.superAndes.negocio;

import java.sql.Date;

public class Promocion implements VOPromocion {

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Fecha en que inicia la promoci�n
	 */
	private Date fechaInicial;
	
	/**
	 * Fecha en que termina la promoci�n
	 */
	private Date fechaFinal;
	
	/**
	 * Descripcion de la promoci�n
	 */
	private String descripcion;
	
	/**
	 * Identificador de la promoci�n
	 */
	private long idPromocion;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	public Promocion(Date fechaInicial, Date fechaFinal, String descripcion, long idPromocion) 
	{
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.descripcion = descripcion;
		this.idPromocion = idPromocion;
	}
	
	public Promocion() 
	{
		this.fechaInicial = null;
		this.fechaFinal = null;
		this.descripcion = null;
		this.idPromocion = 0;
	}
	
	//-----------------------------------------------------
	// M�TODOS
	//-----------------------------------------------------
	

	public Date getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(Date fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public Date getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getIdPromocion() {
		return idPromocion;
	}

	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	@Override
	public String toString() {
		return "Promocion [fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", descripcion=" + descripcion
				+ ", idPromocion=" + idPromocion + "]";
	}
	
	
}
