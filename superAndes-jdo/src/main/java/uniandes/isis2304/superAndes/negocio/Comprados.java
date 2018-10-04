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
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecioTotal() {
		return precioTotal;
	}
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	public long getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	@Override
	public String toString() {
		return "Comprados [codigoBarras=" + codigoBarras + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal
				+ ", idFactura=" + idFactura + "]";
	}

}
