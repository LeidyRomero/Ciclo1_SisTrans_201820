package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Interfaz para los m�todos get de PROMOCION.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public interface VOPromocion {

	/**
	 * @return Fecha de inicio de la promoci�n.
	 */
	public Timestamp getFechaInicial();

	/**
	 * @return Fecha de terminaci�n de la promoci�n.
	 */
	public Timestamp getFechaFinal();

	/**
	 * @return Descripci�n de la promoci�n.
	 */
	public String getDescripcion();

	/**
	 * @return Id de la promoci�n.
	 */
	public long getIdPromocion();
	
	/**
	 *@return El c�digo de barras.
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
	 * @return Una cadena con la informaci�n b�sica de la Promoci�n.
	 */
	public String toString();
	
}
