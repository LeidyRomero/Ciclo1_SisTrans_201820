package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Categoria implements VOCategoria{
	private String nombreCategoria;
	
	public Categoria()
	{
		nombreCategoria = "";
	}
	public Categoria(String pCategoria)
	{
		nombreCategoria = pCategoria;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String pNombre)
	{
		this.nombreCategoria = pNombre;
	}

	public String toString() {
		return "Categoria [nombre=" + nombreCategoria + "]";
	}
	
}
