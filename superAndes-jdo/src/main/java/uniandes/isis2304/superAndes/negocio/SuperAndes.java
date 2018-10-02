package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class SuperAndes {
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
	}
	//-----------------------------------------------------------------------------
	//   Metodos para manejar los PRODUCTOS
	//-----------------------------------------------------------------------------
	public Producto adicionarProducto(String pNombre, String pMarca, String pPresentacion, String pUnidadMedida, String pEspecificacionEmpacado, String pCalidad, double pPrecioUnitario, double pPrecioUnidadMedida, int pCantidadPresentacion, int pCodigoBarras, Date pFechaVencimiento)
	{
		Log.info("Adicionando el producto "+pNombre);
		Producto producto = pp.adicionarProducto(pNombre, pMarca, pPresentacion, pUnidadMedida, pEspecificacionEmpacado, pCalidad, pPrecioUnitario, pPrecioUnidadMedida, pCantidadPresentacion, pCodigoBarras, pFechaVencimiento);
		Log.info("Saliendo de adicionar el producto "+ pNombre);
		return producto;
	}

	//------------------------------------------------------------------
	//  Metodos para manejar PRODUCTO_CATEGORIA
	//------------------------------------------------------------------
	public ProductoCategoria adicionarProductoCategoria(String pNombreCategoria, int pCodigoBarras)
	{
		if(pp.buscarCategoriaNombre(pNombreCategoria) == null)
		{
			Log.info("Adicionando el producto categoria "+pCodigoBarras);
			ProductoCategoria productoCategoria = pp.adicionarProductoCategoria(pNombreCategoria, pCodigoBarras);
			Log.info("Saliendo de adicionar el producto "+ pCodigoBarras);
			return productoCategoria;
		}
		return null;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar CATEGORIA
	//------------------------------------------------------------------
	public Categoria adicionarCategoria(String pNombre)
	{
		if(pp.buscarCategoriaNombre(pNombre) == null)
		{
			Log.info("Adicionando la categoria "+pNombre);
			Categoria categoria = pp.adicionarCategoria(pNombre);
			Log.info("Saliendo de adicionar la categoria "+ pNombre);
			return categoria;
		}
		return null;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar TIPO_PRODUCTO
	//------------------------------------------------------------------
	public TipoProducto adicionarTipoProducto(String pNombreTipo, String pNombreCategoria)
	{
		TipoProducto tipoProducto = null;
		if(pp.buscarTipoNombre(pNombreTipo) == null)
		{
			Log.info("Adicionando el tipo de producto "+pNombreTipo);
			tipoProducto = pp.adicionarTipoProducto(pNombreTipo, pNombreCategoria);
			Log.info("Saliendo de adicionar el producto "+ pNombreTipo);
		}
		return tipoProducto;
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
	public void buscarIndiceBodega()
	{
		Log.info("Iniciando calculo de indice de bodega ");
		List<Double> indices = pp.buscarIndiceBodega();
		Log.info("Saliendo de calculo de indice de bodega ");
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
	public void buscarIndiceEstante()
	{
		Log.info("Iniciando calculo de indice de bodega ");
		List<Double> indices = pp.buscarIndiceEstante();
		Log.info("Saliendo de calculo de indice de bodega ");
	}
	//------------------------------------------------------------------
	//  Metodos para manejar PEDIDO PRODUCTO
	//------------------------------------------------------------------
	public PedidoProducto adicionarPedidoProducto(int pCodigoBarras, long pIdPedido, double pCantidadProducto, double pPrecioProducto)
	{
		Log.info("Adicionando el pedido producto "+ pCodigoBarras+", "+pIdPedido);
		PedidoProducto pedidoProducto = pp.adicionarPedidoProducto(pCodigoBarras, pIdPedido, pCantidadProducto, pPrecioProducto);
		Log.info("Saliendo de adicionar el pedido producto "+ pCodigoBarras+", "+pIdPedido);
		return pedidoProducto;
	}
	//------------------------------------------------------------------
	//  Metodos para manejar COMPRADOS
	//------------------------------------------------------------------
	public Comprados adicionarComprados(int pCodigoBarras,int pCantidad, double pPrecioTotal, String pIdFactura)
	{
		Log.info("Adicionando comprados "+ pCodigoBarras+", "+pCodigoBarras+", "+pIdFactura);
		Comprados comprado = pp.adicionarComprados(pCodigoBarras, pCantidad, pPrecioTotal, pIdFactura);
		Log.info("Saliendo de adicionar comprados "+ pCodigoBarras+", "+pCodigoBarras+", "+pIdFactura);
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

	//---------------------------------------------------------------------
	// M�todos para manejar PROMOCION
	//---------------------------------------------------------------------
	public Promocion adicionarPromocion(Timestamp fechaInicio, Timestamp fechaFin, String descripcion, int codBarras, int uniDisponibles, int uniVendidas)
	{
		Log.info("Adicionando promocion "+ fechaInicio+", "+fechaFin+", "+descripcion+", "+codBarras+", "+uniDisponibles+", "+uniVendidas);
		Promocion promocion = pp.adicionarPromocion(fechaInicio, fechaFin, descripcion, codBarras, uniDisponibles, uniVendidas);
		Log.info("Saliendo de adicionar promocion "+ fechaInicio+", "+fechaFin+", "+descripcion+", "+codBarras+", "+uniDisponibles+", "+uniVendidas);
		return promocion;
	}

	//---------------------------------------------------------------------
	// M�todos para manejar ORDEN PEDIDO
	//---------------------------------------------------------------------
	public OrdenPedido adicionarOrdenPedido(Timestamp fechaEsperada, int nitProveedor, String ciudad, String direccionSucursal, String direccionBodega)
	{
		Log.info("Adicionando orden pedido "+ nitProveedor+", "+fechaEsperada+", "+ciudad+", "+direccionSucursal+", "+direccionBodega);
		OrdenPedido ordenPedido = pp.adicionarOrdenPedido(fechaEsperada, nitProveedor, ciudad, direccionSucursal, direccionBodega);
		Log.info("Saliendo de adicionar orden pedido "+ nitProveedor+", "+fechaEsperada+", "+ciudad+", "+direccionSucursal+", "+direccionBodega);
		return ordenPedido;
	}

	//---------------------------------------------------------------------
	// M�todos para manejar FACTURA
	//---------------------------------------------------------------------
	public Factura adicionarFactura(double costoTotal, Timestamp fecha)
	{
		Log.info("Adicionando factura "+ costoTotal+", "+fecha);
		Factura factura = pp.adicionarFactura(costoTotal, fecha);
		Log.info("Saliendo de adicionar factura "+ costoTotal+", "+fecha);
		return factura;
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

	//---------------------------------------------------------------------
	// M�todos para manejar CANTIDAD EN ESTANTES
	//---------------------------------------------------------------------
	public CantidadEnEstantes adicionarCantidadEnEstantes(int codigoBarras, long idEstante, int cantidadActual, int cantidadMinima)
	{
		Log.info("Adicionando cantidad en estantes "+ codigoBarras+", "+idEstante+", "+cantidadActual+", "+cantidadMinima);
		CantidadEnEstantes cantidadEstantes = pp.adicionarCantidadEnEstante(codigoBarras, idEstante, cantidadActual, cantidadMinima);
		Log.info("Saliendo de adicionar cantidad en estantes "+ codigoBarras+", "+ idEstante+", "+cantidadActual+", "+cantidadMinima);
		return cantidadEstantes;
	}
	
	//---------------------------------------------------------------------
	// M�todos para manejar PRODUCTOS OFRECIDOS
	//---------------------------------------------------------------------
	public ProductosOfrecidos adicionarProductosOfrecidos(int codigoBarras, String direccionSucursal, String ciudad)
	{
		Log.info("Adicionando productos ofrecidos "+ codigoBarras+", "+direccionSucursal+", "+ciudad);
		ProductosOfrecidos productosOfrecidos = pp.adicionarProductosOfrecidos(codigoBarras, direccionSucursal, ciudad);
		Log.info("Saliendo de adicionar productos ofrecidos "+ codigoBarras+", "+direccionSucursal+", "+ciudad);
		return productosOfrecidos;
	}
	
	//---------------------------------------------------------------------
	// M�todos para manejar SUCURSAL FACTURAS
	//---------------------------------------------------------------------
	public SucursalFactura adicionarSucursalFacturas(long idFactura, String direccion, String ciudad)
	{
		Log.info("Adicionando sucursal facturas"+ idFactura+", "+direccion+", "+ciudad);
		SucursalFactura sucursalFactura = pp.adicionarSucursalFactura(idFactura, direccion, ciudad);
		Log.info("Saliendo de adicionar sucursal facturas "+ idFactura+", "+direccion+", "+ciudad);
		return sucursalFactura;
	}
	
	//---------------------------------------------------------------------
	// M�todos para manejar HISTORIAL COMPRA
	//---------------------------------------------------------------------
	public HistorialCompras adicionarHistorialCompra(String correo, long idFactura)
	{
		Log.info("Adicionando historial compra "+ correo+", "+idFactura);
		HistorialCompras historialCompra = pp.adicionarHistorialCompra(correo, idFactura);
		Log.info("Saliendo de adicionar historial compra "+ correo+", "+idFactura);
		return historialCompra;
	}
	
	//---------------------------------------------------------------------
	// M�todos para manejar PROVEEN
	//---------------------------------------------------------------------
	public Proveen adicionarProveen(int nitProveedor, int codigoBarras)
	{
		Log.info("Adicionando proveen "+ nitProveedor+", "+codigoBarras);
		Proveen proveen = pp.adicionarProveen(nitProveedor, codigoBarras);
		Log.info("Saliendo de adicionar proveen "+ nitProveedor+", "+codigoBarras);
		return proveen;
	}
}
