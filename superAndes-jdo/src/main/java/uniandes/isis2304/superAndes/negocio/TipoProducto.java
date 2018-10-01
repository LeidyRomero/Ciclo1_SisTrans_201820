package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public class TipoProducto implements VOTipoProducto{
	private String nombre;
	public TipoProducto()
	{
		nombre = "";	
	}
	public TipoProducto(String pNombre)
	{
		nombre  = pNombre;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String toString() {
		return "TipoProducto [nombre=" + nombre + "]";
	}

}
