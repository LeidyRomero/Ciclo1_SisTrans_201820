package uniandes.isis2304.superAndes.persistencia;

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
	private SQLProductosOfrecidos sqlProductosOfrecidos;
	private SQLProductoCategoria sqlProductoCategoria ;
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
		tablas.add("PROMOCION");
		tablas.add("PROVEEDOR");
		tablas.add("PROVEEN");
		tablas.add("SUCURSAL");
		tablas.add("FACTURAS");
		tablas.add("TIPO_PRODUCTO");
		tablas.add("PRODUCTO_CATEGORIA");
		tablas.add("PRODUCTOS_OFRECIDOS");
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
		sqlPromocion = new SQLPromocion(this);
		sqlProveedor = new SQLProveedor(this);
		sqlProveen = new SQLProveen(this);
		sqlSucursal = new SQLSucursal(this);
		sqlSucursalFactura = new SQLSucursalFactura(this);
		sqlTipoProducto = new SQLTipoProducto(this);
		sqlProductoCategoria = new SQLProductoCategoria(this);
		sqlProductosOfrecidos = new SQLProductosOfrecidos(this);
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

	public String getSqlPromocion() {
		return tablas.get(15);
	}

	public String getSqlProveedor() {
		return tablas.get(16);
	}

	public String getSqlProveen() {
		return tablas.get(17);
	}

	public String getSqlSucursal() {
		return tablas.get(18);
	}

	public String getSqlSucursalFactura() {
		return tablas.get(19);
	}

	public String getSqlTipoProducto() {
		return tablas.get(20);
	}

	public String getSqlProductoCategoria() {
		return tablas.get(21);
	}

	public String getSqlProductosOfrecidos() {
		return tablas.get(22);
	}

	/**
	 * Transacción para el generador de secuencia de SuperAndes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de SuperAndes
	 */
	private long nextval ()
	{
		long resp = sqlUtil.nextval (managerFactory.getPersistenceManager());
		Log.trace ("Generando secuencia: " + resp);
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
			System.out.println("Pre sql");
			long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm, nitProveedor, nombreProveedor);
			System.out.println("Post sql");
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

	//---------------------------------------------------------------------
	// Métodos para manejar las SUCURSALES
	//---------------------------------------------------------------------
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

	//---------------------------------------------------------------------
	// Métodos para manejar las PROMOCIONES
	//---------------------------------------------------------------------
	public Promocion adicionarPromocion(Timestamp fechaInicio, Timestamp fechaFin, String descripcion, int codBarras, int uniDisponibles, int uniVendidas)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idPromocion = nextval();
			long tuplasInsertadas = sqlPromocion.adicionarPromocion(pm, fechaInicio, fechaFin, descripcion, codBarras, idPromocion, uniVendidas, uniDisponibles);
			tx.commit();

			Log.trace("Insercción promocion: " + idPromocion +": "+tuplasInsertadas);
			return new Promocion(fechaInicio, fechaFin, descripcion, idPromocion, codBarras, uniVendidas, uniDisponibles);
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
	// Métodos para manejar las ORDENES DE PEDIDO
	//---------------------------------------------------------------------
	public OrdenPedido adicionarOrdenPedido(Timestamp fechaEsperada, int nitProveedor, String ciudad, String direccionSucursal, String direccionBodega)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idPedido = nextval();
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

	//---------------------------------------------------------------------
	// Métodos para manejar las FACTURAS
	//---------------------------------------------------------------------
	public Factura adicionarFactura(double costoTotal, Timestamp fecha)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long idFactura = nextval();
			long tuplasInsertadas = sqlFactura.adicionarFactura(pm, idFactura, costoTotal, fecha);
			tx.commit();

			Log.trace("Insercción factura: " + idFactura+": "+tuplasInsertadas);
			return new Factura(idFactura, fecha, costoTotal);
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
	// Métodos para manejar las CANTIDADES EN ESTANTE
	//---------------------------------------------------------------------
	public CantidadEnEstantes adicionarCantidadEnEstante(int codigoBarras, long idEstante, int cantidadActual, int cantidadMinima)
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

	//---------------------------------------------------------------------
	// Métodos para manejar las PRODUCTOS OFRECIDOS
	//---------------------------------------------------------------------
	public ProductosOfrecidos adicionarProductosOfrecidos(int codigoBarras, String direccionSucursal, String ciudad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProductosOfrecidos.adicionarProductoOfrecido(pm, codigoBarras, direccionSucursal, ciudad);
			tx.commit();

			Log.trace("Insercción productos ofrecido: " + direccionSucursal + ", "+ ciudad +", "+ codigoBarras +": "+tuplasInsertadas);
			return new ProductosOfrecidos(direccionSucursal, ciudad, codigoBarras);
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
	// Métodos para manejar las SUCURSAL FACTURAS
	//---------------------------------------------------------------------
	public SucursalFactura adicionarSucursalFactura(long idFactura, String direccion, String ciudad)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlSucursalFactura.adicionarSucursalFactura(pm, idFactura, direccion, ciudad);
			tx.commit();

			Log.trace("Insercción facturas sucursal: " + direccion + ", "+ ciudad +", "+ idFactura +": "+tuplasInsertadas);
			return new SucursalFactura(direccion, ciudad, idFactura);
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
	// Métodos para manejar los HISTORIALES COMPRAS
	//---------------------------------------------------------------------
	public HistorialCompras adicionarHistorialCompra(String correo, long idFactura)
	{
		PersistenceManager pm = managerFactory.getPersistenceManager();
		Transaction tx = pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlHistorialCompras.adicionarHistorialCompras(pm, correo, idFactura);
			tx.commit();

			Log.trace("Insercción historial compra: " + correo +", "+ idFactura +": "+tuplasInsertadas);
			return new HistorialCompras(correo, idFactura);
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
	// Métodos para manejar PROVEEN
	//---------------------------------------------------------------------
	public Proveen adicionarProveen(int nitProveedor, int codigoBarras)
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
	
	//------------------------------------------------------------------
	//  Metodos para manejar PRODUCTOS
	//------------------------------------------------------------------
	public Producto adicionarProducto(String pNombre, String pMarca, String pPresentacion, String pUnidadMedida, String pEspecificacionEmpacado, String pCalidad, double pPrecioUnitario, double pPrecioUnidadMedida, int pCantidadPresentacion, int pCodigoBarras, Date pFechaVencimiento)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlProducto.agregarProducto(manager, pNombre, pMarca, pPresentacion, pUnidadMedida, pEspecificacionEmpacado, pCalidad, pPrecioUnitario, pPrecioUnidadMedida, pCantidadPresentacion, pCodigoBarras, pFechaVencimiento);
			t.commit();
			Log.trace("Inserccion producto: "+ pNombre+": "+tuplasInsertadas+ " tuplas insertadas");
			return new Producto(pNombre, pMarca, pPresentacion, pUnidadMedida, pEspecificacionEmpacado, pCalidad, pPrecioUnitario, pPrecioUnidadMedida, pCantidadPresentacion, pCodigoBarras, pFechaVencimiento);
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
	//------------------------------------------------------------------
	//  Metodos para manejar PRODUCTO_CATEGORIA
	//------------------------------------------------------------------
	public ProductoCategoria adicionarProductoCategoria(String pNombreCategoria, int pCodigoBarras)
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			long tuplasInsertadas = sqlProductoCategoria.agregarProductoCategoria(manager, pNombreCategoria, pCodigoBarras);
			t.commit();
			Log.trace("Inserccion producto categoria: "+ pCodigoBarras+": "+tuplasInsertadas+ " tuplas insertadas");
			return new ProductoCategoria(pNombreCategoria,pCodigoBarras);
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
			return new TipoProducto(pNombreTipo);
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
			return new Bodega(pDireccionBodega,pTipo,pPeso,pVolumen);
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
	public List<Double> buscarIndiceBodega()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Double> q = sqlBodega.buscarIndice(manager);
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
	public List<Double> buscarIndiceEstante()
	{
		PersistenceManager manager = managerFactory.getPersistenceManager();
		Transaction t = manager.currentTransaction();
		try 
		{
			t.begin();
			List<Double> q = sqlEstante.buscarIndice(manager);
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
	//------------------------------------------------------------------
	//  Metodos para manejar PEDIDO PRODUCTO
	//------------------------------------------------------------------
	public PedidoProducto adicionarPedidoProducto(int pCodigoBarras, long pIdPedido, double pCantidadProducto, double pPrecioProducto)
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
	//------------------------------------------------------------------
	//  Metodos para manejar COMPRADOS
	//------------------------------------------------------------------
	public Comprados adicionarComprados(int pCodigoBarras,int pCantidad, double pPrecioTotal, String pIdFactura)
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


	//BORRADOR
	//	public X adicionarX()
	//	{
	//		PersistenceManager manager = managerFactory.getPersistenceManager();
	//		Transaction t = manager.currentTransaction();
	//		try 
	//		{
	//			t.begin();
	//			long tuplasInsertadas = sqlX.agregarX(manager, );
	//			t.commit();
	//			Log.trace("Inserccion X: "+ +": "+tuplasInsertadas+ " tuplas insertadas");
	//			return new X();
	//		}
	//		catch(Exception e)
	//		{
	//			Log.error("Exception: "+e.getMessage()+ "\n"+ darDetalleException(e));
	//			return null;
	//		}
	//		finally
	//		{
	//			if (t.isActive())
	//			{
	//				t.rollback();
	//			}
	//			manager.close();
	//		}
	//	}

}
