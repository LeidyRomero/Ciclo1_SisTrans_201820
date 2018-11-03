package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.*;

import uniandes.isis2304.superAndes.negocio.Bodega;

/**
 * 
 * @author lj.romero
 *
 */
class SQLBodega
{
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLBodega(PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	public long agregarBodega(PersistenceManager manager, String pTipo, double pVolumen, double pPeso, String pDireccionBodega, String pDireccionSucursal, String pCiudad)
	{
		Query add = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlBodega()+" (tipobodega, volumenbodega, pesobodega, direccionbodega, direccionsucursal, ciudad) values (?,?,?,?,?,?)");
		add.setParameters(pTipo, pVolumen, pPeso, pDireccionBodega, pDireccionSucursal, pCiudad);
		return (long) add.executeUnique();
	}
	//TODO RFC3 - Mostrar el indice de ocupacion de cada estante
	public List<Bodega> buscarBodegasSucursal(PersistenceManager manager, String pDireccion, String pCiudad)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlBodega()+"WHERE ciudad = ? AND direccionsucursal = ?");
		q.setParameters(pCiudad,pDireccion);
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
	public List<Bodega> buscarBodegas(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlBodega());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
	public long eliminarBodega(PersistenceManager manager,String direccionBodega, String direccionSucursal,String ciudad)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlBodega() + " WHERE direccionbodega = ? AND direccionsucursal = ? AND ciudad = ?");
		add.setParameters(direccionBodega,direccionSucursal,ciudad);
		return (long) add.executeUnique();
	}
	public Bodega buscarBodegaTipo(PersistenceManager manager, String categoria)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlBodega() + " WHERE tipoBodega = ?");
		q.setParameters(categoria);
		return (Bodega) q.executeUnique();
	}
	public List<Object []> calcularIndices(PersistenceManager manager, String pCiudad, String pDireccionSucursal)
	{
		Query q = manager.newQuery(SQL, "SELECT direccionbodega, ((cantidadactual/volumenbodega)*100) FROM (SELECT direccionbodega as direccion, sum(cantidadactual) AS cantidadActual FROM " + persistencia.getSqlCantidadEnBodega() + " WHERE direccionsucursal = ? AND ciudad = ? GROUP BY direccionBodega),"+ persistencia.getSqlBodega()+" WHERE direccion=direccionBodega");
		q.setParameters(pDireccionSucursal,pCiudad);
		return (List<Object []>) q.executeList();
	}
}