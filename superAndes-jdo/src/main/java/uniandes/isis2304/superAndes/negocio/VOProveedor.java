package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de PROVEEDOR.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
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
	 * @return La calificación del proveedor.
	 */
	public String getCalificacion();
	
	/**
	 * @return Una cadena con la información básica del Proveedor
	 */
	public String toString();
}
