package uniandes.isis2304.superAndes.persistencia;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto SUCURSAL FACTURA de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLSucursalFactura
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
	public SQLSucursalFactura(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	public long adicionarSucursalFactura (PersistenceManager pm, long idFactura, String direccion, String ciudad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlSucursalFactura() + "(idfactura, direccionsucursal, ciudad) values (?, ?, ?)");
        q.setParameters(idFactura, direccion, ciudad);
        return (long) q.executeUnique();
	}
	
	public List<String> dineroSucursalEnRango(PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin)
	{
		Query q = pm.newQuery(SQL, "SELECT SUM(fac.costototal) AS dinerorecolectado, direccionsucursal, ciudad "
				+ "FROM " + persistencia.getSqlFactura() + " fac, "+ persistencia.getSqlSucursalFactura()+" fa "
				+ "WHERE fac.idfactura = fa.idfactura AND fac.fecha BETWEEN ? AND ? "
				+ "GROUP BY direccionsucursal, ciudad");
		q.setParameters(fechaInicio, fechaFin);
		q.setResultClass(String.class);
		return (List<String>) q.executeList();
	}
}