package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Bodega;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Indice;

/**
 * 
 * @author lj.romero
 *
 */
class SQLEstante
{
	private final static String SQL = PersistenciaSuperAndes.SQL;

	private PersistenciaSuperAndes persistencia;

	public SQLEstante (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	public long agregarEstante(PersistenceManager manager, String pTipoEstante,double pVolumen, long pId, double pPeso, double pNivelAbastecimiento, String pDireccionSucursal, String pCiudad )
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlEstante()+"(tipoestante,volumenestante,idestante,pesoestante,nivelabastecimiento, direccionsucursal, ciudad) values (?,?,?,?,?,?,?)");
		q.setParameters(pTipoEstante, pVolumen,pId,pPeso,pNivelAbastecimiento,pDireccionSucursal,pCiudad);
		return (long) q.executeUnique();
	} 
	
	//TODO RFC3 - Mostrar el indice de ocupacion de cada estante
	public List<Estante> buscarEstantesSucursal(PersistenceManager manager, String pDireccion, String pCiudad)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlEstante()+ " WHERE ciudad = ? AND direccionsucursal = ?");
		q.setParameters(pCiudad, pDireccion);
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}
	public List<Estante> buscarEstantes(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlEstante());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}
	public long eliminarEstante(PersistenceManager manager, long id)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlEstante() + " WHERE idEstante = ? ");
		add.setParameters(id);
		return (long) add.executeUnique();
	}
	
	public List<Indice> calcularIndices(PersistenceManager manager, String pCiudad, String pDireccionSucursal)
	{
		Query q = manager.newQuery(SQL, "SELECT idestante AS identificador, ((cantidadactual/volumenestante)*100) AS indice FROM (SELECT idestante as id, sum(cantidadactual) AS cantidadActual FROM " + persistencia.getSqlCantidadEnEstantes() + " GROUP BY idestante),"+ persistencia.getSqlEstante()+" WHERE id=idestante");
		q.setParameters(pDireccionSucursal,pCiudad);
		return (List<Indice>) q.executeList();
	}
}