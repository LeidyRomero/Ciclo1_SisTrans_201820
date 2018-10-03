package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PRODCTOS OFRECIDOS de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
public class SQLProductosOfrecidos {
	
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
	public SQLProductosOfrecidos(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta la sentencia sql que adiciona un PRODUCTO OFRECIDO a la base de datos de SuperAndes
	 * @param pm
	 * @param codigoBarras
	 * @param direccionSucursal
	 * @param ciudad
	 * @return
	 */
	public long adicionarProductoOfrecido (PersistenceManager pm, String codigoBarras, String direccionSucursal, String ciudad) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlProductosOfrecidos() + "(codigo_barras, direccion_sucursal, ciudad) values (?, ?, ?)");
        q.setParameters(codigoBarras, direccionSucursal, ciudad);
        return (long) q.executeUnique();
	}
}
