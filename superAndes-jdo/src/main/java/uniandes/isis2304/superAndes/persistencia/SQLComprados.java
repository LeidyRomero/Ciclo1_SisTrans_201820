package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Comprados;
import uniandes.isis2304.superAndes.negocio.Producto;

/**
 * 
 * @author lj.romero
 *
 */
class SQLComprados
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLComprados (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	public long agregarComprados(PersistenceManager manager, String pCodigoBarras,int pCantidad, double pPrecioTotal, long pIdFactura)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlComprados()+" (codigobarras, cantidad, preciototal, idfactura) values (?,?,?,?)");
		q.setParameters(pCodigoBarras, pCantidad, pPrecioTotal,pIdFactura);
		return (long) q.executeUnique();
	}
	public List<Comprados> buscarComprados(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlComprados());
		q.setResultClass(Comprados.class);
		return (List<Comprados>) q.executeList();
	}
	public long eliminarComprados(PersistenceManager manager, String pCodigo, long idFactura)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlComprados()+ " WHERE codigobarras = ? AND idfactura = ?");
		add.setParameters(pCodigo, idFactura);
		return (long) add.executeUnique();
	}
}