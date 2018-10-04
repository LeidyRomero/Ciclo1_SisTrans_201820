package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los m�todos get de PRODUCTO_CATEGORIA
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOProductoCategoria{
	public String getCategoria();
	public String getCodigoBarras();
	/**
	 * @return Una cadena con la informaci�n b�sica deel producto categoria
	 */
	public String toString();
}
