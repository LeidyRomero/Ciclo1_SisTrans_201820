package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class Categoria implements VOCategoria{
	private String nombre;
	
	public Categoria()
	{
		nombre = "";
	}
	public Categoria(String pCategoria)
	{
		nombre = pCategoria;
	}

	public String darNombre() {
		return nombre;
	}
	public void cambiarNombre(String pNombre)
	{
		this.nombre = pNombre;
	}

	public String toString() {
		return "Categoria [nombre=" + nombre + "]";
	}
	
}
