package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

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
	//TODO RF2 - Registrar un producto
	public long agregarCategoria(PersistenceManager manager, String pNombre)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlCategoria()+" (nombre_categoria) values (?)");
		q.setParameters(pNombre);
		return (long) q.executeUnique();
	}
	
	public Categoria buscarNombre(PersistenceManager manager,String pNombre)
	{
		Query q = manager.newQuery(SQL, "SELECT nombre_categoria FROM "+persistencia.getSqlCategoria()+"WHERE nombre_categoria = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(pNombre);
		return (Categoria) q.executeUnique();
	}
}