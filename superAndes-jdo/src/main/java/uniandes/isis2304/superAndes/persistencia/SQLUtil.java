package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.*;

/**
 * 
 * @author lj.romero
 *
 */
class SQLUtil
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes pp;

	public SQLUtil(PersistenciaSuperAndes persistencia) 
	{
		this.pp = persistencia;
	}
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo n�mero de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El n�mero de secuencia generado
	 */
	public long nextvalPedidos (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSecuenciaPedidos() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	public long [] limpiarSuperAndes(PersistenceManager pm)
	{
        Query qTipoPro = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlTipoProducto());
        Query qCantidadEnBodega = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCantidadEnBodega());
        Query qCantidadEnEstantes = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCantidadEnEstantes());

        return null;
	}
}