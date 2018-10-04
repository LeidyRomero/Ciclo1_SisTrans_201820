package uniandes.isis2304.superAndes.persistencia;
import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto FACTURA de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLFactura
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
	public SQLFactura(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que adiciona una FACTURA a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El id de la factura
	 * @param costoTotal - El costo total de la factura
	 * @param fecha - Fecha de generación de la factura
	 * @return El número de tuplas insertadas
	 */
	public long adicionarFactura(PersistenceManager pm, long idFactura, double costoTotal, Timestamp fecha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlFactura() + "(idfactura, costototal, fecha) values (?, ?, ?)");
        q.setParameters(idFactura, costoTotal, fecha);
        return (long) q.executeUnique();
	}
}