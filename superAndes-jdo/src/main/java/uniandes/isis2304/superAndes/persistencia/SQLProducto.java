package uniandes.isis2304.superAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	public long agregarProducto(PersistenceManager manager, String pNombre, String pMarca, String pPresentacion, String pUnidadMedida, String pCalidad, double pPrecioUnitario, double pPrecioUnidadMedida, int pCantidadPresentacion, String pCodigoBarras, Date pFechaVencimiento, String pPeso, String pVolumen, String pCategoria)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlProducto()+"(nombreproducto, marca,preciounitario, presentacion, preciounimedida,cantpresentacion,unidadmedida, codbarras, calidad, fechavencimiento, pesoproducto, volumenproducto, nombrecategoria) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		q.setParameters(pCategoria,pNombre, pMarca, pPrecioUnitario, pPresentacion, pPrecioUnidadMedida, pCantidadPresentacion, pUnidadMedida, pCodigoBarras, pCalidad, pFechaVencimiento, pPeso, pVolumen);
		return (long) q.executeUnique();
	}

	public Producto buscarProductoPorCodigo (PersistenceManager manager, String pCodigoBarras)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+" WHERE codbarras = ?");
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
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlProducto()+ " WHERE codbarras = ?");
		add.setParameters();
		return (long) add.executeUnique();
	}
	
	//TODO Descubrir como setear los parámetros 
	public List<Producto> darProdcutoCaracteristica(PersistenceManager pm, String[] data, List<Integer> caracteristicas )
	{
		List<Producto> productos = new ArrayList<Producto>();
		if(caracteristicas.size() == 1)
		{
			if(caracteristicas.get(0) == 1)
				productos = darProductosPrecioEnRango(pm, Double.parseDouble(data[0]), Double.parseDouble(data[1]));
			else if(caracteristicas.get(0) == 2)
			{
				Timestamp fecha = Timestamp.valueOf(data[3]+" 00:00:00");
				productos = darProductosFechaVencimiento(pm, fecha);
			}
			else if(caracteristicas.get(0) == 3)
				productos = darProductosPesoEnRango(pm, Double.parseDouble(data[4]), Double.parseDouble(data[5]));
			else if(caracteristicas.get(0) == 4)
				productos = darProductosVolumenEnRango(pm, Double.parseDouble(data[6]), Double.parseDouble(data[7]));
			else if(caracteristicas.get(0) == 5)
				productos = darProductosVendidosPorProveedor(pm, Integer.parseInt(data[8]));
			else if(caracteristicas.get(0) == 6)
				productos = darProductosDisponiblesCiudad(pm, data[9]);
			else if(caracteristicas.get(0) == 7)
				productos = darProductosDisponiblesSucursal(pm, data[10], data[11]);
//			else if(caracteristicas.get(0) == 8)
//				productos = darProductosTipo(pm, data[12]);
//			else if(caracteristicas.get(0) == 9)
//				productos = darProductosCategoria(pm, data[13]);

			else if(caracteristicas.get(0) == 10)
			{
				Timestamp fecha1 = Timestamp.valueOf(data[15]+" 00:00:00");
				Timestamp fecha2 = Timestamp.valueOf(data[16]+" 00:00:00");
				productos = darProductosVentasSuperioresAXEnRangoFechas(pm, Double.parseDouble(data[14]), fecha1, fecha2);
			}
		}
		else
		{
			String sql = "SELECT * FROM " + persistencia.getSqlProducto() + " WHERE ";
			if(caracteristicas.contains(1))
				sql += "(preciounitario BETWEEN ? AND ?) AND ";
			if(caracteristicas.contains(2))
				sql += "(fechavencimiento IS NOT NULL AND fechavencimiento >= '?') AND ";
			if(caracteristicas.contains(3))
				sql += "(pesoproducto BETWEEN ? AND ?) AND ";
			if(caracteristicas.contains(4))
				sql += "(volumenproducto BETWEEN ? AND ?) AND ";
			if(caracteristicas.contains(5))
				sql += "(pesoproducto BETWEEN ? AND ?) AND ";
			if(caracteristicas.contains(6))
				sql += "(pesoproducto BETWEEN ? AND ?) AND ";
			if(caracteristicas.contains(7))
				sql += "(pesoproducto BETWEEN ? AND ?) AND ";
			if(caracteristicas.contains(8))
				sql += "(pesoproducto BETWEEN ? AND ?) AND ";
			if(caracteristicas.contains(9))
				sql += "(pesoproducto BETWEEN ? AND ?) AND ";
			if(caracteristicas.contains(10))
				sql += "(pesoproducto BETWEEN ? AND ?) AND ";
			
		}
		return productos;
	}
	//Caracteristica 1:
	public List<Producto> darProductosPrecioEnRango (PersistenceManager manager, double precioMenor, double precioMayor)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+" WHERE preciounitario BETWEEN ? AND ?");
		q.setParameters(precioMenor, precioMayor);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 2:
	public List<Producto> darProductosFechaVencimiento (PersistenceManager manager, Timestamp pFecha)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+" WHERE fechavencimiento IS NOT NULL AND fechavencimiento >= '?'");
		q.setParameters(pFecha);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//*Caracteristica 3:*
	public List<Producto> darProductosPesoEnRango (PersistenceManager manager, double pesoMinimo, double pesoMaximo)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+ " WHERE pesoproducto BETWEEN ? AND ? ");
		q.setParameters(pesoMinimo, pesoMaximo);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//*Caracteristica 4:*
	public List<Producto> darProductosVolumenEnRango (PersistenceManager manager, double volumenMinimo, double volumenMaximo)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+ " WHERE volumenproducto BETWEEN ? AND ?");
		q.setParameters(volumenMinimo,volumenMaximo);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 5:
	public List<Producto> darProductosVendidosPorProveedor (PersistenceManager manager, int pNitProveedor)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+","+persistencia.getSqlProveen()+" WHERE nitproveedor = ? AND A_PRODUCTO.COdBARRAS = A_PROVEEN.CODBARRAS");
		q.setParameters(pNitProveedor);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 6:
	public List<Producto> darProductosDisponiblesCiudad (PersistenceManager manager, String pCiudad)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCantidadEnBodega()+","+persistencia.getSqlCantidadEnEstantes()+","+persistencia.getSqlProducto()+","+persistencia.getSqlEstante()+" WHERE (A_CANTIDAD_EN_BODEGA.CIUDAD = ? OR A_ESTANTE.CIUDAD = ?) AND (A_ESTANTE.ID = A_CANTIDAD_EN_ESTANTES.IDESTANTE) AND (A_CANTIDAD_EN_BODEGA.CANTIDADACTUAL+A_CANTIDAD_EN_ESTANTES.CANTIDADACTUAL>0)");
		q.setParameters(pCiudad,pCiudad);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 7:
	public List<Producto> darProductosDisponiblesSucursal (PersistenceManager manager, String pCiudad, String pDireccion)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlCantidadEnBodega()+","+persistencia.getSqlCantidadEnEstantes()+","+persistencia.getSqlEstante()+"WHERE (A_ESTANTE.CIUDAD = ? AND A_ESTANTE.DIRECCIONSUCURSAL = ? ) AND (A_CANTIDAD_EN_BODEGA.CIUDAD = ? AND A_CANTIDAD_EN_BODEGA.DIRECCIONSUCURSAL = ?) AND (A_ESTANTE.ID = A_CANTIDAD_EN_ESTANTES.IDESTANTE) AND (A_CANTIDAD_EN_BODEGA.CANTIDADACTUAL+A_CANTIDAD_EN_ESTANTES.CANTIDADACTUAL>0)");
		q.setParameters(pCiudad,pDireccion,pCiudad,pDireccion);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 8:
	//TODO REVISAR CATEGORIA
//	public List<Producto> darProductosTipo (PersistenceManager manager, String pTipo)
//	{
//		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+","+persistencia.getSqlTipoProducto()+","+persistencia.getSqlProductoCategoria()+"WHERE nombretipo = ? AND A_TIPO_PRODUCTO.NOMBRECATEGORIA = A_PRODUCTO_CATEGORIA.CATEGORIA AND A_PRODUCTO_CATEGORIA.CODIGOBARRAS = A_PRODUCTO.CODBARRAS");
//		q.setParameters(pTipo);
//		q.setResultClass(Producto.class);
//		return (List<Producto>) q.executeList();
//	}
	//Caracteristica 9:
	//TODO REVISAR CATEGORIA
//	public List<Producto> darProductosCategoria (PersistenceManager manager, String pCategoria)
//	{
//		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto()+","+persistencia.getSqlProductoCategoria()+"WHERE A_PRODUCTO_CATEGORIA.CATEGORIA = ? AND A_PRODUCTO_CATEGORIA.CODIGOBARRAS = A_PRODUCTO.CODBARRAS");
//		q.setParameters(pCategoria);
//		q.setResultClass(Producto.class);
//		return (List<Producto>) q.executeList();
//	}
	//Caracteristica 10:
	public List<Producto> darProductosVentasSuperioresAXEnRangoFechas (PersistenceManager manager, double pVentas, Timestamp fechaInicial, Timestamp fechaFinal)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM (SELECT COUNT(cantidad) numeroUnidades, codigobarras FROM " +"(SELECT * FROM "+persistencia.getSqlComprados()+","+persistencia.getSqlFactura()+" WHERE A_FACTURA.IDFACTURA = A_COMPRADOS.IDFACTURA AND A_FACTURA.FECHA BETWEEN ? AND ?)"+" GROUP BY CODIGOBARRAS HAVING COUNT(cantidad) > ? ) comprados , "+persistencia.getSqlProducto()+" WHERE A_PRODUCTO.CODBARRAS = comprados.codigobarras");
		q.setParameters(fechaInicial, fechaFinal, pVentas);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	
	public long darEstanteProducto(PersistenceManager manager, String codBarras, String direccionSucursal, String ciudad)
	{
		String sql = "SELECT e.idestante "
				+ "FROM A_ESTANTE e, A_CANTIDAD_EN_ESTANTES ce "
				+ "WHERE e.idestante = ce.idestante AND "
				+ "ce.codigobarras = ? AND "
				+ "e.ciudad = ? AND "
				+ "e.direccionsucursal = ?";
		Query q = manager.newQuery(SQL, sql);
		q.setParameters(codBarras, ciudad, direccionSucursal);
		return ((BigDecimal)q.executeUnique()).longValue();
	}
}