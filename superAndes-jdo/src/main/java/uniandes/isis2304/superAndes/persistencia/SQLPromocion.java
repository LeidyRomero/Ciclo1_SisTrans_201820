package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.Proveedor;

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
		System.out.println("Pre");
        Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlPromocion() + "(fecha_incial, fecha_final, descripcion, codigo_barras, id_promocion, unidades_vendidas, unidades_disponibles, estado) values (?, ?, ?, ?, ?, ?, ?, ?)");
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
		 Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlPromocion()+ " SET estado = ?, fecha_final = ? WHERE id_promocion = ?");
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
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromocion() + " WHERE estado = ? AND (unidades_disponibles = ? OR fecha_final <= ?)");
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
		 Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlPromocion()+ " SET unidades_disponibles = unidades_disponibles-1, unidades_vendidad = unidades_vendidad+1 WHERE codigo_barras = ?");
	     q.setParameters(codigoBarras);
	     return (long) q.executeUnique();            
	}
	
	/**
	 * Crea y ejecuta una sentencia sql que consultas las PROMOCIONES de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Lista de tuplas que cumplen con las condiciones
	 */
	public List<Promocion> darPromocionesPorProducto (PersistenceManager pm, String codigoBarras) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlPromocion() + " WHERE codigo_barras = ?");
		q.setParameters(codigoBarras);
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
	
	/**
	 * 
	 * @param pm
	 * @return
	 */
	public List<Promocion> darPromocionesMasPopulares (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT B.id_promocion, B.codigo_barras, B.fecha_incial, B.fecha_final, B.unidades_disponibles, B.unidades_vendidas, B.descripcion FROM (" 
		+ "SELECT descripcion, id_promocion, codigo_barras, fecha_final - fecha_incial AS duracion, unidades_vendidas FROM "+persistencia.getSqlPromocion() + " WHERE estado = ?"
		+ ") A, "+persistencia.getSqlPromocion() +" B"
		+" WHERE rownum < 21 AND A.id_promocion = B.id_promocion ORDER BY unidades_vendidas/duracion DESC");
		q.setParameters("FINALIZADA");
		q.setResultClass(Promocion.class);
		return (List<Promocion>) q.executeList();
	}
	
}