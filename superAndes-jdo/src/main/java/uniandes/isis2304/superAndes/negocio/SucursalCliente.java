package uniandes.isis2304.superAndes.negocio;

public class SucursalCliente implements VOSucursalCliente {

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * 
	 */
	private String direccionSucursal;
	
	/**
	 * 
	 */
	private String ciudadSucursal;
	
	/**
	 * 
	 */
	private String correoCliente;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	public SucursalCliente()
	{
		direccionSucursal = null;
		ciudadSucursal = null;
		correoCliente = null;
	}

	public SucursalCliente(String direccionSucursal, String ciudadSucursal, String correoCliente) 
	{
		this.direccionSucursal = direccionSucursal;
		this.ciudadSucursal = ciudadSucursal;
		this.correoCliente = correoCliente;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	

	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	public String getCiudadSucursal() {
		return ciudadSucursal;
	}

	public void setCiudadSucursal(String ciudadSucursal) {
		this.ciudadSucursal = ciudadSucursal;
	}

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	@Override
	public String toString() {
		return "SucursalCliente [direccionSucursal=" + direccionSucursal + ", ciudadSucursal=" + ciudadSucursal
				+ ", correoCliente=" + correoCliente + "]";
	}
	
}
