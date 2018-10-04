package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los m�todos get de TIPO_PRODUCTO
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOTipoProducto {
	public String getNombreTipo();
	/**
	 * @return Una cadena con la informaci�n b�sica del tipo
	 */
	public String toString();
}
