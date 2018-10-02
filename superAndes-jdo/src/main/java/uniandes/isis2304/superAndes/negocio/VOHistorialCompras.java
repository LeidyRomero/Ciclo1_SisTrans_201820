package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de HISTORIAL COMPRAS.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
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
	 * @return Una cadena con la informaci�n b�sica del Historial Compra.
	 */
	public String toString();
}
