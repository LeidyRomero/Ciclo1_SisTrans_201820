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
	//TODO RF2 - Registrar un producto
	public long agregarTipoDeProducto(PersistenceManager manager, String pNombreTipo, String pNombreCategoria)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlTipoProducto()+"(nombre_tipo, nombre_categoria) values (?,?)");
		q.setParameters(pNombreTipo, pNombreCategoria);
		return (long) q.executeUnique();
	}
}