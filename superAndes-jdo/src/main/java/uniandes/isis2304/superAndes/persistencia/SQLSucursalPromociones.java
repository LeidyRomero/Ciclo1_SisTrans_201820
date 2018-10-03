package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto SUCURSAL PROMOCIONES de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
public class SQLSucursalPromociones {

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
	public SQLSucursalPromociones(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	public long adicionarSucursalPromociones (PersistenceManager pm, long idPromocion, String direccion, String ciudad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlSucursalFactura() + "(id_promocion, direccion_sucursal, ciudad) values (?, ?, ?)");
        q.setParameters(idPromocion, direccion, ciudad);
        return (long) q.executeUnique();
	}
}
