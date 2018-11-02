package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OrdenPedido;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto ORDEN PEDIDO de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLOrdenPedido
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
	// CONSTRUCTOR
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El Manejador de persistencia de la aplicaci�n
	 */
	public SQLOrdenPedido(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------

	/**
	 * Crea y ejecuta una sentencia sql que adiciona una ORDEN DE PEDIDO a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaEsperada - Fecha esperada de entrega
	 * @param nitProveedor - Nit del proveedor
	 * @param ciudad - Ciudad de la sucursal que genera el pedido
	 * @param direccionSucursal - Direcci�n de la sucursal que genera el pedido
	 * @param direccionBodega - Direcci�n de la bodega que recibe el pedido
	 * @param idPedido - id del pedido
	 * @return N�mero de tuplas insertadas
	 */
	public long adicionaroOrdenPedido (PersistenceManager pm, Timestamp fechaEsperada, int nitProveedor, String ciudad, String direccionSucursal, String direccionBodega, long idPedido) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlOrdenPedido() + "(fechaesperadaentrega, estado, fechaentrega, calificacionpedido, nitproveedor, ciudad, direccionsucursal, direccionbodega, idpedido) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(fechaEsperada, "En espera", null, null, nitProveedor, ciudad, direccionSucursal, direccionBodega, idPedido);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta una sentencia sql para cambiar el estado de una orden de pedido en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPedido - El identificador del pedido
	 * @param calificacion - Calificacion del pedido
	 * @return El n�mero de tuplas modificadas
	 */
	public long cambiarEstadoOrdenPedido (PersistenceManager pm, long idPedido, String calificacion) 
	{
		Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
		Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlOrdenPedido() + " SET estado = ?, calificacionpedido = ?, fechaentrega = ?  WHERE idpedido = ?");
		q.setParameters("Recibido",calificacion, fechaActual, idPedido);
		return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para encontrar la informaci�n de UNA ORDEN DE PEDIDO en la base de datos de SuperAndes 
	 * @param pm - El manejador de persistencia
	 * @param idPedido - El identificador del pedido
	 * @return Un objeto ORDEN PEDIDO que tiene el identificador dado
	 */
	public OrdenPedido darPedidoPorId (PersistenceManager pm, long idPedido) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlOrdenPedido() + " WHERE idpedido = ?");
		q.setResultClass(OrdenPedido.class);
		q.setParameters(idPedido);
		return (OrdenPedido) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para eliminar UNA ORDEN DE PEDIDO en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPedido - El identificador de la orden
	 * @return El n�mero de tuplas eliminadas
	 */
	public long eliminarPedidoPorId(PersistenceManager pm, long idPedido)
	{
		 Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlOrdenPedido()+ " WHERE idpedido = ?");
	     q.setParameters(idPedido);
	     return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia para encontrar la informaci�n de LAS ORDENES DE PEDIDO de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ORDEN PEDIDO
	 */
	public List<OrdenPedido> darPedidos (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlOrdenPedido());
		q.setResultClass(OrdenPedido.class);
		return (List<OrdenPedido>) q.executeList();
	}
	
	
}