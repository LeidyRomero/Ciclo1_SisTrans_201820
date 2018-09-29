package uniandes.isis2304.superAndes.negocio;

import java.sql.Date;

public interface VOOrdenPedido {


	public Date getFechaEsperadaEntrega();

	public String getEstado();
	
	public Date getFechaEntrega();
	
	public String getCalificacionPedido();

	public int getNitProveedor();

	public long getIdPedido();
	
	public String getCiudadSucursal();

	public String getDireccionSucursal();
	
	public String toString();
}