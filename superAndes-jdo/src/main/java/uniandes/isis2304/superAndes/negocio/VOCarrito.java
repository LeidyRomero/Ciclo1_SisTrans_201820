package uniandes.isis2304.superAndes.negocio;

/**
* Interfaz para los m�todos get de CARRITO.
* Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
* 
* @author Mar�a Ocampo - mj.ocampov
*
*/
public interface VOCarrito 
{
	/**
	 * @return Direcci�n de la sucursal
	 */
	public String getDireccionSucursal();

	/**
	 * @return Ciudad de la sucursal
	 */
	public String getCiudad();

	/**
	 * @return Identificador del carrito
	 */
	public long getIdCarrito();

	/**
	 * @return Correo del cliente 
	 */
	public String getCorreoCliente();
	
	/**
	 * @return Una cadena con la informaci�n b�sica del CARRITO
	 */
	public String toString();

}
