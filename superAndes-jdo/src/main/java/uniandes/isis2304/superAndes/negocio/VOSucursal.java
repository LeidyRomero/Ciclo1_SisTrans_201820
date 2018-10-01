package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de SUCURSAL.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOSucursal {

	/**
	 * @return Tamaño de la sucursal.
	 */
	public String getTamanio();

	/**
	 * @return Dirección de la sucursal.
	 */
	public String getDireccion();

	/**
	 * @return Ciudad de la sucursal.
	 */
	public String getCiudad();

	/**
	 * @return Nombre de la sucursal.
	 */
	public String getNombre();

	/**
	 * @return Una cadena con la información básica de una Sucursal.
	 */
	public String toString();
}
