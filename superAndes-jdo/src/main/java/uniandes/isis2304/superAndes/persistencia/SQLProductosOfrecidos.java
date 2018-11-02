package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.ProductosOfrecidos;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PRODCTOS OFRECIDOS de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
public class SQLProductosOfrecidos {

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
	public SQLProductosOfrecidos(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}

	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------
	/**
	 * Crea y ejecuta la sentencia sql que adiciona un PRODUCTO OFRECIDO a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codigoBarras - El código de barras del producto
	 * @param direccionSucursal - Dirección de la sucursal
	 * @param ciudad - Ciudad de la sucursal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarProductoOfrecido (PersistenceManager pm, String codigoBarras, String direccionSucursal, String ciudad) 
	{
		//TODO Meter el nivel de reorden
		Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlProductosOfrecidos() + "(codigobarras, direccionsucursal, ciudad) values (?, ?, ?)");
		q.setParameters(codigoBarras, direccionSucursal, ciudad);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia sql para eliminar PRODUCTOS OFRECIDOS de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codBarras - Código de barras del producto
	 * @param ciudad - Ciudad de la sucursal que lo ofrece
	 * @param direccionSucursal - Dirección de la sucursal que lo ofrece
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarProductoOfrecido(PersistenceManager pm, String codBarras, String ciudad, String direccionSucursal)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlProductosOfrecidos() + " WHERE codigobarras = ? AND ciudad = ? AND direccionsucursal = ?");
		q.setParameters(codBarras, ciudad, direccionSucursal);
		return (long) q.executeUnique(); 
	}
	
	/**
	 * Crea y ejecuta la sentencia sql para encontrar información de LOS PRODUCTOS OFRECIDOS en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos PRODUCTOS OFRECIDOS
	 */
	public List<ProductosOfrecidos> darProductosOfrecidos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProductosOfrecidos());
		q.setResultClass(ProductosOfrecidos.class);
		return (List<ProductosOfrecidos>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta la sentencia sql para encontrar la información de los productos ofrecidos por una sucursal de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param ciudad - Ciudad de la sucursal
	 * @param direccionSucursal - Dirección de la sucursal
	 * @return Una lista con los códigos de barras de los productos
	 */
	public List<String> darProductosOfrecidosPorSucursal(PersistenceManager pm, String ciudad, String direccionSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT codigobarras FROM " + persistencia.getSqlProductosOfrecidos() + " WHERE ciudad = ? AND direccionsucursal = ?");
		q.setResultClass(String.class);
		q.setParameters(ciudad, direccionSucursal);
		return (List<String>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para encontrar las sucursales que ofrecen un producto en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codBarras - Código de barras del produto
	 * @return Una lista de arreglos de objetos, de tamaño 2. Los elementos de los arreglos corresponden a los datos de las sucursales (direccionsucursal, ciudad)
	 */
	public List<Object> darSucursalesPorProducto(PersistenceManager pm, String codBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT direccionsucursal, ciudad FROM " + persistencia.getSqlProductosOfrecidos() + " WHERE codigobarras = ?");
		q.setParameters(codBarras);
		return q.executeList();
	}
}
