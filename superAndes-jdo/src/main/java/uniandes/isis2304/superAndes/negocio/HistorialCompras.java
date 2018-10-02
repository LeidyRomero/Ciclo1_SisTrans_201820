package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto HISTORIAL COMPRAS del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class HistorialCompras implements VOHistorialCompras{

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Correo del cliente.
	 */
	private String correoCliente;
	
	/**
	 * Id de la factura asociada al cliente.
	 */
	private long idFactura;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	/**
	 * Constructor con valores
	 * @param correoCliente Correo del cliente. correoCliente != null && correoCliente != ""
	 * @param idFactura Id de la factura. idFactura >= 0
	 */
	public HistorialCompras(String correoCliente, long idFactura) 
	{
		this.correoCliente = correoCliente;
		this.idFactura = idFactura;
	}
	
	/**
	 * Constructor por defecto
	 */
	public HistorialCompras()
	{
		correoCliente = null;
		idFactura = 0;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------

	/**
	 * Retorna el correo del cliente.
	 */
	public String getCorreoCliente() {
		return correoCliente;
	}

	/**
	 * Modifica el correo del cliente.
	 * @param correoCliente Nuevo correo.
	 */
	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	/**
	 * Retorna el id de la factura.
	 */
	public long getIdFactura() {
		return idFactura;
	}

	/**
	 * Modifica el id de la factura.
	 * @param idFactura Nuevo id de la factura.
	 */
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	public String toString() {
		return "HistorialCompras [correoCliente=" + correoCliente + ", idFactura=" + idFactura + "]";
	}
}
