package uniandes.isis2304.superAndes.negocio;
/**
 * 
 * @author lj.romero
 *
 */
public interface VOCantidadEnBodega {

	public String darCodigoBarras();
	public String darDireccionBodega();
	public String darDireccionSucursal();
	public String darCiudad();
	public int darCantidadActual();
	public int darCantidadMinima();
	public String toString();
}
