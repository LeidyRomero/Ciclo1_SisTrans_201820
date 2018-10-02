package uniandes.isis2304.superAndes.persistencia;
import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto FACTURA de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLFactura
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
	public SQLFactura(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que adiciona una FACTURA a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El id de la factura
	 * @param costoTotal - El costo total de la factura
	 * @param fecha - Fecha de generaci�n de la factura
	 * @return El n�mero de tuplas insertadas
	 */
	public long adicionarFactura(PersistenceManager pm, long idFactura, double costoTotal, Timestamp fecha) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlFactura() + "(id_factura, costo_total, fecha) values (?, ?, ?)");
        q.setParameters(idFactura, costoTotal, fecha);
        return (long) q.executeUnique();
	}
}