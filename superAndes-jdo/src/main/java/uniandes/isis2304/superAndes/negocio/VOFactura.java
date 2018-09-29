package uniandes.isis2304.superAndes.negocio;

import java.sql.Date;

public interface VOFactura {

	public long getIdFactura();
	
	public Date getFecha();
	
	public double getCostoTotal();
	
	public String toString();
}
