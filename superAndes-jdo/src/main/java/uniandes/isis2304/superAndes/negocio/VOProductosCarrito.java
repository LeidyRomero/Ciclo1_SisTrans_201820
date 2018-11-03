package uniandes.isis2304.superAndes.negocio;

/**
* Interfaz para los m�todos get de PRODUCTOS CARRITO.
* Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
* 
* @author Mar�a Ocampo - mj.ocampov
*
*/
public interface VOProductosCarrito {

	/**
	 * @return El identificador del carrito
	 */
	public long getIdCarrito();

	/**
	 * @return El c�digo de barras del producto
	 */
	public String getCodBarras();

	/**
	 * @return La cantidad del producto en el carrito
	 */
	public int getCantidad();
	
	/**
	 * @return Una cadena con la informaci�n b�sica de PRODUCTOS CARRITO
	 */
	public String toString();
}
