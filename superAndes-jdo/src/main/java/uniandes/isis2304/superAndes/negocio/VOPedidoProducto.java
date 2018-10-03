package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public interface VOPedidoProducto {
	public String darCodigoBarras();
	public long darIdPedido();
	public double darCantidadProducto();
	public double darPrecioProducto();
	public String toString();
}
