package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;

/**
 * Interfaz para los m�todos get de ORDEN PEDIDO.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
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
	 * @return Calificaci�n del pedido.
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
	 * @return Direcci�n de la sucursal.
	 */
	public String getDireccionSucursal();
	
	/**
	 * @return Direcci�n de la bodega.
	 */
	public String getDireccionBodega();
	
	/**
	 * @return Una cadena con la informaci�n b�sica de la Orden de Pedido.
	 */
	public String toString();
}