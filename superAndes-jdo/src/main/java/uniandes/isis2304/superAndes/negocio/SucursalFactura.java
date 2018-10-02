package uniandes.isis2304.superAndes.negocio;

public class SucursalFactura implements VOSucursalFactura {

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
	private long idFactura;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	public SucursalFactura()
	{
		direccionSucursal = null;
		ciudadSucursal = null;
		idFactura = 0;
	}

	public SucursalFactura(String direccionSucursal, String ciudadSucursal, long idFactura) 
	{
		this.direccionSucursal = direccionSucursal;
		this.ciudadSucursal = ciudadSucursal;
		this.idFactura = idFactura;
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

	public long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	@Override
	public String toString() {
		return "SucursalCliente [direccionSucursal=" + direccionSucursal + ", ciudadSucursal=" + ciudadSucursal
				+ ", correoCliente=" + idFactura + "]";
	}
	
}
