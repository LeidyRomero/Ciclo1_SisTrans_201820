package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromoDescuento;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROMO DESCUENTO de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLPromoDescuento
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
	public SQLPromoDescuento(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que adicionar una PROMO DESCUENTO a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - Fecha de inicio de la promoción
	 * @param fechaFin - Fecha de terminación de la promoción
	 * @param descripcion - Descripción de la promoción
	 * @param codBarras - Código de barras del producto en promoción
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPromocion (PersistenceManager pm, long idPromocion, double descuento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlPromoDescuento() + "(idpromocion, descuento) values (?, ?)");
        q.setParameters(idPromocion, descuento);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que busca las PROMO DESCUENTO en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista con todas la promociones de este tipo
	 */
	public List<PromoDescuento> darPromociones (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromoDescuento());
		q.setResultClass(PromoDescuento.class);
		return (List<PromoDescuento>) q.executeList();
	}
}