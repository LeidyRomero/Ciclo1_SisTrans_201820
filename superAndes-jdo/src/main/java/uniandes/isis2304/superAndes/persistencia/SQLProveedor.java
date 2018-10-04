package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.VOProveedor;

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
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlProveedor() + "(nitproveedor, nombreproveedor, calificacionproveedor) values (?, ?, ?)");
        q.setParameters(nitProveedor, nombreProveedor, null);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia para eliminar un PROVEEDOR en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nombre - Nombre del proveedor
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProveedorPorNombre (PersistenceManager pm, String nombre)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlProveedor() + " WHERE nombreproveedor = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta una sentencia para eliminar un PROVEEDOR en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nitProveedor - Nit del proveedor a eliminar
	 * @return Número de tuplas eliminadas
	 */
	public long eliminarProveedorPorNit (PersistenceManager pm, int nitProveedor)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlProveedor() + " WHERE nitproveedor = ?");
        q.setParameters(nitProveedor);
        return (long) q.executeUnique();            
	}

	/**
	 * Crea y ejecuta una sentencia para consultar un PROVEEDOR en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nitProveedor - Nit del proveedor a consultar
	 * @return El proveedor con el nit dado
	 */
	public Proveedor darProveedorPorNit (PersistenceManager pm, long nitProveedor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProveedor() + " WHERE nitproveedor = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(nitProveedor);
		return (Proveedor) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para consultar PROVEEDORES en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nombre - Nombre del proveedor a consultar
	 * @return Lista de tuplas con el nombre dado
	 */
	public List<Proveedor> darProveedoresPorNombre (PersistenceManager pm, String nombre) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProveedor() + " WHERE nombreproveedor = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(nombre);
		return (List<Proveedor>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia para consultar todos los PROVEEDORES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Lista de todas las tuplas de la tabla PROVEEDOR
	 */
	public List<Proveedor> darProveedores (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProveedor());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia para modificar un PROVEEDOR de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nitProveedor - Nit del proveedor a modificar
	 * @param calificacion - Nueva calificación
	 * @return Número de tuplas modificadas
	 */
	public long cambiarCalificacionProveedor (PersistenceManager pm, int nitProveedor, String calificacion) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlProveedor()+ " SET calificacionproveedor = ? WHERE nitproveedor = ?");
	     q.setParameters(calificacion, nitProveedor);
	     return (long) q.executeUnique();            
	}
}