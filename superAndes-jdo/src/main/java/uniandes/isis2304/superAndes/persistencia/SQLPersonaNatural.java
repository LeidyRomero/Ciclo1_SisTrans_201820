package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author lj.romero
 *
 */
class SQLPersonaNatural
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLPersonaNatural (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	//TODO RF3 - Registrar cliente
	public long agregarPersonaNatural(PersistenceManager manager, String pDocumento, String pCorreo)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlPersonaNatural()+"(documento, correo) values (?,?)");
		q.setParameters(pDocumento, pCorreo);
		return (long) q.executeUnique();
	}
}