package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
/**
 * 
 * @author lj.romero
 *
 */
public interface VOProducto {
	public String darNombre();
	public String darMarca();
	public double darPrecioUnitario();
	public String darPresentacion();
	public double darPrecioUnitarioMedida();
	public int darCantidadPresentacion();
	public String darUnidadMedida();
	public String darEspecificacionEmpacado();
	public int darCodigoBarras();
	public String darCalidad();
	public Date darFechaVencimiento();
	
	public String toString();
}
