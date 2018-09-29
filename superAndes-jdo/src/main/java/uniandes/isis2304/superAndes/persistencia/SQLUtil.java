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
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSecuenciaSuperAndes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
}