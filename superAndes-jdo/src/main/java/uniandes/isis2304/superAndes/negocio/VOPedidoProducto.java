package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de ORDEN_PEDIDO
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOPedidoProducto {
	public String getCodigoBarras();
	public long getIdPedido();
	public double getCantidadProducto();
	public double getPrecioProducto();
	/**
	 * @return Una cadena con la información básica del pedido producto
	 */
	public String toString();
}
