package uniandes.isis2304.superAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.PromoUnidades;
/**
 * Clase que encapsula los m�todos que hacen acceso a la base de datos para el concepto PROMO UNIDADES de SuperAndes
 * 
 * @author Mar�a Ocampo - mj.ocampov
 */
class SQLPromoUnidades
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
	public SQLPromoUnidades(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que adicionar una PROMO UNIDADES a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - Fecha de inicio de la promoci�n
	 * @param fechaFin - Fecha de terminaci�n de la promoci�n
	 * @param descripcion - Descripci�n de la promoci�n
	 * @param codBarras - C�digo de barras del producto en promoci�n
	 * @return El n�mero de tuplas insertadas
	 */
	public long adicionarPromocion (PersistenceManager pm, long idPromocion, int unidadesPagar, int unidadesLlevar ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlPromoUnidades() + "(idpromocion, unidadespagar, unidadesllevar ) values (?, ?, ?)");
        q.setParameters(idPromocion, unidadesPagar, unidadesLlevar);
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que busca todas las PROMO UNIDADES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lsita con todas las promociones de este tipo
	 */
	public List<PromoUnidades> darPromociones (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromoUnidades());
		q.setResultClass(PromoUnidades.class);
		return (List<PromoUnidades>) q.executeList();
	}
}