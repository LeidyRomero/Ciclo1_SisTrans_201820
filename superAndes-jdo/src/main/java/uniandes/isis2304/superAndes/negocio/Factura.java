package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar el concepto FACTURA del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class Factura implements VOFactura{

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Id de la factura
	 */
	private long idFactura;
	
	/**
	 * Fecha de generación de la factura
	 */
	private Timestamp fecha;
	
	/**
	 * Costo total de la factura
	 */
	private double costoTotal;
	
	/**
	 * Correo del cliente
	 */
	private String correoCliente;
	
	/**
	 * Dirección de la sucursal
	 */
	private String direccionSucursal;
	
	/**
	 * Ciudad de la sucursal
	 */
	private String ciudad;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	/**
	 * Constructor por defecto.
	 */
	public Factura()
	{
		idFactura = 0;
		fecha = null;
		costoTotal = 0.0;
		correoCliente = null;
		direccionSucursal = null;
		ciudad = null;
	}
	
	/**
	 * Constructor con valores
	 * @param pId Id de la factura. pId >= 0
	 * @param pFecha Fecha de generación de la factura. pFecha != null
	 * @param pCosto Costo total. pCosto >= 0.0
	 */
	public Factura(long pId, Timestamp pFecha, double pCosto, String pCorreoCliente, String pDireccion, String pCiudad)
	{
		idFactura = pId;
		fecha = pFecha;
		costoTotal = pCosto;
		correoCliente = pCorreoCliente;
		direccionSucursal = pDireccion;
		ciudad = pCiudad;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------

	/**
	 * Retorna el id de la factura.
	 */
	public long getIdFactura() {
		return idFactura;
	}

	/**
	 * Modifica el id de la factura.
	 * @param idFactura Nueva id.
	 */
	public void setIdFactura(long idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * Retorna la fecha de generación de la factura.
	 */
	public Timestamp getFecha() {
		return fecha;
	}

	/**
	 * Modifica la fecha de generación de la factura.	 * 
	 * @param fecha Nueva fecha de generación.
	 */
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/**
	 * Retorna el costo total de la factura.
	 */
	public double getCostoTotal() {
		return costoTotal;
	}

	/**
	 * Modifica el costo total de la factura
	 * @param costoTotal Nuevo costo.
	 */
	public void setCostoTotal(double costoTotal) {
		this.costoTotal = costoTotal;
	}

	/**
	 * Retorna el correo del cliente
	 */
	public String getCorreoCliente() {
		return correoCliente;
	}

	/**
	 * Modifica el correo del cliente
	 * @param correoCliente Nuevo correo.
	 */
	public void setCorreoCliente(String correoCliente) {
		this.correoCliente = correoCliente;
	}

	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	@Override
	public String toString() {
		return "Factura [idFactura=" + idFactura + ", fecha=" + fecha + ", costoTotal=" + costoTotal
				+ ", correoCliente=" + correoCliente + ", direccionSucursal=" + direccionSucursal + ", ciudad=" + ciudad
				+ "]";
	}
	
	
	
	
		
}
