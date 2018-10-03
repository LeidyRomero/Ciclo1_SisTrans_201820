package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.OrdenPedido;
import uniandes.isis2304.superAndes.negocio.Promocion;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ORDEN PEDIDO de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLOrdenPedido
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
	public SQLOrdenPedido(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}

	/**
	 * Crea y ejecuta una sentencia sql que adiciona una ORDEN DE PEDIDO a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaEsperada - Fecha esperada de entrega
	 * @param nitProveedor - Nit del proveedor
	 * @param ciudad - Ciudad de la sucursal que genera el pedido
	 * @param direccionSucursal - Dirección de la sucursal que genera el pedido
	 * @param direccionBodega - Dirección de la bodega que recibe el pedido
	 * @param idPedido - id del pedido
	 * @return Número de tuplas insertadas
	 */
	public long adicionaroOrdenPedido (PersistenceManager pm, Timestamp fechaEsperada, int nitProveedor, String ciudad, String direccionSucursal, String direccionBodega, long idPedido) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlOrdenPedido() + "(fecha_esperada_entrega, estado, fecha_entrega, calificacion_pedido, nit_proveedor, ciudad, direccion_sucursal, direccion_bodega, id_pedido) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(fechaEsperada, "En espera", null, null, nitProveedor, ciudad, direccionSucursal, direccionBodega, idPedido);
		return (long) q.executeUnique();
	}

	/**
	 * 
	 * @param pm
	 * @param idPedido
	 * @param calificacion
	 * @return
	 */
	public long cambiarEstadoOrdenPedido (PersistenceManager pm, long idPedido, String calificacion) 
	{
		Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
		Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlOrdenPedido() + " SET estado = ?, calificacion_pedido = ?, fecha_entrega = ?  WHERE id_pedido = ?");
		q.setParameters("Recibido",calificacion, fechaActual, idPedido);
		return (long) q.executeUnique();            
	}
	
	/**
	 * 
	 * @param pm
	 * @param idPedido
	 * @return
	 */
	public OrdenPedido darPedidoPorId (PersistenceManager pm, long idPedido) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlOrdenPedido() + " WHERE id_pedido = ?");
		q.setParameters(idPedido);
		q.setResultClass(OrdenPedido.class);
		return (OrdenPedido) q.executeUnique();
	}
	
	/**
	 * 
	 * @param pm
	 * @param idPedido
	 * @return
	 */
	public long eliminarPedidoPorId(PersistenceManager pm, long idPedido)
	{
		 Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlOrdenPedido()+ " WHERE id_pedido = ?");
	     q.setParameters(idPedido);
	     return (long) q.executeUnique();
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public List<OrdenPedido> darPedidos (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlOrdenPedido());
		q.setResultClass(OrdenPedido.class);
		return (List<OrdenPedido>) q.executeList();
	}
}