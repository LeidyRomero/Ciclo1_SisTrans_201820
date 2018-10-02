package uniandes.isis2304.superAndes.negocio;

public interface VOProductosOfrecidos {

	/**
	 * @return La direcci�n de la sucursal
	 */
	public String getDireccionSucursal();

	/**
	 * @return La ciudad de la sucursal
	 */
	public String getCiudad();

	/**
	 * @return El c�digo de barras del producto
	 */
	public long getCodigoBarras();

	/**
	 * @return Una cadena con la informaci�n b�sica de los productos ofrecidos
	 */
	public String toString();
}
