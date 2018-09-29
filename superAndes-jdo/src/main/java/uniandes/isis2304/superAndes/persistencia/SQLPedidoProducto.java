package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author lj.romero
 *
 */
class SQLPedidoProducto
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLPedidoProducto (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	
	public long agregar (PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlPedidoProducto()+"");
		q.setParameters();
		return (long) q.executeUnique();
	}
}