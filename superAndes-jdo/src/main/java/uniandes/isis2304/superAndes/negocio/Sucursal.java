package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto SUCURSAL del negocio de los SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public class Sucursal implements VOSucursal {

	public final static int NIT_SUPERANDES = 0;
	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Tama�o de la sucursal
	 */
	private String tamanio;
	
	/**
	 * Direcci�n de la sucursal.
	 */
	private String direccionSucursal;
	
	/**
	 * Ciudad de la sucursal.
	 */
	private String ciudad;
	
	/**
	 * Nombre de la sucursal.
	 */
	private String nombreSucursal;
	
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
		nombreSucursal = null;
		direccionSucursal = null;
	}

	/**
	 * Constructor con valores.
	 * @param tamanio Tama�o. tamanio != null && tamanio != ""
	 * @param direccion Direccion. direccion != null && direccion != ""
	 * @param ciudad Ciudad. ciudad != null && ciudad != ""
	 * @param nombre Nombre. nombre != null && nombre != ""
	 */
	public Sucursal(String tamanio, String direccion, String ciudad, String nombre) 
	{
		this.tamanio = tamanio;
		this.direccionSucursal = direccion;
		this.ciudad = ciudad;
		this.nombreSucursal = nombre;
	}

	//-----------------------------------------------------
	// M�TODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna el tama�o de la sucursal.
	 */
	public String getTamanio() {
		return tamanio;
	}

	/**
	 * Modifica el tama�o de la sucursal.
	 * @param tamanio Nuevo tama�o
	 */
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}

	/**
	 * Retorna la direcci�n de la sucursal.
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * Modifica la direcci�n de la sucursal.
	 * @param direccion Nueva direcci�n
	 */
	public void setDireccionSucursal(String direccion) {
		this.direccionSucursal = direccion;
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
	public String getNombreSucursal() {
		return nombreSucursal;
	}

	/**
	 * Modifica el nombre de la sucursal.
	 * @param nombre Nuevo nombre.
	 */
	public void setNombreSucursal(String nombre) {
		this.nombreSucursal = nombre;
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
		return "Sucursal [tamanio=" + tamanio + ", direccion=" + direccionSucursal + ", ciudad=" + ciudad + ", nombre=" + nombreSucursal
				+ "]";
	}

}
