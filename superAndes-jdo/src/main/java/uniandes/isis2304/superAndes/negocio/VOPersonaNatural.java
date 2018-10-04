package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los m�todos get de PERSONA_NATURAL
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOPersonaNatural extends VOCliente{
	public String getDocumento();
	/**
	 * @return Una cadena con la informaci�n b�sica de la persona
	 */
	public String toString();
}
