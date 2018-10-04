package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto CANTIDAD EN BODEGA del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class CantidadEnBodega implements VOCantidadEnBodega{
	//----------------------------------------------
	//Atributos
	//----------------------------------------------
	/**
	 * direccion de la bodega
	 */
	private String direccionBodega;
	/**
	 * direccion de la sucursal
	 */
	private String direccionSucursal;
	/**
	 * direccion de la ciudad
	 */
	private String ciudad;
	/**
	 * numero de productos en la bodega
	 */
	private int cantidadActual;
	/**
	 * numero de productos minimo para hacer un pedido
	 */
	private int cantidadMinima;
	/**
	 * codigo de barras del producto
	 */
	private String codigoBarras;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	/**
	 * Cnstructor por defecto
	 */
	public CantidadEnBodega()
	{
		direccionBodega = "";
		direccionSucursal = "";
		ciudad="";
		cantidadActual = 0;
		cantidadMinima = 0;
		codigoBarras = "";
	}
	/**
	 * Crea un nuevo inventario de bodega
	 * @param pDireccionBodega direccion de la bodega
	 * @param pDireccionSucursal direccion de la sucursal
	 * @param pCiudad ciudad de la sucursal
	 * @param pCantidadActual numero de productos en bodega
	 * @param pCantidadMinima numero de productos minimos
	 * @param pCodigoBarras codigo de barras del producto
	 */
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
	/**
	 * retorna la direccion de la bodega
	 */
	public String getDireccionBodega() {
		return direccionBodega;
	}
	/**
	 * cambia la direccion de la bodega por la recibida por parametro
	 */
	public void setDireccionBodega(String direccionBodega) {
		this.direccionBodega = direccionBodega;
	}
	/**
	 * retorna la direccion de la sucursal
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}
	/**
	 * cambia la direccion de la sucursal por la recibida por parametro
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	/**
	 * retorna la ciudad de la sucursal
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * cambia la ciudad de la sucursal por la ciudad recibida por parametro
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * retorna el numero de productos existentes en la bodega
	 */
	public int getCantidadActual() {
		return cantidadActual;
	}
	/**
	 * cambia el numero de productos existentes en la bodega por el numero recibido por parametro
	 */
	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}
	/**
	 * retorna el numero minimo de productos de la bodega
	 */
	public int getCantidadMinima() {
		return cantidadMinima;
	}
	/**
	 * cambia el numero minimo de productos en bodega por el numero recibido por parametro
	 */
	public void setCantidadMinima(int cantidadMinima) {
		this.cantidadMinima = cantidadMinima;
	}
	/**
	 * retorna el codigo de barras del producto
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}
	/**
	 * cambia el codigo de barras del producto
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	/**
	 * Retorna un string con la informacion del inventario
	 */
	public String toString() {
		return "CantidadEnBodega [direccionBodega=" + direccionBodega + ", direccionSucursal=" + direccionSucursal
				+ ", ciudad=" + ciudad + ", cantidadActual=" + cantidadActual + ", cantidadMinima=" + cantidadMinima
				+ ", codigoBarras=" + codigoBarras + "]";
	}
	

}
