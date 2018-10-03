package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	//TODO RF9 - Registrar un producto a un pedido
	public long agregarPedidoProducto(PersistenceManager manager, String pCodigoBarras, long pIdPedido, double pCantidadProducto, double pPrecioProducto)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlPedidoProducto()+"(codigo_barras, id_pedido,cantidad_producto, precio_producto) values (?,?,?,?)");
		q.setParameters(pCodigoBarras,pIdPedido,pCantidadProducto, pPrecioProducto);
		return (long) q.executeUnique();
	}
	public int darNumeroProductosPedido(PersistenceManager manager,long idPedido)
	{
		Query q = manager.newQuery(SQL, "SELECT numeroProductos, id_pedido FROM (SELECT COUNT(*) numeroProductos, id_pedido FROM " + persistencia.getSqlPedidoProducto()+" GROUP BY id_pedido) WHERE id_pedido = ?");
		q.setParameters(idPedido);
		q.setResultClass(Integer.class);
		return (Integer) q.executeUnique();
	}
}