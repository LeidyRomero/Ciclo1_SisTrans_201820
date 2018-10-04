package uniandes.isis2304.superAndes.negocio;

/**
 * 
 * @author lj.romero
 *
 */
public class CantidadEnBodega implements VOCantidadEnBodega{
	//----------------------------------------------
	//Atributos
	//----------------------------------------------
	private String direccionBodega;
	private String direccionSucursal;
	private String ciudad;
	private int cantidadActual;
	private int cantidadMinima;
	private String codigoBarras;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	public CantidadEnBodega()
	{
		direccionBodega = "";
		direccionSucursal = "";
		ciudad="";
		cantidadActual = 0;
		cantidadMinima = 0;
		codigoBarras = "";
	}
	public CantidadEnBodega(String pDireccionBodega,String pDireccionSucursal,String pCiudad,int pCantidadActual,int pCantidadMinima,String pCodigoBarras)
	{
		direccionBodega = pDireccionBodega;
		direccionSucursal = pDireccionSucursal;
		ciudad=pCiudad;
		cantidadActual = pCantidadActual;
		cantidadMinima = pCantidadMinima;
		codigoBarras = pCodigoBarras;
	}
	//----------------------------------------------
	//Metodos
	//----------------------------------------------
	public String getDireccionBodega() {
		return direccionBodega;
	}
	public void setDireccionBodega(String direccionBodega) {
		this.direccionBodega = direccionBodega;
	}
	public String getDireccionSucursal() {
		return direccionSucursal;
	}
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getCantidadActual() {
		return cantidadActual;
	}
	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}
	public int getCantidadMinima() {
		return cantidadMinima;
	}
	public void setCantidadMinima(int cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	@Override
	public String toString() {
		return "CantidadEnBodega [direccionBodega=" + direccionBodega + ", direccionSucursal=" + direccionSucursal
				+ ", ciudad=" + ciudad + ", cantidadActual=" + cantidadActual + ", cantidadMinima=" + cantidadMinima
				+ ", codigoBarras=" + codigoBarras + "]";
	}
	

}
