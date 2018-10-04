package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class TipoProducto implements VOTipoProducto{
	private String nombreTipo;
	private String nombreCategoria;
	public TipoProducto()
	{
		nombreTipo = "";
		nombreCategoria = "";
	}
	public TipoProducto(String pNombre, String pCategoria)
	{
		nombreTipo  = pNombre;
		nombreCategoria = pCategoria;
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String categoria) {
		this.nombreCategoria = categoria;
	}
	public String getNombreTipo() {
		return nombreTipo;
	}

	public void setNombreTipo(String nombre) {
		this.nombreTipo = nombre;
	}
	@Override
	public String toString() {
		return "TipoProducto [nombre=" + nombreTipo + ", categoria=" + nombreCategoria + "]";
	}

}
