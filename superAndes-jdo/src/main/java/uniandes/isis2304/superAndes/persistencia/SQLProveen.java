package uniandes.isis2304.superAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveen;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROVEEN de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLProveen
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
	// CONSTRUCTOR
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El Manejador de persistencia de la aplicación
	 */
	public SQLProveen(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------
	/**
	 * Crea y ejecuta una sentencia sql para adicionar un PROVEEN a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nitProveedor - Nit del proveedor
	 * @param codigoBarras - Código de barras del producto que provee
	 * @return El número de tuplas insertadas
	 */
	public long adicionarProveen (PersistenceManager pm, int nitProveedor, String codigoBarras) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlProveen() + "(nitproveedor, codbarras) values (?, ?)");
        q.setParameters(nitProveedor, codigoBarras);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para eliminar PROVEEN de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nitProveedor - Nit del proveedor
	 * @param codigoBarras - Código de barras del producto
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProveen(PersistenceManager pm, int nitProveedor, String codigoBarras)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlProveen() + " WHERE codbarras = ? AND nitproveedor = ?");
        q.setParameters(codigoBarras, nitProveedor);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta una sentencia para encontrar la información de PROVEEN en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PROVEEN
	 */
	public List<Proveen> darProveen(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProveen());
		q.setResultClass(Proveen.class);
		return (List<Proveen>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia para encontrar la información de los proveedores de un producto de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codBarras - Código de barras del producto
	 * @return Una lista con los nit de lo proveedores que tiene  el producto dado
	 */
	public List<Long> darProveedoresProducto(PersistenceManager pm, String codBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT nitproveedor FROM " + persistencia.getSqlProveen() + " WHERE codbarras = ?");
		q.setResultClass(Long.class);
		q.setParameters(codBarras);
		return (List<Long>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia para encontrar la información de los productos ofrecidos por un proveedor en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param nitProveedor - El nit del proveedor
	 * @return Una lista de los códigos de barras de los productos que tienen el proveedor dado
	 */
	public List<String> darProductosProveedor(PersistenceManager pm, int nitProveedor)
	{
		Query q = pm.newQuery(SQL, "SELECT codbarras FROM " + persistencia.getSqlProveen() + " WHERE nitproveedor = ?");
		q.setResultClass(String.class);
		q.setParameters(nitProveedor);
		return (List<String>) q.executeList();
	}
}