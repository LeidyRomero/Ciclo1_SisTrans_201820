package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto PROVEEDOR del negocio de los SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public class Proveedor implements VOProveedor {

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 *  Nit del proveedor
	 */
	private int nitProveedor;
	
	/**
	 * Nombre del proveedor
	 */
	private String nombreProveedor;
	
	/**
	 * Calificai�n del proveedor
	 */
	private String calificacionProveedor;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public Proveedor()
	{
		nitProveedor = 0;
		nombreProveedor = null;
		calificacionProveedor = null;
	}

	/**
	 * Constructor con valores
	 * @param nitProveedor Nit del proveedor. nitProveedor != null && nitProveedor >= 0
	 * @param nombreProveedor Nombre del proveedor. nombreProveedor != null && nombreProveedor != ""
	 * @param calificacion Calificaci�n del proveedor.
	 */
	public Proveedor(int nitProveedor, String nombreProveedor, String calificacion) 
	{
		this.nitProveedor = nitProveedor;
		this.nombreProveedor = nombreProveedor;
		this.calificacionProveedor = calificacion;
	}
	
	//-----------------------------------------------------
	// M�TODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna el nit del proveedor.
	 */
	public int getNitProveedor() {
		return nitProveedor;
	}

	/**
	 * Modifica el nit del proveedor.
	 * @param nitProveedor Nuevo nit.
	 */ 
	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	/**
	 * Retorna el nombre del proveedor.
	 */
	public String getNombreProveedor() {
		return nombreProveedor;
	}

	/**
	 * Modifica el nombre del proveedor.
	 * @param nombreProveedor Nuevo nombre.
	 */
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	/**
	 * Retorna la calificaci�n del proveedor.
	 */
	public String getCalificacionProveedor() {
		return calificacionProveedor;
	}

	/**
	 * Modifica la calificaci�n del proveedor.
	 * @param calificacion Nueva calificaci�n.
	 */
	public void setCalificacionProveedor(String calificacion) {
		this.calificacionProveedor = calificacion;
	}

	@Override
	public String toString() {
		return "Proveedor [nitProveedor=" + nitProveedor + ", nombreProveedor=" + nombreProveedor + ", calificacion="
				+ calificacionProveedor + "]";
	}
}
