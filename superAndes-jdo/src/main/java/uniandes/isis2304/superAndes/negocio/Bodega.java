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
	private String tipoBodega;
	private double volumenBodega;
	private String direccionBodega;
	private double pesoBodega;
	private String direccionSucursal;
	private String ciudad;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	public Bodega()
	{
		this.tipoBodega = "";
		this.volumenBodega = 0;
		this.pesoBodega = 0;
		this.direccionBodega = "";
		this.direccionSucursal = "";
		this.ciudad = "";
	}
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
	public String getTipoBodega() {
		return tipoBodega;
	}
	public void setTipoBodega(String tipoBodega) {
		this.tipoBodega = tipoBodega;
	}
	public double getVolumenBodega() {
		return volumenBodega;
	}
	public void setVolumenBodega(double volumenBodega) {
		this.volumenBodega = volumenBodega;
	}
	public String getDireccionBodega() {
		return direccionBodega;
	}
	public void setDireccionBodega(String direccionBodega) {
		this.direccionBodega = direccionBodega;
	}
	public double getPesoBodega() {
		return pesoBodega;
	}
	public void setPesoBodega(double pesoBodega) {
		this.pesoBodega = pesoBodega;
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
	@Override
	public String toString() {
		return "Bodega [tipoBodega=" + tipoBodega + ", volumenBodega=" + volumenBodega + ", direccionBodega="
				+ direccionBodega + ", pesoBodega=" + pesoBodega + ", direccionSucursal=" + direccionSucursal
				+ ", ciudad=" + ciudad + "]";
	}

	
}
