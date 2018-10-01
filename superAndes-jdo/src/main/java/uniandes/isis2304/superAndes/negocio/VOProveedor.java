package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de PROVEEDOR.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public interface VOProveedor {

	/**
	 * @return El nit del proveedor.
	 */
	public int getNitProveedor();

	/**
	 * @return El nombre del proveedor.
	 */
	public String getNombreProveedor();

	/**
	 * @return La calificaci�n del proveedor.
	 */
	public String getCalificacion();
	
	/**
	 * @return Una cadena con la informaci�n b�sica del Proveedor
	 */
	public String toString();
}
