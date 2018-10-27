package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PedidoProducto;
import uniandes.isis2304.superAndes.negocio.Producto;

/**
 * 
 * @author lj.romero
 *
 */
class SQLPedidoProducto
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLPedidoProducto (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	public long agregarPedidoProducto(PersistenceManager manager, String pCodigoBarras, long pIdPedido, double pCantidadProducto, double pPrecioProducto)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlPedidoProducto()+"(codigobarras, idpedido,cantidadproducto, precioproducto) values (?,?,?,?)");
		q.setParameters(pCodigoBarras,pIdPedido,pCantidadProducto, pPrecioProducto);
		return (long) q.executeUnique();
	}
	public List<PedidoProducto> buscarPedidoProducto(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPedidoProducto());
		q.setResultClass(PedidoProducto.class);
		return (List<PedidoProducto>) q.executeList();
	}
	public int darNumeroProductosPedido(PersistenceManager manager,long idPedido)
	{
		Query q = manager.newQuery(SQL, "SELECT numeroProductos, idpedido FROM (SELECT COUNT(*) numeroProductos, idpedido FROM " + persistencia.getSqlPedidoProducto()+" GROUP BY idpedido) WHERE idpedido = ?");
		q.setParameters(idPedido);
		q.setResultClass(Integer.class);
		return (Integer) q.executeUnique();
	}
	public long eliminarPedidoProducto(PersistenceManager manager, long id)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlPedidoProducto()+ " WHERE idpedido = ?");
		add.setParameters();
		return (long) add.executeUnique();
	}
}