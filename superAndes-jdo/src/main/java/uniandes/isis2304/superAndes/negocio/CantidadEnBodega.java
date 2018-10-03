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
	public String darCodigoBarras() {
		return codigoBarras;
	}
	public void cambiarCodigoBarras(String pCodigoBarras)
	{
		this.codigoBarras = pCodigoBarras;
	}
	public String darDireccionBodega() {
		return direccionBodega;
	}
	
	public void cambiarDireccionBodega(String pDireccion)
	{
		this.direccionBodega = pDireccion;
	}


	public String darDireccionSucursal() {
		return direccionSucursal;
	}
	public void cambiarDireccionSucursal(String pDireccion)
	{
		this.direccionSucursal = pDireccion;
	}


	public String darCiudad() {
		return ciudad;
	}
	public void cambiarCiudad(String pCiudad)
	{
		this.ciudad = pCiudad;
	}

	public int darCantidadActual() {
		return cantidadActual;
	}
	public void cambiarCantidadActual(int pCantidad)
	{
		this.cantidadActual = pCantidad;
	}

	public int darCantidadMinima() {
		return cantidadMinima;
	}
	public void cambiarCantidadMinima(int pCantidad)
	{
		this.cantidadMinima = pCantidad;
	}

	public String toString() {
		return "CantidadEnBodega [direccionBodega=" + direccionBodega + ", direccionSucursal=" + direccionSucursal
				+ ", ciudad=" + ciudad + ", cantidadActual=" + cantidadActual + ", cantidadMinima=" + cantidadMinima
				+ ", codigoBarras=" + codigoBarras + "]";
	}

}
