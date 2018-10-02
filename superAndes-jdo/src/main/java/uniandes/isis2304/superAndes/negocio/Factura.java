package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar el concepto FACTURA del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class Factura implements VOFactura{

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Id de la factura
	 */
	private long idFactura;
	
	/**
	 * Fecha de generación de la factura
	 */
	private Timestamp fecha;
	
	/**
	 * Costo total de la factura
	 */
	private double costoTotal;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	/**
	 * Constructor por defecto.
	 */
	public Factura()
	{
		idFactura = 0;
		fecha = null;
		costoTotal = 0.0;
	}
	
	/**
	 * Constructor con valores
	 * @param pId Id de la factura. pId >= 0
	 * @param pFecha Fecha de generación de la factura. pFecha != null
	 * @param pCosto Costo total. pCosto >= 0.0
	 */
	public Factura(long pId, Timestamp pFecha, double pCosto)
	{
		idFactura = pId;
		fecha = pFecha;
		costoTotal = pCosto;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------

	/**
	 * Retorna el id de la factura.
	 */
	public long getIdFactura() {
		return idFactura;
	}

	/**
	 * Modifica el id de la factura.
	 * @param idFactura Nueva id.
	 */
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * Retorna la fecha de generación de la factura.
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * Modifica la fecha de generación de la factura.	 * 
	 * @param fecha Nueva fecha de generación.
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * Retorna el costo total de la factura.
	 */
	public double getCostoTotal() {
		return costoTotal;
	}

	/**
	 * Modifica el costo total de la factura
	 * @param costoTotal Nuevo costo.
	 */
	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", fecha=" + fecha + ", costoTotal=" + costoTotal + "]";
	}
		
}
