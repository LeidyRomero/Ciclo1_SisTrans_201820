package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto SUCURSAL del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class Sucursal implements VOSucursal {

	public final static int NIT_SUPERANDES = 0;
	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Tamaño de la sucursal
	 */
	private String tamanio;
	
	/**
	 * Dirección de la sucursal.
	 */
	private String direccion;
	
	/**
	 * Ciudad de la sucursal.
	 */
	private String ciudad;
	
	/**
	 * Nombre de la sucursal.
	 */
	private String nombre;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	/**
	 * Constructor por defecto.
	 */
	public Sucursal()
	{
		ciudad = null;
		tamanio = null;
		nombre = null;
		direccion = null;
	}

	/**
	 * Constructor con valores.
	 * @param tamanio Tamaño. tamanio != null && tamanio != ""
	 * @param direccion Direccion. direccion != null && direccion != ""
	 * @param ciudad Ciudad. ciudad != null && ciudad != ""
	 * @param nombre Nombre. nombre != null && nombre != ""
	 */
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
	
	/**
	 * Retorna el tamaño de la sucursal.
	 */
	public String getTamanio() {
		return tamanio;
	}

	/**
	 * Modifica el tamaño de la sucursal.
	 * @param tamanio Nuevo tamaño
	 */
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	/**
	 * Retorna la dirección de la sucursal.
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Modifica la dirección de la sucursal.
	 * @param direccion Nueva dirección
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Retorna la ciudad de la sucursal.
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Modifica la ciudad de la sucursal.
	 * @param ciudad Nueva ciudad.
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Retorna el nombre de la sucursal.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Modifica el nombre de la sucursal.
	 * @param nombre Nuevo nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public static int getNitSuperandes() {
		return NIT_SUPERANDES;
	}

	@Override
	public String toString() {
		return "Sucursal [tamanio=" + tamanio + ", direccion=" + direccion + ", ciudad=" + ciudad + ", nombre=" + nombre
				+ "]";
	}

}
