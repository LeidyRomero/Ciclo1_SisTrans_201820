package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
/**
 * 
 * @author lj.romero
 *
 */
public interface VOProducto {
	public String getNombre();
	public String getMarca();
	public double getPrecioUnitario();
	public String getPresentacion();
	public double getPrecioUnidadMedida();
	public int getCantidadPresentacion();
	public String getUnidadMedida();
	public String getPeso();
	public String getVolumen();
	public String getCodigoBarras();
	public String getCalidad();
	public Date getFechaVencimiento();
	
	public String toString();
}
