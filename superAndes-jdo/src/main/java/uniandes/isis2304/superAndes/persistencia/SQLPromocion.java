package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto PROMOCION de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLPromocion
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
	public SQLPromocion(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que adicionar una PROMOCION a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - Fecha de inicio de la promoción
	 * @param fechaFin - Fecha de terminación de la promoción
	 * @param descripcion - Descripción de la promoción
	 * @param codBarras - Código de barras del producto en promoción
	 * @return El número de tuplas insertadas
	 */
	public long adicionarPromocion (PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, String descripcion, String codBarras, long idPromocion, int uniVendidas, int uniDisponibles) 
	{
		System.out.println(idPromocion);
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlPromocion() + "(fechainicial, fechafinal, descripcion, codigobarras, idpromocion, unidadesvendidas, unidadesdisponibles, estado) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(fechaInicio, fechaFin, descripcion, codBarras, idPromocion, uniVendidas, uniDisponibles, "VIGENTE");
        return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que modifica una PROMOCION en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - Id de la promocion
	 * @return El número de tuplas insertadas
	 */
	public long finalizarPromocion (PersistenceManager pm, long idPromocion) 
	{
		Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
		 Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlPromocion()+ " SET estado = ?, fechafinal = ? WHERE idpromocion = ?");
	     q.setParameters("FINALIZADA",fechaActual, idPromocion);
	     return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que consultas las PROMOCIONES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Lista de tuplas que cumplen con las condiciones
	 */
	public List<Promocion> darPromocionesPorFinalizar (PersistenceManager pm) 
	{
		Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromocion() + " WHERE estado = ? AND (unidadesdisponibles = ? OR fechafinal <= ?)");
		q.setParameters("VIGENTE", 0, fechaActual);
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que modifica una PROMOCION en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param codigoBarras - Codigo de barras de la promoción a modificar
	 * @return El número de tuplas insertadas
	 */
	public long registrarCompraPromocion (PersistenceManager pm, String codigoBarras) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlPromocion()+ " SET unidadesdisponibles = unidadesdisponibles-1, unidadesvendidad = unidadesvendidad+1 WHERE codigobarras = ?");
	     q.setParameters(codigoBarras);
	     return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que consulta las PROMOCIONES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Lista de tuplas que cumplen con las condiciones
	 */
	public List<Promocion> darPromocionesPorProducto (PersistenceManager pm, String codigoBarras) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromocion() + " WHERE codigobarras = ?");
		q.setParameters(codigoBarras);
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que consulta las 20 mejores PROMOCIONES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Lista de tuplas que cumplen con las condiciones
	 */
	public List<Promocion> darPromocionesMasPopulares (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT B.idpromocion, B.codigobarras, B.fechainicial, B.fechafinal, B.unidadesdisponibles, B.unidadesvendidas, B.descripcion FROM (" 
		+ "SELECT descripcion, idpromocion, codigobarras, fechafinal - fechainicial AS duracion, unidadesvendidas FROM "+persistencia.getSqlPromocion() + " WHERE estado = ?"
		+ ") A, "+persistencia.getSqlPromocion() +" B"
		+" WHERE rownum < 21 AND A.idpromocion = B.idpromocion ORDER BY unidadesvendidas/duracion DESC");
		q.setParameters("FINALIZADA");
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
	
	/**
	 * Crea y ejecuta una sentencia para consultar todos las PROMOCIONES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Lista de todos las promociones
	 */
	public List<Promocion> darPromociones (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromocion());
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
	/**
	 * Crea y ejecuta la sentencia que elimina una PROMOCION de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - El identificador de la promoción
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarPromocionPorId(PersistenceManager pm, long idPromocion)
	{
		 Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlPromocion() + " WHERE idpromocion = ?");
	     q.setParameters(idPromocion);
	     return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta una sentencia sql para encontrar la información de UNA PROMOCIÓN en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idPromocion - El identificador de la promoción
	 * @return El objeto PROMOCION que tiene el identificador dado
	 */
	public Promocion darPromocionPorId(PersistenceManager pm, String idPromocion)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromocion() + " WHERE idpromocion = ?");
		q.setResultClass(Promocion.class);
		q.setParameters(idPromocion);
		return (Promocion) q.executeUnique();
	}
}