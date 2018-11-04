package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductosCarrito;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PRODUCTOS CARRITO de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
public class SQLProductosCarrito {

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
	
	private PersistenciaSuperAndes pp;

	//------------------------------------------------------------------
	// CONSTRUCTOR
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param pPersistencia - El manejador de persistencia de la aplicación
	 */
	public SQLProductosCarrito(PersistenciaSuperAndes pPersistencia)
	{
		pp = pPersistencia;
	}
	
	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------
	/**
	 * Crea y ejecuta una sentencia sql que adiciona un PRODUCTO CARRITO a la base de datos de SuperAdes
	 * @param pm - El manejador de persistencia
	 * @param codBarras - El código de barras del producto
	 * @param idCarrito - El identificador del carrito
	 * @param cantidad - La cantidad del producto
	 * @return La cantidad de tuplas insertadas.
	 */
	public long adicionarProductoCarrito(PersistenceManager pm, String codBarras, long idCarrito, int cantidad)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.getSqlProductosCarrito() + " (idcarrito, codbarras, cantidad) VALUES (?, ?, ?)");
		q.setParameters(idCarrito, codBarras, cantidad);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que elimina un PRODUCTO CARRITO de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codBarras - El código de barras del producto
	 * @param idCarrito - El identificador del carrito
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoCarrito(PersistenceManager pm, String codBarras, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlProductosCarrito() + " WHERE idcarrito = ? AND codbarras = ?");
		q.setParameters(idCarrito, codBarras);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que elimina un PRODUCTO CARRITO de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idCarrito - El identificador del carrito
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductosCarrito(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlProductosCarrito() + " WHERE idcarrito = ? AND cantidad = 0");
		q.setParameters(idCarrito);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que elimina una cantidad de un PRODUCTO del CARRITO de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codBarras - El código de barras del producto
	 * @param idCarrito - El identificador del carrito
	 * @param pCantidad - Cantidad a disminuir
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarCantidadProductoCarrito(PersistenceManager pm, String codBarras, long idCarrito, int pCantidad)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.getSqlProductosCarrito() + " SET cantidad = cantidad-? WHERE idcarrito = ? AND codbarras = ?");
		q.setParameters(pCantidad, idCarrito, codBarras);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para encontrar la información de LOS PRODUCTOS CARRITO con el identificador dado
	 * @param pm - El manejador de persistencia
	 * @param idCarrito - El identificador del carrito
	 * @return Una lista de objetos PRODUCTOS CARRITO que tienen el identificador dado
	 */
	public List<Object> darProductosCarritoPorId(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "SELECT cantidad, codbarras FROM " + pp.getSqlProductosCarrito() + " WHERE idcarrito = ?");
		q.setParameters(idCarrito);
		return (List<Object>) q.executeList();
	}
	
}
