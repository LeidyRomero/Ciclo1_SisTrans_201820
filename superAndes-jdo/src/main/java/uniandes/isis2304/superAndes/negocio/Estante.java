package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto ESTANTE del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class Estante implements VOEstante{
	/**
	 * 
	 */
	private String ciudad;
	/**
	 * 
	 */
	private String direccionSucursal;
	/**
	 * 
	 */
	private String tipoEstante;
	/**
	 * 
	 */
	private double nivelAbastecimiento;
	/**
	 * 
	 */
	private double pesoEstante;
	/**
	 * 
	 */
	private double volumenEstante;
	/**
	 * 
	 */
	private long idEstante;
	/**
	 * 
	 */
	public Estante()
	{
		ciudad="";
		direccionSucursal = "";
		tipoEstante = "";
		nivelAbastecimiento = 0;
		pesoEstante = 0;
		volumenEstante = 0;
		idEstante = 0;
	}
	/**
	 * 
	 * @param pCiudad
	 * @param pDireccionSucursal
	 * @param pTipo
	 * @param pNivelAbastecimiento
	 * @param pPeso
	 * @param pVolumen
	 * @param pId
	 */
	public Estante(String pCiudad, String pDireccionSucursal, String pTipo, double pNivelAbastecimiento, double pPeso, double pVolumen, long pId)
	{
		ciudad=pCiudad;
		direccionSucursal = pDireccionSucursal;
		tipoEstante = pTipo;
		nivelAbastecimiento = pNivelAbastecimiento;
		pesoEstante = pPeso;
		volumenEstante = pVolumen;
		idEstante = pId;
	}
	/**
	 * 
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * 
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * 
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}
	/**
	 * 
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	/**
	 * 
	 */
	public String getTipoEstante() {
		return tipoEstante;
	}
	/**
	 * 
	 */
	public void setTipoEstante(String tipoEstante) {
		this.tipoEstante = tipoEstante;
	}
	/**
	 * 
	 */
	public double getNivelAbastecimiento() {
		return nivelAbastecimiento;
	}
	/**
	 * 
	 */
	public void setNivelAbastecimiento(double nivelAbastecimiento) {
		this.nivelAbastecimiento = nivelAbastecimiento;
	}
	/**
	 * 
	 */
	public double getPesoEstante() {
		return pesoEstante;
	}
	/**
	 * 
	 */
	public void setPesoEstante(double pesoEstante) {
		this.pesoEstante = pesoEstante;
	}
	/**
	 * 
	 */
	public double getVolumenEstante() {
		return volumenEstante;
	}
	/**
	 * 
	 */
	public void setVolumenEstante(double volumenEstante) {
		this.volumenEstante = volumenEstante;
	}
	/**
	 * 
	 */
	public long getIdEstante() {
		return idEstante;
	}
	/**
	 * 
	 */
	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
	}
	/**
	 * 
	 */
	public String toString() {
		return "Estante [ciudad=" + ciudad + ", direccionSucursal=" + direccionSucursal + ", tipoEstante=" + tipoEstante
				+ ", nivelAbastecimiento=" + nivelAbastecimiento + ", pesoEstante=" + pesoEstante + ", volumenEstante="
				+ volumenEstante + ", idEstante=" + idEstante + "]";
	}
	
	
}
