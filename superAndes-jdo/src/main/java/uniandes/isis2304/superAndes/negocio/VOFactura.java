package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Interfaz para los m�todos get de FACTURA.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public interface VOFactura {

	/**
	 * @return El id de la factura.
	 */
	public long getIdFactura();
	
	/**
	 * @return Fecha de generaci�n de la factura.
	 */
	public Timestamp getFecha();
	
	/** 
	 * @return Costo total de la factura
	 */
	public double getCostoTotal();
	
	/**
	 * @return Una cadena con la informaci�n b�sica de la factura.
	 */
	public String toString();
}
