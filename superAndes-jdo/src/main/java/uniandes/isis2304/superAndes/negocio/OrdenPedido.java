package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar el concepto ORDEN CLIENTE del negocio de los SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
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
	 * Calificaci�n del pedido.
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
	private String ciudad;
	
	/**
	 * Direcci�n de la sucursal que hizo el pedido.
	 */
	private String direccionSucursal;
	
	/**
	 * Direcci�n de la bodega que va a recibir el pedido.
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
		ciudad = null;
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
		this.ciudad = ciudadSucursal;
		this.direccionSucursal = direccionSucursal;
		this.direcccionBodega = direccionBodega;
	}

	//-----------------------------------------------------
	// M�TODOS
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
	 * Retorna la calificaci�n del pedido.
	 */
	public String getCalificacionPedido() {
		return calificacionPedido;
	}

	/**
	 * Modifica la calificaci�n del pedido.
	 * @param calificacionPedido Nueva calificaci�n.
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
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Modifica la ciudad de la sucursal.
	 * @param ciudadSucursal Nueva ciudad.
	 */
	public void setCiudad(String ciudadSucursal) {
		this.ciudad = ciudadSucursal;
	}

	/**
	 * Retorna la direcci�n de la sucursal.
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * Modifica la direcci�n de la sucursal.
	 * @param direccionSucursal Nueva direcci�n.
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	

	/**
	 * Retorna la direcci�n de la bodega.
	 */
	public String getDireccionBodega() {
		return this.direcccionBodega;
	}
	
	/**
	 * Modifica la direcci�n de la bodega.
	 * @param direccionBodega Nueva direcci�n.
	 */
	public void setDireccionBodega(String direccionBodega)
	{
		this.direcccionBodega = direccionBodega;
	}

	@Override
	public String toString() {
		return "OrdenPedido [fechaEsperadaEntrega=" + fechaEsperadaEntrega + ", estado=" + estado + ", fechaEntrega="
				+ fechaEntrega + ", calificacionPedido=" + calificacionPedido + ", nitProveedor=" + nitProveedor
				+ ", idPedido=" + idPedido + ", ciudadSucursal=" + ciudad + ", direccionSucursal="
				+ direccionSucursal + ", direcccionBodega=" + direcccionBodega + "]";
	}	
}
