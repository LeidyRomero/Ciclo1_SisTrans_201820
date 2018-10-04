package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de CLIENTE.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOCliente {
	
	/**
	 * @return El correo del cliente.
	 */
	public String getCorreo();
	
	/**
	 * @return El nombre del cliente.
	 */
	public String getNombreConsumidor();
	
	/**
	 * @return Los puntos del cliente.
	 */
	public int getPuntos();
	
	/**
	 * @return Una cadena con la información básica del cliente.
	 */
	public String toString();
}
