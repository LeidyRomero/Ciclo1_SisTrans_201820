package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de CATEGORIA
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOCategoria {
	/**
	 * @return nombre de la categoria
	 */
	public String getNombreCategoria();
	/**
	 * @return Una cadena con la información básica de la categoria
	 */
	public String toString();
}
