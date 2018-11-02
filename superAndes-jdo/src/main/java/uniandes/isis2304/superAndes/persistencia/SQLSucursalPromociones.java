package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.SucursalPromociones;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto SUCURSAL PROMOCIONES de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
public class SQLSucursalPromociones {

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
	public SQLSucursalPromociones(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------
	/**
	 * Crea y ejecuta una sentencia sql para adicionar una SUCURSAL PROMOCIONES a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - El identificador de la promoci�n
	 * @param direccion - La direcci�n de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @return El n�mero de tuplas insertadas
	 */
	public long adicionarSucursalPromociones (PersistenceManager pm, long idPromocion, String direccion, String ciudad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlSucursalPromociones() + "(idpromocion, direccionsucursal, ciudad) values (?, ?, ?)");
        q.setParameters(idPromocion, direccion, ciudad);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para eliminar una SUCURSAL PROMOCIONES de la base de dartos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - El identificador de la promoci�n
	 * @param direccion - La direcci�n de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @return El n�mero de tuplas eliminadas
	 */
	public long eliminarSucursalPromociones (PersistenceManager pm, long idPromocion, String direccion, String ciudad)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlSucursalPromociones() + " WHERE idpromocion = ? AND direccionsucursal = ? AND ciudad = ?");
        q.setParameters(idPromocion, direccion, ciudad);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia SQL para encontar la informaci�n de LAS SUCURSAL PROMOCIONES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos SUCURSAL PROMOCIONES
	 */
	public List<SucursalPromociones> darSucursalPromociones(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlSucursalPromociones());
		q.setResultClass(SucursalPromociones.class);
		return (List<SucursalPromociones>) q.executeList();
	}
	
}
