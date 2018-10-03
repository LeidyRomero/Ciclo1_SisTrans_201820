package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class PedidoProducto implements VOPedidoProducto{
	private String codigoBarras;
	private long id;
	private double cantidadProducto;
	private double precioProducto;
	public PedidoProducto()
	{
		codigoBarras = "";
		id = 0;
		cantidadProducto=0;
		precioProducto=0;
	}
	public PedidoProducto(String pCodigoBarras, long pId, double pCantidadProducto, double pPrecioProducto )
	{
		codigoBarras = pCodigoBarras;
		id = pId;
		cantidadProducto=pCantidadProducto;
		precioProducto=pPrecioProducto;
	}
	//TODO SET
	public String darCodigoBarras() {
		return codigoBarras;
	}

	public long darIdPedido() {
		return id;
	}

	public double darCantidadProducto() {
		return cantidadProducto;
	}
	public double darPrecioProducto() {
		return precioProducto;
	}

	public String toString() {
		return "PedidoProducto [codigoBarras=" + codigoBarras + ", id=" + id + ", cantidadProducto=" + cantidadProducto
				+ ", precioProducto=" + precioProducto + "]";
	}
	
}
