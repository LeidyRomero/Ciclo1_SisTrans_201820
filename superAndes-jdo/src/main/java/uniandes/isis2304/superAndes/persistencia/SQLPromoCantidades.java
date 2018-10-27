package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromoCantidades;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROMO CANTIDADES de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLPromoCantidades
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
	public SQLPromoCantidades(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que adicionar una PROMO CANTIDADES a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - Fecha de inicio de la promoción
	 * @param fechaFin - Fecha de terminación de la promoción
	 * @param descripcion - Descripción de la promoción
	 * @param codBarras - Código de barras del producto en promoción
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPromocion (PersistenceManager pm, long idPromocion, int cantidadLlevar, int cantidadPagar) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlPromoCantidades() + "(cantidadpagar, cantidadllevar, idpromocion) values (?, ?, ?)");
        q.setParameters(cantidadPagar, cantidadLlevar, idPromocion);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta una sentencia sql que busca las PROMO CANTIDADES en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista con todas la promociones de este tipo
	 */
	public List<PromoCantidades> darPromociones (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromoCantidades());
		q.setResultClass(PromoCantidades.class);
		return (List<PromoCantidades>) q.executeList();
	}
}