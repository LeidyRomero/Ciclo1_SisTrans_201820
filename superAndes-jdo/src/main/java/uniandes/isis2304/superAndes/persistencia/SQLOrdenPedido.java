package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	// M�TODOS
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El Manejador de persistencia de la aplicaci�n
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
	 * @param direccionSucursal - Direcci�n de la sucursal que genera el pedido
	 * @param direccionBodega - Direcci�n de la bodega que recibe el pedido
	 * @param idPedido - id del pedido
	 * @return N�mero de tuplas insertadas
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
		 Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlOrdenPedido() + " SET estado = ?, calificacion_pedido = ?  WHERE id = ?");
	     q.setParameters("Recibido",calificacion, idPedido);
	     return (long) q.executeUnique();            
	}
}