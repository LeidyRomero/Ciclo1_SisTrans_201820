package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Comprados implements VOComprados{

	private int codigoBarras;
	private int cantidad;
	private double precioTotal;
	private String idFactura;

	public Comprados()
	{
		codigoBarras = 0;
		cantidad = 0;
		precioTotal=0;
		idFactura = "";
	}
	public Comprados(int pCodigoBarras, int pCantidad, double pPrecioTotal, String pIdFactura)
	{
		codigoBarras = pCodigoBarras;
		cantidad = pCantidad;
		precioTotal=pPrecioTotal;
		idFactura = pIdFactura;
	}
	//TODO SET de esta clase
	public int darCodigoBarras() {
		return codigoBarras;
	}

	public String darIdFactura() {
		return idFactura;
	}

	public int darCantidad() {
		return cantidad;
	}

	public double darPrecioTotal() {
		return precioTotal;
	}
	//TODO completar
	public String toString()
	{
		return "";
	}
}
