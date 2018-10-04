package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto CATEGORIA del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class Categoria implements VOCategoria{
	//----------------------------------------------
	//Atringutos
	//----------------------------------------------
	/**
	 * nombre de la categoria
	 */
	private String nombreCategoria;
	//----------------------------------------------
	//Constructores
	//----------------------------------------------
	/**
	 * constructor por defecto
	 */
	public Categoria()
	{
		nombreCategoria = "";
	}
	/**
	 * crea una nueva categoria
	 * @param pCategoria nombre de la categoria
	 */
	public Categoria(String pCategoria)
	{
		nombreCategoria = pCategoria;
	}
	//----------------------------------------------
	//Metodos
	//----------------------------------------------
	/**
	 * retorna el nombre de la categoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	/**
	 * cambia el nombre de la categoria por el recibido por parametro
	 */
	public void setNombreCategoria(String pNombre)
	{
		this.nombreCategoria = pNombre;
	}

	/**
	 * Retonra un string con la informacion de la categoria
	 */
	public String toString() {
		return "Categoria [nombre=" + nombreCategoria + "]";
	}

}
