package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Producto;
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
	public long agregarCategoria(PersistenceManager manager, String pNombre)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlCategoria()+" (nombrecategoria) values (?)");
		q.setParameters(pNombre);
		return (long) q.executeUnique();
	}
	public List<Categoria> buscarCategorias(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCategoria());
		q.setResultClass(Categoria.class);
		return (List<Categoria>) q.executeList();
	}
	public Categoria buscarNombre(PersistenceManager manager,String pNombre)
	{
		Query q = manager.newQuery(SQL, "SELECT nombrecategoria FROM "+persistencia.getSqlCategoria()+"WHERE nombrecategoria = ?");
		q.setResultClass(Categoria.class);
		q.setParameters(pNombre);
		return (Categoria) q.executeUnique();
	}
	public long eliminarCategoria(PersistenceManager manager, String pCategoria)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlCategoria()+ " WHERE nombrecategoria = ?");
		add.setParameters(pCategoria);
		return (long) add.executeUnique();
	}
}