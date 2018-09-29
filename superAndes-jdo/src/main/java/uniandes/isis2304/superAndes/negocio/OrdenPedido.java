package uniandes.isis2304.superAndes.negocio;

import java.sql.Date;

public class OrdenPedido implements VOOrdenPedido{
	
	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * 
	 */
	private Date fechaEsperadaEntrega;
	
	/**
	 * 
	 */
	private String estado;
	
	/**
	 * 
	 */
	private Date fechaEntrega;
	
	/**
	 * 
	 */
	private String calificacionPedido;
	
	/**
	 * 
	 */
	private int nitProveedor;
	
	/**
	 * 
	 */
	private long idPedido;
	
	/**
	 * 
	 */
	private String ciudadSucursal;
	
	/**
	 * 
	 */
	private String direccionSucursal;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	public OrdenPedido()
	{
		calificacionPedido = null;
		ciudadSucursal = null;
		direccionSucursal = null;
		estado = null;
		fechaEntrega = null;
		fechaEsperadaEntrega = null;
		idPedido = 0;
		nitProveedor = 0;
	}

	public OrdenPedido(Date fechaEsperadaEntrega, String estado, Date fechaEntrega, String calificacionPedido,
			int nitProveedor, long idPedido, String ciudadSucursal, String direccionSucursal) {

		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
		this.estado = estado;
		this.fechaEntrega = fechaEntrega;
		this.calificacionPedido = calificacionPedido;
		this.nitProveedor = nitProveedor;
		this.idPedido = idPedido;
		this.ciudadSucursal = ciudadSucursal;
		this.direccionSucursal = direccionSucursal;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	

	public Date getFechaEsperadaEntrega() {
		return fechaEsperadaEntrega;
	}

	public void setFechaEsperadaEntrega(Date fechaEsperadaEntrega) {
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public String getCalificacionPedido() {
		return calificacionPedido;
	}

	public void setCalificacionPedido(String calificacionPedido) {
		this.calificacionPedido = calificacionPedido;
	}

	public int getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public long getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public String getCiudadSucursal() {
		return ciudadSucursal;
	}

	public void setCiudadSucursal(String ciudadSucursal) {
		this.ciudadSucursal = ciudadSucursal;
	}

	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}

	@Override
	public String toString() {
		return "OrdenPedido [fechaEsperadaEntrega=" + fechaEsperadaEntrega + ", estado=" + estado + ", fechaEntrega="
				+ fechaEntrega + ", calificacionPedido=" + calificacionPedido + ", nitProveedor=" + nitProveedor
				+ ", idPedido=" + idPedido + ", ciudadSucursal=" + ciudadSucursal + ", direccionSucursal="
				+ direccionSucursal + "]";
	}

	
}
