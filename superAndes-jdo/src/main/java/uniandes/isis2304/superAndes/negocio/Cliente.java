package uniandes.isis2304.superAndes.negocio;

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
	public Cliente()
	{
		nombreCliente = null;
		correo = null;
		puntos = 0;
	}
	
	public Cliente(String pNombre, String pCorreo, int pPuntos)
	{
		nombreCliente = pNombre;
		correo = pCorreo;
		puntos = pPuntos;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "Cliente [nombreCliente=" + nombreCliente + ", correo=" + correo + ", puntos=" + puntos + "]";
	}
	
	
}
