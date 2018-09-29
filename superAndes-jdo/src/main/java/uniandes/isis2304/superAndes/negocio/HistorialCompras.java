package uniandes.isis2304.superAndes.negocio;

public class HistorialCompras implements VOHistorialCompras{

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * 
	 */
	private String correoCliente;
	
	/**
	 * 
	 */
	private String idFactura;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------

	public HistorialCompras(String correoCliente, String idFactura) 
	{
		this.correoCliente = correoCliente;
		this.idFactura = idFactura;
	}
	
	public HistorialCompras()
	{
		
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------

	public String getCorreoCliente() {
		return correoCliente;
	}

	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public String getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(String idFactura) {
		this.idFactura = idFactura;
	}

	public String toString() {
		return "HistorialCompras [correoCliente=" + correoCliente + ", idFactura=" + idFactura + "]";
	}
}
