package uniandes.isis2304.superAndes.negocio;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class SuperAndes extends SwingWorker<Boolean, List<Object[]>[]>{
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger Log = Logger.getLogger(SuperAndes.class.getName());
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * El manejador de persistencia
	 */
	private PersistenciaSuperAndes pp;

	/**
	 * 
	 */
	private ActualizacionListasListener oidorCarrito;

	/**
	 * Carrito actual de la apliaci�n
	 */
	private Carrito carrito;

	/**
	 * Fecha de la �ltima transacci�n del carrito
	 */
	private long ultimaTransaccion;
	
	/**
	 * Representa si se termin� de usar el carrito, ya sea si se abandon� o 
	 */
	private boolean termino;
	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * El constructor que recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes (JsonObject tableConfig)
	{
		pp = PersistenciaSuperAndes.getInstance (tableConfig);
		pp.finalizarPromocionesPendientes();
	}

	public void setOidor(ActualizacionListasListener oidorCarrito)
	{
		this.oidorCarrito = oidorCarrito;
	}
	//-----------------------------------------------------------------------------
	//   Metodos para manejar los PRODUCTOS
	//-----------------------------------------------------------------------------
	public Producto adicionarProducto(int pCantidadMinima,String pNombre, String pMarca, String pPresentacion, String pUnidadMedida, String pCalidad, double pPrecioUnitario, double pPrecioUnidadMedida, int pCantidadPresentacion, String pCodigoBarras, Timestamp pFechaVencimiento, String pPeso, String pVolumen, String pCategoria)
	{
		Log.info("Adicionando el producto "+pNombre);
		Producto producto = pp.adicionarProducto(pCantidadMinima,pNombre, pMarca, pPresentacion, pUnidadMedida, pCalidad, pPrecioUnitario, pPrecioUnidadMedida, pCantidadPresentacion, pCodigoBarras, pFechaVencimiento, pPeso, pVolumen, pCategoria);
		Log.info("Saliendo de adicionar el producto "+ pNombre);
		return producto;
	}
	public List<VOProducto> darVOProducto()
	{
		Log.info ("Generando los VO de Producto");
		List<VOProducto> voProducto = new LinkedList<VOProducto> ();
		for (Producto producto: pp.buscarProductos())
		{
			voProducto.add (producto);
		}
		Log.info ("Generando los VO de Producto: " + voProducto.size () + " productos existentes");
		return voProducto;
	}
	public long eliminarProducto(String pCodigo)
	{
		Log.info("Borrar el producto ");
		long numero = pp.eliminarProducto(pCodigo);
		Log.info("Saliendo de borrar el producto ");
		return numero;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar CATEGORIA
	//------------------------------------------------------------------
	public Categoria adicionarCategoria(String pNombre)
	{
		Log.info("Adicionando la categoria "+pNombre);
		Categoria categoria = pp.adicionarCategoria(pNombre);
		Log.info("Saliendo de adicionar la categoria "+ pNombre);
		return categoria;
	}
	public List<VOCategoria> darVOCategoria()
	{
		Log.info ("Generando los VO de Categoria");
		List<VOCategoria> voCategoria = new LinkedList<VOCategoria> ();
		for (Categoria categoria: pp.buscarCategorias())
		{
			voCategoria.add (categoria);
		}
		Log.info ("Generando los VO de Categoria: " + voCategoria.size () + " categorias existentes");
		return voCategoria;
	}
	public long eliminarCategoria(String pCategoria)
	{
		Log.info("Borrar la categoria ");
		long numero = pp.eliminarCategoria(pCategoria);
		Log.info("Saliendo de borrar la categoria ");
		return numero;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar TIPO_PRODUCTO
	//------------------------------------------------------------------
	public TipoProducto adicionarTipoProducto(String pNombreTipo, String pNombreCategoria)
	{

		Log.info("Adicionando el tipo de producto "+pNombreTipo);
		TipoProducto tipoProducto = pp.adicionarTipoProducto(pNombreTipo, pNombreCategoria);
		Log.info("Saliendo de adicionar el producto "+ pNombreTipo);
		return tipoProducto;
	}
	public List<VOTipoProducto> darVOTipoProducto()
	{
		Log.info ("Generando los VO de Tipo de producto");
		List<VOTipoProducto> voTipoProducto = new LinkedList<VOTipoProducto> ();
		for (TipoProducto tipoProducto: pp.buscarTiposProductos())
		{
			voTipoProducto.add (tipoProducto);
		}
		Log.info ("Generando los VO de Tipo de producto: " + voTipoProducto.size () + " tipos existentes");
		return voTipoProducto;
	}
	public long eliminarTipoProducto( String pTipo)
	{
		Log.info("Borrar el tipo ");
		long numero = pp.eliminarTipo(pTipo);
		Log.info("Saliendo de borrar el tipo ");
		return numero;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar EMPRESA
	//------------------------------------------------------------------
	public Empresa adicionarEmpresa(String pDireccion, int pNit, String pCorreo)
	{
		Log.info("Adicionando la empresa "+pNit);
		Empresa empresa = pp.adicionarEmpresa(pDireccion, pNit, pCorreo);
		Log.info("Saliendo de adicionar la empresa "+ pNit);
		return empresa;
	}
	public List<VOEmpresa> darVOEmpresa()
	{
		Log.info ("Generando los VO de Empresa");
		List<VOEmpresa> voEmpresa = new LinkedList<VOEmpresa> ();
		for (Empresa empresa: pp.buscarEmpresas())
		{
			voEmpresa.add (empresa);
		}
		Log.info ("Generando los VO de Empresa: " + voEmpresa.size () + " Empresa existentes");
		return voEmpresa;
	}
	public long eliminarEmpresa(String pCorreo)
	{
		Log.info("Borrar la empresa");
		long numero = pp.eliminarEmpresa(pCorreo);
		Log.info("Saliendo de borrar la empresa ");
		return numero;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar PERSONA NATURAL
	//------------------------------------------------------------------
	public PersonaNatural adicionarPersonaNatural(String pDocumento, String pCorreo)
	{
		Log.info("Adicionando la persona "+pDocumento);
		PersonaNatural persona = pp.adicionarPersonaNatural(pDocumento, pCorreo);
		Log.info("Saliendo de adicionar la persona "+ pDocumento);
		return persona;
	}
	public List<VOPersonaNatural> darVOPersonaNatural()
	{
		Log.info ("Generando los VO de Persona natural");
		List<VOPersonaNatural> voPersonaNatural = new LinkedList<VOPersonaNatural> ();
		for (PersonaNatural personaNatural: pp.buscarPersonaNatural())
		{
			voPersonaNatural.add (personaNatural);
		}
		Log.info ("Generando los VO de PersonaNatural: " + voPersonaNatural.size () + " personas existentes");
		return voPersonaNatural;
	}
	public long eliminarPersonaNatural(String pCorreo)
	{
		Log.info("Borrar la persona");
		long numero = pp.eliminarPersonaNatural(pCorreo);
		Log.info("Saliendo de borrar la persona ");
		return numero;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar BODEGA
	//------------------------------------------------------------------
	public Bodega adicionarBodega(String pTipo, double pVolumen, double pPeso, String pDireccionBodega, String pDireccionSucursal, String pCiudad)
	{
		Log.info("Adicionando la bodega "+ pDireccionBodega+", "+pDireccionSucursal+","+pCiudad);
		Bodega bodega = pp.adicionarBodega(pTipo, pVolumen, pPeso, pDireccionBodega, pDireccionSucursal, pCiudad);
		Log.info("Saliendo de adicionar la bodega "+ pDireccionBodega+", "+pDireccionSucursal+","+pCiudad);
		return bodega;
	}

	public String buscarIndiceBodega(String pDireccionSucursal, String pCiudadSucursal)
	{
		String mensaje = "";
		Log.info("Iniciando calculo de indice de bodega ");
		List<Indice> indicesBodegas = pp.calcularIndicesOcupacionBodegas(pCiudadSucursal,pDireccionSucursal);

		//TODO como manejar objects
		for(int i = 0;i<indicesBodegas.size();i++)
		{
			mensaje+="El indice de ocupaci�nde la bodega con direcci�n"+indicesBodegas.get(i)+", de la ciudad"+pCiudadSucursal+"es: "+indicesBodegas+"\n";
		}
		Log.info("Saliendo de calculo de indice de bodega ");
		return mensaje;
	}
	public List<VOBodega> darVOBodegas ()
	{
		Log.info ("Generando los VO de Bodegas");
		List<VOBodega> voBodega = new LinkedList<VOBodega> ();
		for (Bodega bodega: pp.buscarBodegas())
		{
			voBodega.add (bodega);
		}
		Log.info ("Generando los VO de Bodegas: " + voBodega.size () + " bodegas existentes");
		return voBodega;
	}
	public long eliminarBodega(String direccionBodega, String direccionSucursal,String ciudad)
	{
		Log.info("Borrar la bodega "+ direccionBodega+", "+direccionSucursal+","+ciudad);
		long numero = pp.eliminarBodega(direccionBodega, direccionSucursal, ciudad);
		Log.info("Saliendo de borrar la bodega "+ direccionBodega+", "+direccionSucursal+","+ciudad);
		return numero;
	}
	//---------------------------------------------------------------------
	// M�todos para manejar CANTIDAD EN BODEGA
	//---------------------------------------------------------------------
	public CantidadEnBodega adicionarCantidadEnBodega( String pDireccionBodega, String pDireccionSucursal, String pCiudad, int pCantidadActual, int pCantidadMinima, String pCodigoBarras)
	{
		Log.info("Adicionando cantidad enbodega "+ pDireccionBodega);
		CantidadEnBodega cantidadBodega = pp.adicionarCantidadEnBodega(pDireccionBodega, pDireccionSucursal, pCiudad, pCantidadActual, pCantidadMinima, pCodigoBarras);
		Log.info("Saliendo de adicionar cantidad en bodega "+ pDireccionBodega);
		return cantidadBodega;
	}
	public List<VOCantidadEnBodega> darVOCantidadEnBodega ()
	{
		Log.info ("Generando los VO de Cantidad en bodegas");
		List<VOCantidadEnBodega> voCantidadEnBodega = new LinkedList<VOCantidadEnBodega> ();
		for (CantidadEnBodega cantidadEnBodega: pp.buscarCantidadEnBodega())
		{
			voCantidadEnBodega.add (cantidadEnBodega);
		}
		Log.info ("Generando los VO de Bodegas: " + voCantidadEnBodega.size () + " cantidad en bodegas existentes");
		return voCantidadEnBodega;
	}
	public long eliminarCantidadEnBodega(String pCodigo, String pDireccionSucursal, String pDireccionBodega, String pCiudad)
	{
		Log.info("Borrar la cantidad en bodega ");
		long numero = pp.eliminarCantidadEnBodega(pCodigo, pDireccionSucursal, pDireccionBodega, pCiudad);
		Log.info("Saliendo de borrar la cantidad en bodega ");
		return numero;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar ESTANTE
	//------------------------------------------------------------------
	public Estante adicionarEstante(String pTipoEstante,double pVolumen, long pId, double pPeso, double pNivelAbastecimiento, String pDireccionSucursal, String pCiudad)
	{
		Log.info("Adicionando el estante "+ pId);
		Estante estante = pp.adicionarEstante(pTipoEstante, pVolumen, pId, pPeso, pNivelAbastecimiento, pDireccionSucursal, pCiudad);
		Log.info("Saliendo de adicionar el estante "+ pId);
		return estante;
	}

	public String buscarIndiceEstante(String pDireccion, String pCiudad)
	{
		String mensaje = "Para la sucursal con direcci�n: "+pDireccion+" de la ciudad: "+pCiudad;
		Log.info("Iniciando calculo de indice de los estantes ");
		List<Indice> indicesEstantes = pp.calcularIndicesOcupacionEstantes(pCiudad,pDireccion);
		Log.info("Saliendo de calculo de indice de los estantes");

		//TODO como manejar objects
		for(int i = 0;i<indicesEstantes.size();i++)
		{
			mensaje+="El indice de ocupaci�n del estante de id: "+indicesEstantes.get(i)+"es: "+indicesEstantes+"\n";
		}

		return mensaje;
	}
	public List<VOEstante> darVOEstante()
	{
		Log.info ("Generando los VO de Estante");
		List<VOEstante> voEstante = new LinkedList<VOEstante> ();
		for (Estante estante: pp.buscarEstantes())
		{
			voEstante.add (estante);
		}
		Log.info ("Generando los VO de Estante: " + voEstante.size () + " estantes existentes");
		return voEstante;
	}
	public long eliminarEstante(long id)
	{
		Log.info("Borrar el Estante ");
		long numero = pp.eliminarEstante(id);
		Log.info("Saliendo de borrar el Estante ");
		return numero;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar PEDIDO PRODUCTO
	//------------------------------------------------------------------
	public PedidoProducto adicionarPedidoProducto(String pCodigoBarras, long pIdPedido, double pCantidadProducto, double pPrecioProducto)
	{
		Log.info("Adicionando el pedido producto "+ pCodigoBarras+", "+pIdPedido);
		PedidoProducto pedidoProducto = pp.adicionarPedidoProducto(pCodigoBarras, pIdPedido, pCantidadProducto, pPrecioProducto);
		Log.info("Saliendo de adicionar el pedido producto "+ pCodigoBarras+", "+pIdPedido);
		return pedidoProducto;
	}
	public List<VOPedidoProducto> darVOPedidoProducto()
	{
		Log.info ("Generando los VO de Pedido producto");
		List<VOPedidoProducto> voPedidoProducto = new LinkedList<VOPedidoProducto> ();
		for (PedidoProducto pedidoProducto: pp.buscarPedidoProducto())
		{
			voPedidoProducto.add (pedidoProducto);
		}
		Log.info ("Generando los VO de Pedido producto: " + voPedidoProducto.size () + " pedido producto existentes");
		return voPedidoProducto;
	}
	public long eliminarPedidoProducto(long id)
	{
		Log.info("Borrar el pedido producto");
		long numero = pp.eliminarPedidoProducto(id);
		Log.info("Saliendo de borrar el pedido producto ");
		return numero;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar COMPRADOS
	//------------------------------------------------------------------
	public Comprados adicionarComprados(String pCodigoBarras,int pCantidad, double pPrecioTotal, long pIdFactura)
	{
		Log.info("Adicionando comprados "+ pCodigoBarras+", "+pCodigoBarras+", "+pIdFactura);
		Comprados comprado = pp.adicionarComprados(pCodigoBarras, pCantidad, pPrecioTotal, pIdFactura);
		Log.info("Saliendo de adicionar comprados "+ pCodigoBarras+", "+pCodigoBarras+", "+pIdFactura);
		return comprado;
	}
	public List<VOComprados> darVOComprados()
	{
		Log.info ("Generando los VO de Comprados");
		List<VOComprados> voComprados = new LinkedList<VOComprados> ();
		for (Comprados comprados: pp.buscarComprados())
		{
			voComprados.add (comprados);
		}
		Log.info ("Generando los VO de Comprados: " + voComprados.size () + " compras existentes");
		return voComprados;
	}
	public long eliminarComprados(String pCodigo, long idFactura)
	{
		Log.info("Borrar las compras");
		long numero = pp.eliminarComprados(pCodigo, idFactura);
		Log.info("Saliendo de borrar las compras");
		return numero;
	}

	public Comprados registrarCompra(String pCodigoBarras,int pCantidad, String correo, String ciudad, String direccion)
	{
		Log.info("Registrando comprados "+ pCodigoBarras+", "+pCodigoBarras);
		Producto producto = pp.buscarProductoCodigoBarras(pCodigoBarras);
		Factura factura = pp.adicionarFactura(pCantidad*producto.getPrecioUnitario(), new Timestamp(System.currentTimeMillis()), correo, ciudad, direccion);
		Comprados comprado = pp.adicionarComprados(pCodigoBarras, pCantidad, pCantidad*producto.getPrecioUnitario(), factura.getIdFactura());

		//TODO Maria  actualizar inventario
		//pp.disminuirCantidadEnBodega();

		Log.info("Saliendo de registrar comprados "+ pCodigoBarras+", "+pCodigoBarras);
		return comprado;
	}
	//---------------------------------------------------------------------
	// M�todos para manejar CLIENTE
	//---------------------------------------------------------------------
	public Cliente adicionarCliente(String correo, String nombre)
	{
		Log.info("Adicionando cliente "+ correo+", "+nombre);
		Cliente cliente = pp.adicionarCliente(correo, nombre);
		Log.info("Saliendo de adicionar cliente "+ correo+", "+nombre);
		return cliente;
	}

	public List<VOCliente> darVOClientes ()
	{
		Log.info ("Generando los VO de Cliente");
		List<VOCliente> voCliente = new LinkedList<VOCliente> ();
		for (Cliente cliente : pp.buscarClientes())
		{
			voCliente.add (cliente);
		}
		Log.info ("Generando los VO de Cliente: " + voCliente.size () + " clientes existentes");
		return voCliente;
	}

	public long eliminarClientePorCorreo(String correo)
	{
		Log.info("Eliminando cliente "+ correo);
		long cliente = pp.eliminarClientePorCorreo(correo);
		Log.info("Saliendo de eliminar cliente "+ correo);
		return cliente;
	}
	//---------------------------------------------------------------------
	// M�todos para manejar SUCURSAL
	//---------------------------------------------------------------------
	public Sucursal adicionarSucursal(String tamanio, String direccion, String ciudad, String nombre)
	{
		Log.info("Adicionando sucursal "+ tamanio+", "+direccion+", "+ciudad+", "+nombre);
		Sucursal sucursal = pp.adicionarSucursal(tamanio, direccion, ciudad, nombre);
		Log.info("Saliendo de adicionar sucursal "+ tamanio+", "+direccion+", "+ciudad+", "+nombre);
		return sucursal;
	}

	public List<VOSucursal> darVOSucursales ()
	{
		Log.info ("Generando los VO de Sucursal");
		List<VOSucursal> voSucursal = new LinkedList<VOSucursal> ();
		for (Sucursal sucursal : pp.buscarSucursales())
		{
			voSucursal.add (sucursal);
		}
		Log.info ("Generando los VO de Sucursal: " + voSucursal.size () + " sucursales existentes");
		return voSucursal;
	}

	public long eliminarSucursal(String direccion, String ciudad)
	{
		Log.info("Eliminando sucursal "+ direccion+", "+ciudad);
		long sucursal = pp.eliminarSucursalPorDireccionYCiudad(direccion, ciudad);
		Log.info("Saliendo de eliminar sucursal "+ direccion+", "+ciudad);
		return sucursal;
	}
	//---------------------------------------------------------------------
	// M�todos para manejar PROMOCION
	//---------------------------------------------------------------------
	public Promocion adicionarPromocion(Timestamp fechaInicio, Timestamp fechaFin, String descripcion, String codBarras, int uniDisponibles, int uniVendidas, String direccionSucursal, String ciudad)
	{
		Log.info("Adicionando promocion "+ fechaInicio+", "+fechaFin+", "+descripcion+", "+codBarras+", "+uniDisponibles+", "+uniVendidas);
		Promocion promocion = pp.adicionarPromocion(fechaInicio, fechaFin, descripcion, codBarras, uniDisponibles, 0);
		pp.adicionarSucursalPromociones(promocion.getIdPromocion(), direccionSucursal, ciudad);
		Log.info("Saliendo de adicionar promocion "+ fechaInicio+", "+fechaFin+", "+descripcion+", "+codBarras+", "+uniDisponibles+", "+uniVendidas);
		return promocion;
	}

	public List<Promocion> darPromocionesMasPopulares()
	{
		Log.info("Consultando promociones mas populares ");
		List<Promocion> promociones = pp.darPromocionesMasPopulares();
		Log.info("Saliendo de consultar promociones mas populares ");
		return promociones;
	}

	public List<VOPromocion> darVOPromociones ()
	{
		Log.info ("Generando los VO de Promocion");
		List<VOPromocion> voPromocion = new LinkedList<VOPromocion> ();
		for (Promocion promocion : pp.darPromociones())
		{
			voPromocion.add (promocion);
		}
		Log.info ("Generando los VO de Promocion: " + voPromocion.size () + " sucursales existentes");
		return voPromocion;
	}

	public long eliminarPromocion(long idPromocion)
	{
		Log.info("Eliminando promocion "+ idPromocion);
		long promocion = pp.eliminarPromocionPorId(idPromocion);
		Log.info("Saliendo de eliminar promocion "+ idPromocion);
		return promocion;
	}

	public void finalizarPromocion(long idPromocion)
	{
		pp.finalizarPromocion(idPromocion);
	}
	//---------------------------------------------------------------------
	// M�todos para manejar ORDEN PEDIDO
	//---------------------------------------------------------------------
	public OrdenPedido adicionarOrdenPedido(Timestamp fechaEsperada, int nitProveedor, String ciudad, String direccionSucursal, String direccionBodega, String pCodigoBarras, int pCantidadProducto, double pPrecioProducto)
	{
		Log.info("Adicionando orden pedido "+ nitProveedor+", "+fechaEsperada+", "+ciudad+", "+direccionSucursal+", "+direccionBodega);
		OrdenPedido ordenPedido = pp.adicionarOrdenPedido(fechaEsperada, nitProveedor, ciudad, direccionSucursal, direccionBodega);
		pp.adicionarPedidoProducto(pCodigoBarras, ordenPedido.getIdPedido(), pCantidadProducto, pPrecioProducto);
		Log.info("Saliendo de adicionar orden pedido "+ nitProveedor+", "+fechaEsperada+", "+ciudad+", "+direccionSucursal+", "+direccionBodega);
		return ordenPedido;
	}

	public OrdenPedido llegadaOrdenPedido(long idPedido, String calificacion) throws Exception
	{
		Log.info("Modificando orden pedido "+ idPedido +", "+calificacion);
		OrdenPedido ordenPedido = pp.llegadaOrdenPedido(idPedido, calificacion);
		Log.info("Saliendo de modificar orden pedido "+ idPedido +", "+calificacion);
		return ordenPedido;
	}

	public List<VOOrdenPedido> darVOPedidos()
	{
		Log.info ("Generando los VO de Promocion");
		List<VOOrdenPedido> voPedido = new LinkedList<VOOrdenPedido> ();
		for (OrdenPedido pedido : pp.darPedidos())
		{
			voPedido.add (pedido);
		}
		Log.info ("Generando los VO de Pedido: " + voPedido.size () + " pedidos existentes");
		return voPedido;
	}

	public long eliminarPedido(long idPedido)
	{
		return pp.eliminarPedidoPorId(idPedido);
	}
	//---------------------------------------------------------------------
	// M�todos para manejar FACTURA
	//---------------------------------------------------------------------
	public Factura adicionarFactura(double costoTotal, Timestamp fecha, String correo, String ciudad, String direccion)
	{
		Log.info("Adicionando factura "+ costoTotal+", "+fecha);
		Factura factura = pp.adicionarFactura(costoTotal, fecha, correo, ciudad, direccion);
		Log.info("Saliendo de adicionar factura "+ costoTotal+", "+fecha);
		return factura;
	}
	public List<Object[]> darClientesFrecuentes(String ciudad, String direccion)
	{
		Log.info("Consultando clientes frecuentes: "+ciudad+", "+direccion);
		List<Object[]> clientes = pp.darClientesFrecuentes(direccion, ciudad);
		Log.info("Saliendo de consultar clientes frecuentes: "+ciudad+", "+direccion);
		return clientes;
	}
	//---------------------------------------------------------------------
	// M�todos para manejar PROVEEDOR
	//---------------------------------------------------------------------
	public Proveedor adicionarProveedor(int nitProveedor, String nombreProveedor)
	{
		Log.info("Adicionando proveedor "+ nitProveedor+", "+nombreProveedor);
		Proveedor proveedor = pp.adicionarProveedor(nitProveedor, nombreProveedor);
		Log.info("Saliendo de adicionar proveedor "+ nitProveedor+", "+nombreProveedor);
		return proveedor;
	}

	public List<VOProveedor> darVOProveedores ()
	{
		Log.info ("Generando los VO de Proveedor");
		List<VOProveedor> voProveedor = new LinkedList<VOProveedor> ();
		if(pp.buscarProveedores().size() > 0)
		{
			for (Proveedor proveedor: pp.buscarProveedores())
			{
				voProveedor.add (proveedor);
			}
		}
		Log.info ("Generando los VO de Proveedor: " + voProveedor.size () + " proveedores existentes");
		return voProveedor;
	}

	public long eliminarProveedorPorNit(int nitProveedor)
	{
		Log.info("Eliminando proveedor "+ nitProveedor);
		long proveedor = pp.eliminarProveedorPorNit(nitProveedor);
		Log.info("Saliendo de eliminar proveedor "+ nitProveedor);
		return proveedor;
	}

	public Proveedor darProveedorPorNit(int nitProveedor)
	{
		Log.info("Dar informaci�n de un proveedor por nit: "+nitProveedor);
		Proveedor proveedor = pp.darProveedorPorNit(nitProveedor);
		Log.info("Buscando proveedor por nit: " + proveedor != null ? proveedor : "NO EXISTE");
		return proveedor;
	}
	//---------------------------------------------------------------------
	// M�todos para manejar CANTIDAD EN ESTANTES
	//---------------------------------------------------------------------
	public CantidadEnEstantes adicionarCantidadEnEstantes(String codigoBarras, long idEstante, int cantidadActual, int cantidadMinima)
	{
		Log.info("Adicionando cantidad en estantes "+ codigoBarras+", "+idEstante+", "+cantidadActual+", "+cantidadMinima);
		CantidadEnEstantes cantidadEstantes = pp.adicionarCantidadEnEstante(codigoBarras, idEstante, cantidadActual, cantidadMinima);
		Log.info("Saliendo de adicionar cantidad en estantes "+ codigoBarras+", "+ idEstante+", "+cantidadActual+", "+cantidadMinima);
		return cantidadEstantes;
	}

	public List<Object[]> darCantidadEnEstantesReales(String ciudad, String direccionSucursal)
	{
		Log.info("Consultando cantidad en estantes: "+ ciudad+", "+direccionSucursal);
		List<Object[]> cantidadEstantes = pp.darCantidadEnEstantesReales(ciudad, direccionSucursal);
		Log.info("Saliendo de consultar cantidad en estantes: "+ ciudad+", "+direccionSucursal);
		return cantidadEstantes;
	}

	//---------------------------------------------------------------------
	// M�todos para manejar PRODUCTOS OFRECIDOS
	//---------------------------------------------------------------------
	public ProductosOfrecidos adicionarProductosOfrecidos(String codigoBarras, String direccionSucursal, String ciudad)
	{
		Log.info("Adicionando productos ofrecidos "+ codigoBarras+", "+direccionSucursal+", "+ciudad);
		ProductosOfrecidos productosOfrecidos = pp.adicionarProductosOfrecidos(codigoBarras, direccionSucursal, ciudad);
		Log.info("Saliendo de adicionar productos ofrecidos "+ codigoBarras+", "+direccionSucursal+", "+ciudad);
		return productosOfrecidos;
	}

	//---------------------------------------------------------------------
	// M�todos para manejar SUCURSAL FACTURAS
	//---------------------------------------------------------------------
	public List<Object[]> dineroSucursalEnRango(Timestamp fechaInicio, Timestamp fechaFin)
	{
		Log.info("Consultando el dinero recolectado por sucursales en periodo de tiempo: " + fechaInicio + ", "+fechaFin);
		List<Object[]> list = pp.dineroSucursalEnRango(fechaInicio, fechaFin);
		Log.info("Saliendo de consultar el dinero recolectado por sucursales en periodo de tiempo: " + fechaInicio + ", "+fechaFin);
		return list;
	}

	//---------------------------------------------------------------------
	// M�todos para manejar PROVEEN
	//---------------------------------------------------------------------
	public Proveen adicionarProveen(int nitProveedor, String codigoBarras)
	{
		Log.info("Adicionando proveen "+ nitProveedor+", "+codigoBarras);
		Proveen proveen = pp.adicionarProveen(nitProveedor, codigoBarras);
		Log.info("Saliendo de adicionar proveen "+ nitProveedor+", "+codigoBarras);
		return proveen;
	}

	//---------------------------------------------------------------------
	// M�todos para manejar CARRITO
	//---------------------------------------------------------------------
	public void disminuirProductosEnEstante(int pCantidad, Producto pProducto, long idEstante)
	{
		Log.info("Actualizando inventario"+ pCantidad+","+pProducto);
		pp.disminuirCantidadEnEstantes(pCantidad, pProducto, idEstante);
		Log.info("Saliendo de actualizar inventario"+ pCantidad+", "+pProducto);
	}
	public void aumentarProductosEnEstante(int pCantidad, Producto pProducto, long idEstante)
	{
		Log.info("Actualizando inventario"+ pCantidad+","+pProducto);
		pp.aumentarCantidadEnEstantes(pCantidad, pProducto, idEstante);
		Log.info("Saliendo de actualizar inventario"+ pCantidad+", "+pProducto);
	}

	public Carrito adicionarCarrito(String direccionSucursal, String ciudad, String correoCliente)
	{
		Log.info("Adicionando carrito: " + direccionSucursal +", "+ ciudad + ", "+ correoCliente);
		carrito  = pp.adicionarCarrito(direccionSucursal, ciudad, correoCliente);
		Log.info("Saliendo de adicionar carrito: " + direccionSucursal +", "+ ciudad + ", "+ correoCliente);

		List<Object[]>[] listas = new LinkedList[2];
		listas[0] = darCantidadEnEstantesReales(ciudad, direccionSucursal);
		listas[1] = buscarProductosCarritoPorId(carrito.getIdCarrito());
		publish(listas);

		ultimaTransaccion = System.currentTimeMillis();

		return carrito;
	}
	
	public Carrito agregarCarrito(String direccionSucursal, String ciudad, String correoCliente)
	{
		Log.info("Adicionando carrito: " + direccionSucursal +", "+ ciudad + ", "+ correoCliente);
		carrito  = pp.adicionarCarrito(direccionSucursal, ciudad, correoCliente);
		Log.info("Saliendo de adicionar carrito: " + direccionSucursal +", "+ ciudad + ", "+ correoCliente);
		return carrito;
	}

	public long[] eliminarCarritoPorId(long idCarrito)
	{
		Log.info("Eliminando un carrito: " + idCarrito);
		long[] tuplasEliminadas = pp.eliminarCarritoPorId(idCarrito);
		Log.info("Saliendo de eliminar un carrito: " + idCarrito);

		List<Object[]>[] listas = new LinkedList[2];
		listas[0] = null;
		listas[1] = buscarProductosCarritoPorId(carrito.getIdCarrito());
		publish(listas);

		return tuplasEliminadas;
	}
	
	public long eliminarCarrito(long idCarrito)
	{
		Log.info("Eliminando un carrito: " + idCarrito);
		long[] tuplasEliminadas = pp.eliminarCarritoPorId(idCarrito);
		Log.info("Saliendo de eliminar un carrito: " + idCarrito);
		return tuplasEliminadas[1];
	}

	public Carrito darCarritoPorId(long idCarrito)
	{
		Log.info("Consultando carrito por identificador: " + idCarrito);
		Carrito carrito  = pp.darCarritoPorId(idCarrito);
		Log.info("Saliendo de consultar carrito por identificador: " + idCarrito);
		return carrito;
	}

	public Carrito darCarritoPorCorreoCliente(String correoCliente)
	{
		Log.info("Consultando carrito por correo: " + correoCliente);
		Carrito carrito  = pp.darCarritoPorCorreoCliente(correoCliente);
		Log.info("Saliendo de consultar carrito por correo: " + correoCliente);
		return carrito;
	}

	public List<VOCarrito> darVOCarrito ()
	{
		Log.info ("Generando los VO de Carrito");
		List<VOCarrito> voCarrito = new LinkedList<VOCarrito> ();
		if(pp.darCarritos().size() > 0)
		{
			for (Carrito carrito: pp.darCarritos())
			{
				voCarrito.add(carrito);
			}
		}
		Log.info ("Generando los VO de Carrito: " + voCarrito.size () + " carritos existentes");
		return voCarrito;
	}
	//---------------------------------------------------------------------
	// M�todos para manejar PRODUCTOS EN CARRITO
	//---------------------------------------------------------------------
	public ProductosCarrito adicionarProductoAlCarrito(String pCodigo, int pCantidad)
	{
		Log.info("Agregar productos al carrito "+pCodigo+", "+ pCantidad);
		ProductosCarrito nuevo = pp.adicionarProductoAlCarrito(pCodigo, carrito.getIdCarrito(), pCantidad);
		Log.info("Saliendo de agregar productos al carrito "+pCodigo+", "+ pCantidad);

		List<Object[]>[] listas = new LinkedList[2];
		listas[0] = darCantidadEnEstantesReales(carrito.getCiudad(), carrito.getDireccionSucursal());
		listas[1] = buscarProductosCarritoPorId(carrito.getIdCarrito());
		publish(listas);
	
		termino = false;
		ultimaTransaccion = System.currentTimeMillis();
		return nuevo;
	}
	public ProductosCarrito adicionarProductoCarrito(String pCodigo, long idCarrito, int pCantidad)
	{
		Log.info("Agregar productos al carrito "+pCodigo+", "+ pCantidad+", "+idCarrito);
		ProductosCarrito nuevo = pp.adicionarProductoAlCarrito(pCodigo, idCarrito, pCantidad);
		Log.info("Saliendo de agregar productos al carrito "+pCodigo+", "+ pCantidad+", "+idCarrito);
		return nuevo;
	}
	
	public long eliminarProductoDelCarrito(String pCodigo, long idCarrito)
	{
		Log.info("Eliminar productos al carrito "+pCodigo+", "+ idCarrito);
		long numero = pp.eliminarProductoDelCarrito(pCodigo, idCarrito);
		Log.info("Saliendo de eliminar productos al carrito "+pCodigo+", "+ idCarrito);
		return numero;
	}
	public long eliminarProductosDelCarrito()
	{
		Log.info("Eliminar productos al carrito "+carrito.getIdCarrito());
		long numero = pp.eliminarProductoDelCarrito(carrito.getIdCarrito());
		Log.info("Saliendo de eliminar productos al carrito "+carrito.getIdCarrito());
		return numero;
	}
	public long eliminarCantidadProductoDelCarrito(String pCodigo, int pCantidad)
	{
		Log.info("Eliminar cantidad productos al carrito "+pCodigo+", "+ carrito.getIdCarrito());
		long numero = pp.eliminarCantidadProductoDelCarrito(pCodigo, carrito.getIdCarrito(), pCantidad);
		Log.info("Saliendo de eliminar cantidad productos al carrito "+pCodigo+", "+ carrito.getIdCarrito());

		eliminarProductosDelCarrito();
		List<Object[]>[] listas = new LinkedList[2];
		listas[0] = darCantidadEnEstantesReales(carrito.getCiudad(), carrito.getDireccionSucursal());
		listas[1] = buscarProductosCarritoPorId(carrito.getIdCarrito());
		publish(listas);

		ultimaTransaccion = System.currentTimeMillis();

		return numero;
	}
	public List<Object[]> buscarProductosCarritoPorId( long idCarrito)
	{
		Log.info("Buscar productos de un carrito "+ idCarrito);
		List<Object[]> productos = pp.buscarProductosCarrito(idCarrito);
		Log.info("Saliendo de buscar productos de un carrito "+ idCarrito);
		return productos;
	}

	public List<VOProductosCarrito> darVOProductosCarrito(long id)
	{
		Log.info ("Generando los VO de Productos en el carrito");
		List<VOProductosCarrito> voProductos = new LinkedList<VOProductosCarrito> ();
		for (ProductosCarrito producto: pp.darProductosCarrito(id))
		{
			voProductos.add (producto);
		}
		Log.info ("Generando los VO de Productos en el carrito: " + voProductos.size () + " productos existentes");
		return voProductos;
	}
	public long eliminarProductoCarrito(String pCodigo, long idCarrito)
	{
		Log.info("Borrar el producto del carrito");
		long numero = pp.eliminarProductoDelCarrito(pCodigo, idCarrito);
		Log.info("Saliendo de borrar el producto del carrito");
		return numero;
	}

	public Factura registrarVenta()
	{
		Log.info("Registrando venta del carrito");
		Factura nueva = pp.registrarVenta(carrito.getDireccionSucursal(), carrito.getCiudad(), carrito.getIdCarrito(), carrito.getCorreoCliente());
		Log.info("Saliendo de registra venta: "+nueva);
		termino = true;
		return nueva;
	}
	
	//-------------------------------------------------------
	// CONSULTA
	//-------------------------------------------------------
	public List<Object[]>[] operacionesAnuales(String tipo)
	{
		Log.info("Consultando operaciones anuales para el tipo de producto: "+tipo);
		List<Object[]>[] retorno = pp.operacionesAnuales(tipo);
		Log.info("Saliendo de consultar las operaciones anuales para el tipo de producto: "+tipo);
		return retorno;
	}
	
	public List<Object[]>[] operacionesMensuales(String tipo)
	{
		Log.info("Consultando operaciones mensuales para el tipo de producto: "+tipo);
		List<Object[]>[] retorno = pp.operacionesMensuales(tipo);
		Log.info("Saliendo de consultar las operaciones mensuales para el tipo de producto: "+tipo);
		return retorno;
	}
	
	public List<Object[]>[] operacionesSemanales(String tipo)
	{
		Log.info("Consultando operaciones semanales para el tipo de producto: "+tipo);
		List<Object[]>[] retorno = pp.operacionesSemanales(tipo);
		Log.info("Saliendo de consultar las operaciones semanales para el tipo de producto: "+tipo);
		return retorno;
	}
	
	public List<Object[]>[] operacionesDiarias(String tipo)
	{
		Log.info("Consultando operaciones diarias para el tipo de producto: "+tipo);
		List<Object[]>[] retorno = pp.operacionesDiarias(tipo);
		Log.info("Saliendo de consultar las operaciones diarias para el tipo de producto: "+tipo);
		return retorno;
	}

	//--------------------------------------------------------
	// Actualizaci�n constante
	//--------------------------------------------------------
	@Override
	protected Boolean doInBackground() throws Exception {

		while(true)
		{
			long actual = System.currentTimeMillis();
			if(actual-ultimaTransaccion > 30000)
			{
				eliminarCarritoPorId(carrito.getIdCarrito());
				break;
			}
			else if(termino)
			{
				break;
			}

		}

		return false;
	}

	@Override
	protected void process(List<List<Object[]>[]> chunks) 
	{
		oidorCarrito.actualizarLista(chunks.get(chunks.size()-1));
	}


}
