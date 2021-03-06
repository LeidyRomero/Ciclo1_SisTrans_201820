package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.TipoProducto;

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
	public long agregarTipoDeProducto(PersistenceManager manager, String pNombreTipo, String pNombreCategoria)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlTipoProducto()+"(nombretipo, nombrecategoria) values (?,?)");
		q.setParameters(pNombreTipo, pNombreCategoria);
		return (long) q.executeUnique();
	}
	public List<TipoProducto> buscarTipos(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlTipoProducto());
		q.setResultClass(TipoProducto.class);
		return (List<TipoProducto>) q.executeList();
	}
	public TipoProducto buscarNombre(PersistenceManager manager,String pNombre)
	{
		Query q = manager.newQuery(SQL, "SELECT nombretipo FROM "+persistencia.getSqlTipoProducto()+"WHERE nombretipo = ?");
		q.setResultClass(TipoProducto.class);
		q.setParameters(pNombre);
		return (TipoProducto) q.executeUnique();
	}
	public long eliminarTipo(PersistenceManager manager, String pTipo)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlTipoProducto()+ " WHERE nombretipo = ?");
		add.setParameters(pTipo);
		return (long) add.executeUnique();
	}
}