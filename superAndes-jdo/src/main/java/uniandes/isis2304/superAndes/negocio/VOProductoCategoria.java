package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de PRODUCTO_CATEGORIA
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOProductoCategoria{
	public String getCategoria();
	public String getCodigoBarras();
	/**
	 * @return Una cadena con la información básica deel producto categoria
	 */
	public String toString();
}
