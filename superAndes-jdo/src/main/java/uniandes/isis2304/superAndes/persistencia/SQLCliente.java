package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto CLIENTE de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLCliente
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
	public SQLCliente(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un CLIENTE a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param correo - El correo del cliente
	 * @param nombre - El nombre del cliente
	 * @return El n�mero de tuplas insertadas
	 */
	public long adicionarCliente (PersistenceManager pm, String correo, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlCliente() + "(correo, nombre_consumidor, puntos) values (?, ?, ?)");
        q.setParameters(correo, nombre, 0.0);
        return (long) q.executeUnique();
	}
}