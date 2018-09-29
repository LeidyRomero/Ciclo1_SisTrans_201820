package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author lj.romero
 *
 */
class SQLCategoria
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLCategoria(PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	
	public long agregar (PersistenceManager manager, String pNombre)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlCategoria()+" (nombre_categoria) values (?)");
		q.setParameters(pNombre);
		return (long) q.executeUnique();
	}
}