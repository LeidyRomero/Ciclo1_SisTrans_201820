package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
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

import uniandes.isis2304.superAndes.negocio.*;

public class PersistenciaSuperAndes {

	//---------------------------------------------------
	// CONSTANTES
	//---------------------------------------------------
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger Log = Logger.getLogger(PersistenciaSuperAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	//---------------------------------------------------
	// CONSTANTES
	//---------------------------------------------------
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaSuperAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory managerFactory;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos
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
	private SQLOrdenPedido sqlOrdenPedido ;
	private SQLPedidoProducto sqlPedidoProducto;
	private SQLPersonaNatural sqlPersonaNatural ;
	private SQLProducto sqlProducto ;
	private SQLProductosOfrecidos sqlProductosOfrecidos;
	private SQLPromocion sqlPromocion;
	private SQLProveedor sqlProveedor;
	private SQLProveen sqlProveen;
	private SQLSucursal sqlSucursal;
	private SQLTipoProducto sqlTipoProducto;
	private SQLSucursalPromociones sqlSucursalPromociones;
	private SQLPromoCantidades sqlPromoCantidades;
	private SQLPromoUnidadDescuento sqlPromoUnidadDescuento;
	private SQLPromoUnidades sqlPromoUnidades;
	private SQLPromoDescuento sqlPromoDescuento;
	private SQLCarrito sqlCarrito;
	private SQLProductosCarrito sqlProductosCarrito;

	//---------------------------------------------------
	// Métodos del MANEJADOR DE PERSISTENCIA
	//---------------------------------------------------
	/**
	 * Constructor privado con valores por defecto - Patron SINGLETON
	 */
	private PersistenciaSuperAndes()
	{
		managerFactory = JDOHelper.getPersistenceManagerFactory("SuperAndes");
		crearClasesSQL();
		tablas = new LinkedList<String>();
		tablas.add("pedidos_sequence");
		tablas.add("A_BODEGA");
		tablas.add("A_CANTIDAD_EN_BODEGA");
		tablas.add("A_CANTIDAD_EN_ESTANTES");
		tablas.add("A_CATEGORIA");
		tablas.add("A_CLIENTE");
		tablas.add("A_COMPRADOS");
		tablas.add("A_EMPRESA");
		tablas.add("A_ESTANTE");
		tablas.add("A_FACTURA");
		tablas.add("A_ORDEN_PEDIDO");
		tablas.add("A_PEDIDO_PRODUCTO");
		tablas.add("A_PERSONA_NATURAL");
		tablas.add("A_PRODUCTO");
		tablas.add("A_PROMOCION");
		tablas.add("A_PROVEEDOR");
		tablas.add("A_PROVEEN");
		tablas.add("A_SUCURSAL");
		tablas.add("A_TIPO_PRODUCTO");
		tablas.add("A_PRODUCTO_CATEGORIA");
		tablas.add("A_PRODUCTOS_OFRECIDOS");
		tablas.add("A_PROMOCIONES");
		tablas.add("A_PROMO_CANTIDADES");
		tablas.add("A_PROMO_UNIDADES");
		tablas.add("A_PROMO_DESCUENTO");
		tablas.add("A_PROMO_UNIDADES_DESCUENTO");
		tablas.add("promocion_sequence");
		tablas.add("factura_sequence");
		tablas.add("A_CARRITO");
		tablas.add("A_PRODUCTOS_CARRITO");
		tablas.add("carrito_sequence");

	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaSuperAndes(JsonObject tableConfig)
	{
		crearClasesSQL();
		tablas = leerNombresTablas(tableConfig);

		String unidadPersistencia = tableConfig.get("unidadPersistencia").getAsString();
		Log.trace("Accediendo a la unidad de persistencia: "+ unidadPersistencia);
		managerFactory = JDOHelper.getPersistenceManagerFactory(unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaSuperAndes existente - Patrón SINGLETON
	 */
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

	/**
	 * Cierra la conexión con la base de datos
	 */
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
		sqlOrdenPedido = new SQLOrdenPedido(this);
		sqlPedidoProducto = new SQLPedidoProducto(this);
		sqlPersonaNatural = new SQLPersonaNatural(this);
		sqlProducto = new SQLProducto(this);
		sqlPromocion = new SQLPromocion(this);
		sqlProveedor = new SQLProveedor(this);
		sqlProveen = new SQLProveen(this);
		sqlSucursal = new SQLSucursal(this);
		sqlTipoProducto = new SQLTipoProducto(this);
		sqlProductosOfrecidos = new SQLProductosOfrecidos(this);
		sqlSucursalPromociones = new SQLSucursalPromociones(this);
		sqlPromoCantidades = new SQLPromoCantidades(this);
		sqlPromoUnidades = new SQLPromoUnidades(this);
		sqlPromoUnidadDescuento = new SQLPromoUnidadDescuento(this);
		sqlPromoDescuento = new SQLPromoDescuento(this);
		sqlCarrito = new SQLCarrito(this);
		sqlProductosCarrito = new SQLProductosCarrito(this);

		sqlUtil = new SQLUtil(this);
	}
	public String getSecuenciaPedidos()
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

	public String getSqlOrdenPedido() {
		return tablas.get(10);
	}

	public String getSqlPedidoProducto() {
		return tablas.get(11);
	}

	public String getSqlPersonaNatural() {
		return tablas.get(12);
	}

	public String getSqlProducto() {
		return tablas.get(13);
	}

	public String getSqlPromocion() {
		return tablas.get(14);
	}

	public String getSqlProveedor() {
		return tablas.get(15);
	}

	public String getSqlProveen() {
		return tablas.get(16);
	}

	public String getSqlSucursal() {
		return tablas.get(17);
	}

	public String getSqlTipoProducto() {
		return tablas.get(18);
	}

	public String getSqlProductosOfrecidos() {
		return tablas.get(19);
	}

	public String getSqlSucursalPromociones()
	{
		return tablas.get(20);
	}

	public String getSqlPromoCantidades()
	{
		return tablas.get(21);
	}

	public String getSqlPromoUnidades()
	{
		return tablas.get(22);
	}

	public String getSqlPromoDescuento()
	{
		return tablas.get(23);
	}

	public String getSqlPromoUnidadDescuento()
	{
		return tablas.get(24);
	}

	public String getSecuenciaPromociones()
	{
		return tablas.get(25);
	}

	public String getSecuenciaFacturas()
	{
		return tablas.get(26);
	}

	public String getSqlCarrito()
	{
		return tablas.get(27);
	}

	public String getSqlProductosCarrito()
	{
		return tablas.get(28);
	}

	public String getSecuenciaCarrito()
	{
		return tablas.get(29);
	}

	/**
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextvalPedidos ()
	{
		long resp = sqlUtil.nextvalPedidos (managerFactory.getPersistenceManager());
		Log.trace ("Generando secuencia pedidos: " + resp);
		return resp;
	}
	/**
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextvalPromociones()
	{
		long resp = sqlUtil.nextvalPromociones(managerFactory.getPersistenceManager());
		Log.trace ("Generando secuencia promociones: " + resp);
		return resp;
	}
	/**
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextvalFacturas()
	{
		long resp = sqlUtil.nextvalFacturas(managerFactory.getPersistenceManager());
		Log.trace ("Generando secuencia facturas: " + resp);
		return resp;
	}
	/**
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextvalCarritos()
	{
		long resp = sqlUtil.nextvalCarritos(managerFactory.getPersistenceManager());
		Log.trace ("Generando secuencia carrito: " + resp);
		return resp;
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
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * Método que consulta todas las tuplas en la tabla Proveedores
	 * @return La lista de objetos Proveedor, construidos con base a las tuplas de la tabla PROVEEDOR
	 */
	public List<Proveedor> buscarProveedores()
	{
		return sqlProveedor.darProveedores(managerFactory.getPersistenceManager());
	}
	/**
	 * Método que elimina, de manera transaccional, una tupla de la tabla Proveedor, dado el nit del proveedor
	 * @param nitProveedor - El nit del proveedor
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarProveedorPorNit(int nitProveedor)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasEliminadas = sqlProveedor.eliminarProveedorPorNit(pm, nitProveedor);
			tx.commit();

			Log.trace("Eliminación proveedor: " + nitProveedor + ": "+tuplasEliminadas);
			return tuplasEliminadas;
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	/**
	 * Método que consulta la tupla de la tabla Proveedor que tienen el nit dado
	 * @param nitProveedor - Nit del proveedor 
	 * @return La lista de objetos Proveedor, construidos con base en las tuplas de la tabla PROVEEDOR
	 */
	public Proveedor darProveedorPorNit(int nitProveedor)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Proveedor tuplaEncontra = sqlProveedor.darProveedorPorNit(pm, nitProveedor);
		return tuplaEncontra;
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
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Cliente
	 * @return La lista de objetos Cliente, construidos con base a las tuplas de la tabla CLIENTE
	 */
	public List<Cliente> buscarClientes()
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		return sqlCliente.darClientes(pm);
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Cliente
	 * Adiciona entradas al log de la aplicación
	 * @param correo . El correo del cliente
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna excepción.
	 */
	public long eliminarClientePorCorreo(String correo)

	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasEliminadas = sqlCliente.eliminarClientePorCorreo(pm, correo);
			tx.commit();

			Log.trace("Eliminación cliente: " + correo + ": "+tuplasEliminadas);
			return tuplasEliminadas;
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Cliente con un correo dado
	 * @param correo - El correo del cliente
	 * @return El objeto Cliente, construido con base en las tuplas de la tabla CLIENTE con el correo dado
	 */
	public Cliente darClientePorCorreo(String correo)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		return sqlCliente.darClientePorCorreo(pm, correo);
	}

	//---------------------------------------------------------------------
	// Métodos para manejar los SUCURSALES
	//---------------------------------------------------------------------
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Sucursal
	 * Adiciona entradas al log de la aplicación
	 * @param tamanio - El tamaño de la sucursal
	 * @param direccion - La dirección de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @param nombre - El nombre de la sucursal
	 * @return El objeto Sucursal adicionado. null si ocurre alguna excepcion
	 */
	public Sucursal adicionarSucursal(String tamanio, String direccion, String ciudad, String nombre)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, tamanio, direccion, ciudad, nombre);
			tx.commit();

			Log.trace("Insercción sucursal: " + direccion +", "+ciudad + ": "+tuplasInsertadas);
			return new Sucursal(tamanio, direccion, ciudad, nombre);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Sucursal con una direccion y ciudad dadas
	 * @param direccion - La dirección de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @return El objeto Sucursal, construido con base en las tuplas de la tabla SUCURSAL con la direccion y ciudad dada
	 */
	public Sucursal buscarSucursal(String direccion, String ciudad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		return sqlSucursal.darSucursalPorDireccionYCiudad(pm, direccion, ciudad);
	}

	/**
	 *Método que consulta todas las tuplas en la tabla Sucursal 
	 * @return La lista de objetos Sucursal, construidos con base a las tuplas de la tabla SUCURSAL
	 */
	public List<Sucursal> buscarSucursales()
	{
		return sqlSucursal.darSucursales(managerFactory.getPersistenceManager());
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla de la tabla Sucursal
	 * Adiciona entradas al log de la aplicación
	 * @param direccion - La dirección de la sucursal
	 * @param ciudad - La ciudad de la sucursal
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna excepción.
	 */
	public long eliminarSucursalPorDireccionYCiudad(String direccion, String ciudad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasEliminadas = sqlSucursal.eliminarSucursalPorDireccionYCiudad(pm, direccion, ciudad);
			tx.commit();

			Log.trace("Eliminación sucursal: " +direccion+", "+ciudad + ": "+tuplasEliminadas);
			return tuplasEliminadas;
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	//---------------------------------------------------------------------
	// Métodos para manejar los PROMOCIÓN
	//---------------------------------------------------------------------
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Promocion
	 * Adiciona entradas al log de la aplicación
	 * @param fechaInicio - Fecha de inicio de la promoción
	 * @param fechaFin - Fecha de fin de la promoción
	 * @param descripcion - Descripción de la promoción
	 * @param codBarras - Código de barras de producto
	 * @param uniDisponibles - Unidades disponibles para la promoción
	 * @param uniVendidas - Unidades vendidas de la promoción
	 * @return El objeto Promocion adicionada. null si ocurre alguna Excepción
	 */
	public Promocion adicionarPromocion(Timestamp fechaInicio, Timestamp fechaFin, String descripcion, String codBarras, int uniDisponibles, int uniVendidas)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idPromocion = nextvalPromociones();
			//long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm, fechaInicio, fechaFin, descripcion, codBarras, 24, uniVendidas, uniDisponibles);

			//TODO Terminar de verificar los datos que llegan
			if(descripcion.contains("cantidad, lleve"))
			{
				System.out.println(descripcion.substring(23, 24));
				System.out.println(descripcion.substring(6, 7));
				//sqlPromoCantidades.adicionarPromocion(pm, idPromocion, Integer.parseInt(descripcion.substring(23, 24)), Integer.parseInt(descripcion.substring(6, 7)));
			}
			else if(descripcion.startsWith("Descuento del"))
			{
				System.out.println(Double.parseDouble(descripcion.replace("%", "").substring(14)));
				//sqlPromoDescuento.adicionarPromocion(pm, idPromocion, Double.parseDouble(descripcion.substring(14, 15)));
			}
			else if(descripcion.contains("y lleve el siguiente con"))
			{
				System.out.println(descripcion.substring(6, 7));
				System.out.println(descripcion.substring(33, 34));
				//sqlPromoUnidadDescuento.adicionarPromocion(pm, idPromocion, Integer.parseInt(descripcion.substring(6, 7)), Double.parseDouble(descripcion.substring(33, 34)));
			}
			else if(descripcion.contains("unidades, lleve"))
			{
				System.out.println(descripcion.substring(6, 7));
				System.out.println(descripcion.substring(23, 24));

				//sqlPromoUnidades.adicionarPromocion(pm, idPromocion, Integer.parseInt(descripcion.substring(6, 7)), Integer.parseInt(descripcion.substring(23, 24)));
			}

			tx.commit();

			Log.trace("Insercción promocion: " + idPromocion +": "/*+tuplasInsertadas*/);
			return new Promocion(fechaInicio, fechaFin, descripcion, idPromocion, codBarras, uniVendidas, uniDisponibles, "VIGENTE");
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que cambia, de manera transaccional, el estado de una tupla en la tabla Promoción con el identificador válido
	 * Adiciona entradas al log de la aplicación
	 * @param idPromocion - El identificador de la promoción
	 */
	public void finalizarPromocion(long idPromocion)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasModificadas = sqlPromocion.finalizarPromocion(pm, idPromocion);
			tx.commit();

			Log.trace("Modificación promocion: " + idPromocion +": "+tuplasModificadas);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que finaliza todas las promociones que deben ser finalizadas
	 */
	public void finalizarPromocionesPendientes()
	{
		// Cada vez que inicia el sistema debe verificar si hay promociones por finalizar
		PersistenceManager pm  = managerFactory.getPersistenceManager();
		List<Promocion> porFinalizar = sqlPromocion.darPromocionesPorFinalizar(pm);
		for(Promocion actual: porFinalizar)
		{
			sqlPromocion.finalizarPromocion(pm, actual.getIdPromocion());
		}
	}

	/**
	 * Método que consulta las 20 promociones más populares de todo SuperAndes
	 * @return La lista de objetos Promocion
	 */
	public List<Promocion> darPromocionesMasPopulares()
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		return sqlPromocion.darPromocionesMasPopulares(pm);
	}

	/**
	 * Método que consulta todas las tuplas en la tabla PROMOCION
	 * @return La lista de objetos Promocion, construidos con base en las tuplas de la tabla PROMOCION
	 */
	public List<Promocion> darPromociones()
	{
		return sqlPromocion.darPromociones(managerFactory.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas de la tabla Promocion con un identificador dado
	 * @param idPromocion - El identificador de la promocion
	 * @return El objeto Promocion, construido con base a las tuplas de la tabla PROMOCION con el identificador dado
	 */
	public Promocion darPromocionPorId(String idPromocion)
	{
		return sqlPromocion.darPromocionPorId(managerFactory.getPersistenceManager(), idPromocion);
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla Promocion con el identificador dado
	 * @param idPromocion - El identificador de la promoción
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarPromocionPorId(long idPromocion)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasEliminadas = sqlPromocion.eliminarPromocionPorId(pm, idPromocion);
			tx.commit();

			Log.trace("Insercción promocion: " + idPromocion +": "+tuplasEliminadas);
			return tuplasEliminadas;
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	//---------------------------------------------------------------------
	// Métodos para manejar los ORDEN PEDIDO
	//---------------------------------------------------------------------
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Orden Pedido
	 * @param fechaEsperada - Fecha esperada de entrega del pedido
	 * @param nitProveedor - Nit del proveedor del pedido
	 * @param ciudad - Ciudad de la sucursal que hace el pedido
	 * @param direccionSucursal - Dirección de la sucursal que hace el pedido
	 * @param direccionBodega - Dirección de la bodega a la que debe llegar el pedido
	 * @return El objeto Orden Pedido adicionado. null si ocurre alguna Excepción.
	 */
	public OrdenPedido adicionarOrdenPedido(Timestamp fechaEsperada, int nitProveedor, String ciudad, String direccionSucursal, String direccionBodega)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idPedido = nextvalPedidos();
			long tuplasInsertadas = sqlOrdenPedido.adicionaroOrdenPedido(pm, fechaEsperada, nitProveedor, ciudad, direccionSucursal, direccionBodega, idPedido);
			tx.commit();

			Log.trace("Insercción promocion: " + idPedido +": "+tuplasInsertadas);
			return new OrdenPedido(fechaEsperada, "En espera", null, null, nitProveedor, idPedido, ciudad, direccionSucursal, direccionBodega);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que cambia, de manera transaccional, el estado de una tupla en la tabla OrdenPedido
	 * Adiciona entradas al log de la aplicación
	 * @param idPedido - El identificador del pedido
	 * @param calificacion - La califiación de la orde de pedido
	 * @return El número de tuplas modificadas
	 */
	public OrdenPedido llegadaOrdenPedido(long idPedido, String calificacion)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasModificadas = sqlOrdenPedido.cambiarEstadoOrdenPedido(pm, idPedido, calificacion);
			Log.trace("Modificación estado orden pedido: " + idPedido +": "+tuplasModificadas);

			OrdenPedido op = sqlOrdenPedido.darPedidoPorId(pm, idPedido);
			int numProductos = sqlPedidoProducto.darNumeroProductosPedido(pm, idPedido);
			long tuplasBodega = sqlCantidadEnBodega.subirInventario(pm, numProductos, op.getCiudad(), op.getDireccionSucursal(), op.getDireccionBodega());
			tx.commit();

			Log.trace("Modificación de cantidad en bodega: " + op.getDireccionBodega() + ", "+ op.getDireccionSucursal()  + ", "+ op.getCiudad()+": "+tuplasBodega);
			return op;
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Orden Pedido
	 * @return La lista de objetos OrdenPedido, construidos con base a las tuplas de la tablas ORDEN PEDIDO
	 */
	public List<OrdenPedido> darPedidos()
	{
		return sqlOrdenPedido.darPedidos(managerFactory.getPersistenceManager());
	}

	/**
	 * Método que consulta todas las tuplas en la tabla OrdenPedido con un identificador dado
	 * @param idPedido - El identificador del pedido
	 * @return El objeto OrdenPedido, construido con base en las tuplas de la tabla ORDENPEDIDO
	 */
	public OrdenPedido darPedidoPorId(long idPedido)
	{
		return sqlOrdenPedido.darPedidoPorId(managerFactory.getPersistenceManager(), idPedido);
	}

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla OrdenPedido
	 * Adiciona entradas al log de la aplicación
	 * @param idPedido - El identificador del pedido
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarPedidoPorId(long idPedido)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasEliminadas = sqlOrdenPedido.eliminarPedidoPorId(pm, idPedido);
			tx.commit();

			Log.trace("Eliminación pedido: " + idPedido +": "+tuplasEliminadas);
			return tuplasEliminadas;
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	//---------------------------------------------------------------------
	// Métodos para manejar los FACTURA
	//---------------------------------------------------------------------
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Factura
	 * Adiciona entradas al log de la aplicación
	 * @param costoTotal - El costo total de la factura
	 * @param fecha - La fecha en que se genera la factura
	 * @param correoCliente - El correo del clente que compra
	 * @param ciudad - La ciudad de la sucursal
	 * @param direccion - La dirección de la sucursal
	 * @return El número de tuplas insertadas. -1 si ocurre una Excepción
	 */
	public Factura adicionarFactura(double costoTotal, Timestamp fecha, String correoCliente, String ciudad, String direccion)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idFactura = nextvalFacturas();
			long tuplasInsertadas = sqlFactura.adicionarFactura(pm, idFactura, costoTotal, fecha, correoCliente, ciudad, direccion);
			tx.commit();

			Log.trace("Insercción factura: " + idFactura+": "+tuplasInsertadas);
			return new Factura(idFactura, fecha, costoTotal, correoCliente);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que eliminada, de manera transaccional, una tupla en la tabla Factura
	 * Adiciona entradas al log de la aplicación
	 * @param idFactura - El identificador de la factura
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarFacturaPorId(long idFactura)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasEliminadas = sqlFactura.eliminarFacturaPorId(pm, idFactura);
			tx.commit();

			Log.trace("Eliminación factura: " + idFactura +": "+tuplasEliminadas);
			return tuplasEliminadas;
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta todas las tuplas en la tabla Factura que tienen el identificador dado
	 * @param idFactura - El identificador de la factura
	 * @return El objeto Factura, cosntruido con base a las tuplas de la tabla Factura con el identificador dado
	 */
	public Factura darFacturaPorId(long idFactura)
	{
		return sqlFactura.darFacturaPorId(managerFactory.getPersistenceManager(), idFactura);
	}

	/**
	 * Método que consulta todas la tuplas en la tabla Factura
	 * @return La lista de objetos Factura, cosntruidos con base en las tuplas de la tabla Factura
	 */
	public List<Factura> darFacturas()
	{
		return sqlFactura.darFacturas(managerFactory.getPersistenceManager());
	}

	/**
	 * Método que modifica, de manera transaccional, el costo total de una tupla de la tabla Factura
	 * Adiciona entradas al log de la aplicación
	 * @param costoAdicional - El valor en que se desea aumentar el costo
	 * @param idFactura - El identificador de la factura
	 * @return El número de tuplas modificadas. -1 en caso de que ocurra una Excepción
	 */
	public long aumentarCostoFactura(double costoAdicional, long idFactura)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasModificadas = sqlFactura.aumentarCostoFactura(pm, costoAdicional, idFactura);
			tx.commit();

			Log.trace("Modificación del costo de una factura: " + idFactura +": "+tuplasModificadas);
			return tuplasModificadas;
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return 0;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que consulta el dinero ganado por una sucursal en un periodo de tiempo
	 * @param fechaInicio - La fecha de inicio de la consulta
	 * @param fechaFin - La fecha de fin de la consulta
	 * @return Una lista de objetos de tamaño 3
	 */
	public List<Object> dineroSucursalEnRango(Timestamp fechaInicio, Timestamp fechaFin)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		return sqlFactura.dineroSucursalEnRango(pm, fechaInicio, fechaFin);
	}
	
	
	/**
	 * Retorna los clientes frecuentes de superAndes
	 * @param pDireccion
	 * @param pCiudad
	 * @return
	 */
	public List<String> darClientesFrecuentes(String pDireccion, String pCiudad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		return sqlFactura.darClientesFrecuentes(pm, pDireccion, pCiudad);
	}
	//---------------------------------------------------------------------
	// Métodos para manejar los CANTIDAD EN ESTANTES
	//---------------------------------------------------------------------
	/**
	 * Método que inseta, de manera transaccional, una tupla en la tabla CantidadEnEstantes
	 * Adiciona entradas al log de la aplicación
	 * @param codigoBarras - El código de barras del producto
	 * @param idEstante - El identificador del estante
	 * @param cantidadActual - La cantidad actual del producto
	 * @param cantidadMinima - La cantidad mínima del producto
	 * @return El obejto CantidadEnEstantes. null en caso de que ocurra una Excepción
	 */
	public CantidadEnEstantes adicionarCantidadEnEstante(String codigoBarras, long idEstante, int cantidadActual, int cantidadMinima)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCantidadEnEstantes.adicionarCantidadEstantes(pm, codigoBarras, idEstante, cantidadActual, cantidadMinima);
			tx.commit();

			Log.trace("Insercción cantidad en estante: " + idEstante + ", "+ codigoBarras +": "+tuplasInsertadas);
			return new CantidadEnEstantes(cantidadActual, cantidadMinima, codigoBarras, idEstante);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que modifica, de manera transaccional, la cantidad de un producto en estante
	 * Adiciona entradas al log de la aplicación
	 * @param pCantidad - La cantidad a disminuir
	 * @param producto - El producto del que se quiere disminuir la cantidad
	 * @param idEstante - El identificador del estante donde está el producto
	 * @return El número de tuplas modificadas. -1 en caso de que ocurra una Excepción
	 */
	public long disminuirCantidadEnEstantes(int pCantidad, Producto producto, long idEstante)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			long tuplasModificadas = sqlCantidadEnEstantes.disminuirCantidadEnEstantes(pm, pCantidad, producto.getCodBarras(), idEstante);
			t.commit();

			Log.trace("Disminuyendo cantidad en estantes: " + idEstante + ", " + producto.getCodBarras() + ", " + pCantidad +": " + tuplasModificadas  );
			return tuplasModificadas;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return -1;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}

	/**
	 * Método que modifica, de manera transaccional, la cantidad de un producto en estante
	 * Adiciona entradas al log de la aplicación
	 * @param pCantidad - La cantidad a aumentar
	 * @param producto - El producto del que se quiere aumentar la cantidad
	 * @param idEstante - El identificador del estante donde está el producto
	 * @return El número de tuplas modificadas. -1 en caso de que ocurra una Excepción
	 */
	public long aumentarCantidadEnEstantes(int pCantidad, Producto producto, long idEstante)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			long tuplasModificadas = sqlCantidadEnEstantes.aumentarCantidadEnEstantes(pm, pCantidad, producto.getCodBarras(), idEstante);
			t.commit();

			Log.trace("Aumentando cantidad en estantes: " + idEstante + ", " + producto.getCodBarras() + ", " + pCantidad +": " + tuplasModificadas  );
			return tuplasModificadas;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return -1;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}
	
	/**
	 * Método que consulta la cantidad en estantes real
	 * @param ciudad - La ciudad de la sucursal
	 * @param direccionSucursal - La dirección de la sucursal
	 * @return La lista de parejas de objetos, construidos en base a las tuplas de la tabla CANTIDAD EN ESTANTES y PRODUCTO
	 * El primer elemento de la pareja es una cantidad en estantes;
	 * el segundo elemento es el nombre del producto.
	 */
	public List<Object[]> darCantidadEnEstantesReales(String ciudad, String direccionSucursal)
	{
		List<Object[]> respuesta = new LinkedList<Object[]>();
		List<Object> tuplas = sqlCantidadEnEstantes.darCantidadEnEstantesReales(managerFactory.getPersistenceManager(), ciudad, direccionSucursal);
		for(Object tupla: tuplas)
		{
			Object[] datos = (Object[]) tupla;
			String nombreProducto = (String) datos[0];
			String codigoBarras = (String) datos[1];
			int cantidadMinima = ((BigDecimal) datos [2]).intValue ();
			long idEstante = ((BigDecimal) datos [3]).longValue ();
			int cantidadActual = ((BigDecimal) datos [4]).intValue ();
			
			Object[] resp = new Object[2];
			resp[0] = new CantidadEnEstantes(cantidadActual, cantidadMinima, codigoBarras, idEstante);
			resp[1] = nombreProducto;
			
			respuesta.add(resp);
		}
		return respuesta;
	}

	//---------------------------------------------------------------------
	// Métodos para manejar los PRODUCTOS OFRECIDOS
	//---------------------------------------------------------------------
	public ProductosOfrecidos adicionarProductosOfrecidos(String codigoBarras, String direccionSucursal, String ciudad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductosOfrecidos.adicionarProductoOfrecido(pm, codigoBarras, direccionSucursal, ciudad);
			tx.commit();

			Log.trace("Insercción productos ofrecido: " + direccionSucursal + ", "+ ciudad +", "+ codigoBarras +": "+tuplasInsertadas);
			return new ProductosOfrecidos(direccionSucursal, ciudad, codigoBarras, 0);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	//---------------------------------------------------------------------
	// Métodos para manejar los PROVEEN
	//---------------------------------------------------------------------
	public Proveen adicionarProveen(int nitProveedor, String codigoBarras)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProveen.adicionarProveen(pm, nitProveedor, codigoBarras);
			tx.commit();

			Log.trace("Insercción proveen: " + nitProveedor +", "+ codigoBarras +": "+tuplasInsertadas);
			return new Proveen(nitProveedor, codigoBarras);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	//---------------------------------------------------------------------
	// Métodos para manejar SUCURSAL PROMOCIONES
	//---------------------------------------------------------------------

	//---------------------------------------------------------------------
	// Métodos para manejar los SUCURSAL PROMOCIONES
	//---------------------------------------------------------------------
	public SucursalPromociones adicionarSucursalPromociones(long idPromocion, String direccion, String ciudad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlSucursalPromociones.adicionarSucursalPromociones(pm, idPromocion, direccion, ciudad);
			tx.commit();

			Log.trace("Insercción sucursal promocion: " + idPromocion +", "+ direccion +", "+ ciudad+ ": "+tuplasInsertadas);
			return new SucursalPromociones(idPromocion, ciudad, direccion);
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	//---------------------------------------------------------------------
	// Métodos para manejar los PRODUCTO
	//---------------------------------------------------------------------
	public Producto adicionarProducto(int pCantidadMinima, String pNombre, String pMarca, String pPresentacion, String pUnidadMedida, String pCalidad, double pPrecioUnitario, double pPrecioUnidadMedida, int pCantidadPresentacion, String pCodigoBarras, Date pFechaVencimiento, String pPeso, String pVolumen, String pCategoria)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			Bodega bodega = buscarBodegaTipo(pCategoria);
			adicionarCantidadEnBodega(bodega.getDireccionBodega(), bodega.getDireccionSucursal(), bodega.getCiudad(),0, pCantidadMinima, pCodigoBarras);
			long tuplasInsertadas = sqlProducto.agregarProducto(manager, pNombre, pMarca, pPresentacion, pUnidadMedida, pCalidad, pPrecioUnitario, pPrecioUnidadMedida, pCantidadPresentacion, pCodigoBarras, pFechaVencimiento,pPeso, pVolumen, pCategoria);
			t.commit();
			Log.trace("Inserccion producto: "+ pNombre+": "+tuplasInsertadas+ " tuplas insertadas");
			return new Producto(pNombre, pMarca, pPresentacion, pUnidadMedida, pCalidad, pPrecioUnitario, pPrecioUnidadMedida, pCantidadPresentacion, pCodigoBarras, pFechaVencimiento, pPeso, pVolumen);
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public Producto buscarProductoCodigoBarras(String pCodigoBarras)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			Producto buscado = sqlProducto.buscarProductoPorCodigo(manager, pCodigoBarras);
			t.commit();
			Log.trace("Busqueda producto: "+ pCodigoBarras);
			return buscado;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Producto> buscarProductos()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Producto> q = sqlProducto.buscarProductos(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarProducto(String pCodigo)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlProducto.eliminarProducto(manager, pCodigo);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	
	//------------------------------------------------------------------
	//  Metodos para manejar CATEGORIA
	//------------------------------------------------------------------
	public Categoria adicionarCategoria( String pNombre)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlCategoria.agregarCategoria(manager, pNombre );
			t.commit();
			Log.trace("Inserccion categoria: "+pNombre +": "+tuplasInsertadas+ " tuplas insertadas");
			return new Categoria();
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public Categoria buscarCategoriaNombre(String pNombre)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		Categoria categoria = null;
		try 
		{
			t.begin();
			categoria = sqlCategoria.buscarNombre(manager, pNombre);
			t.commit();
			Log.trace("Buscar tipo de producto por nombre: "+ categoria);
			return categoria;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Categoria> buscarCategorias()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Categoria> q = sqlCategoria.buscarCategorias(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarCategoria(String pCategoria)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlCategoria.eliminarCategoria(manager, pCategoria);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}

	//------------------------------------------------------------------
	//  Metodos para manejar TIPO_PRODUCTO
	//------------------------------------------------------------------
	public TipoProducto adicionarTipoProducto(String pNombreTipo, String pNombreCategoria)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlTipoProducto.agregarTipoDeProducto(manager, pNombreTipo, pNombreCategoria );
			t.commit();
			Log.trace("Inserccion tipo producto: "+ pNombreTipo +": "+tuplasInsertadas+ " tuplas insertadas");
			return new TipoProducto(pNombreTipo, pNombreCategoria);
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public TipoProducto buscarTipoNombre(String pNombre)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		TipoProducto tipo = null;
		try 
		{
			t.begin();
			tipo = sqlTipoProducto.buscarNombre(manager, pNombre);
			t.commit();
			Log.trace("Buscar tipo de producto por nombre: "+ tipo);
			return tipo;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<TipoProducto> buscarTiposProductos()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<TipoProducto> q = sqlTipoProducto.buscarTipos(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarTipo( String pTipo)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlTipoProducto.eliminarTipo(manager, pTipo);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}

	//------------------------------------------------------------------
	//  Metodos para manejar EMPRESA
	//------------------------------------------------------------------
	public Empresa adicionarEmpresa(String pDireccion, int pNit, String pCorreo)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlEmpresa.agregarEmpresa(manager,pDireccion, pNit, pCorreo );
			t.commit();
			Log.trace("Inserccion empresa: "+ pNit +": "+tuplasInsertadas+ " tuplas insertadas");
			return new Empresa(pNit,pDireccion);
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Empresa> buscarEmpresas()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Empresa> q = sqlEmpresa.buscarEmpresas(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarEmpresa(String pCorreo)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlEmpresa.eliminarEmpresa(manager, pCorreo);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}

	//------------------------------------------------------------------
	//  Metodos para manejar PERSONA_NATURAL
	//------------------------------------------------------------------
	public PersonaNatural adicionarPersonaNatural(String pDocumento, String pCorreo)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlPersonaNatural.agregarPersonaNatural(manager,pDocumento, pCorreo );
			t.commit();
			Log.trace("Inserccion X: "+ pDocumento +": "+tuplasInsertadas+ " tuplas insertadas");
			return new PersonaNatural(pDocumento);
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<PersonaNatural> buscarPersonaNatural()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<PersonaNatural> q = sqlPersonaNatural.buscarPersonas(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarPersonaNatural( String pCorreo)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlPersonaNatural.eliminarPersona(manager, pCorreo);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	
	//------------------------------------------------------------------
	//  Metodos para manejar BODEGA
	//------------------------------------------------------------------
	public Bodega adicionarBodega(String pTipo, double pVolumen, double pPeso, String pDireccionBodega, String pDireccionSucursal, String pCiudad)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlBodega.agregarBodega(manager, pTipo,pVolumen,pPeso,pDireccionBodega,pDireccionSucursal,pCiudad);
			t.commit();
			Log.trace("Inserccion X: "+ pDireccionBodega+", "+pDireccionSucursal+","+pCiudad+": "+tuplasInsertadas+ " tuplas insertadas");
			return new Bodega(pDireccionBodega,pTipo,pPeso,pVolumen, pDireccionSucursal, pCiudad);
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Indice> calcularIndicesOcupacionBodegas(String pCiudad, String pDireccionSucursal)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		List<Indice> indices = sqlBodega.calcularIndices(manager, pCiudad, pDireccionSucursal);
		return indices;
	}
	public Bodega buscarBodegaTipo(String pCategoria)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			Bodega bodega = sqlBodega.buscarBodegaTipo(manager, pCategoria );
			t.commit();
			return bodega;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Bodega> buscarBodegas()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Bodega> q = sqlBodega.buscarBodegas(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Bodega> buscarBodegasSucursal(String pDireccion, String pCiudad)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Bodega> q = sqlBodega.buscarBodegasSucursal(manager, pDireccion, pCiudad);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarBodega(String direccionBodega, String direccionSucursal,String ciudad)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlBodega.eliminarBodega(manager, direccionBodega, direccionSucursal, ciudad);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}

	//---------------------------------------------------------------------
	// Métodos para manejar las CANTIDADES EN BODEGA
	//---------------------------------------------------------------------
	public CantidadEnBodega adicionarCantidadEnBodega( String pDireccionBodega, String pDireccionSucursal, String pCiudad, int pCantidadActual, int pCantidadMinima, String pCodigoBarras)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCantidadEnBodega.adicionarCantidadEnBodega(pm, pDireccionBodega, pDireccionSucursal, pCiudad, pCantidadActual, pCantidadMinima, pCodigoBarras);
			tx.commit();

			Log.trace("Insercción cantidad en bodega: " + pDireccionBodega +": "+tuplasInsertadas);
			return new CantidadEnBodega();
		}
		catch(Exception e)
		{
			Log.error("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	public List<CantidadEnBodega> buscarCantidadEnBodega()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<CantidadEnBodega> q = sqlCantidadEnBodega.buscarCantidadEnBodega(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarCantidadEnBodega(String pCodigo, String pDireccionSucursal, String pDireccionBodega, String pCiudad)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlCantidadEnBodega.eliminarCantidadEnBodega(manager,pCodigo, pDireccionSucursal, pDireccionBodega, pCiudad);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public void disminuirCantidadEnBodega(int pCantidad, Producto producto, String pDireccionSucursal, String pCiudad, String pDireccionBodega)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			sqlCantidadEnBodega.disminuirInventario(pm, pCantidad, pCiudad, pDireccionSucursal, pDireccionBodega);
			t.commit();
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}

	//------------------------------------------------------------------
	//  Metodos para manejar ESTANTE
	//------------------------------------------------------------------
	public Estante adicionarEstante(String pTipoEstante,double pVolumen, long pId, double pPeso, double pNivelAbastecimiento, String pDireccionSucursal, String pCiudad)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlEstante.agregarEstante(manager, pTipoEstante, pVolumen, pId, pPeso, pNivelAbastecimiento, pDireccionSucursal, pCiudad);
			t.commit();
			Log.trace("Inserccion estante: "+ pId +": "+tuplasInsertadas+ " tuplas insertadas");
			return new Estante();
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Estante> buscarEstantes()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Estante> q = sqlEstante.buscarEstantes(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarEstante(long id)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlEstante.eliminarEstante(manager,id);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Indice> calcularIndicesOcupacionEstantes(String pCiudad, String pDireccionSucursal)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		List<Indice> indices = sqlEstante.calcularIndices(manager, pCiudad, pDireccionSucursal);
		return indices;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar PEDIDO PRODUCTO
	//------------------------------------------------------------------
	public PedidoProducto adicionarPedidoProducto(String pCodigoBarras, long pIdPedido, double pCantidadProducto, double pPrecioProducto)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlPedidoProducto.agregarPedidoProducto(manager, pCodigoBarras, pIdPedido, pCantidadProducto, pPrecioProducto);
			t.commit();
			Log.trace("Inserccion pedido producto: "+ pCodigoBarras+", "+pIdPedido+": "+tuplasInsertadas+ " tuplas insertadas");
			return new PedidoProducto(pCodigoBarras, pIdPedido, pCantidadProducto, pPrecioProducto);
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<PedidoProducto> buscarPedidoProducto()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<PedidoProducto> q = sqlPedidoProducto.buscarPedidoProducto(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarPedidoProducto(long id)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlPedidoProducto.eliminarPedidoProducto(manager, id);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}

	//------------------------------------------------------------------
	//  Metodos para manejar COMPRADOS
	//------------------------------------------------------------------
	public Comprados adicionarComprados(String pCodigoBarras,int pCantidad, double pPrecioTotal, long pIdFactura)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlComprados.agregarComprados(manager, pCodigoBarras, pCantidad, pPrecioTotal, pIdFactura);
			t.commit();
			Log.trace("Inserccion comprados: "+pCodigoBarras+", "+pIdFactura +": "+tuplasInsertadas+ " tuplas insertadas");
			return new Comprados(pCodigoBarras, pCantidad, pPrecioTotal, pIdFactura);
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public List<Comprados> buscarComprados()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Comprados> q = sqlComprados.buscarComprados(manager);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}
	public long eliminarComprados(String pCodigo, long idFactura)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long q = sqlComprados.eliminarComprados(manager, pCodigo, idFactura);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}

	//------------------------------------------------------------------
	//  Metodos para manejar CANTIDAD EN BODEGA
	//------------------------------------------------------------------
	public int buscarCantidadActualBodega(String pDireccionBodega, String pDireccionSucursal, String pCiudad)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			int q = sqlCantidadEnBodega.buscarCantidadActual(manager,pDireccionBodega,pDireccionSucursal,pCiudad);
			t.commit();
			return q;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			manager.close();
		}
	}

	//------------------------------------------------------------------
	//  Metodos para manejar CARRITO
	//------------------------------------------------------------------
	/**
	 * 
	 * @param direccionSucursal
	 * @param ciudad
	 * @param correoCliente
	 * @return
	 */
	public Carrito adicionarCarrito(String direccionSucursal, String ciudad, String correoCliente)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			long idCarrito =  nextvalCarritos();
			long tuplasInsertadas = sqlCarrito.adicionarCarrito(pm, idCarrito, direccionSucursal, ciudad, correoCliente);
			t.commit();

			Log.trace("Inserción carrito : " + idCarrito + ": "+ tuplasInsertadas);
			return new Carrito(direccionSucursal, ciudad, idCarrito, correoCliente);
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}

	/**
	 * 
	 * @return
	 */
	public List<Carrito> darCarritos()
	{
		return sqlCarrito.darCarritos(managerFactory.getPersistenceManager());
	}
	
	/**
	 * 
	 * @param idCarrito
	 * @return
	 */
	public Carrito darCarritoPorId(long idCarrito)
	{
		return sqlCarrito.darCarritoPorId(managerFactory.getPersistenceManager(), idCarrito);
	}
	
	/**
	 * 
	 * @param correoCliente
	 * @return
	 */
	public Carrito darCarritoPorCorreoCliente(String correoCliente)
	{
		return sqlCarrito.darCarritoPorCorreoCliente(managerFactory.getPersistenceManager(), correoCliente);
	}
	
	/**
	 * 
	 * @param pm
	 * @param idCarrito
	 * @return
	 */
	public long[] eliminarCarritoPorId(long idCarrito)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			long[] tuplasEliminadas = sqlCarrito.eliminarCarritoPorId(pm, idCarrito);
			t.commit();
			
			Log.trace("Eliminación carrito : " + idCarrito + ": "+ tuplasEliminadas);
			return tuplasEliminadas;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return new long[] {-1, -1};
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}


	//------------------------------------------------------------------
	//  Metodos para manejar PRODUCTOS CARRITO
	//------------------------------------------------------------------


	public ProductosCarrito adicionarProductoAlCarrito(String pCodigo, long idCarrito, int pCantidad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlProductosCarrito.adicionarProductoCarrito(pm, pCodigo, idCarrito, pCantidad);
			ProductosCarrito nuevo = new ProductosCarrito(idCarrito, pCodigo, pCantidad);
			t.commit();

			Log.trace("Inserción producto al carrito : " + idCarrito + ": "+ tuplasInsertadas);
			return nuevo;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return null;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}

	public long eliminarProductoDelCarrito(String pCodigo, long idCarrito)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlProductosCarrito.eliminarProductoCarrito(pm, pCodigo, idCarrito);
			t.commit();

			Log.trace("Eliminar producto del carrito : " + idCarrito + ": "+ tuplasInsertadas);
			return tuplasInsertadas;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}
	
	public long eliminarProductoDelCarrito(long idCarrito)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlProductosCarrito.eliminarProductosCarrito(pm, idCarrito);
			t.commit();

			Log.trace("Eliminar producto del carrito : " + idCarrito + ": "+ tuplasInsertadas);
			return tuplasInsertadas;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return 0;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}
	
	public long eliminarCantidadProductoDelCarrito(String pCodigo, long idCarrito, int pCantidad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction t = pm.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlProductosCarrito.eliminarCantidadProductoCarrito(pm, pCodigo, idCarrito, pCantidad);
			t.commit();

			Log.trace("Eliminar cantidad de un producto del carrito : " + idCarrito + ": "+ tuplasInsertadas);
			return tuplasInsertadas;
		}
		catch(Exception e)
		{
			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
			return -1;
		}
		finally
		{
			if (t.isActive())
			{
				t.rollback();
			}
			pm.close();
		}
	}
	
	public List<Object[]> buscarProductosCarrito(long idCarrito)
	{
		List<Object[]> respuesta = new LinkedList<Object[]>();
		List<Object> tuplas = sqlProductosCarrito.darProductosCarritoPorId(managerFactory.getPersistenceManager(), idCarrito);
		for(Object tupla: tuplas)
		{
			Object[] datos = (Object[]) tupla;
			int cantidad = ((BigDecimal) datos [0]).intValue ();
			String codBarras = (String) datos [1];
			
			Object[] resp = new Object[1];
			resp[0] = new ProductosCarrito(idCarrito, codBarras, cantidad);
			
			respuesta.add(resp);
		}
		
		return respuesta;
	}
	//------------------------------------------------------------------
	//  Metodos de ADMINISTRACION
	//------------------------------------------------------------------
	/**
	 * 
	 * @return
	 */
	public long [] limpiarSuperAndes()
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long [] resp = sqlUtil.limpiarSuperAndes(pm);
			tx.commit ();

			Log.info ("Borrada la base de datos");
			return resp;
		}
		catch (Exception e)
		{
			Log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}
}
