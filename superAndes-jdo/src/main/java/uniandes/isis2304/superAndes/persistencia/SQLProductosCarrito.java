package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductosCarrito;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto PRODUCTOS CARRITO de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
public class SQLProductosCarrito {

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
	
	private PersistenciaSuperAndes pp;

	//------------------------------------------------------------------
	// CONSTRUCTOR
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param pPersistencia - El manejador de persistencia de la aplicaci�n
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
	 * @param codBarras - El c�digo de barras del producto
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
	 * @param codBarras - El c�digo de barras del producto
	 * @param idCarrito - El identificador del carrito
	 * @return El n�mero de tuplas eliminadas
	 */
	public long eliminarProductoCarrito(PersistenceManager pm, String codBarras, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlProductosCarrito() + " WHERE idcarrito = ? AND codbarras = ?");
		q.setParameters(idCarrito, codBarras);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que elimina una cantidad de un PRODUCTO del CARRITO de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codBarras - El c�digo de barras del producto
	 * @param idCarrito - El identificador del carrito
	 * @param pCantidad - Cantidad a disminuir
	 * @return El n�mero de tuplas eliminadas
	 */
	public long eliminarCantidadProductoCarrito(PersistenceManager pm, String codBarras, long idCarrito, int pCantidad)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + pp.getSqlProductosCarrito() + " SET cantidad = cantidad-? WHERE idcarrito = ? AND codbarras = ?");
		q.setParameters(pCantidad, idCarrito, codBarras);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para encontrar la informaci�n de LOS PRODUCTOS CARRITO con el identificador dado
	 * @param pm - El manejador de persistencia
	 * @param idCarrito - El identificador del carrito
	 * @return Una lista de objetos PRODUCTOS CARRITO que tienen el identificador dado
	 */
	public List<ProductosCarrito> darProductosCarritoPorId(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.getSqlProductosCarrito() + " WHERE idcarrito = ?");
		q.setResultClass(ProductosCarrito.class);
		q.setParameters(idCarrito);
		return (List<ProductosCarrito>) q.executeList();
	}
	
	
}
