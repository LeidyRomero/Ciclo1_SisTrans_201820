package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los m�todos get de BODEGA
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOBodega {
	/**
	 * @return tipo de la bodega
	 */
	public String getTipoBodega();
	/**
	 * @return volumen de la bodega
	 */
	public double getVolumenBodega();
	/**
	 * @return peso de la bodega
	 */
	public double getPesoBodega();
	/**
	 * @return direccion de la bodega
	 */
	public String getDireccionBodega();
	/**
	 * @return direccion de la sucursal
	 */
	public String getDireccionSucursal();
	/**
	 * @return ciudad de la sucursal
	 */
	public String getCiudad();
	/**
	 * @return Una cadena con la informaci�n b�sica de las bodegas.
	 */
	public String toString();
}
