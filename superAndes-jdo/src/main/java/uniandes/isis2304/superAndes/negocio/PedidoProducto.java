package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class PedidoProducto implements VOPedidoProducto{
	private int codigoBarras;
	private long id;
	private double cantidadProducto;
	private double precioProducto;
	public PedidoProducto()
	{
		codigoBarras = 0;
		id = 0;
		cantidadProducto=0;
		precioProducto=0;
	}
	public PedidoProducto(int pCodigoBarras, long pId, double pCantidadProducto, double pPrecioProducto )
	{
		codigoBarras = pCodigoBarras;
		id = pId;
		cantidadProducto=pCantidadProducto;
		precioProducto=pPrecioProducto;
	}
	//TODO SET
	public int darCodigoBarras() {
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
