package uniandes.isis2304.superAndes.negocio;
/**
 * Clase para modelar el concepto TIPO PRODUCTO del negocio de los SuperAndes
 * @author lj.romero
 *
 */
public class TipoProducto implements VOTipoProducto{
	/**
	 * nombre del tipo
	 */
	private String nombreTipo;
	/**
	 * categoria del tipo
	 */
	private String nombreCategoria;
	/**
	 * constructor por defecto
	 */
	public TipoProducto()
	{
		nombreTipo = "";
		nombreCategoria = "";
	}
	/**
	 * Crea un nuevo tipo con los valores recibidos por parametro
	 */
	public TipoProducto(String pNombre, String pCategoria)
	{
		nombreTipo  = pNombre;
		nombreCategoria = pCategoria;
	}
	/**
	 * retorna el nombre de la categoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	/**
	 * cambia el nombre de la categoria por el recibido por parametro
	 */
	public void setNombreCategoria(String categoria) {
		this.nombreCategoria = categoria;
	}
	/**
	 * retorna el nombre del tipo
	 */
	public String getNombreTipo() {
		return nombreTipo;
	}
	/**
	 * cambia el nombre del tipo por el recibido por parametro
	 */
	public void setNombreTipo(String nombre) {
		this.nombreTipo = nombre;
	}
	/**
	 * retorna un string con la informacion del tipo
	 */
	public String toString() {
		return "TipoProducto [nombre=" + nombreTipo + ", categoria=" + nombreCategoria + "]";
	}

}
