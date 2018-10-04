package uniandes.isis2304.superAndes.negocio;

/**
 * Clase para modelar el concepto PROVEEN del negocio de los SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 *
 */
public class Proveen implements VOProveen {

	//-----------------------------------------------------
	// ATRIBUTOS
	//-----------------------------------------------------
	
	/**
	 * Nit del proveedor 
	 */
	private int nitProveedor;
	
	/**
	 * Codigo de barras del producto
	 */
	private String codBarras;
	
	//-----------------------------------------------------
	// CONSTRUCTORES
	//-----------------------------------------------------
	
	/**
	 * Constructor por defecto
	 */
	public Proveen()
	{
		nitProveedor = 0;
		codBarras = null;
	}
	
	/**
	 * Cosntructor con valores
	 * @param nitProveedor Nit del proveedor
	 * @param codigoBarras Código de barras del producto
	 */
	public Proveen(int nitProveedor, String codigoBarras) 
	{
		this.nitProveedor = nitProveedor;
		this.codBarras = codigoBarras;
	}
	
	//-----------------------------------------------------
	// MÉTODOS
	//-----------------------------------------------------
	
	/**
	 * Retorna el nit del proveedor
	 */
	public int getNitProveedor() {
		return nitProveedor;
	}

	/**
	 * Modifica el nit del proveedor
	 * @param nitProveedor Nuevo nit
	 */
	public void setNitProveedor(int nitProveedor) {
		this.nitProveedor = nitProveedor;
	}

	/**
	 * Retorna el código de barras del producto
	 */
	public String getCodBarras() {
		return codBarras;
	}

	/**
	 * Modifica el código de barras del producto
	 * @param codigoBarras Nuevo código
	 */
	public void setCodBarras(String codigoBarras) {
		this.codBarras = codigoBarras;
	}

	@Override
	public String toString() {
		return "Proveen [nitProveedor=" + nitProveedor + ", codigoBarras=" + codBarras + "]";
	}
}
