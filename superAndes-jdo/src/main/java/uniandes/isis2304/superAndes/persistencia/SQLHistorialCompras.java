package uniandes.isis2304.superAndes.persistencia;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto HISTORIAL DE COMPRAS de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLHistorialCompras
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
	public SQLHistorialCompras(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
	
	/**
	 * Crea y ejecuta la sentencia sql que adiciona un HISTORIAL DE COMPRA en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param correo - El correo del cliente
	 * @param idFactura - El id de la factura asociada
	 * @return El numero de tuplas insertadas
	 */
	public long adicionarHistorialCompras(PersistenceManager pm, String correo, long idFactura) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlHistorialCompras() + "(correocliente, idfactura) values (?, ?)");
        q.setParameters(correo, idFactura);
        return (long) q.executeUnique();
	}
}