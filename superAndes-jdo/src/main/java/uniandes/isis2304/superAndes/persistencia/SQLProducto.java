package uniandes.isis2304.superAndes.persistencia;

import java.util.Date;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

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
	
	public long agregar (PersistenceManager manager, String pNombre, String pMarca, String pPresentacion, String pUnidadMedida, String pEspecificacionEmpacado, String pCalidad, double pPrecioUnitario, double pPrecioUnidadMedida, int pCantidadPresentacion, int pCodigoBarras, Date pFechaVencimiento)
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlProducto()+"(nombre_producto, marca,precio_unitario, presentacion, precio_uni_medida,cant_presentacion,unidad_medida, espe_empacado, cod_barras, calidad, fecha_vencimiento) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		q.setParameters(pNombre, pMarca, pPrecioUnitario, pPresentacion, pPrecioUnidadMedida, pCantidadPresentacion, pUnidadMedida, pEspecificacionEmpacado, pCodigoBarras, pCalidad, pFechaVencimiento);
		return (long) q.executeUnique();
	}
}