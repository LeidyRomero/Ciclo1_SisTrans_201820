package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
/**
 * Interfaz para los métodos get de PRODUCTO
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz
 * @author lj.romero
 *
 */
public interface VOProducto {
	public String getNombreProducto();
	public String getMarca();
	public double getPrecioUnitario();
	public String getPresentacion();
	public double getPrecioUniMedida();
	public int getCantPresentacion();
	public String getUnidadMedida();
	public String getPesoProducto();
	public String getVolumenProducto();
	public String getCodBarras();
	public String getCalidad();
	public Date getFechaVencimiento();
	
	public String toString();
}
