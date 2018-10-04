package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los m�todos get de CATEGORIA
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOCategoria {
	/**
	 * @return nombre de la categoria
	 */
	public String getNombreCategoria();
	/**
	 * @return Una cadena con la informaci�n b�sica de la categoria
	 */
	public String toString();
}
