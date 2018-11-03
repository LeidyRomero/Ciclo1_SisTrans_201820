package uniandes.isis2304.superAndes.negocio;

/**
* Interfaz para los métodos get de PRODUCTOS CARRITO.
* Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
* 
* @author María Ocampo - mj.ocampov
*
*/
public interface VOProductosCarrito {

	/**
	 * @return El identificador del carrito
	 */
	public long getIdCarrito();

	/**
	 * @return El código de barras del producto
	 */
	public String getCodBarras();

	/**
	 * @return La cantidad del producto en el carrito
	 */
	public int getCantidad();
	
	/**
	 * @return Una cadena con la información básica de PRODUCTOS CARRITO
	 */
	public String toString();
}
