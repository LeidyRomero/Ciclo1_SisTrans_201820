package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto BODEGA del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class Bodega implements VOBodega{

	//----------------------------------------------
	//Atributos
	//----------------------------------------------
	/**
	 * Tipo de productos que puede almacenar la bodega.
	 */
	private String tipoBodega;
	/**
	 * Capacidad total volumetrica de la bodega
	 */
	private double volumenBodega;
	/**
	 * Direccion de la bodega
	 */
	private String direccionBodega;
	/**
	 * Capacidad total en peso de la bodega
	 */
	private double pesoBodega;
	/**
	 * Direccion de la sucursal
	 */
	private String direccionSucursal;
	/**
	 * Direccion de la ciudad
	 */
	private String ciudad;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	/**
	 * Constructor por defecto
	 */
	public Bodega()
	{
		this.tipoBodega = "";
		this.volumenBodega = 0;
		this.pesoBodega = 0;
		this.direccionBodega = "";
		this.direccionSucursal = "";
		this.ciudad = "";
	}
	/**
	 * Crea una nueva bodega con los valores ingresados por parametro
	 * @param pDireccion direccion de la bodega
	 * @param pTipo tipo de la bodega
	 * @param pPeso peso de la bodega
	 * @param pVolumen volumen de la bodega
	 * @param pDireccionSucursal direccion de la sucursal
	 * @param pCiudad ciudad de la sucursal
	 */
	public Bodega(String pDireccion, String pTipo, double pPeso, double pVolumen, String pDireccionSucursal, String pCiudad)
	{
		this.volumenBodega = pVolumen;
		this.pesoBodega = pPeso;
		this.direccionBodega=pDireccion;
		this.tipoBodega = pTipo;
		this.direccionSucursal = "";
		this.ciudad = "";
	}
	//----------------------------------------------
	//Metodos
	//----------------------------------------------
	/**
	 * @return retorna el tipo de la bodega
	 */
	public String getTipoBodega() {
		return tipoBodega;
	}
	/**
	 * Cambia el tipo de la bodega por el recibido por parametro
	 */
	public void setTipoBodega(String tipoBodega) {
		this.tipoBodega = tipoBodega;
	}
	/**
	 * @return retorna el volumen de la bodega
	 */
	public double getVolumenBodega() {
		return volumenBodega;
	}
	/**
	 * Cambia el volumen de la bodega por el recibido por parametro
	 */
	public void setVolumenBodega(double volumenBodega) {
		this.volumenBodega = volumenBodega;
	}
	/**
	 * @return retorna la direccion de la bodega
	 */
	public String getDireccionBodega() {
		return direccionBodega;
	}
	/**
	 * Cambia la direccion de la bodega por la recibido por parametro
	 */
	public void setDireccionBodega(String direccionBodega) {
		this.direccionBodega = direccionBodega;
	}
	/**
	 * @return retorna el peso de la bodega
	 */
	public double getPesoBodega() {
		return pesoBodega;
	}
	/**
	 * Cambia el peso de la bodega por el recibido por parametro
	 */
	public void setPesoBodega(double pesoBodega) {
		this.pesoBodega = pesoBodega;
	}
	/**
	 * @return retorna la direccion de la bodega
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}
	/**
	 * Cambia la direccion de la sucursal por la recibida por parametro
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	/**
	 * @return retorna la ciudad de la bodega
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * Cambia la ciudad de la sucursal por la recibida por parametro
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @return string con la informacion de la bodega
	 */
	public String toString() {
		return "Bodega [tipoBodega=" + tipoBodega + ", volumenBodega=" + volumenBodega + ", direccionBodega="
				+ direccionBodega + ", pesoBodega=" + pesoBodega + ", direccionSucursal=" + direccionSucursal
				+ ", ciudad=" + ciudad + "]";
	}


}
