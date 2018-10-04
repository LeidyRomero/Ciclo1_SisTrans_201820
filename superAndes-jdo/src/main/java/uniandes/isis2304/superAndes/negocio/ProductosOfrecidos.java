package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto PRODUCTOS OFRECIDOS del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class ProductosOfrecidos implements VOProductosOfrecidos{

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	/**
	 * Dirección de la sucursal que ofrece el producto
	 */
	private String direccionSucursal;
	
	/**
	 * Ciudad de la sucursal que ofrece le producto
	 */
	private String ciudad;
	
	/**
	 * Código de barras del producto ofrecido
	 */
	private String codigoBarras;

	/**
	 * 
	 */
	private int nivelReorden;
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	/**
	 * Constructor con valores
	 * @param direccionSucursal Dirección de la sucursal
	 * @param ciudad Ciudad de la sucursal
	 * @param codigoBarras Código de barras del producto
	 */
	public ProductosOfrecidos(String direccionSucursal, String ciudad, String codigoBarras,int pNivel) {
		this.direccionSucursal = direccionSucursal;
		this.ciudad = ciudad;
		this.codigoBarras = codigoBarras;
		this.nivelReorden = pNivel;
	}
	
	/**
	 * Constructor por defecto
	 */
	public ProductosOfrecidos() {
		this.direccionSucursal = null;
		this.ciudad = null;
		this.codigoBarras = null;
		this.nivelReorden = 0;
	}

	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna la dirección de a sucursal
	 */
	public String getDireccionSucursal() {
		return direccionSucursal;
	}

	/**
	 * Modifica la dirección de la sucursal.
	 * @param direccionSucursal Nueva dirección
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
	 * Retorna el código de barras del producto.
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}

	/**
	 * Modifica el código de barras
	 * @param codigoBarras Nuevo código.
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	public int getNivelReorden() {
		return nivelReorden;
	}

	public void setNivelReorden(int nivelReorden) {
		this.nivelReorden = nivelReorden;
	}

	@Override
	public String toString() {
		return "ProductosOfrecidos [direccionSucursal=" + direccionSucursal + ", ciudad=" + ciudad + ", codigoBarras="
				+ codigoBarras + ", nivelReorden=" + nivelReorden + "]";
	}


	
}
