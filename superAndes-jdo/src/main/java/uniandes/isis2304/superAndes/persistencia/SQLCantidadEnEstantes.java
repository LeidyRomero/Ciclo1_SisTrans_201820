package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto CANTIDAD EN ESTANTES de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLCantidadEnEstantes
{
	//------------------------------------------------------------------
	// CONSTANTES
	//------------------------------------------------------------------
	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	//------------------------------------------------------------------
	// ATRIBUTOS
	//------------------------------------------------------------------
	
	private PersistenciaSuperAndes persistencia;

	//------------------------------------------------------------------
	// M�TODOS
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLCantidadEnEstantes(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	/**
	 * Crea y ejecuta una sentencia sql que adiciona uns CANTIDAD EN ESTANTES a la base de datos de SuperAndes
	 * @param pm
	 * @param codigoBarras
	 * @param idEstante
	 * @param cantidadActual
	 * @param cantidadMinima
	 * @return
	 */
	public long adicionarCantidadEstantes (PersistenceManager pm, int codigoBarras, long idEstante, int cantidadActual, int cantidadMinima) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlCantidadEnEstantes() + "(cod_barras, id_estante, cantidad_actual, cantidad_minima) values (?, ?, ?, ?)");
        q.setParameters(codigoBarras, idEstante, cantidadActual, cantidadMinima);
        return (long) q.executeUnique();
	}
	
	public int buscarCantidadActual(PersistenceManager manager, long idEstante)
	{
		Query q = manager.newQuery(SQL, "SELECT cantidad_actual FROM " + persistencia.getSqlCantidadEnEstantes()+" WHERE id_estante = ?");
		q.setParameters(idEstante);
		q.setResultClass(Integer.class);
		return (Integer) q.executeUnique();
	}
}