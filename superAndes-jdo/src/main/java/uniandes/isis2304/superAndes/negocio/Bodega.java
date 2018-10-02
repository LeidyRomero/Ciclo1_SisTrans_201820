package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Bodega implements VOBodega{

	//----------------------------------------------
	//Atributos
	//----------------------------------------------
	private String tipo;
	private double volumen;
	private String direccionBodega;
	private double peso;
	private String direccionSucursal;
	private String ciudad;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	public Bodega()
	{
		this.tipo = "";
		this.volumen = 0;
		this.peso = 0;
		this.direccionBodega = "";
		this.direccionSucursal = "";
		this.ciudad = "";
	}
	public Bodega(String pDireccion, String pTipo, double pPeso, double pVolumen, String pDireccionSucursal, String pCiudad)
	{
		this.volumen = pVolumen;
		this.peso = pPeso;
		this.direccionBodega=pDireccion;
		this.tipo = pTipo;
		this.direccionSucursal = "";
		this.ciudad = "";
	}
	//----------------------------------------------
	//Metodos
	//----------------------------------------------
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
	public String darTipo() {
		return tipo;
	}
	public void cambiarTipo(String pTipo) {
		this.tipo = pTipo;
	}

	public double darVolumen() {
		return volumen;
	}
	public void cambiarVolumen(double pVolumen) {
		this.volumen = pVolumen;
	}
	public double darPeso() {
		return peso;
	}
	public void cambiarPeso(double pPeso) {
		this.peso = pPeso;
	}
	public String darDireccion() {
		return direccionBodega;
	}
	public void cambiarDireccion(String pDireccion) {
		this.direccionBodega = pDireccion;
	}

	public String toString() {
		return "Bodega [tipo=" + tipo + ", volumen=" + volumen + ", direccionBodega=" + direccionBodega + ", peso="
				+ peso + ", direccionSucursal=" + direccionSucursal + ", ciudad=" + ciudad + "]";
	}
	
}
