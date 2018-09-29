package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author lj.romero
 *
 */
class SQLProductosEnPromocion
{
private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLProductosEnPromocion(PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	
	public long agregar (PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlProductosEnPromocion()+"");
		q.setParameters();
		return (long) q.executeUnique();
	}
}