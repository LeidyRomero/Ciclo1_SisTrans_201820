package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto PRODUCTOS OFRECIDOS del negocio de los SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 *
 */
public class ProductosOfrecidos implements VOProductosOfrecidos{

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	/**
	 * Direcci�n de la sucursal que ofrece el producto
	 */
	private String direccionSucursal;
	
	/**
	 * Ciudad de la sucursal que ofrece le producto
	 */
	private String ciudad;
	
	/**
	 * C�digo de barras del producto ofrecido
	 */
	private String codigoBarras;

	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	/**
	 * Constructor con valores
	 * @param direccionSucursal Direcci�n de la sucursal
	 * @param ciudad Ciudad de la sucursal
	 * @param codigoBarras C�digo de barras del producto
	 */
	public ProductosOfrecidos(String direccionSucursal, String ciudad, String codigoBarras) {
		this.direccionSucursal = direccionSucursal;
		this.ciudad = ciudad;
		this.codigoBarras = codigoBarras;
	}
	
	/**
	 * Constructor por defecto
	 */
	public ProductosOfrecidos() {
		this.direccionSucursal = null;
		this.ciudad = null;
		this.codigoBarras = null;
	}

	//-----------------------------------------------------
	// M�TODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna la direcci�n de a sucursal
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * Modifica la direcci�n de la sucursal.
	 * @param direccionSucursal Nueva direcci�n
	 */
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	
	/**
	 * Retorna la ciudad de la sucursal. 
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * Modifica la ciudad de la sucursal
	 * @param ciudad Nueva ciudad
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * Retorna el c�digo de barras del producto.
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * Modifica el c�digo de barras
	 * @param codigoBarras Nuevo c�digo.
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Override
	public String toString() {
		return "ProductosOfrecidos [direccionSucursal=" + direccionSucursal + ", ciudad=" + ciudad + ", codigoBarras="
				+ codigoBarras + "]";
	}	
	
}
