package uniandes.isis2304.superAndes.negocio;

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
	
	public Proveedor()
	{
		nitProveedor = 0;
		nombreProveedor = null;
		calificacion = null;
	}

	public Proveedor(int nitProveedor, String nombreProveedor, String calificacion) 
	{
		this.nitProveedor = nitProveedor;
		this.nombreProveedor = nombreProveedor;
		this.calificacion = calificacion;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	

	public int getNitProveedor() {
		return nitProveedor;
	}

	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}

	@Override
	public String toString() {
		return "Proveedor [nitProveedor=" + nitProveedor + ", nombreProveedor=" + nombreProveedor + ", calificacion="
				+ calificacion + "]";
	}
}
