package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.superAndes.negocio.PromoUnidadDescuento;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROMO UNIDAD DESCUENTO de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLPromoUnidadDescuento
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
	public SQLPromoUnidadDescuento(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que adicionar una PROMO UNIDAD DESCUENTO a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - Fecha de inicio de la promoción
	 * @param fechaFin - Fecha de terminación de la promoción
	 * @param descripcion - Descripción de la promoción
	 * @param codBarras - Código de barras del producto en promoción
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPromocion (PersistenceManager pm, long idPromocion, int unidades, double descuento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlPromoUnidadDescuento() + "(idpromocion, unidades, descuento) values (?, ?, ?");
        q.setParameters(idPromocion, unidades, descuento);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta una sentencia sql que busca todas las PROMO UNIDAD DESCUENTO de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista con la promociones de este tipo.
	 */
	public List<PromoUnidadDescuento> darPromociones (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromoUnidadDescuento());
		q.setResultClass(PromoUnidadDescuento.class);
		return (List<PromoUnidadDescuento>) q.executeList();
	}

}