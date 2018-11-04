package uniandes.isis2304.superAndes.persistencia;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Factura;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto FACTURA de SuperAndes
 * 
 * @author María Ocampo - mj.ocampov
 */
class SQLFactura
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
	// CONSTRUCTOR
	//------------------------------------------------------------------
	/**
	 * Constructor
	 * @param persistencia - El Manejador de persistencia de la aplicación
	 */
	public SQLFactura(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}

	//------------------------------------------------------------------
	// CRD
	//------------------------------------------------------------------	
	/**
	 * Crea y ejecuta una sentencia sql que adiciona una FACTURA a la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador de la factura
	 * @param costoTotal - El costo total de la factura
	 * @param fecha - Fecha de generación de la factura
	 * @return El número de tuplas insertadas
	 */
	public long adicionarFactura(PersistenceManager pm, long idFactura, double costoTotal, Timestamp fecha, String correoCliente, String ciudad, String direccionSucursal) 
	{
		if(correoCliente.equals(""))
			correoCliente = "default@default.com";
		Query q = pm.newQuery(SQL, "INSERT INTO " + persistencia.getSqlFactura() + "(idfactura, costototal, fecha, correocliente, ciudad, direccionsucursal) values (?, ?, ?, ?, ?, ?)");
		q.setParameters(idFactura, costoTotal, fecha, correoCliente, ciudad, direccionSucursal);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta una sentencia sql para eliminar FACTURAS de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador de la factura
	 * @return El número de tuplas eliminadas
	 */
	public long eliminarFacturaPorId(PersistenceManager pm, long idFactura)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + persistencia.getSqlFactura() + " WHERE idfactura = ?");
		q.setParameters(idFactura);
		return (long) q.executeUnique(); 
	}

	/**
	 * Crea y ejecuta la sentencia sql para encontrar la información de UNA FACTURA en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param idFactura - El identificador de la factura
	 * @return El objeto FACTURA con el identificador dado
	 */
	public Factura darFacturaPorId(PersistenceManager pm, long idFactura)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlFactura() + " WHERE idfactura = ?");
		q.setResultClass(Factura.class);
		q.setParameters(idFactura);
		return (Factura) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia para encontrar la información de LAS FACTURAS en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos FACTURA
	 */
	public List<Factura> darFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlFactura());
		q.setResultClass(Factura.class);
		return (List<Factura>) q.executeList();
	}

	/**
	 * Crea y ejecuta una sentencia sql para encontrar la información de LAS FACTURAS en la base de datos de SuperAndes 
	 * @param pm - El manejador de persistencia
	 * @param correoCliente - El correo del cliente
	 * @return Una lista de objetos FACTURA que tienen el correo del cliente dado
	 */
	public List<Factura> darFacturasPorCliente(PersistenceManager pm, String correoCliente)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlFactura() + " WHERE correocliente = ?");
		q.setResultClass(Factura.class);
		q.setParameters(correoCliente);
		return (List<Factura>) q.executeList();
	}

	/**
	 * Crea y ejecuta una sentencia para encontrar la información de LAS FACTURAS de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param ciudad - Ciudad de la sucursal
	 * @param direccionSucursal - Dirección de la sucursal
	 * @return Una lista de objetos FACTURA de la sucursal dada
	 */
	public List<Factura> darFacturasSucursal(PersistenceManager pm, String ciudad, String direccionSucursal)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlFactura() + " WHERE ciudad = ? AND direccionsucursal = ?");
		q.setResultClass(Factura.class);
		q.setParameters(ciudad, direccionSucursal);
		return (List<Factura>) q.executeList();
	}
	//------------------------------------------------------------------
	// MÉTODOS
	//------------------------------------------------------------------

	/**
	 * Crea y ejecuta una sentencia sql para aumentar el costo de una factura en la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param costoAdicional - El costo que se desea aumentar
	 * @param idFactura - El identificador de la factura
	 * @return El número de tuplas modificadas
	 */
	public long aumentarCostoFactura(PersistenceManager pm, double costoAdicional, long idFactura)
	{
		Query q = pm.newQuery(SQL, "UPDATE " + persistencia.getSqlFactura() + " SET costototal = costototal + ? WHERE idfactura = ?");
		q.setParameters(costoAdicional, idFactura);
		return (long) q.executeUnique(); 
	}

	//------------------------------------------------------------------
	// CONSULTAS
	//------------------------------------------------------------------
	/**
	 * Crea y ejecuta una sentencia para consultar el dinero ganado por una sucursal en un rango de tiempo de la base de datos de SuperAndes
	 * @param pm - El manejador de persistencia
	 * @param fechaInicio - Fecha de inicio de la consulta
	 * @param fechaFin - Fecha de fin de consulta
	 * @return Una lista de objetos, de tamñano 3.
	 */
	public List<Object> dineroSucursalEnRango(PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin)
	{
		Query q = pm.newQuery(SQL, "SELECT SUM(fac.costototal) AS dinerorecolectado, direccionsucursal, ciudad "
				+ "FROM " + persistencia.getSqlFactura() + " fac "
				+ "WHERE fac.fecha BETWEEN ? AND ? "
				+ "GROUP BY direccionsucursal, ciudad");
		q.setResultClass(Object.class);
		q.setParameters(fechaInicio, fechaFin);
		return (List<Object>) q.executeList();
	}

	/**
	 * 
	 * @param pm
	 * @param pDireccionSucursal
	 * @param pCiudad
	 * @return
	 */
	public List<Object> darClientesFrecuentes(PersistenceManager pm, String pDireccionSucursal, String pCiudad)
	{
		Query q = pm.newQuery(SQL, "SELECT correocliente "
				+ "FROM (SELECT COUNT(*), correocliente "
				+ "FROM(SELECT COUNT(EXTRACT(MONTH FROM fecha)) AS numerofacturas,EXTRACT(MONTH FROM fecha),correocliente "
				+ "FROM a_factura "
				+ "WHERE direccionsucursal = ? AND ciudad = ? "
				+ "GROUP BY EXTRACT(MONTH FROM fecha), correocliente "
				+ "HAVING COUNT(EXTRACT(MONTH FROM fecha))>=2) "
				+ "GROUP BY correocliente "
				+ "HAVING COUNT(*) = (SELECT EXTRACT(MONTH FROM(SELECT SYSDATE FROM DUAL)) - EXTRACT(MONTH FROM(SELECT MIN(FECHA) FROM A_FACTURA)) "
				+ "+ 12*(EXTRACT(YEAR FROM (SELECT SYSDATE FROM DUAL)) - EXTRACT(MONTH FROM(SELECT MIN(FECHA) FROM A_FACTURA))) as dateDiff from dual))");
		q.setParameters(pDireccionSucursal,pCiudad);
		return (List<Object>) q.executeList();
	}

	/**
	 * 
	 * @param pm
	 * @param tipo
	 * @return
	 */
	public List<List<Object>> operacionesAnuales(PersistenceManager pm, String tipo)
	{
		//TODO Costo máximo
		String sqlMaxCosto = "SELECT b.max, b.ciudad, b.direccionsucursal, d.año FROM (SELECT MAX(a.costo) as max, a.ciudad, a.direccionsucursal FROM( SELECT f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) AS año, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE  c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) ) a GROUP BY a.ciudad, a.direccionsucursal) b,( SELECT f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) AS año, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.costo";
		Query q1 = pm.newQuery(SQL, sqlMaxCosto);
		q1.setParameters(tipo, tipo);
	
		//TODO Unidades máximas
		String sqlMaxUnidades = "SELECT b.max, b.ciudad, b.direccionsucursal, d.año FROM ( SELECT MAX(a.numvendidos) as max, a.ciudad, a.direccionsucursal FROM (SELECT f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) AS año, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE  c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) ) a GROUP BY a.ciudad, a.direccionsucursal) b,(SELECT f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) AS año, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.numvendidos";
		Query q2 = pm.newQuery(SQL, sqlMaxUnidades);
		q2.setParameters(tipo, tipo);
		
		//TODO Costo mínimo
		String sqlMinCosto = "SELECT b.min, b.ciudad, b.direccionsucursal, d.año FROM (SELECT MIN(a.costo) as min, a.ciudad, a.direccionsucursal FROM( SELECT f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) AS año, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE  c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) ) a GROUP BY a.ciudad, a.direccionsucursal) b,( SELECT f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) AS año, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.costo";
		Query q3 = pm.newQuery(SQL, sqlMinCosto);
		q3.setParameters(tipo, tipo);
		
		//TODO Unidades mínimo
		String sqlMinUnidades = "SELECT b.min, b.ciudad, b.direccionsucursal, d.año FROM (SELECT MIN(a.numvendidos) as min, a.ciudad, a.direccionsucursal FROM (SELECT f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) AS año, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE  c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) ) a GROUP BY a.ciudad, a.direccionsucursal) b,(SELECT f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) AS año, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, EXTRACT(YEAR FROM f.fecha) )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.numvendidos";
		Query q4 = pm.newQuery(SQL, sqlMinUnidades);
		q4.setParameters(tipo, tipo);

		List<Object> maxCosto = (List<Object>) q1.executeList();
		List<Object> maxCantidad = (List<Object>) q2.executeList();
		List<Object> minCosto = (List<Object>) q3.executeList();
		List<Object> minCantidad = (List<Object>) q4.executeList();
		
		List<List<Object>> retorno = new LinkedList<>();
		retorno.add(maxCosto);
		retorno.add(maxCantidad);
		retorno.add(minCosto);
		retorno.add(minCantidad);
		
		return retorno;
	}

	/**
	 * 
	 * @param pm
	 * @param tipo
	 * @return
	 */
	public List<List<Object>> operacionesMensuales(PersistenceManager pm, String tipo)
	{
		//TODO Costo máximo
		String sqlMaxCosto = "SELECT b.max, b.ciudad, b.direccionsucursal, d.mes FROM (SELECT MAX(a.costo) as max, a.ciudad, a.direccionsucursal FROM ( SELECT f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) AS mes, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) ) a GROUP BY a.ciudad, a.direccionsucursal) b,(SELECT f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) AS mes, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.costo";
		Query q1 = pm.newQuery(SQL, sqlMaxCosto);
		q1.setParameters(tipo, tipo);
	
		//TODO Unidades máximas
		String sqlMaxUnidades = "SELECT b.max, b.ciudad, b.direccionsucursal, d.mes FROM (SELECT MAX(a.numvendidos) as max, a.ciudad, a.direccionsucursal FROM (SELECT f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) AS mes, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) ) a GROUP BY a.ciudad, a.direccionsucursal) b,( SELECT f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) AS mes, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.numvendidos";
		Query q2 = pm.newQuery(SQL, sqlMaxUnidades);
		q2.setParameters(tipo, tipo);
		
		//TODO Costo mínimo
		String sqlMinCosto = "SELECT b.min, b.ciudad, b.direccionsucursal, d.mes FROM (SELECT MIN(a.costo) as min, a.ciudad, a.direccionsucursal FROM ( SELECT f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) AS mes, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) ) a GROUP BY a.ciudad, a.direccionsucursal) b,(SELECT f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) AS mes, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.costo";
		Query q3 = pm.newQuery(SQL, sqlMinCosto);
		q3.setParameters(tipo, tipo);
		
		//TODO Unidades mínimo
		String sqlMinUnidades = "SELECT b.min, b.ciudad, b.direccionsucursal, d.mes FROM (SELECT MIN(a.numvendidos) as min, a.ciudad, a.direccionsucursal FROM (SELECT f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) AS mes, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) ) a GROUP BY a.ciudad, a.direccionsucursal) b,( SELECT f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) AS mes, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, SUBSTR(to_char(fecha),4,5) )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.numvendidos";
		Query q4 = pm.newQuery(SQL, sqlMinUnidades);
		q4.setParameters(tipo, tipo);

		List<Object> maxCosto = q1.executeList();
		List<Object> maxCantidad = q2.executeList();
		List<Object> minCosto = q3.executeList();
		List<Object> minCantidad = q4.executeList();
		
		List<List<Object>> retorno = new LinkedList<>();
		retorno.add(maxCosto);
		retorno.add(maxCantidad);
		retorno.add(minCosto);
		retorno.add(minCantidad);
		
		return retorno;
	}
	
	/**
	 * 
	 * @param pm
	 * @param tipo
	 * @return
	 */
	public List<List<Object>> operacionesSemanales(PersistenceManager pm, String tipo)
	{
		//TODO Costo máximo
		String sqlMaxCosto = "";
		Query q1 = pm.newQuery(SQL, sqlMaxCosto);
		q1.setParameters(tipo, tipo);
	
		//TODO Unidades máximas
		String sqlMaxUnidades = "";
		Query q2 = pm.newQuery(SQL, sqlMaxUnidades);
		q2.setParameters(tipo, tipo);
		
		//TODO Costo mínimo
		String sqlMinCosto = "";
		Query q3 = pm.newQuery(SQL, sqlMinCosto);
		q3.setParameters(tipo, tipo);
		
		//TODO Unidades mínimo
		String sqlMinUnidades = "";
		Query q4 = pm.newQuery(SQL, sqlMinUnidades);
		q4.setParameters(tipo, tipo);
		
		System.out.println(sqlMaxCosto);
		System.out.println(sqlMaxUnidades);
		System.out.println(sqlMinCosto);
		System.out.println(sqlMinUnidades);
		List<Object> maxCosto = q1.executeList();
		List<Object> maxCantidad = q2.executeList();
		List<Object> minCosto = q3.executeList();
		List<Object> minCantidad = q4.executeList();
		
		List<List<Object>> retorno = new LinkedList<>();
		retorno.add(maxCosto);
		retorno.add(maxCantidad);
		retorno.add(minCosto);
		retorno.add(minCantidad);
		
		return retorno;
	}
	
	/**
	 * 
	 * @param pm
	 * @param tipo
	 * @return
	 */
	public List<List<Object>> operacionesDiarias(PersistenceManager pm, String tipo)
	{
		//TODO Costo máximo
		String sqlMaxCosto = "SELECT b.max, b.ciudad, b.direccionsucursal, d.fecha FROM (SELECT MAX(a.costo) as max, a.ciudad, a.direccionsucursal FROM (SELECT f.ciudad, f.direccionsucursal, f.fecha, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, f.fecha ) a GROUP BY a.ciudad, a.direccionsucursal) b,( SELECT f.ciudad, f.direccionsucursal, f.fecha, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, f.fecha )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.costo";
		Query q1 = pm.newQuery(SQL, sqlMaxCosto);
		q1.setParameters(tipo, tipo);
	
		//TODO Unidades máximas
		String sqlMaxUnidades = "SELECT b.max, b.ciudad, b.direccionsucursal, d.fecha FROM (SELECT MAX(a.numvendidos) as max, a.ciudad, a.direccionsucursal FROM (SELECT f.ciudad, f.direccionsucursal, f.fecha, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, f.fecha ) a GROUP BY a.ciudad, a.direccionsucursal) b,( SELECT f.ciudad, f.direccionsucursal, f.fecha, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, f.fecha )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.max = d.numvendidos";
		Query q2 = pm.newQuery(SQL, sqlMaxUnidades);
		q2.setParameters(tipo, tipo);
		
		//TODO Costo mínimo
		String sqlMinCosto = "SELECT b.min, b.ciudad, b.direccionsucursal, d.fecha FROM (SELECT MIN(a.costo) as min, a.ciudad, a.direccionsucursal FROM (SELECT f.ciudad, f.direccionsucursal, f.fecha, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, f.fecha ) a GROUP BY a.ciudad, a.direccionsucursal) b,( SELECT f.ciudad, f.direccionsucursal, f.fecha, SUM(f.costototal) AS costo FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, f.fecha )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.costo";
		Query q3 = pm.newQuery(SQL, sqlMinCosto);
		q3.setParameters(tipo, tipo);
		
		//TODO Unidades mínimo
		String sqlMinUnidades = "SELECT b.min, b.ciudad, b.direccionsucursal, d.fecha FROM (SELECT MIN(a.numvendidos) as min, a.ciudad, a.direccionsucursal FROM (SELECT f.ciudad, f.direccionsucursal, f.fecha, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, f.fecha ) a GROUP BY a.ciudad, a.direccionsucursal) b,( SELECT f.ciudad, f.direccionsucursal, f.fecha, SUM(c.cantidad) AS numvendidos FROM a_comprados c, a_factura f, a_producto p WHERE c.idfactura = f.idfactura AND p.codbarras = c.codigobarras AND p.nombrecategoria = ? GROUP BY f.ciudad, f.direccionsucursal, f.fecha )d WHERE b.ciudad = d.ciudad AND b.direccionsucursal = d.direccionsucursal AND b.min = d.numvendidos";
		Query q4 = pm.newQuery(SQL, sqlMinUnidades);
		q4.setParameters(tipo, tipo);
		
		System.out.println(sqlMaxCosto);
		System.out.println(sqlMaxUnidades);
		System.out.println(sqlMinCosto);
		System.out.println(sqlMinUnidades);
		List<Object> maxCosto = q1.executeList();
		List<Object> maxCantidad = q2.executeList();
		List<Object> minCosto = q3.executeList();
		List<Object> minCantidad = q4.executeList();
		
		List<List<Object>> retorno = new LinkedList<>();
		retorno.add(maxCosto);
		retorno.add(maxCantidad);
		retorno.add(minCosto);
		retorno.add(minCantidad);
		
		return retorno;
	}
}