package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.*;

/**
 * 
 * @author lj.romero / mj.ocampov
 *
 */
class SQLUtil
{
	//--------------------------------------------------------
	// CONSTANTES
	//--------------------------------------------------------
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	//--------------------------------------------------------
	// ATRIBUTOS
	//--------------------------------------------------------
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaSuperAndes pp;

	//--------------------------------------------------------
	// CONSTRUCTOR
	//--------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El manejador de persistencia de la aplicación
	 */
	public SQLUtil(PersistenciaSuperAndes persistencia) 
	{
		this.pp = persistencia;
	}
	
	//--------------------------------------------------------
	// MÉTODOS
	//--------------------------------------------------------
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia para los pedidos
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalPedidos (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.getSecuenciaPedidos() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalPromociones (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.getSecuenciaPromociones() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextvalFacturas(PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.getSecuenciaFacturas() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}
	
	/**
	 * Crea y ejecuta una sentencia SQL para cada tabla de la base de datos - 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 24 números que indican el número de tuplas borrasdas en las tablas de la base de datos de SuperAndes
	 */
	public long [] limpiarSuperAndes(PersistenceManager pm)
	{
		//TODO Terminar el limpiar
        Query qTipoPro = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlTipoProducto());
        Query qCantidadEnBodega = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCantidadEnBodega());
        Query qCantidadEnEstantes = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCantidadEnEstantes());

        return null;
	}
}