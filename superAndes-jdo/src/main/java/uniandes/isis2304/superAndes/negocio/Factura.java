package uniandes.isis2304.superAndes.negocio;

import java.sql.Date;

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
	private Date fecha;
	
	/**
	 * Costo total de la factura
	 */
	private double costoTotal;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	public Factura()
	{
		idFactura = 0;
		fecha = null;
		costoTotal = 0.0;
	}
	
	public Factura(int pId, Date pFecha, double pCosto)
	{
		idFactura = pId;
		fecha = pFecha;
		costoTotal = pCosto;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------

	public long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", fecha=" + fecha + ", costoTotal=" + costoTotal + "]";
	}
	
	
}
