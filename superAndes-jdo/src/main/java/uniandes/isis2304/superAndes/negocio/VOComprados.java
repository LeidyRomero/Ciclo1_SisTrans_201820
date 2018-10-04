package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de COMPRADOS
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOComprados {
	/**
	 * @return codigo del producto
	 */
	public String getCodigoBarras();
	/**
	 * @return id de la factura
	 */
	public long getIdFactura();
	/**
	 * @return cantidad de productos
	 */
	public int getCantidad();
	/**
	 * @return precio por total del mismo producto
	 */
	public double getPrecioTotal();
	/**
	 * @return Una cadena con la información básica de las compras
	 */
	public String toString();
}
