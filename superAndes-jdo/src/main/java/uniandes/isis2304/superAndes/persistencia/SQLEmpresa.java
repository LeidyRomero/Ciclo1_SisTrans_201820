package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author lj.romero
 *
 */
class SQLEmpresa
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLEmpresa (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	
	public long agregar (PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlEmpresa()+"");
		q.setParameters();
		return (long) q.executeUnique();
	}
}