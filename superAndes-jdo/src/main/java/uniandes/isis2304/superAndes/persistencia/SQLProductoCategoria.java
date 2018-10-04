package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.*;

import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.ProductoCategoria;

/**
 * 
 * @author lj.romero
 *
 */
class SQLProductoCategoria
{
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLProductoCategoria(PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	// TODO RF2 - Registrar producto
	public long agregarProductoCategoria(PersistenceManager manager, String pNombreCategoria, String pCodigoBarras)
	{
		Query add = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlProductoCategoria()+" (nombrecategoria, codigobarras) values (?,?)");
		add.setParameters(pNombreCategoria,pCodigoBarras);
		return (long) add.executeUnique();
	}
	public List<ProductoCategoria> buscarProductoCategoria(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlProductoCategoria());
		q.setResultClass(ProductoCategoria.class);
		return (List<ProductoCategoria>) q.executeList();
	}
	public long eliminarProductoCategoria(PersistenceManager manager, String pCodigo, String pCategoria)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlProductoCategoria()+ " WHERE codbarras = ? AND categoria = ?");
		add.setParameters(pCodigo, pCategoria);
		return (long) add.executeUnique();
	}
}