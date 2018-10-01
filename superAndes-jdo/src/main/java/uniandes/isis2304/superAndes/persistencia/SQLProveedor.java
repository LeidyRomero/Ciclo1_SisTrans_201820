package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROVEEDOR de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLProveedor
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
	public SQLProveedor(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta la sentencia que adiciona un PROVEEDOR a la basa de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nitProveedor - Nit del proveedor
	 * @param nombreProveedor - Nombre del proveedor
	 * @return Número de tuplas insertadas
	 */
	public long adicionarProveedor (PersistenceManager pm, int nitProveedor, String nombreProveedor) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlProveedor() + "(nit_proveedor, nombre_proveedor, calificacion_proveedor) values (?, ?, ?)");
        q.setParameters(nitProveedor, nombreProveedor, null);
        return (long) q.executeUnique();
	}
}