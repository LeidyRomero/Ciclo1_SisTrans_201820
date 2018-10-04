package uniandes.isis2304.superAndes.negocio;
/**
 * Interfaz para los métodos get de ESTANTE
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOEstante {
	/**
	 * @return ciudad de la bodega
	 */
	public String getCiudad();
	/**
	 * @return direccion de la sucursal
	 */
	public String getDireccionSucursal();
	/**
	 * @return nivel de abastecimiento
	 */
	public double getNivelAbastecimiento();
	/**
	 * @return peso del estante
	 */
	public double getPesoEstante();
	/**
	 * @return id del estante
	 */
	public long getIdEstante();
	/**
	 * @return volumen del estante
	 */
	public double getVolumenEstante();
	/**
	 * @return tipo de estante
	 */
	public String getTipoEstante();
	/**
	 * @return Una cadena con la información básica del estante
	 */
	public String toString();
}
