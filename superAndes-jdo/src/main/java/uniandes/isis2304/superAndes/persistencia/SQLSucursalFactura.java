package uniandes.isis2304.superAndes.persistencia;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto SUCURSAL FACTURA de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLSucursalFactura
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
	public SQLSucursalFactura(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	public long adicionarSucursalFactura (PersistenceManager pm, long idFactura, String direccion, String ciudad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlSucursalFactura() + "(id_factura, direccion_sucursal, ciudad) values (?, ?, ?)");
        q.setParameters(idFactura, direccion, ciudad);
        return (long) q.executeUnique();
	}
}