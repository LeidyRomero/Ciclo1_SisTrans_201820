package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar el concepto ORDEN CLIENTE del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class OrdenPedido implements VOOrdenPedido{
	
	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Fecha esperada de entrega del pedido.
	 */
	private Timestamp fechaEsperadaEntrega;
	
	/**
	 * Estado del pedido.
	 */
	private String estado;
	
	/**
	 * Fecha real de entrega del pedido.
	 */
	private Timestamp fechaEntrega;
	
	/**
	 * Calificación del pedido.
	 */
	private String calificacionPedido;
	
	/**
	 * Nit del proveedor al que se le hizo el pedido
	 */
	private int nitProveedor;
	
	/**
	 * Id del pedido.
	 */
	private long idPedido;
	
	/**
	 * Ciudad de la sucursal que hizo el pedido.
	 */
	private String ciudadSucursal;
	
	/**
	 * Dirección de la sucursal que hizo el pedido.
	 */
	private String direccionSucursal;
	
	/**
	 * Dirección de la bodega que va a recibir el pedido.
	 */
	private String direcccionBodega;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
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
		direcccionBodega = null;
	}
	
	/**
	 * Constructor con valores
	 * @param fechaEsperadaEntrega Fecha esperada de entrega. fechaEsperadaEntrega != null.
	 * @param estado Estado del pedido. estado != null && estado != ""
	 * @param fechaEntrega Fecha real de entrega. fechaEntrega != null
	 * @param calificacionPedido Calificacion del pedido. calificacionPedido != null  && calificacionPedido != ""
	 * @param nitProveedor Nit del proveedor. nitProveedor >= 0
	 * @param idPedido Id del pedido. idPedido >= 0
	 * @param ciudadSucursal Ciudad de la sucursal. ciudadSucursal != null && ciudadSucursal != ""
	 * @param direccionSucursal Direccion de la sucursal. direccionSucursal != null && direccionSucursal != ""
	 * @param direccionBodega Direccion de la bodege. direccionBodega != null && direccionBodega != ""
	 */
	public OrdenPedido(Timestamp fechaEsperadaEntrega, String estado, Timestamp fechaEntrega, String calificacionPedido,
			int nitProveedor, long idPedido, String ciudadSucursal, String direccionSucursal, String direccionBodega) {

		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
		this.estado = estado;
		this.fechaEntrega = fechaEntrega;
		this.calificacionPedido = calificacionPedido;
		this.nitProveedor = nitProveedor;
		this.idPedido = idPedido;
		this.ciudadSucursal = ciudadSucursal;
		this.direccionSucursal = direccionSucursal;
		this.direcccionBodega = direccionBodega;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	/**
	 * Returna la fecha esperada de entrega del pedido.
	 */
	public Timestamp getFechaEsperadaEntrega() {
		return fechaEsperadaEntrega;
	}

	/**
	 * Modifica la fecha esperada de entrega del pedido.
	 * @param fechaEsperadaEntrega Nueva fecha esperada.
	 */
	public void setFechaEsperadaEntrega(Timestamp fechaEsperadaEntrega) {
		this.fechaEsperadaEntrega = fechaEsperadaEntrega;
	}

	/**
	 * Retorna el estado actual del pedido.
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Modifica el estado actual del pedido.
	 * @param estado Nuevo estado.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Retorna la fecha real de entrega.
	 */
	public Timestamp getFechaEntrega() {
		return fechaEntrega;
	}
	
	/**
	 * Modifica la fecha real de entrega.
	 * @param fechaEntrega Nueva fecha real de entrega.
	 */
	public void setFechaEntrega(Timestamp fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	/**
	 * Retorna la calificación del pedido.
	 */
	public String getCalificacionPedido() {
		return calificacionPedido;
	}

	/**
	 * Modifica la calificación del pedido.
	 * @param calificacionPedido Nueva calificación.
	 */
	public void setCalificacionPedido(String calificacionPedido) {
		this.calificacionPedido = calificacionPedido;
	}

	/**
	 * Retorna el nit del proveedor.
	 */
	public int getNitProveedor() {
		return nitProveedor;
	}

	/**
	 * Modifica el nit del proveedor.
	 * @param nitProveedor Nuevo nit del proveedor.
	 */
	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	/**
	 * Retorna el id del pedido.
	 */
	public long getIdPedido() {
		return idPedido;
	}

	/**
	 * Modifica el id del pedido.
	 * @param idPedido Nuevo id.
	 */
	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	/**
	 * Retorna la ciudad de la sucursal.
	 */
	public String getCiudadSucursal() {
		return ciudadSucursal;
	}

	/**
	 * Modifica la ciudad de la sucursal.
	 * @param ciudadSucursal Nueva ciudad.
	 */
	public void setCiudadSucursal(String ciudadSucursal) {
		this.ciudadSucursal = ciudadSucursal;
	}

	/**
	 * Retorna la dirección de la sucursal.
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * Modifica la dirección de la sucursal.
	 * @param direccionSucursal Nueva dirección.
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	

	/**
	 * Retorna la dirección de la bodega.
	 */
	public String getDireccionBodega() {
		return this.direcccionBodega;
	}
	
	/**
	 * Modifica la dirección de la bodega.
	 * @param direccionBodega Nueva dirección.
	 */
	public void setDireccionBodega(String direccionBodega)
	{
		this.direcccionBodega = direccionBodega;
	}

	@Override
	public String toString() {
		return "OrdenPedido [fechaEsperadaEntrega=" + fechaEsperadaEntrega + ", estado=" + estado + ", fechaEntrega="
				+ fechaEntrega + ", calificacionPedido=" + calificacionPedido + ", nitProveedor=" + nitProveedor
				+ ", idPedido=" + idPedido + ", ciudadSucursal=" + ciudadSucursal + ", direccionSucursal="
				+ direccionSucursal + ", direcccionBodega=" + direcccionBodega + "]";
	}	
}
