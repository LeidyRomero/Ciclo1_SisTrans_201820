package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar el concepto PROMOCION del negocio de los SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public class Promocion implements VOPromocion {

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Fecha en que inicia la promoci�n
	 */
	private Timestamp fechaInicial;
	
	/**
	 * Fecha en que termina la promoci�n
	 */
	private Timestamp fechaFinal;
	
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
	
	/**
	 * Constructor con valores.
	 * @param fechaInicial Fecha inicio. fechaInicial != null
	 * @param fechaFinal Fecha final. fechaFinal != null
	 * @param descripcion Descripcion de la promocion. descripcion != null && descripcion != ""
	 * @param idPromocion Id de la promocion. idPromocion >= 0
	 */
	public Promocion(Timestamp fechaInicial, Timestamp fechaFinal, String descripcion, long idPromocion) 
	{
		this.fechaInicial = fechaInicial;
		this.fechaFinal = fechaFinal;
		this.descripcion = descripcion;
		this.idPromocion = idPromocion;
	}
	
	/**
	 * Constructor por defecto
	 */
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
	
	/**
	 * Retorna la fecha inicial de la promoci�n.
	 */
	public Timestamp getFechaInicial() {
		return fechaInicial;
	}

	/**
	 * Modifica la fecha inicial de la promoci�n. 
	 * @param fechaInicial Nueva fecha inicial.
	 */
	public void setFechaInicial(Timestamp fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	/**
	 *  Retorna la fecha final de la promoci�n.
	 */
	public Timestamp getFechaFinal() {
		return fechaFinal;
	}

	/**
	 * Modifica la fecha final de la promoci�n.
	 * @param fechaFinal Nueva fecha final.
	 */
	public void setFechaFinal(Timestamp fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	/**
	 * Retorna la descripci�n de la promoci�n.
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Modifica la descripci�n de la promoci�n.
	 * @param descripcion Nueva descripci�n.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Retorna el id de la promoci�n.
	 */
	public long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * Modifica el id de la promoc�n.
	 * @param idPromocion Nueva id.
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	@Override
	public String toString() {
		return "Promocion [fechaInicial=" + fechaInicial + ", fechaFinal=" + fechaFinal + ", descripcion=" + descripcion
				+ ", idPromocion=" + idPromocion + "]";
	}
	
	
}
