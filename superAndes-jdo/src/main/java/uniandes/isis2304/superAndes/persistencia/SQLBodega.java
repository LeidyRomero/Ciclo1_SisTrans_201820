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
	 * Se renombra acá para facilitar la escritura de las sentencias
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
	public List<Double> buscarIndice(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlBodega());
		q.setResultClass(Bodega.class);
		return (List<Double>) q.executeList();
	}
}