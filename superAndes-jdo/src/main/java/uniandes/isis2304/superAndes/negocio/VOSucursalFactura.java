package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los métodos get de SUCURSAL FACTURA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public interface VOSucursalFactura {

	/**
	 * @return Dirección de la sucursal
	 */
	public String getDireccionSucursal();

	/**
	 * @return Ciudad de la sucursal
	 */
	public String getCiudad();

	/**
	 * @return Id de la factura
	 */
	public long getIdFactura();

	/**	
	 * @return Una cadena con la información básica de SUCURSAL FACTURA.
	 */
	public String toString();
	
}
