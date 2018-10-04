package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de CANTIDAD_EN_BODEGA
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOCantidadEnBodega {
	/**
	 * @return identificador del producto
	 */
	public String getCodigoBarras();
	/**
	 * @return direccion de la bodega
	 */
	public String getDireccionBodega();
	/**
	 * @return direccion de la sucursal
	 */
	public String getDireccionSucursal();
	/**
	 * @return ciudad de la sucursal
	 */
	public String getCiudad();
	/**
	 * @return cantidadActual
	 */
	public int getCantidadActual();
	/**
	 * @return cantidadMinima
	 */
	public int getCantidadMinima();
	/**
	 * @return Una cadena con la información básica de la cantidad en bodegas.
	 */
	public String toString();
}
