package uniandes.isis2304.superAndes.negocio;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import oracle.net.aso.p;
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
		pp.finalizarPromocionesPendientes();
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
	public String buscarProductosPrecioEnRango(double pPrecioMinimo, double pPrecioMaximo)
	{
		String mensaje = "";
		Log.info("Buscar los productos en un rango de precios, precio minimo: "+pPrecioMinimo+", precio maximo: "+pPrecioMaximo);
		List<Producto> productos = pp.buscarProductosPrecioEnRango(pPrecioMinimo, pPrecioMaximo);
		Log.info("Saliendo de buscar los productos en un rango de precios, precio minimo: "+pPrecioMinimo+", precio maximo: "+pPrecioMaximo);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto()+", con precio: "+productos.get(i).getPrecioUnitario()+"\n";
		}
		return mensaje;
	}
	public String buscarProductosFechaVencimiento(Date pFecha)
	{
		String mensaje = "";
		Log.info("Buscar los productos cuya fecha de nacimiento sea posterior a la fecha "+pFecha);
		List<Producto> productos = pp.buscarProductosFechaVencimiento(pFecha);
		Log.info("Saliendo de buscar los productos cuya fecha de nacimiento sea posterior a la fecha"+pFecha );
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto()+", con fecha de vencimiento: "+productos.get(i).getFechaVencimiento()+"\n";
		}
		return mensaje;
	}
	public String buscarProductosPesoEnRango(double pPesoMinimo, double pPesoMaximo)
	{
		String mensaje = "";
		Log.info("Buscar los productos en un rango de pesos, peso minimo: "+pPesoMaximo+", peso maximo: "+pPesoMaximo);
		List<Producto> productos = pp.buscarProductosPesoEnRango(pPesoMinimo, pPesoMaximo);
		Log.info("Saliendo de buscar los productos en un rango de pesos, peso minimo: "+pPesoMaximo+", peso maximo: "+pPesoMaximo);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto()+", con peso: "+productos.get(i).getPesoProducto()+"\n";
		}
		return mensaje;
	}
	public String buscarProductosVolumenEnRango(double pVolumenMinimo, double pVolumenMaximo)
	{
		String mensaje = "";
		Log.info("Buscar los productos en un rango de volumenes, volumen minimo: "+pVolumenMinimo+", volumen maximo: "+pVolumenMaximo);
		List<Producto> productos = pp.buscarProductosVolumenEnRango(pVolumenMinimo, pVolumenMaximo);
		Log.info("Saliendo de buscar los productos en un rango de volumenes, volumen minimo: "+pVolumenMinimo+", volumen maximo: "+pVolumenMaximo);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto()+", con volumen: "+productos.get(i).getVolumenProducto()+"\n";
		}
		return mensaje;
	}
	public String buscarProductosVendidosPorProveedor(int nit)
	{
		String mensaje = "Los productos del proveedor: "+nit+"son: ";
		Log.info("Buscar los productos vendidos por el proveedor con NIT: "+nit);
		List<Producto> productos = pp.buscarProductosVendidosPorProveedor(nit);
		Log.info("Saliendo de buscar los productos vendidos por el proveedor con NIT: "+nit);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto()+"\n";
		}
		return mensaje;
	}
	public String buscarProductosDisponiblesCiudad(String ciudad)
	{
		String mensaje = "Los productos disponibles en la ciudad: "+ciudad+" son:";
		Log.info("Buscar los productos disponibles en la ciudad: "+ciudad);
		List<Producto> productos = pp.buscarProductosDisponiblesCiudad(ciudad);
		Log.info("Saliendo de buscar los productos disponibles en la ciudad: "+ciudad);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto();
		}
		return mensaje;
	}
	public String buscarProductosDisponiblesSucursal(String pCiudad, String pDireccion)
	{
		String mensaje = "Los productos disponibles en la sucursal: "+pDireccion+" de la ciudad: "+pCiudad+" son: ";
		Log.info("Buscar los productos disponibles en la sucursal: "+pDireccion+" en la ciudad: "+pCiudad);
		List<Producto> productos = pp.buscarProductosDisponiblesSucursal(pCiudad, pDireccion);
		Log.info("Saliendo de buscar los productos disponibles en la sucursal: "+pDireccion+" en la ciudad: "+pCiudad);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto();
		}
		return mensaje;
	}
	public String buscarProductosTipo(String pTipo)
	{
		String mensaje = "Los productos de tipo: "+pTipo+" son:";
		Log.info("Buscar los productos de tipo: "+pTipo);
		List<Producto> productos = pp.buscarProductosTipo(pTipo);
		Log.info("Saliendo de buscar los productos de tipo: "+pTipo);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto();
		}
		return mensaje;
	}
	public String buscarProductosCategoria(String pCategoria)
	{
		String mensaje = "Los productos de categoria: "+pCategoria+" son:";
		Log.info("Buscar los productos de tipo: "+pCategoria);
		List<Producto> productos = pp.buscarProductosCategoria(pCategoria);
		Log.info("Saliendo de buscar los productos de tipo: "+pCategoria);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto();
		}
		return mensaje;
	}
	public String buscarProductosVentasSuperiores(double pVentasMinimas, Date pFechaInicial, Date pFechaFinal)
	{
		String mensaje = "";
		Log.info("Buscar los productos cuyas ventas sean superiores a: "+pVentasMinimas);
		List<Producto> productos = pp.buscarProductosVentasSuperiores(pVentasMinimas,pFechaInicial, pFechaFinal);
		Log.info("Saliendo de buscar los productos cuyas ventas sean superiores a: "+pVentasMinimas);
		for(int i = 0;i<productos.size();i++)
		{
			mensaje+=productos.get(i).getNombreProducto()+", con ventas: "+"\n";
		}
		return mensaje;
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
	public String buscarIndiceBodega(String pDireccion, String pCiudad)
	{
		String mensaje = "El indice de ocupaci�n ";
		Log.info("Iniciando calculo de indice de bodega ");
		List<Bodega> bodegas = pp.buscarBodegasSucursal(pDireccion, pCiudad);
		Log.info("Saliendo de calculo de indice de bodega ");
		double capActual = 0;
		double capTotal = 0;

		for(int i = 0;i<bodegas.size();i++)
		{
			capActual += (double)(pp.buscarCantidadActualBodega(bodegas.get(i).getDireccionBodega(),bodegas.get(i).getDireccionSucursal(),bodegas.get(i).getCiudad()));
			capTotal += bodegas.get(i).getVolumenBodega();
		}
		for(int i = 0;i<bodegas.size();i++)
		{
			double indice = (double)(capActual/capTotal)*100;
			mensaje+="de la bodega con direcci�n"+bodegas.get(i).getDireccionBodega()+", de la ciudad"+bodegas.get(i).getCiudad()+"es: "+indice+"\n";
		}
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
		String mensaje = "El indice de ocupaci�n ";
		Log.info("Iniciando calculo de indice de estante ");
		List<Estante> estantes = pp.buscarEstantesSucursal(pDireccion, pCiudad);
		Log.info("Saliendo de calculo de indice de estante ");
		for(int i = 0;i<estantes.size();i++)
		{
			double indice = (double)(pp.buscarCantidadActualEstante(estantes.get(i).getIdEstante())*100);
			mensaje+="del estante con id: "+estantes.get(i).getIdEstante()+"es: "+indice+"\n";
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

	public Comprados registrarCompra(String pCodigoBarras,int pCantidad, String correo)
	{
		Log.info("Registrando comprados "+ pCodigoBarras+", "+pCodigoBarras);
		Producto producto = pp.buscarProductoCodigoBarras(pCodigoBarras);
		Factura factura = pp.adicionarFactura(pCantidad*producto.getPrecioUnitario(), new Timestamp(System.currentTimeMillis()));
		HistorialCompras hc = pp.adicionarHistorialCompra(correo, factura.getIdFactura());
		Comprados comprado = pp.adicionarComprados(pCodigoBarras, pCantidad, pCantidad*producto.getPrecioUnitario(), factura.getIdFactura());
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
	public SucursalFactura adicionarSucursalFacturas(long idFactura, String direccion, String ciudad)
	{
		Log.info("Adicionando sucursal facturas"+ idFactura+", "+direccion+", "+ciudad);
		SucursalFactura sucursalFactura = pp.adicionarSucursalFactura(idFactura, direccion, ciudad);
		Log.info("Saliendo de adicionar sucursal facturas "+ idFactura+", "+direccion+", "+ciudad);
		return sucursalFactura;
	}

	public List<String> dineroSucursalEnRango(Timestamp fechaInicio, Timestamp fechaFin)
	{
		Log.info("Consultando el dinero recolectado por sucursales en periodo de tiempo: " + fechaInicio + ", "+fechaFin);
		List<String> list = pp.dineroSucursalEnRango(fechaInicio, fechaFin);
		Log.info("Saliendo de consultar el dinero recolectado por sucursales en periodo de tiempo: " + fechaInicio + ", "+fechaFin);
		return list;
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
	public Proveen adicionarProveen(int nitProveedor, String codigoBarras)
	{
		Log.info("Adicionando proveen "+ nitProveedor+", "+codigoBarras);
		Proveen proveen = pp.adicionarProveen(nitProveedor, codigoBarras);
		Log.info("Saliendo de adicionar proveen "+ nitProveedor+", "+codigoBarras);
		return proveen;
	}
}
