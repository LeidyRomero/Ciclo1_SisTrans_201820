package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de SUCURSAL.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public interface VOSucursal {

	/**
	 * @return Tama�o de la sucursal.
	 */
	public String getTamanio();

	/**
	 * @return Direcci�n de la sucursal.
	 */
	public String getDireccionSucursal();

	/**
	 * @return Ciudad de la sucursal.
	 */
	public String getCiudad();

	/**
	 * @return Nombre de la sucursal.
	 */
	public String getNombreSucursal();

	/**
	 * @return Una cadena con la informaci�n b�sica de una Sucursal.
	 */
	public String toString();
}
