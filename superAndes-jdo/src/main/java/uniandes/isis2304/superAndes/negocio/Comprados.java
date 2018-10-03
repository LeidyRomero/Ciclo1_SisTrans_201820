package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Comprados implements VOComprados{

	private String codigoBarras;
	private int cantidad;
	private double precioTotal;
	private long idFactura;

	public Comprados()
	{
		codigoBarras = "";
		cantidad = 0;
		precioTotal=0;
		idFactura = 0;
	}
	public Comprados(String pCodigoBarras, int pCantidad, double pPrecioTotal, long pIdFactura)
	{
		codigoBarras = pCodigoBarras;
		cantidad = pCantidad;
		precioTotal=pPrecioTotal;
		idFactura = pIdFactura;
	}
	//TODO SET de esta clase
	public String darCodigoBarras() {
		return codigoBarras;
	}

	public long darIdFactura() {
		return idFactura;
	}

	public int darCantidad() {
		return cantidad;
	}

	public double darPrecioTotal() {
		return precioTotal;
	}
	//TODO completar
	@Override
	public String toString() {
		return "Comprados [codigoBarras=" + codigoBarras + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal
				+ ", idFactura=" + idFactura + "]";
	}
}
