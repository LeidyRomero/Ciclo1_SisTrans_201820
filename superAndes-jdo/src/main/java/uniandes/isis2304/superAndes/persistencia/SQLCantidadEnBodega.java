package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Bodega;
import uniandes.isis2304.superAndes.negocio.CantidadEnBodega;

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

	public long adicionarCantidadEnBodega(PersistenceManager manager, String pDireccionBodega, String pDireccionSucursal, String pCiudad, int pCantidadActual, int pCantidadMinima, String pCodigoBarras)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlCantidadEnBodega()+" (direccionbodega, direccionsucursal, ciudad, cantidadactual, cantidadminima, codigobarras) values (?,?,?,?,?,?)");
		q.setParameters(pDireccionBodega, pDireccionSucursal, pCiudad, pCantidadActual, pCantidadMinima, pCodigoBarras);
		return (long) q.executeUnique();
	}
	public int buscarCantidadActual(PersistenceManager manager, String pDireccionBodega, String pDireccionSucursal, String pCiudad)
	{
		Query q = manager.newQuery(SQL, "SELECT cantidadactual FROM " + persistencia.getSqlCantidadEnBodega()+" WHERE direccionbodega = ? AND ciudad = ? AND direccionsucursal = ?");
		q.setParameters(pDireccionBodega, pCiudad,pDireccionSucursal);
		q.setResultClass(Integer.class);
		return (Integer) q.executeUnique();
	}
	public long subirInventario(PersistenceManager manager, int pCantidad, String pCiudad, String pDireccionSucursal, String pDireccionBodega)
	{
		Query q = manager.newQuery(SQL, "UPDATE " + persistencia.getSqlCantidadEnBodega() + " SET cantidadactual = cantidadactual + ? WHERE direccionbodega = ? AND direccionsucursal = ? AND ciudad = ?");
		q.setParameters(pDireccionBodega, pDireccionSucursal, pCiudad);
		return (long) q.executeUnique();    
	}
	public long disminuirInventario(PersistenceManager manager, int pCantidad, String pCiudad, String pDireccionSucursal, String pDireccionBodega)
	{
		Query q = manager.newQuery(SQL, "UPDATE " + persistencia.getSqlCantidadEnBodega() + " SET cantidadactual = cantidadactual - ? WHERE direccionbodega = ? AND direccionsucursal = ? AND ciudad = ?");
		q.setParameters(pDireccionBodega, pDireccionSucursal, pCiudad);
		return (long) q.executeUnique(); 
	}
	public List<CantidadEnBodega> buscarCantidadEnBodega(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCantidadEnBodega());
		q.setResultClass(Bodega.class);
		return (List<CantidadEnBodega>) q.executeList();
	}
	public long eliminarCantidadEnBodega(PersistenceManager manager, String pCodigo, String pDireccionSucursal, String pDireccionBodega, String pCiudad)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlCantidadEnBodega() + " WHERE direccionbodega = ? AND direccionsucursal = ? AND ciudad = ? AND codigobarras = ?");
		add.setParameters(pDireccionBodega,pDireccionSucursal, pCiudad, pCodigo);
		return (long) add.executeUnique();
	}
}