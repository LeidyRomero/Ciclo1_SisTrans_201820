package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Interfaz para los métodos get de ORDEN PEDIDO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOOrdenPedido {

	/**
	 * @return Fecha esperada de entrega del pedido.
	 */
	public Timestamp getFechaEsperadaEntrega();

	/**
	 * @return Estado actual del pedido
	 */
	public String getEstado();
	
	/**
	 * @return Fecha de entrega real del pedido.
	 */
	public Timestamp getFechaEntrega();
	
	/**
	 * @return Calificación del pedido.
	 */
	public String getCalificacionPedido();

	/**
	 * @return Nit del proveedor.
	 */
	public int getNitProveedor();
 
	/**
	 * @return Id del pedido.
	 */
	public long getIdPedido();
	
	/**
	 * @return Ciudad de la sucursal.
	 */
	public String getCiudadSucursal();

	/**
	 * @return Dirección de la sucursal.
	 */
	public String getDireccionSucursal();
	
	/**
	 * @return Dirección de la bodega.
	 */
	public String getDireccionBodega();
	
	/**
	 * @return Una cadena con la información básica de la Orden de Pedido.
	 */
	public String toString();
}