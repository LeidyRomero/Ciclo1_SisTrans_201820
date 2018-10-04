package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de PERSONA_NATURAL
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOPersonaNatural extends VOCliente{
	public String getDocumento();
	/**
	 * @return Una cadena con la información básica de la persona
	 */
	public String toString();
}
