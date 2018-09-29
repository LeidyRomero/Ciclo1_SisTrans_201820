package uniandes.isis2304.superAndes.negocio;

public class Sucursal implements VOSucursal {

	public final static int NIT_SUPERANDES = 0;
	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * 
	 */
	private String tamanio;
	
	/**
	 * 
	 */
	private String direccion;
	
	/**
	 * 
	 */
	private String ciudad;
	
	/**
	 * 
	 */
	private String nombre;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	public Sucursal()
	{
		ciudad = null;
		tamanio = null;
		nombre = null;
		direccion = null;
	}

	public Sucursal(String tamanio, String direccion, String ciudad, String nombre) 
	{
		this.tamanio = tamanio;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.nombre = nombre;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	public String getTamanio() {
		return tamanio;
	}

	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public static int getNitSuperandes() {
		return NIT_SUPERANDES;
	}

	@Override
	public String toString() {
		return "Sucursal [tamanio=" + tamanio + ", direccion=" + direccion + ", ciudad=" + ciudad + ", nombre=" + nombre
				+ "]";
	}

}
