package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public interface VOComprados {
	public String darCodigoBarras();
	public String darIdFactura();
	public int darCantidad();
	public double darPrecioTotal();
	public String toString();
}
