package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;
import uniandes.isis2304.superAndes.negocio.Sucursal;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto SUCURSAL de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLSucursal
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
	public SQLSucursal(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia SQL para adicionar una SUCURSAL a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param tamanio - El tama�o de la sucursal
	 * @param direccion - La direcci�n de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @param nombre - El nombre de la sucursal
	 * @return El n�mero de tuplas insertadas
	 */
	public long adicionarSucursal (PersistenceManager pm, String tamanio, String direccion, String ciudad, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlSucursal() + "(tamanio, direccion_sucursal, ciudad, nombre_sucursal) values (?, ?, ?, ?)");
        q.setParameters(tamanio, direccion, ciudad, nombre);
        return (long) q.executeUnique();
	}
	
	/**
	 * 
	 * @param pm
	 * @param direccion
	 * @param ciudad
	 * @return
	 */
	public Sucursal darSucursalPorDireccionYCiudad (PersistenceManager pm, String direccion, String ciudad) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlSucursal() + " WHERE ciudad = ? AND direccion_sucursal = ?");
		q.setResultClass(Sucursal.class);
		q.setParameters(ciudad, direccion);
		return (Sucursal) q.executeUnique();
	}
}