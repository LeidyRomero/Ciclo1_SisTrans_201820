package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author lj.romero
 *
 */
class SQLCantidadEnBodega
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLCantidadEnBodega (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	
	public long agregar (PersistenceManager manager, String pDireccionBodega, String pDireccionSucursal, String pCiudad, int pCantidadActual, int pCantidadMinima, int pCodigoBarras)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.darTablaCantidadEnBodega()+" (direccion_bodega, direccion_sucursal, ciudad, cantidad_actual, cantidad_minima, codigo_barras) values (?,?,?,?,?,?)");
		q.setParameters(pDireccionBodega, pDireccionSucursal, pCiudad, pCantidadActual, pCantidadMinima, pCodigoBarras);
		return (long) q.executeUnique();
	}
}