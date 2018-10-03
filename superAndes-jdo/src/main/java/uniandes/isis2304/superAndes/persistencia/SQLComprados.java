package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	//TODO RF11 - Registrar venta TODO REAL: DISMINUIR INVENTARIO
	public long agregarComprados(PersistenceManager manager, String pCodigoBarras,int pCantidad, double pPrecioTotal, String pIdFactura)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlComprados()+" (codigo_barras, cantidad, precio_total, id_factura) values (?,?,?,?)");
		q.setParameters(pCodigoBarras, pCantidad, pPrecioTotal,pIdFactura);
		return (long) q.executeUnique();
	}
}