package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto SUCURSAL PROMOCIONES del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class SucursalPromociones implements VOSucursalPromociones{

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Id de la promocion
	 */
	private long idPromocion;
	
	/**
	 * Ciudad de la sucursal
	 */
	private String ciudad;
	
	/**
	 * Direccion de la sucursal
	 */
	private String direccion;

	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	/**
	 * Constructor con valores
	 * @param idPromocion
	 * @param ciudad
	 * @param direccion
	 */
	public SucursalPromociones(long idPromocion, String ciudad, String direccion) {
		this.idPromocion = idPromocion;
		this.ciudad = ciudad;
		this.direccion = direccion;
	}
	
	/**
	 * Constructor por defecto
	 */
	public SucursalPromociones() {
		this.idPromocion = 0;
		this.ciudad = null;
		this.direccion = null;
	}
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	/**
	 * 
	 */
	public long getIdPromocion() {
		return idPromocion;
	}

	/**
	 * 
	 * @param idPromocion
	 */
	public void setIdPromocion(long idPromocion) {
		this.idPromocion = idPromocion;
	}

	/**
	 * 
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * 
	 * @param ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * 
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * 
	 * @param direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "SucursalPromociones [idPromocion=" + idPromocion + ", ciudad=" + ciudad + ", direccion=" + direccion
				+ "]";
	}
}
