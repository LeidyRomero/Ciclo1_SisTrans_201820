package uniandes.isis2304.superAndes.persistencia;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SUCURSAL FACTURA de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLSucursalFactura
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
	public SQLSucursalFactura(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	public long adicionarSucursalFactura (PersistenceManager pm, long idFactura, String direccion, String ciudad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlSucursalFactura() + "(id_factura, direccion_sucursal, ciudad) values (?, ?, ?)");
        q.setParameters(idFactura, direccion, ciudad);
        return (long) q.executeUnique();
	}
	
	public List<String> dineroSucursalEnRango(PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin)
	{
		Query q = pm.newQuery(SQL, "SELECT SUM(fac.costo_total) AS dinero_recolectado, direccion_sucursal, ciudad "
				+ "FROM " + persistencia.getSqlFactura() + " fac, "+ persistencia.getSqlSucursalFactura()+" fa "
				+ "WHERE fac.id_factura = fa.id_factura AND fac.fecha BETWEEN ? AND ? "
				+ "GROUP BY direccion_sucursal, ciudad");
		q.setParameters(fechaInicio, fechaFin);
		q.setResultClass(String.class);
		return (List<String>) q.executeList();
	}
}