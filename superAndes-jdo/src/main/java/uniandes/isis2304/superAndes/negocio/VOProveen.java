package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de PROVEEN.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOProveen {

	/**
	 * @return Nit del proveedor
	 */
	public int getNitProveedor();

	/**
	 * @return Código de barras del producto
	 */
	public int getCodigoBarras();

	/**
	 * @return Una cadena con la información básica de PROVEEN.
	 */
	public String toString();
}
