package uniandes.isis2304.superAndes.negocio;

/**
* Interfaz para los métodos get de CARRITO.
* Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
* 
* @author María Ocampo - mj.ocampov
*
*/
public interface VOCarrito 
{
	/**
	 * @return Dirección de la sucursal
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
	 * @return Una cadena con la información básica del CARRITO
	 */
	public String toString();

}
