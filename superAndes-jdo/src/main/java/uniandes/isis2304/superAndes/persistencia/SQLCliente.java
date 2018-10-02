package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CLIENTE de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLCliente
{
	//------------------------------------------------------------------
	// CONSTANTES
	//------------------------------------------------------------------
	
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	//------------------------------------------------------------------
	// ATRIBUTOS
	//------------------------------------------------------------------
	
	private PersistenciaSuperAndes persistencia;

	//------------------------------------------------------------------
	// MÉTODOS
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El Manejador de persistencia de la aplicación
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
	 * @return El número de tuplas insertadas
	 */
	public long adicionarCliente (PersistenceManager pm, String correo, String nombre) 
	{
		int puntos = 0;
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlCliente() + "(correo, nombre_consumidor, puntos) values (?, ?, ?)");
        q.setParameters(correo, nombre, puntos);
        return (long) q.executeUnique();
	}
}