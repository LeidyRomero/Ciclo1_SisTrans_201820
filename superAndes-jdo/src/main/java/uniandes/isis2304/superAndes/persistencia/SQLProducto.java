package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	public long agregarProducto(PersistenceManager manager, String pNombre, String pMarca, String pPresentacion, String pUnidadMedida, String pCalidad, double pPrecioUnitario, double pPrecioUnidadMedida, int pCantidadPresentacion, String pCodigoBarras, Date pFechaVencimiento, String pPeso, String pVolumen)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlProducto()+"(nombre_producto, marca,precio_unitario, presentacion, precio_uni_medida,cant_presentacion,unidad_medida, cod_barras, calidad, fecha_vencimiento, peso_producto, volumen_producto) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
		q.setParameters(pNombre, pMarca, pPrecioUnitario, pPresentacion, pPrecioUnidadMedida, pCantidadPresentacion, pUnidadMedida, pCodigoBarras, pCalidad, pFechaVencimiento, pPeso, pVolumen);
		return (long) q.executeUnique();
	}

	//------------------------------------------------------------------------
	//TODO RFC4 - Mostrar los productos que cumplen con cierta caracteristica
	//------------------------------------------------------------------------

	//Caracteristica 1:
	//TODO: revisar si el between funciona con precios
	public List<Producto> darProductosPrecioEnRango (PersistenceManager manager, double precioMenor, double precioMayor)
	{
		Query q = manager.newQuery(SQL, "SELECT nombre_producto, precio_unitario FROM " + persistencia.getSqlProducto()+" WHERE precio_unitario BETWEEN "+ precioMenor +" AND "+ precioMayor);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 2:
	//TODO: Verificar que si sean los productos que vencen despues de una fecha dada, 2: revisar que las fechas si se puedan comparar con >= (revisar el ejercicio que funciono de las diapositivas)
	public List<Producto> darProductosFechaVencimiento (PersistenceManager manager, Date pFecha)
	{
		Query q = manager.newQuery(SQL, "SELECT nombre_producto, fecha_vencimiento FROM " + persistencia.getSqlProducto()+" WHERE fecha_vencimiento >= '"+pFecha+"'");
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//*Caracteristica 3:*
	//TODO UNIDAD DE MEDIDA! Tocarevisar, en el caso de volumen si esta en unidades de volumen, o en peso, si esta en unidades de peso
	//TODO El peso del producto si es la cantidad por presentacion?, 2: revisar between
	public List<Producto> darProductosPesoEnRango (PersistenceManager manager, double pesoMinimo, double pesoMaximo)
	{
		Query q = manager.newQuery(SQL, "SELECT nombre_producto, cant_presentacion FROM " + persistencia.getSqlProducto()+ " WHERE cant_presentacion BETWEEN "+pesoMinimo +" AND "+pesoMaximo);
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//*Caracteristica 4:*
	public List<Producto> darProductosVolumenEnRango (PersistenceManager manager, double volumenMinimo, double volumenMaximo)
	{
		Query q = manager.newQuery(SQL, "SELECT nombre_producto FROM " + persistencia.getSqlProducto());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 5:
	//TODO REVISAR ACCESO A COLUMNAS
	public List<Producto> darProductosVendidosPorProveedor (PersistenceManager manager, int pNitProveedor)
	{
		Query q = manager.newQuery(SQL, "SELECT nombre_producto FROM " + persistencia.getSqlProducto()+","+persistencia.getSqlProveen()+" WHERE nit_proveedor = "+pNitProveedor+" AND A_PRODUCTO.COD_BARRAS = A_PROVEEN.COD_BARRAS");
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 6:
	public List<Producto> darProductosDisponiblesCiudad (PersistenceManager manager, String pCiudad)
	{
		//TODO Nueva tabla
//		Query q = manager.newQuery(SQL, "SELECT nombre_producto FROM " + persistencia.getSqlProductosDisponibles()+" GOUP BY ciudad");
//		q.setResultClass(Producto.class);
//		return (List<Producto>) q.executeList();
		return null;
	}
	//Caracteristica 7:
	public List<Producto> darProductosDisponiblesSucursal (PersistenceManager manager, String pCiudad, String pDireccion)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 8:
	public List<Producto> darProductosTipo (PersistenceManager manager, String pTipo)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 9:
	public List<Producto> darProductosCategoria (PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
	//Caracteristica 10:
	public List<Producto> darProductosVentasSuperioresAXEnRangoFechas (PersistenceManager manager, double pVentas)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProducto());
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}
}