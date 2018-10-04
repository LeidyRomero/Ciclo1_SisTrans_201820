package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de SUCURSAL PROMOCIONES.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOSucursalPromociones {

	/**
	 * @return Id de la promoción
	 */
	public long getIdPromocion();

	/**
	 * @return Ciudad de la sucursal
	 */
	public String getCiudad();

	/**
	 * @return Dirección de la sucursal
	 */
	public String getDireccionSucursal();
	
	/**
	 * @return Una cadena con la información básica de SUCURSAL PROMOCIONES
	 */
	public String toString();
}
