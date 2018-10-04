package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class PedidoProducto implements VOPedidoProducto{
	private String codigoBarras;
	private long idPedido;
	private double cantidadProducto;
	private double precioProducto;
	public PedidoProducto()
	{
		codigoBarras = "";
		idPedido = 0;
		cantidadProducto=0;
		precioProducto=0;
	}
	public PedidoProducto(String pCodigoBarras, long pId, double pCantidadProducto, double pPrecioProducto )
	{
		codigoBarras = pCodigoBarras;
		idPedido = pId;
		cantidadProducto=pCantidadProducto;
		precioProducto=pPrecioProducto;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public long getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}
	public double getCantidadProducto() {
		return cantidadProducto;
	}
	public void setCantidadProducto(double cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}
	public double getPrecioProducto() {
		return precioProducto;
	}
	public void setPrecioProducto(double precioProducto) {
		this.precioProducto = precioProducto;
	}
	@Override
	public String toString() {
		return "PedidoProducto [codigoBarras=" + codigoBarras + ", idPedido=" + idPedido + ", cantidadProducto="
				+ cantidadProducto + ", precioProducto=" + precioProducto + "]";
	}
	
}
