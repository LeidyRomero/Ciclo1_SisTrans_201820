package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class ProductoCategoria implements VOProductoCategoria{
	private String nombreCategoria;
	private String codigoBarras;

	public ProductoCategoria()
	{
		nombreCategoria = "";
		codigoBarras = "";
	}
	public ProductoCategoria(String pCategoria, String pCodigo)
	{
		nombreCategoria = pCategoria;
		codigoBarras = pCodigo;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public void setNombre(String nombre) {
		this.nombreCategoria = nombre;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Override
	public String toString() {
		return "ProductoCategoria [nombreCategoria=" + nombreCategoria + ", codigoBarras=" + codigoBarras + "]";
	}
	
}
