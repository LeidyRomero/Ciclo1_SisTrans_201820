package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto CANTIDAD EN ESTANTES de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLCantidadEnEstantes
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
	public SQLCantidadEnEstantes(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	/**
	 * Crea y ejecuta una sentencia sql que adiciona uns CANTIDAD EN ESTANTES a la base de datos de SuperAndes
	 * @param pm
	 * @param codigoBarras
	 * @param idEstante
	 * @param cantidadActual
	 * @param cantidadMinima
	 * @return
	 */
	public long adicionarCantidadEstantes (PersistenceManager pm, String codigoBarras, long idEstante, int cantidadActual, int cantidadMinima) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlCantidadEnEstantes() + "(codbarras, idestante, cantidadactual, cantidadminima) values (?, ?, ?, ?)");
        q.setParameters(codigoBarras, idEstante, cantidadActual, cantidadMinima);
        return (long) q.executeUnique();
	}
	
	/**
	 * 
	 * @param manager
	 * @param idEstante
	 * @return
	 */
	public int buscarCantidadActual(PersistenceManager manager, long idEstante)
	{
		Query q = manager.newQuery(SQL, "SELECT cantidadactual FROM " + persistencia.getSqlCantidadEnEstantes()+" WHERE idestante = ?");
		q.setParameters(idEstante);
		q.setResultClass(Integer.class);
		return (Integer) q.executeUnique();
	}
	
	/**
	 * 
	 * @param manager
	 * @param ciudad
	 * @param direccion
	 * @param codigoBarras
	 * @return
	 */
	public int buscarCantidadActual(PersistenceManager manager, String ciudad, String direccion, String codigoBarras)
	{
		Query q = manager.newQuery(SQL, "SELECT SUM(cant.cantidadactual) "
				+ "FROM " + persistencia.getSqlCantidadEnEstantes()+" cant, "
				+ persistencia.getSqlEstante() + " est"
				+" WHERE est.idestante = cant.idestante AND"
				+ "est.ciudad = ? AND"
				+ "est.direccionsucursal = ? AND"
				+ "cant.codbarras = ?");
		q.setParameters(ciudad, direccion, codigoBarras);
		q.setResultClass(Integer.class);
		return (Integer) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que busca todos los códigos de barras del estante dado
	 * @param pm
	 * @return
	 */
	public List<String> buscarCodigosDeBarrasProductos(PersistenceManager pm, long idEstante)
	{
		Query q = pm.newQuery(SQL, "SELECT codbarras FROM " + persistencia.getSqlCantidadEnEstantes() + " WHERE idestante = ?");
		q.setParameters(idEstante);
		q.setResultClass(String.class);
		return (List<String>) q.executeList();
	}
	
	public void aumentarCantidadEnEstantes(PersistenceManager pm, int pCantidad, String pCodBarras, long idEstante)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlCantidadEnEstantes() +" SET cantidadactual = cantidadactual + ? WHERE codbarras = ? AND idestante = ?");
		q.setParameters(pCantidad, pCodBarras, idEstante);
	}
	
	public void disminuirCantidadEnEstantes(PersistenceManager pm, int pCantidad, String pCodBarras, long idEstante)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlCantidadEnEstantes() +" SET cantidadactual = cantidadactual - ? WHERE codbarras = ? AND idestante = ?");
		q.setParameters(pCantidad, pCodBarras, idEstante);
	}
}