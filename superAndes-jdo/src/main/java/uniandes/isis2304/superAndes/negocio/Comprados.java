package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto COMPRADOS del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class Comprados implements VOComprados{
	//----------------------------------------------
	//Atringutos
	//----------------------------------------------
	/**
	 * codigo de barras del producto comprado
	 */
	private String codigoBarras;
	/**
	 * cantidad de unidades compradas
	 */
	private int cantidad;
	/**
	 * precio total de las cantidades compradas
	 */
	private double precioTotal;
	/**
	 * id de la factura
	 */
	private long idFactura;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	/**
	 * Constructor por defecto
	 */
	public Comprados()
	{
		codigoBarras = "";
		cantidad = 0;
		precioTotal=0;
		idFactura = 0;
	}
	/**
	 * Crea una nueva compra
	 * @param pCodigoBarras codigo del producto
	 * @param pCantidad numero de unidades compradas
	 * @param pPrecioTotal precio total de la compra
	 * @param pIdFactura id factura
	 */
	public Comprados(String pCodigoBarras, int pCantidad, double pPrecioTotal, long pIdFactura)
	{
		codigoBarras = pCodigoBarras;
		cantidad = pCantidad;
		precioTotal=pPrecioTotal;
		idFactura = pIdFactura;
	}
	//----------------------------------------------
	//Metodos
	//----------------------------------------------
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
	 * retorna el numero de unidades vendidas
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * cambia el numero de unidades vendidas por el recibido por parametro
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	/**
	 * retorna el precio total
	 */
	public double getPrecioTotal() {
		return precioTotal;
	}
	/**
	 * cambia el precio total por el recibido por parametro
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	/**
	 * retorna el id de la factura
	 */
	public long getIdFactura() {
		return idFactura;
	}
	/**
	 * cambia el id de la factura por el id recibido por parametro
	 */
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}
	/**
	 * retorna un string con la informacion de la compra
	 */
	public String toString() {
		return "Comprados [codigoBarras=" + codigoBarras + ", cantidad=" + cantidad + ", precioTotal=" + precioTotal
				+ ", idFactura=" + idFactura + "]";
	}

}
