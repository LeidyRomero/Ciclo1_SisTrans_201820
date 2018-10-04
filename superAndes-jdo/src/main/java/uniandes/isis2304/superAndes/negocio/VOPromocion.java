package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Interfaz para los métodos get de PROMOCION.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOPromocion {

	/**
	 * @return Fecha de inicio de la promoción.
	 */
	public Timestamp getFechaIncial();

	/**
	 * @return Fecha de terminación de la promoción.
	 */
	public Timestamp getFechaFinal();

	/**
	 * @return Descripción de la promoción.
	 */
	public String getDescripcion();

	/**
	 * @return Id de la promoción.
	 */
	public long getIdPromocion();
	
	/**
	 *@return El código de barras.
	 */
	public String getCodigoBarras() ;
	
	/**
	 * @return Las unidades disponibles
	 */
	public int getUnidadesDisponibles();
	
	/**
	 * @return Las unidades vendidas
	 */
	public int getUnidadesVendidas();
	
	/**
	 * @return Retorna el estado de la promoción
	 */
	public String getEstado();
	
	/**
	 * @return Una cadena con la información básica de la Promoción.
	 */
	public String toString();
	
}
