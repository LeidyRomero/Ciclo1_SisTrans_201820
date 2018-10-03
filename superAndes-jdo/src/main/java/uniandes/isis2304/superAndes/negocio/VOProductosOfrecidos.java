package uniandes.isis2304.superAndes.negocio;

public interface VOProductosOfrecidos {

	/**
	 * @return La dirección de la sucursal
	 */
	public String getDireccionSucursal();

	/**
	 * @return La ciudad de la sucursal
	 */
	public String getCiudad();

	/**
	 * @return El código de barras del producto
	 */
	public String getCodigoBarras();

	/**
	 * @return Una cadena con la información básica de los productos ofrecidos
	 */
	public String toString();
}
