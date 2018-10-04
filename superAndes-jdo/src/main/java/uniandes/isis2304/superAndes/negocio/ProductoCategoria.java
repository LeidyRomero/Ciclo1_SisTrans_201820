package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto PRODUCTO CATEGORIA del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class ProductoCategoria implements VOProductoCategoria{
	/**
	 * categoria del producto
	 */
	private String categoria;
	/**
	 * codigo del producto
	 */
	private String codigoBarras;
	/**
	 * constructor por defecy¡to
	 */
	public ProductoCategoria()
	{
		categoria = "";
		codigoBarras = "";
	}
	/**
	 * crea un nuevo producto categoria con los valores recibidos por parametro
	 */
	public ProductoCategoria(String pCategoria, String pCodigo)
	{
		categoria = pCategoria;
		codigoBarras = pCodigo;
	}
	/**
	 * retorna la categoria
	 */
	public String getCategoria() {
		return categoria;
	}
	/**
	 * cambia la categoria por la recibida por parametro
	 */
	public void setCategoria(String nombre) {
		this.categoria = nombre;
	}
	/**
	 * retorna el codigo de barras del producto
	 */
	public String getCodigoBarras() {
		return codigoBarras;
	}
	/**
	 * cambia el codigo de barras del producto por el recibido por parametro
	 */
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	/**
	 * retorna un string con la informacion del producto categoria
	 */
	public String toString() {
		return "ProductoCategoria [nombreCategoria=" + categoria + ", codigoBarras=" + codigoBarras + "]";
	}
	
}
