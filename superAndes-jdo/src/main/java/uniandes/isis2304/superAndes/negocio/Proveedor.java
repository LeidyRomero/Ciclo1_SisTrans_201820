package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto PROVEEDOR del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
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
	 * Calificaión del proveedor
	 */
	private String calificacion;
	
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
		calificacion = null;
	}

	/**
	 * Constructor con valores
	 * @param nitProveedor Nit del proveedor. nitProveedor != null && nitProveedor >= 0
	 * @param nombreProveedor Nombre del proveedor. nombreProveedor != null && nombreProveedor != ""
	 * @param calificacion Calificación del proveedor.
	 */
	public Proveedor(int nitProveedor, String nombreProveedor, String calificacion) 
	{
		this.nitProveedor = nitProveedor;
		this.nombreProveedor = nombreProveedor;
		this.calificacion = calificacion;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
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
	 * Retorna la calificación del proveedor.
	 */
	public String getCalificacion() {
		return calificacion;
	}

	/**
	 * Modifica la calificación del proveedor.
	 * @param calificacion Nueva calificación.
	 */
	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public String toString() {
		return "Proveedor [nitProveedor=" + nitProveedor + ", nombreProveedor=" + nombreProveedor + ", calificacion="
				+ calificacion + "]";
	}
}
