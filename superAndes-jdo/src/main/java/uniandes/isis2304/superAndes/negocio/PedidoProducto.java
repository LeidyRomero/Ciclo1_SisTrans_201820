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
	//TOTO SET
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
	//TODO
	public String toString()
	{
		return "";
	}
	
}
