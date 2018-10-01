package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de CLIENTE.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
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
	public String getNombreCliente();
	
	/**
	 * @return Los puntos del cliente.
	 */
	public int getPuntos();
	
	/**
	 * @return Una cadena con la informaci�n b�sica del cliente.
	 */
	public String toString();
}
