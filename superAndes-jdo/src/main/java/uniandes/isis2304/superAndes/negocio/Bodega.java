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
	private String direccion;
	private double peso;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	public Bodega()
	{
		this.tipo = "";
		this.volumen = 0;
		this.peso = 0;
		this.direccion = "";
	}
	public Bodega(String pDireccion, String pTipo, double pPeso, double pVolumen)
	{
		this.volumen = pVolumen;
		this.peso = pPeso;
		this.direccion=pDireccion;
		this.tipo = pTipo;
	}
	//----------------------------------------------
	//Metodos
	//----------------------------------------------
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
		return direccion;
	}
	public void cambiarDireccion(String pDireccion) {
		this.direccion = pDireccion;
	}
	public String toString() {
		return "Bodega [tipo = "+tipo+", volumen"+volumen+", peso"+peso+", direccion"+direccion+"]";
	}
}
