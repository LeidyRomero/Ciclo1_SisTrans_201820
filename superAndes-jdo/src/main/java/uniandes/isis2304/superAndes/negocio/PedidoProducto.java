package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto PEDIDO PRODUCTO del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class PedidoProducto implements VOPedidoProducto{
	/**
	 * codigo del producto
	 */
	private String codigoBarras;
	/**
	 * id del pedido
	 */
	private long idPedido;
	/**
	 * numero de unidades pedidas
	 */
	private double cantidadProducto;
	/**
	 * precio del producto
	 */
	private double precioProducto;
	/**
	 * constructor por defecto
	 */
	public PedidoProducto()
	{
		codigoBarras = "";
		idPedido = 0;
		cantidadProducto=0;
		precioProducto=0;
	}
	/**
	 * crea un nuevo pedido producto con los valores recibidos por parametro
	 */
	public PedidoProducto(String pCodigoBarras, long pId, double pCantidadProducto, double pPrecioProducto )
	{
		codigoBarras = pCodigoBarras;
		idPedido = pId;
		cantidadProducto=pCantidadProducto;
		precioProducto=pPrecioProducto;
	}
	/**
	 * retorna el codigo del producto
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}
	/**
	 * cambia el codigo del producto por el recibido por parametro
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	/**
	 * retorna el id del pedido
	 */
	public long getIdPedido() {
		return idPedido;
	}
	/**
	 * cambia el id del pedido por el recibido por parametro
	 */
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	/**
	 * retonra la cantidad pedida
	 */
	public double getCantidadProducto() {
		return cantidadProducto;
	}
	/**
	 * cambia el numero de unidades pedido
	 */
	public void setCantidadProducto(double cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	/**
	 * retorna el precio del producto
	 */
	public double getPrecioProducto() {
		return precioProducto;
	}
	/**
	 * cambia el precio del producto por el recibid por parametro
	 */
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	/**
	 * retorna un string con la informacion del pedido producto
	 */
	public String toString() {
		return "PedidoProducto [codigoBarras=" + codigoBarras + ", idPedido=" + idPedido + ", cantidadProducto="
				+ cantidadProducto + ", precioProducto=" + precioProducto + "]";
	}
	
}
