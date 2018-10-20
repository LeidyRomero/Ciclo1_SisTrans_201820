package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los m�todos get de CARRITO
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOCarrito {
	/**
	 * @return correo del cliente
	 */
	public String getCorreo();
	/**
	 * @return id del carrito
	 */
	public long getIdCarrito();
	/**
	 * @return Una cadena con la informaci�n b�sica de los carritos.
	 */
	public String toString();
}
