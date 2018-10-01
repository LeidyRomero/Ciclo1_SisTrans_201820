package uniandes.isis2304.superAndes.persistencia;

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
	private SQLProductoCategoria sqlProductoCategoria ;
	private SQLProductosEnPromocion sqlProductosEnPromocion ;
	private SQLPromocion sqlPromocion;
	private SQLProveedor sqlProveedor;
	private SQLProveen sqlProveen;
	private SQLSucursal sqlSucursal;
	private SQLSucursalCliente sqlSucursalCliente;
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
		tablas.add("SUCURSAL_CLIENTE");
		tablas.add("TIPO_PRODUCTO");
		tablas.add("PRODUCTO_CATEGORIA");
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
		sqlSucursalCliente = new SQLSucursalCliente(this);
		sqlTipoProducto = new SQLTipoProducto(this);
		sqlProductoCategoria = new SQLProductoCategoria(this);
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

	public String getSqlSucursalCliente() {
		return tablas.get(20);
	}

	public String getSqlTipoProducto() {
		return tablas.get(21);
	}
	public String getSqlProductoCategoria() {
		return tablas.get(22);
	}
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
