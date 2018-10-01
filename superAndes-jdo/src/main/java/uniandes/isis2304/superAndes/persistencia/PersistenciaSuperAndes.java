package uniandes.isis2304.superAndes.persistencia;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.superAndes.negocio.Cliente;
import uniandes.isis2304.superAndes.negocio.Proveedor;

public class PersistenciaSuperAndes {
	
	private static Logger Log = Logger.getLogger(PersistenciaSuperAndes.class.getName());
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperAndes instance;
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";
	
	/**
	 * 
	 */
	private PersistenceManagerFactory managerFactory;
	
	/**
	 * 
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaSuperAndes 
	 */
	private SQLUtil sqlUtil;
	
	private SQLBodega sqlBodega;
	private SQLCantidadEnBodega sqlCantidadEnBodega ;
	private SQLCantidadEnEstantes sqlCantidadEnEstantes;
	private SQLCategoria sqlCategoria  ;
	private SQLCliente sqlCliente ;
	private SQLComprados sqlComprados ;
	private SQLEmpresa sqlEmpresa;
	private SQLEstante sqlEstante;
	private SQLFactura sqlFactura ;
	private SQLHistorialCompras sqlHistorialCompras ;
	private SQLOrdenPedido sqlOrdenPedido ;
	private SQLPedidoProducto sqlPedidoProducto;
	private SQLPersonaNatural sqlPersonaNatural ;
	private SQLProducto sqlProducto ;
	private SQLProductosEnPromocion sqlProductosEnPromocion ;
	private SQLPromocion sqlPromocion;
	private SQLProveedor sqlProveedor;
	private SQLProveen sqlProveen;
	private SQLSucursal sqlSucursal;
	private SQLSucursalFactura sqlSucursalFactura;
	private SQLTipoProducto sqlTipoProducto;
	
	
	
	/**
	 * Constructor privado con valores por defecto - Patron SINGLETON
	 */
	private PersistenciaSuperAndes()
	{
		managerFactory = JDOHelper.getPersistenceManagerFactory("SuperAndes");
		crearClasesSQL();
		tablas = new LinkedList<String>();
		tablas.add("SuperAndes_sequence");
		tablas.add("BODEGA");
		tablas.add("CANTIDAD_EN_BODEGA");
		tablas.add("CANTIDAD_EN_ESTANTES");
		tablas.add("CATEGORIA");
		tablas.add("CLIENTE");
		tablas.add("COMPRADOS");
		tablas.add("EMPRESA");
		tablas.add("ESTANTE");
		tablas.add("FACTURA");
		tablas.add("HISTORIAL_COMPRAS");
		tablas.add("ORDEN_PEDIDO");
		tablas.add("PEDIDO_PRODUCTO");
		tablas.add("PERSONA_NATURAL");
		tablas.add("PRODUCTO");
		tablas.add("PRODUCTOS_EN_PROMOCION");
		tablas.add("PROMOCION");
		tablas.add("PROVEEDOR");
		tablas.add("PROVEEN");
		tablas.add("SUCURSAL");
		tablas.add("FACTURAS");
		tablas.add("TIPO_PRODUCTO");
	}
	
	private PersistenciaSuperAndes(JsonObject tableConfig)
	{
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);
		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		Log.trace("Accediendo a la unidad de persistencia: "+ unidadPersistencia);
		managerFactory = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}
	
	
	public static PersistenciaSuperAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes ();
		}
		return instance;
	}
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes (tableConfig);
		}
		return instance;
	}
	
	public void cerrarUnidadPersistencia()
	{
		managerFactory.close();
		instance = null;
	}
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlBodega = new SQLBodega(this);
		sqlCantidadEnBodega = new SQLCantidadEnBodega(this);
		sqlCantidadEnEstantes = new SQLCantidadEnEstantes(this);
		sqlCategoria = new SQLCategoria(this);
		sqlCliente = new SQLCliente(this);
		sqlComprados = new SQLComprados(this);
		sqlEmpresa = new SQLEmpresa(this);
		sqlEstante = new SQLEstante(this);
		sqlFactura = new SQLFactura(this);
		sqlHistorialCompras = new SQLHistorialCompras(this);
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlPedidoProducto = new SQLPedidoProducto(this);
		sqlPersonaNatural = new SQLPersonaNatural(this);
		sqlProducto = new SQLProducto(this);
		sqlProductosEnPromocion = new SQLProductosEnPromocion(this);
		sqlPromocion = new SQLPromocion(this);
		sqlProveedor = new SQLProveedor(this);
		sqlProveen = new SQLProveen(this);
		sqlSucursal = new SQLSucursal(this);
		sqlSucursalFactura = new SQLSucursalFactura(this);
		sqlTipoProducto = new SQLTipoProducto(this);
		sqlUtil = new SQLUtil(this);
	}
	public String darSecuenciaSuperAndes()
	{
		return tablas.get(0);
	}

	public String getSqlBodega() {
		return tablas.get(1);
	}

	public String getSqlCantidadEnBodega() {
		return tablas.get(2);
	}

	public String getSqlCantidadEnEstantes() {
		return tablas.get(3);
	}

	public String getSqlCategoria() {
		return tablas.get(4);
	}

	public String getSqlCliente() {
		return tablas.get(5);
	}

	public String getSqlComprados() {
		return tablas.get(6);
	}

	public String getSqlEmpresa() {
		return tablas.get(7);
	}

	public String getSqlEstante() {
		return tablas.get(8);
	}

	public String getSqlFactura() {
		return tablas.get(9);
	}

	public String getSqlHistorialCompras() {
		return tablas.get(10);
	}

	public String getSqlOrdenPedido() {
		return tablas.get(11);
	}

	public String getSqlPedidoProducto() {
		return tablas.get(12);
	}

	public String getSqlPersonaNatural() {
		return tablas.get(13);
	}

	public String getSqlProducto() {
		return tablas.get(14);
	}

	public String getSqlProductosEnPromocion() {
		return tablas.get(15);
	}

	public String getSqlPromocion() {
		return tablas.get(16);
	}

	public String getSqlProveedor() {
		return tablas.get(17);
	}

	public String getSqlProveen() {
		return tablas.get(18);
	}

	public String getSqlSucursal() {
		return tablas.get(19);
	}

	public String getSqlSucursalFactura() {
		return tablas.get(20);
	}

	public String getSqlTipoProducto() {
		return tablas.get(21);
	}
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	//TODO Metodos por cada tabla:
	
	//---------------------------------------------------------------------
	// Métodos para manejar los PROVEEDORES
	//---------------------------------------------------------------------
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Proveedor
	 * Adiciona entradas al log de la aplicación
	 * @param nitProveedor - El nit del proveedor
	 * @param nombreProveedor - El nombre del proveedor
	 * @return El objeto Proveedor adicionado. null si ocurre alguna Excepción
	 */
	public Proveedor adicionarProveedor(int nitProveedor, String nombreProveedor)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm, nitProveedor, nombreProveedor);
			tx.commit();
			
			Log.trace("Insercción proveedor: " + nitProveedor + ": "+tuplasInsertadas);
			return new Proveedor(nitProveedor, nombreProveedor, "");
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
		}
	}
	
	//---------------------------------------------------------------------
	// Métodos para manejar los CLIENTES
	//---------------------------------------------------------------------
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Cliente
	 * Adiciona entradas al log de la aplicación
	 * @param correo - El correo del cliente
	 * @param nombre - El nombre del cliente
	 * @return El objeto Cliente adicionado. null si ocurre alguna Excepción 
	 */
	public Cliente adicionarCliente(String correo, String nombre)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCliente.adicionarCliente(pm, correo, nombre);
			tx.commit();
			
			Log.trace("Insercción cliente: " + correo + ": "+tuplasInsertadas);
			return new Cliente(nombre, correo, 0);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
		}
	}
	
	//---------------------------------------------------------------------
	// Métodos para manejar las SUCURSALES
	//---------------------------------------------------------------------
	
}
