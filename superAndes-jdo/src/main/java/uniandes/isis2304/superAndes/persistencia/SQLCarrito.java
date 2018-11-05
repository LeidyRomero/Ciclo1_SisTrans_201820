package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Carrito;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CARRITO de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
public class SQLCarrito {

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

	/**
	 * 
	 */
	private PersistenciaSuperAndes pp;

	//------------------------------------------------------------------
	// CONSTRUCTOR
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param pPersistencia - El manejador de persistencia de la aplicación
	 */
	public SQLCarrito(PersistenciaSuperAndes pPersistencia)
	{
		pp = pPersistencia;
	}

	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------
	/**
	 * Crea y ejecuta una sentencia sql que adiciona un CARRITO a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idCarrito - El identificador del carrito
	 * @param direccionSucursal - La dirección de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @param correoCliente - El correo del cliente
	 * @return El número de tuplas insertadas
	 */
	public long adicionarCarrito(PersistenceManager pm, long idCarrito, String direccionSucursal, String ciudad, String correoCliente)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.getSqlCarrito() + "(idcarrito, direccionsucursal, ciudad, correocliente) VALUES (?, ?, ?, ?)");
		q.setParameters(idCarrito,direccionSucursal,ciudad,correoCliente);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta una sentencia para eliminar un CARRITO de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idCarrito - El identificador del carrito
	 * @return El número de tuplas eliminadas
	 */
	public long[] eliminarCarritoPorId(PersistenceManager pm, long idCarrito)
	{
		Query q1 = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlProductosCarrito() + " WHERE idcarrito = ?");
		q1.setParameters(idCarrito);
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.getSqlCarrito() + " WHERE idcarrito = ?");
		q.setParameters(idCarrito);
		
		long productosCarrito = (long) q1.executeUnique();
		long carrito = (long) q.executeUnique();
		return new long[] {productosCarrito, carrito};
	}

	/**
	 * Crea y ejecuta una sentencia sql para encontrar la información de LOS CARRITOS de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos CARRITO
	 */
	public List<Carrito> darCarritos(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.getSqlCarrito());
		q.setResultClass(Carrito.class);
		return (List<Carrito>) q.executeList();
	}

	/**
	 * Crea y ejecuta una sentencia sql para encontrar la información de UN CARRITO en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idCarrito - El identificador del carrito
	 * @return El objeto CARRITO con el identificador dado
	 */
	public Carrito darCarritoPorId(PersistenceManager pm, long idCarrito)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.getSqlCarrito() + " WHERE idcarrito = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(idCarrito);
		return (Carrito) q.executeUnique();
	}

	/**
	 * Crea y ejecuta una sentencia sql para encontrar la información de UN CARRITO en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param correoCliente - El correo del cliente
	 * @return El objeto CARRITO con el correo dado
	 */
	public Carrito darCarritoPorCorreoCliente(PersistenceManager pm, String correoCliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.getSqlCarrito() + " WHERE correoCliente = ?");
		q.setResultClass(Carrito.class);
		q.setParameters(correoCliente);
		return (Carrito) q.executeUnique();
	}
}
