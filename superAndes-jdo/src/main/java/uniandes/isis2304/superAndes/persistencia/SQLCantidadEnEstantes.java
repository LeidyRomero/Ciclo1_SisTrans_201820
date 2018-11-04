package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.CantidadEnEstantes;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto CANTIDAD EN ESTANTES de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLCantidadEnEstantes
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
	
	/**
	 * 
	 */
	private PersistenciaSuperAndes persistencia;

	//------------------------------------------------------------------
	// CONSTRUCTOR
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLCantidadEnEstantes(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------
	
	/**
	 * Crea y ejecuta una sentencia sql que adiciona una CANTIDAD EN ESTANTES a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codigoBarras - Codigo de barras del producto
	 * @param idEstante - Id del estante donde se va a poner el producto
	 * @param cantidadActual - Cantidad actual del producto en el estante
	 * @param cantidadMinima - Cantidad m�nima para hacer un pedido en bodega
	 * @return La cantidad de filas insertadas.
	 */
	public long adicionarCantidadEstantes (PersistenceManager pm, String codigoBarras, long idEstante, int cantidadActual, int cantidadMinima) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlCantidadEnEstantes() + "(codbarras, idestante, cantidadactual, cantidadminima) values (?, ?, ?, ?)");
        q.setParameters(codigoBarras, idEstante, cantidadActual, cantidadMinima);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para eliminar CANTIDADES EN ESTANTES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idEstante - Id del estante 
	 * @param codBarras - C�digo de barras del prodcuto
	 * @return La cantidad de filas eliminidas
	 */
	public long eliminarCantidadEstantes(PersistenceManager pm, long idEstante, String codBarras)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlCantidadEnEstantes() + " WHERE idestante = ? AND codbarras = ?");
        q.setParameters(idEstante, codBarras);
        return (long) q.executeUnique(); 
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para encontrar la informaci�n de UNA CANTIDAD EN ESTANTE de la base de datos de SuperAndes 
	 * @param pm - El manejador de persistencia
	 * @param idEstante - El id del estante que se busca
	 * @param codBarras - C�digo de barras del producto en el estante
	 * @return El objeto CANTIDAD EN ESTANTES que tiene los par�metros dados
	 */
	public CantidadEnEstantes CantidadEnEstantes(PersistenceManager pm, long idEstante, String codBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCantidadEnEstantes() + " WHERE idestante = ? AND codbarras = ?");
		q.setResultClass(CantidadEnEstantes.class);
		q.setParameters(idEstante, codBarras);
		return (CantidadEnEstantes) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para encontrar la informaci�n de LAS CANTIDADES EN ESTANTES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CANTIDAD EN ESTANTES
	 */
	public List<CantidadEnEstantes> darCantidadesEnEstante(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCantidadEnEstantes());
		q.setResultClass(CantidadEnEstantes.class);
		return (List<CantidadEnEstantes>) q.executeList();
	}
	
	//------------------------------------------------------------------
	// M�TODOS
	//------------------------------------------------------------------
	
	/**
	 * Crea y ejecuta una sentencia sql que busca la cantidad actual de un producto en un estante en la base de datos de SuperAndes 
	 * @param pm - El manejador de persistencia
	 * @param ciudad - Ciudad de la sucursal donde est� el estante
	 * @param direccion - Direcci�n de la sucursal donde est� el estante
	 * @param codigoBarras - C�digo de barras del producto que se quiere saber la cantidad actual
	 * @return La cantidad actual del producto en el estante
	 */
	public int buscarCantidadActual(PersistenceManager pm, String ciudad, String direccion, String codigoBarras)
	{
		Query q = pm.newQuery(SQL, "SELECT SUM(cant.cantidadactual) "
				+ "FROM " + persistencia.getSqlCantidadEnEstantes()+" cant, "
				+ persistencia.getSqlEstante() + " est"
				+" WHERE est.idestante = cant.idestante AND"
				+ "est.ciudad = ? AND"
				+ "est.direccionsucursal = ? AND"
				+ "cant.codbarras = ?");
		q.setResultClass(Integer.class);
		q.setParameters(ciudad, direccion, codigoBarras);
		return (Integer) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que busca todos los c�digos de barras del estante dado
	 * @param pm - El manejador de persistencia
	 * @return Los c�digos de barras de los productos en ese estante
	 */
	public List<String> buscarCodigosDeBarrasProductos(PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT codbarras FROM " + persistencia.getSqlCantidadEnEstantes() + " WHERE idestante = ?");
		q.setResultClass(String.class);
		q.setParameters(idEstante);
		return (List<String>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que aumenta la cantidad en estantes del producto en la base de datos de SuperAdes
	 * @param pm - El manejador de persistencia
	 * @param pCantidad - Cantidad a aumentar del producto
	 * @param pCodBarras - C�digo de barras del producto
	 * @param idEstante - Id del estante del producto
	 */
	public long aumentarCantidadEnEstantes(PersistenceManager pm, int pCantidad, String pCodBarras, long idEstante)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlCantidadEnEstantes() +" SET cantidadactual = cantidadactual + ? WHERE codbarras = ? AND idestante = ?");
		q.setParameters(pCantidad, pCodBarras, idEstante);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que disminuye la cantidad en estantes del producto en la base de datos de SuperAdes
	 * @param pm - El manejador de persistencia
	 * @param pCantidad - Cantidad a disminuir del producto
	 * @param pCodBarras - C�digo de barras del producto
	 * @param idEstante - Id del estante del producto
	 */
	public long disminuirCantidadEnEstantes(PersistenceManager pm, int pCantidad, String pCodBarras, long idEstante)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlCantidadEnEstantes() +" SET cantidadactual = cantidadactual - ? WHERE codigobarras = ? AND idestante = ?");
		q.setParameters(pCantidad, pCodBarras, idEstante);
		return (long) q.executeUnique();
	}
	
	//------------------------------------------------------
	// CONSULTAS
	//------------------------------------------------------
	/**
	 * Crea y ejecuta una sentencia para consultar la cantidad en estantes real de un sucursal dada
	 * @param pm - El manejador de persistencia
	 * @param ciudad - La ciudad de la sucursal
	 * @param direccionSucursal - La direcci�n de la sucursal
	 * @return 
	 */
	public List<Object> darCantidadEnEstantesReales(PersistenceManager pm, String ciudad, String direccionSucursal)
	{
		String sql = "SELECT a.nombreproducto, a.codigobarras, a.cantidadminima, a.idestante, NVL((a.cantidadactual - b.total),a.cantidadactual)AS cantidadactual FROM ((SELECT e.ciudad, e.direccionsucursal, ce.codigobarras, ce.cantidadactual, ce.cantidadminima, ce.idestante, p.nombreproducto FROM A_CANTIDAD_EN_ESTANTES ce, A_ESTANTE e, A_PRODUCTO p WHERE ce.idestante = e.idestante AND ce.codigobarras = p.codbarras AND e.ciudad = ? AND e.direccionsucursal = ?) a LEFT OUTER JOIN (SELECT  c.ciudad, c.direccionsucursal, pc.codbarras, SUM(pc.cantidad) AS total FROM A_PRODUCTOS_CARRITO pc, A_CARRITO c WHERE pc.idcarrito = c.idcarrito GROUP BY c.ciudad, c.direccionsucursal, pc.codbarras) b ON (b.ciudad = a.ciudad AND b.direccionsucursal = a.direccionsucursal AND a.codigobarras = b.codbarras))";
		Query q = pm.newQuery(SQL, sql);
		q.setParameters(ciudad, direccionSucursal);
		return (List<Object>) q.executeList();
	}
	
	
}