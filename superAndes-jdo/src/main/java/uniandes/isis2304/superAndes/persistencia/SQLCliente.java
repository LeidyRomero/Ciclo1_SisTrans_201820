package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CLIENTE de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLCliente
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
	public SQLCliente(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un CLIENTE a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param correo - El correo del cliente
	 * @param nombre - El nombre del cliente
	 * @return El número de tuplas insertadas
	 */
	public long adicionarCliente (PersistenceManager pm, String correo, String nombre) 
	{
		int puntos = 0;
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlCliente() + "(correo, nombre_consumidor, puntos) values (?, ?, ?)");
        q.setParameters(correo, nombre, puntos);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia que elimina un CLIENTE de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param correo - El correo del cliente a eliminar
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarClientePorCorreo (PersistenceManager pm, String correo)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlCliente() + " WHERE correo = ?");
        q.setParameters(correo);
        return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta una sentencia para consultar todos los CLIENTES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Lista de todos los clientes
	 */
	public List<Cliente> darClientes (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCliente());
		q.setResultClass(Proveedor.class);
		return (List<Cliente>) q.executeList();
	}
	
}