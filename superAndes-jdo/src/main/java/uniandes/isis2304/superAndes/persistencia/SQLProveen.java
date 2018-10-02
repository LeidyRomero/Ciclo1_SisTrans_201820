package uniandes.isis2304.superAndes.persistencia;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROVEEN de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLProveen
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
	public SQLProveen(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	public long adicionarProveen (PersistenceManager pm, int nitProveedor, int codigoBarras) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlProveen() + "(nit_proveedor, cod_barras) values (?, ?)");
        q.setParameters(nitProveedor, codigoBarras);
        return (long) q.executeUnique();
	}
	
}