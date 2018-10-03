package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de SUCURSAL PROMOCIONES.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public interface VOSucursalPromociones {

	/**
	 * @return Id de la promoci�n
	 */
	public long getIdPromocion();

	/**
	 * @return Ciudad de la sucursal
	 */
	public String getCiudad();

	/**
	 * @return Direcci�n de la sucursal
	 */
	public String getDireccion();
	
	/**
	 * @return Una cadena con la informaci�n b�sica de SUCURSAL PROMOCIONES
	 */
	public String toString();
}
