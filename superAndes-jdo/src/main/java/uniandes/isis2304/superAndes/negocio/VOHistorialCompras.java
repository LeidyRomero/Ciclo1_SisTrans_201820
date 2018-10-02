package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de HISTORIAL COMPRAS.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOHistorialCompras {

	/** 
	 * @return El correo del cliente.
	 */
	public String getCorreoCliente();
	
	/**
	 * @return El id de la factura.
	 */
	public long getIdFactura();
	
	/**
	 * @return Una cadena con la información básica del Historial Compra.
	 */
	public String toString();
}
