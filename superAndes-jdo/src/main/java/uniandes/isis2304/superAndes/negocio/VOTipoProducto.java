package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de TIPO_PRODUCTO
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOTipoProducto {
	public String getNombreTipo();
	/**
	 * @return Una cadena con la información básica del tipo
	 */
	public String toString();
}
