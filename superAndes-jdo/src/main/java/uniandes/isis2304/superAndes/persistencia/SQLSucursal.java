package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SUCURSAL de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLSucursal
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
	public SQLSucursal(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia SQL para adicionar una SUCURSAL a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param tamanio - El tamaño de la sucursal
	 * @param direccion - La dirección de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @param nombre - El nombre de la sucursal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSucursal (PersistenceManager pm, String tamanio, String direccion, String ciudad, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlSucursal() + "(tamanio, direccion_sucursal, ciudad, nombre_sucursal) values (?, ?, ?, ?)");
        q.setParameters(tamanio, direccion, ciudad, nombre);
        return (long) q.executeUnique();
	}
}