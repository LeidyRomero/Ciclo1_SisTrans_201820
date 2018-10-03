package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.net.aso.p;
import uniandes.isis2304.superAndes.negocio.PersonaNatural;
import uniandes.isis2304.superAndes.negocio.Producto;

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
	public List<PersonaNatural> buscarPersonas(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPersonaNatural());
		q.setResultClass(PersonaNatural.class);
		return (List<PersonaNatural>) q.executeList();
	}
	public long eliminarPersona(PersistenceManager manager, String pCorreo)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlPersonaNatural()+ " WHERE correo = ?");
		add.setParameters(pCorreo);
		return (long) add.executeUnique();
	}
}