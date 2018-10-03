package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de PROVEEN.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public interface VOProveen {

	/**
	 * @return Nit del proveedor
	 */
	public int getNitProveedor();

	/**
	 * @return C�digo de barras del producto
	 */
	public String getCodigoBarras();

	/**
	 * @return Una cadena con la informaci�n b�sica de PROVEEN.
	 */
	public String toString();
}
