package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto CLIENTE del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class Cliente implements VOCliente{

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Nombre del cliente
	 */
	protected String nombreCliente;
	
	/**
	 * Correo del cliente
	 */
	protected String correo;
	
	/**
	 * Puntos del cliente
	 */
	protected int puntos;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	/**
	 * Constructor por defecto
	 */
	public Cliente()
	{
		nombreCliente = null;
		correo = null;
		puntos = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param pNombre Nombre del cliente. pNombre != null && pNombre != ""
	 * @param pCorreo Correo del cliente. pCorreo != null && pCorreo != ""
	 * @param pPuntos Puntos del cliente. pPuntos != null && pPuntos != ""
	 */
	public Cliente(String pNombre, String pCorreo, int pPuntos)
	{
		nombreCliente = pNombre;
		correo = pCorreo;
		puntos = pPuntos;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna el nombre del cliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * Modifica el nombre del cliente.
	 * @param pNombreCliente Nuevo nombre
	 */
	public void setNombreCliente(String pNombreCliente) {
		this.nombreCliente = pNombreCliente;
	}

	/**
	 * Retorna el correo del cliente.
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * Modifica el correo del cliente.
	 * @param pCorreo Nuevo correo.
	 */
	public void setCorreo(String pCorreo) {
		this.correo = pCorreo;
	}

	/**
	 * Retorna los puntos del cliente.
	 */
	public int getPuntos() {
		return puntos;
	}

	/**
	 * Modifica los puntos del cliente.
	 * @param pPuntos Nuevos puntos
	 */
	public void setPuntos(int pPuntos) {
		this.puntos = pPuntos;
	}

	@Override
	public String toString() {
		return "Cliente [nombreCliente=" + nombreCliente + ", correo=" + correo + ", puntos=" + puntos + "]";
	}
	
	
}
