package uniandes.isis2304.superAndes.negocio;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;
import uniandes.isis2304.superAndes.persistencia.PersistenciaSuperAndes;

public class SuperAndes {
	/**
	 * Logger para escribir la traza de la ejecución
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
}
