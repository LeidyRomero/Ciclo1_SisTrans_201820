package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de CANTIDAD EN ESTANTES.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOCantidadEnEstantes {

	/**
	 * @return La cantidad actual de un producto en un estante.
	 */
	public int getCantidadActual();

	/**
	 * @return La cantidad mínima de un producto en un estante.
	 */
	public int getCantidadMinima();

	/**
	 * @return El código de barras del producto.
	 */
	public String getCodigoBarrasProducto();

	/**
	 * @return El id del estante.
	 */
	public long getIdEstante();
	
	/**
	 * @return Una cadena con la información básica de la cantidad en estantes.
	 */
	public String toString();
}
