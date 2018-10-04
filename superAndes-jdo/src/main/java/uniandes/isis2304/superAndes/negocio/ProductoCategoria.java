package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class ProductoCategoria implements VOProductoCategoria{
	private String categoria;
	private String codigoBarras;

	public ProductoCategoria()
	{
		categoria = "";
		codigoBarras = "";
	}
	public ProductoCategoria(String pCategoria, String pCodigo)
	{
		categoria = pCategoria;
		codigoBarras = pCodigo;
	}
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String nombre) {
		this.categoria = nombre;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Override
	public String toString() {
		return "ProductoCategoria [nombreCategoria=" + categoria + ", codigoBarras=" + codigoBarras + "]";
	}
	
}
