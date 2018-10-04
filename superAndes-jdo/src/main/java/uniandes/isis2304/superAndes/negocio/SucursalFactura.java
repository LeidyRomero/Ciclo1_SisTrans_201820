package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto SUCURSAL FACTURA del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class SucursalFactura implements VOSucursalFactura {

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Dirección de la sucursal
	 */
	private String direccionSucursal;
	
	/**
	 * Ciudad de la sucursal
	 */
	private String ciudad;
	
	/**
	 * Id de la factura asociadad a la sucursal
	 */
	private long idFactura;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	/**
	 * Constructor por defecto
	 */
	public SucursalFactura()
	{
		direccionSucursal = null;
		ciudad = null;
		idFactura = 0;
	}

	/**
	 * Constructor con valores
	 * @param direccionSucursal
	 * @param ciudadSucursal
	 * @param idFactura
	 */
	public SucursalFactura(String direccionSucursal, String ciudadSucursal, long idFactura) 
	{
		this.direccionSucursal = direccionSucursal;
		this.ciudad = ciudadSucursal;
		this.idFactura = idFactura;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna la dirección de la sucursal
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * Modifica la dirección de la sucursal
	 * @param direccionSucursal Nueva direccion
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	/**
	 * Retorna la ciudad de la sucursal
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Modifica la ciudad de la sucursal 
	 * @param ciudadSucursal Nueva ciudad
	 */
	public void setCiudad(String ciudadSucursal) {
		this.ciudad = ciudadSucursal;
	}

	/**
	 * Retorna el id de la factura
	 */
	public long getIdFactura() {
		return idFactura;
	}

	/**
	 *  Modifica el id de la factura
	 * @param idFactura Nuevo id
	 */
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	@Override
	public String toString() {
		return "SucursalCliente [direccionSucursal=" + direccionSucursal + ", ciudadSucursal=" + ciudad
				+ ", correoCliente=" + idFactura + "]";
	}
	
}
