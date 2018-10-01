package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Estante implements VOEstante{
	private String ciudad;
	private String direccionSucursal;
	private String tipo;
	private double nivelAbastecimiento;
	private double peso;
	private double volumen;
	private long id;

	public Estante()
	{
		ciudad="";
		direccionSucursal = "";
		tipo = "";
		nivelAbastecimiento = 0;
		peso = 0;
		volumen = 0;
		id = 0;
	}
	public Estante(String pCiudad, String pDireccionSucursal, String pTipo, double pNivelAbastecimiento, double pPeso, double pVolumen, long pId)
	{
		ciudad=pCiudad;
		direccionSucursal = pDireccionSucursal;
		tipo = pTipo;
		nivelAbastecimiento = pNivelAbastecimiento;
		peso = pPeso;
		volumen = pVolumen;
		id = pId;
	}
	public String darCiudad() {
		return ciudad;
	}
	//TODO REVISAR SET
	public void cambiarCiudad(String pCiudad)
	{
		this.ciudad = pCiudad;
	}
	public String darDireccionSucursal() {
		return direccionSucursal;
	}
	public void cambiarDireccionSucursal(String pDireccion)
	{
		this.direccionSucursal=pDireccion;
	}
	public double darNivelAbastecimiento() {
		return nivelAbastecimiento;
	}
	public void cambiarNivelAbastecimiento(double pNivel)
	{
		this.nivelAbastecimiento = pNivel;
	}
	public double darPeso() {
		return peso;
	}
	public void cambiarPeso(double pPeso)
	{
		this.peso=pPeso;
	}
	public long darId() {
		return id;
	}
//TODO SET ID
	public double darVolumen() {
		return volumen;
	}
	public void cambiarVolumen(double pVolumen)
	{
		this.volumen=pVolumen;
	}
	public String darTipo() {

		return tipo;
	}
	public void cambiarTipo(String pTipo)
	{
		this.tipo=pTipo;
	}

	public String toString() {
		return "Estante [ciudad=" + ciudad + ", direccionSucursal=" + direccionSucursal + ", tipo=" + tipo
				+ ", nivelAbastecimiento=" + nivelAbastecimiento + ", peso=" + peso + ", volumen=" + volumen + ", id="
				+ id + "]";
	}
	
	
}
