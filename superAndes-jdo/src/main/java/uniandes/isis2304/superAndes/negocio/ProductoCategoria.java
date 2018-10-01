package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class ProductoCategoria implements VOProductoCategoria{
	private String nombreCategoria;
	private int codigoBarras;

	public ProductoCategoria()
	{
		nombreCategoria = "";
		codigoBarras = 0;
	}
	public ProductoCategoria(String pCategoria, int pCodigo)
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

	public int getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(int codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Override
	public String toString() {
		return "ProductoCategoria [nombreCategoria=" + nombreCategoria + ", codigoBarras=" + codigoBarras + "]";
	}
	
}
