package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class TipoProducto implements VOTipoProducto{
	private String nombre;
	private String categoria;
	public TipoProducto()
	{
		nombre = "";
		categoria = "";
	}
	public TipoProducto(String pNombre, String pCategoria)
	{
		nombre  = pNombre;
		categoria = pCategoria;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "TipoProducto [nombre=" + nombre + ", categoria=" + categoria + "]";
	}

}
