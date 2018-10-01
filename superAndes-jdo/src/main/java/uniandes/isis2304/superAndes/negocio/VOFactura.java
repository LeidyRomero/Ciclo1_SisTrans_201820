package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Interfaz para los métodos get de FACTURA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOFactura {

	/**
	 * @return El id de la factura.
	 */
	public long getIdFactura();
	
	/**
	 * @return Fecha de generación de la factura.
	 */
	public Timestamp getFecha();
	
	/** 
	 * @return Costo total de la factura
	 */
	public double getCostoTotal();
	
	/**
	 * @return Una cadena con la información básica de la factura.
	 */
	public String toString();
}
