package uniandes.isis2304.superAndes.negocio;

/**
 * Clase que representa el concepto PRODUCTOS CARRITO en el negocio de SuperAndes
 * @author María Ocampo - mj.ocampov
 *
 */
public class ProductosCarrito implements VOProductosCarrito {
	
	//-------------------------------------------
	// ATRIBUTOS
	//-------------------------------------------
	
	/**
	 * El identificador del carrito
	 */
	private long idCarrito;

	/**
	 * El código de barras del producto agregado al carrito
	 */
	private String codBarras;
	
	/**
	 * La cantidad del producto
	 */
	private int cantidad;

	//-------------------------------------------
	// CONSTRUCTORES
	//-------------------------------------------
	/**
	 * Constructor con parámetros
	 * @param idCarrito - El identificador del carrito
	 * @param codBarras - El código de barras del producto
	 * @param cantidad - La cantidad del producto
	 */
	public ProductosCarrito(long idCarrito, String codBarras, int cantidad) {
		this.idCarrito = idCarrito;
		this.codBarras = codBarras;
		this.cantidad = cantidad;
	}
	
	/**
	 * Constructor por defecto
	 */
	public ProductosCarrito() {
		this.idCarrito = 0;
		this.codBarras = "";
		this.cantidad = 0;
	}

	//-------------------------------------------
	// MÉTODOS
	//-------------------------------------------
	/**
	 * Retorna el identificador del carrito
	 */
	public long getIdCarrito() {
		return idCarrito;
	}

	/**
	 * Modifica el identificador del carrito
	 * @param idCarrito Nuevo identificador
	 */
	public void setIdCarrito(long idCarrito) {
		this.idCarrito = idCarrito;
	}

	/**
	 * Retorna el código de barras del prodcuto
	 */
	public String getCodBarras() {
		return codBarras;
	}

	/**
	 * Modifica el código de barras del producto
	 * @param codBarras Nuevo código de barras
	 */
	public void setCodBarras(String codBarras) {
		this.codBarras = codBarras;
	}

	/**
	 * Retorna la cantidad del producto
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Modifica la cantidad del producto
	 * @param cantidad Nueva cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ProductosCarrito [idCarrito=" + idCarrito + ", codBarras=" + codBarras + ", cantidad=" + cantidad + "]";
	}	

}
