package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de CANTIDAD EN ESTANTES.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public interface VOCantidadEnEstantes {

	/**
	 * @return La cantidad actual de un producto en un estante.
	 */
	public int getCantidadActual();

	/**
	 * @return La cantidad m�nima de un producto en un estante.
	 */
	public int getCantidadMinima();

	/**
	 * @return El c�digo de barras del producto.
	 */
	public String getCodigoBarrasProducto();

	/**
	 * @return El id del estante.
	 */
	public long getIdEstante();
	
	/**
	 * @return Una cadena con la informaci�n b�sica de la cantidad en estantes.
	 */
	public String toString();
}
