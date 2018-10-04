package uniandes.isis2304.superAndes.negocio;

/**
 * Interfaz para los m�todos get de SUCURSAL FACTURA.
 * Sirve para proteger la informaci�n del negocio de posibles manipulaciones desde la interfaz.
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public interface VOSucursalFactura {

	/**
	 * @return Direcci�n de la sucursal
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
	 * @return Una cadena con la informaci�n b�sica de SUCURSAL FACTURA.
	 */
	public String toString();
	
}
