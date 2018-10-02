package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Bodega;

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
	
	public long agregarCantidadEnBodega(PersistenceManager manager, String pDireccionBodega, String pDireccionSucursal, String pCiudad, int pCantidadActual, int pCantidadMinima, int pCodigoBarras)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlCantidadEnBodega()+" (direccion_bodega, direccion_sucursal, ciudad, cantidad_actual, cantidad_minima, codigo_barras) values (?,?,?,?,?,?)");
		q.setParameters(pDireccionBodega, pDireccionSucursal, pCiudad, pCantidadActual, pCantidadMinima, pCodigoBarras);
		return (long) q.executeUnique();
	}
	public int buscarCantidadActual(PersistenceManager manager, String pDireccionBodega, String pDireccionSucursal, String pCiudad)
	{
		Query q = manager.newQuery(SQL, "SELECT cantidad_actual FROM " + persistencia.getSqlCantidadEnBodega()+" WHERE direccion_bodega = ? AND ciudad = ? AND direccion_sucursal = ?");
		q.setParameters(pDireccionBodega, pCiudad,pDireccionSucursal);
		q.setResultClass(Integer.class);
		return (Integer) q.executeUnique();
	}
}