package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author lj.romero
 *
 */
class SQLTipoProducto
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLTipoProducto (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	
	public long agregar (PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.darTablaTipoProducto()+"");
		q.setParameters();
		return (long) q.executeUnique();
	}
}