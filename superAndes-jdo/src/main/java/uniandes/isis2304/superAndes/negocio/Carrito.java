package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto CARRITO del negocio SuperAndes
 * @author María Ocampo - mj.ocampov
 */
public class Carrito implements VOCarrito{
	
	//--------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------
	/**
	 * Dirección de la sucursal donde se pide el carrito
	 */
	private String direccionSucursal;
	
	/**
	 * Ciudad de la sucursal donde se pide el carrito
	 */
	private String ciudad;
	
	/**
	 * Identificador del carrito
	 */
	private long idCarrito;
	
	/**
	 * Correo del cliente que pide el carrito
	 */
	private String correoCliente;

	//--------------------------------------------------
	// CONSTRUCTORES
	//--------------------------------------------------
	
	/**
	 * Constructor con parámetros
	 * @param direccionSucursal - La dirección de la sucursal donde se pide el carrito
	 * @param ciudad - La ciudad de la sucursal donde se pide el carrito
	 * @param idCarrito - El identificador del carrito
	 * @param correoCliente - El correo del cliente que pide el carrito
	 */
	public Carrito(String direccionSucursal, String ciudad, long idCarrito, String correoCliente) {
		this.direccionSucursal = direccionSucursal;
		this.ciudad = ciudad;
		this.idCarrito = idCarrito;
		this.correoCliente = correoCliente;
	}
	
	/**
	 * Constructor por defecto
	 */
	public Carrito() 
	{
		direccionSucursal = "";
		ciudad = "";
		idCarrito = 0;
		correoCliente = "";
	}

	//--------------------------------------------------
	// MÉTODOS
	//--------------------------------------------------
	/**
	 * Retorna la dirección de la sucursal del carrito
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * Modifica la dirección de la sucursal del carrito
	 * @param direccionSucursal Nueva dirección
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	/**
	 * Retorna la ciudad de la sucursal del carrito
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Modifica la ciudad de la susucursal del carrito
	 * @param ciudad Nueva ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Retorna el identificador del carrito
	 */
	public long getIdCarrito() {
		return idCarrito;
	}

	/**
	 * Modifica el identificador del carrito
	 * @param idCarrito Nuevo identificador
	 */
	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	/**
	 * Retorna el correo del cliente del carrito
	 */
	public String getCorreoCliente() {
		return correoCliente;
	}

	/**
	 * Modifica el correo del cliente del carrito
	 * @param correoCliente Nuevo correo del cliente
	 */
	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	@Override
	public String toString() {
		return "Carrito [direccionSucursal=" + direccionSucursal + ", ciudad=" + ciudad + ", idCarrito=" + idCarrito
				+ ", correoCliente=" + correoCliente + "]";
	}
}
