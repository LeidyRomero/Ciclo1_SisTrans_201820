package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Sucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SUCURSAL de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLSucursal
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
	public SQLSucursal(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------
	/**
	 * Crea y ejecuta una sentencia SQL para adicionar una SUCURSAL a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param tamanio - El tamaño de la sucursal
	 * @param direccion - La dirección de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @param nombre - El nombre de la sucursal
	 * @return El número de tuplas insertadas
	 */
	public long adicionarSucursal (PersistenceManager pm, String tamanio, String direccion, String ciudad, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlSucursal() + "(tamanio, direccionsucursal, ciudad, nombresucursal) values (?, ?, ?, ?)");
        q.setParameters(tamanio, direccion, ciudad, nombre);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentenca que consulta una SUCURSAL de la base de datos de SuperAndes
	 * @param pm - El manejador de la base de datos
	 * @param direccion - La direccion de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @return La sucursal consultada
	 */
	public Sucursal darSucursalPorDireccionYCiudad (PersistenceManager pm, String direccion, String ciudad) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlSucursal() + " WHERE ciudad = ? AND direccionsucursal = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(ciudad, direccion);
		return (Sucursal) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentenca que elimina una SUCURSAL de la base de datos de SuperAndes
	 * @param pm - El manejador de la base de datos
	 * @param direccion - La direccion de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @return Número de tuplas eliminadas
	 */
	public long eliminarSucursalPorDireccionYCiudad (PersistenceManager pm, String direccion, String ciudad) 
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlSucursal() + " WHERE ciudad = ? AND direccionsucursal = ?");
		q.setParameters(ciudad, direccion);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia que consulta todad las SUCURSALES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Lista de tuplas de sucursal
	 */
	public List<Sucursal> darSucursales (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlSucursal() );
		q.setResultClass(Sucursal.class);
		return (List<Sucursal>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia para encontrar la información de LAS SUCURSALES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param ciudad - Ciudad de la sucursal
	 * @return Una lista de objetos SUCURSAL que tienen la ciudad dada
	 */
	public List<Sucursal> darSucursalesPorCiudad (PersistenceManager pm, String ciudad)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlSucursal() + " WHERE ciudad = ?" );
		q.setResultClass(Sucursal.class);
		q.setParameters(ciudad);
		return (List<Sucursal>) q.executeList();
	}
}