package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromoDescuento;

/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto PROMO DESCUENTO de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLPromoDescuento
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
	public SQLPromoDescuento(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que adicionar una PROMO DESCUENTO a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - Fecha de inicio de la promoci�n
	 * @param fechaFin - Fecha de terminaci�n de la promoci�n
	 * @param descripcion - Descripci�n de la promoci�n
	 * @param codBarras - C�digo de barras del producto en promoci�n
	 * @return El n�mero de tuplas insertadas
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