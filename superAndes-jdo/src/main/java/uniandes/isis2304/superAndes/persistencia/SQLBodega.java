package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.*;

import uniandes.isis2304.superAndes.negocio.Bodega;

/**
 * 
 * @author lj.romero
 *
 */
class SQLBodega
{
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLBodega(PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	//TODO RF5 - Registrar una bodega a una sucursal
	public long agregarBodega(PersistenceManager manager, String pTipo, double pVolumen, double pPeso, String pDireccionBodega, String pDireccionSucursal, String pCiudad)
	{
		Query add = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlBodega()+" (tipo_bodega, volumen_bodega, peso_bodega, direccion_bodega, direccion_sucursal, ciudad) values (?,?,?,?,?,?)");
		add.setParameters(pTipo, pVolumen, pPeso, pDireccionBodega, pDireccionSucursal, pCiudad);
		return (long) add.executeUnique();
	}
	//TODO RFC3 - Mostrar el indice de ocupacion de cada estante
	public List<Bodega> buscarBodegasSucursal(PersistenceManager manager, String pDireccion, String pCiudad)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlBodega()+"WHERE ciudad = ? AND direccion_sucursal = ?");
		q.setParameters(pCiudad,pDireccion);
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
	public List<Bodega> buscarBodegas(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlBodega());
		q.setResultClass(Bodega.class);
		return (List<Bodega>) q.executeList();
	}
	public long eliminarBodega(PersistenceManager manager,String direccionBodega, String direccionSucursal,String ciudad)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlBodega() + " WHERE direccion_bodega = ? AND direccion_sucursal = ? AND ciudad = ?");
		add.setParameters(direccionBodega,direccionSucursal,ciudad);
		return (long) add.executeUnique();
	}
}