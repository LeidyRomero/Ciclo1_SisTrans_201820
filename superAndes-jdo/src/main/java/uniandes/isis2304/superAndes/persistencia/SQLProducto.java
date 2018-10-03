package uniandes.isis2304.superAndes.persistencia;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.net.aso.p;
import uniandes.isis2304.superAndes.negocio.Bodega;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Producto;

/**
 * 
 * @author lj.romero
 *
 */
class SQLProducto
{
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes persistencia;

	public SQLProducto (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	/**
	 * 
	 * @param manager
	 * @param pNombre
	 * @param pMarca
	 * @param pPresentacion
	 * @param pUnidadMedida
	 * @param pEspecificacionEmpacado
	 * @param pCalidad
	 * @param pPrecioUnitario
	 * @param pPrecioUnidadMedida
	 * @param pCantidadPresentacion
	 * @param pCodigoBarras
	 * @param pFechaVencimiento parametro no obligatorio
	 * @return
	 */
	//TODO RF2 - Registrar productos
	public long agregarProducto(PersistenceManager manager, String pNombre, String pMarca, String pPresentacion, String pUnidadMedida, String pCalidad, double pPrecioUnitario, double pPrecioUnidadMedida, int pCantidadPresentacion, String pCodigoBarras, Timestamp pFechaVencimiento, String pPeso, String pVolumen)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlProducto()+"(nombre_producto, marca,precio_unitario, presentacion, precio_uni_medida,cant_presentacion,unidad_medida, cod_barras, calidad, fecha_vencimiento, peso_producto, volumen_producto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
		q.setParameters(pNombre, pMarca, pPrecioUnitario, pPresentacion, pPrecioUnidadMedida, pCantidadPresentacion, pUnidadMedida, pCodigoBarras, pCalidad, pFechaVencimiento, pPeso, pVolumen);
		return (long) q.executeUnique();
	}

	public Producto buscarProductoPorCodigo (PersistenceManager manager, String pCodigoBarras)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+" WHERE cod_barras = ?");
		q.setParameters(pCodigoBarras);
		q.setResultClass(Producto.class);
		return (Producto) q.executeUnique();
	}
	public List<Producto> buscarProductos(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	public long eliminarProducto(PersistenceManager manager, String pCodigo)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlProducto()+ " WHERE cod_barras = ?");
		add.setParameters();
		return (long) add.executeUnique();
	}
	//------------------------------------------------------------------------
	//TODO RFC4 - Mostrar los productos que cumplen con cierta caracteristica
	//------------------------------------------------------------------------

	//Caracteristica 1:
	public List<Producto> darProductosPrecioEnRango (PersistenceManager manager, double precioMenor, double precioMayor)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+" WHERE precio_unitario BETWEEN ? AND ?");
		q.setParameters(precioMenor, precioMayor);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 2:
	public List<Producto> darProductosFechaVencimiento (PersistenceManager manager, Date pFecha)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+" WHERE fecha_vencimiento IS NOT NULL AND fecha_vencimiento >= '?'");
		q.setParameters(pFecha);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//*Caracteristica 3:*
	public List<Producto> darProductosPesoEnRango (PersistenceManager manager, double pesoMinimo, double pesoMaximo)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+ " WHERE peso_producto BETWEEN ? AND ? ");
		q.setParameters(pesoMinimo, pesoMaximo);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//*Caracteristica 4:*
	public List<Producto> darProductosVolumenEnRango (PersistenceManager manager, double volumenMinimo, double volumenMaximo)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+ " WHERE volumen_producto BETWEEN ? AND ?");
		q.setParameters(volumenMinimo,volumenMaximo);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 5:
	public List<Producto> darProductosVendidosPorProveedor (PersistenceManager manager, int pNitProveedor)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+","+persistencia.getSqlProveen()+" WHERE nit_proveedor = ? AND A_PRODUCTO.COD_BARRAS = A_PROVEEN.COD_BARRAS");
		q.setParameters(pNitProveedor);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 6:
	public List<Producto> darProductosDisponiblesCiudad (PersistenceManager manager, String pCiudad)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCantidadEnBodega()+","+persistencia.getSqlCantidadEnEstantes()+","+persistencia.getSqlProducto()+","+persistencia.getSqlEstante()+" WHERE (A_CANTIDAD_EN_BODEGA.CIUDAD = ? OR A_ESTANTE.CIUDAD = ?) AND (A_ESTANTE.ID = A_CANTIDAD_EN_ESTANTES.ID_ESTANTE) AND (A_CANTIDAD_EN_BODEGA.CANTIDAD_ACTUAL+A_CANTIDAD_EN_ESTANTES.CANTIDAD_ACTUAL>0)");
		q.setParameters(pCiudad,pCiudad);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 7:
	public List<Producto> darProductosDisponiblesSucursal (PersistenceManager manager, String pCiudad, String pDireccion)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCantidadEnBodega()+","+persistencia.getSqlCantidadEnEstantes()+","+persistencia.getSqlEstante()+"WHERE (A_ESTANTE.CIUDAD = ? AND A_ESTANTE.DIRECCION_SUCURSAL = ? ) AND (A_CANTIDAD_EN_BODEGA.CIUDAD = ? AND A_CANTIDAD_EN_BODEGA.DIRECCION_SUCURSAL = ?) AND (A_ESTANTE.ID = A_CANTIDAD_EN_ESTANTES.ID_ESTANTE) AND (A_CANTIDAD_EN_BODEGA.CANTIDAD_ACTUAL+A_CANTIDAD_EN_ESTANTES.CANTIDAD_ACTUAL>0)");
		q.setParameters(pCiudad,pDireccion,pCiudad,pDireccion);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 8:
	public List<Producto> darProductosTipo (PersistenceManager manager, String pTipo)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+","+persistencia.getSqlTipoProducto()+","+persistencia.getSqlProductoCategoria()+"WHERE nombre_tipo = ? AND A_TIPO_PRODUCTO.NOMBRE_CATEGORIA = A_PRODUCTO_CATEGORIA.CATEGORIA AND A_PRODUCTO_CATEGORIA.CODIGO_BARRAS = A_PRODUCTO.COD_BARRAS");
		q.setParameters(pTipo);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 9:
	public List<Producto> darProductosCategoria (PersistenceManager manager, String pCategoria)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+","+persistencia.getSqlProductoCategoria()+"WHERE A_PRODUCTO_CATEGORIA = ? AND A_PRODUCTO_CATEGORIA.CODIGO_BARRAS = A_PRODUCTO.COD_BARRAS");
		q.setParameters(pCategoria);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 10:
	public List<Producto> darProductosVentasSuperioresAXEnRangoFechas (PersistenceManager manager, double pVentas, Date fechaInicial, Date fechaFinal)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM "+ "(SELECT COUNT(cantidad) numeroUnidades, codigo_barras FROM " +"(SELECT * FROM "+persistencia.getSqlComprados()+","+persistencia.getSqlFactura()+" WHERE A_FACTURA.ID_FACTURA = A_COMPRADOS.ID_FACTURA AND A_FACTURA.FECHA BETWEEN ? AND ?)"+" GROUP BY CODIGO_BARRAS HAVING COUNT(cantidad) > ? ) comprados , "+persistencia.getSqlProducto()+" WHERE A_PRODUCTO.COD_BARRAS = comprados.codigo_barras");
		q.setParameters(fechaInicial, fechaFinal, pVentas);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
}