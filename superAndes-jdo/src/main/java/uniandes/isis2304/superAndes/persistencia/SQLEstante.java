package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Bodega;
import uniandes.isis2304.superAndes.negocio.Estante;

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
	//TODO RF6 - Registrar un estante a una sucursal
	public long agregarEstante(PersistenceManager manager, String pTipoEstante,double pVolumen, long pId, double pPeso, double pNivelAbastecimiento, String pDireccionSucursal, String pCiudad )
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlEstante()+"(tipo_estante,volumen_estante,id_estante,peso_estante,nivel_abastecimiento, direccion_sucursal, ciudad) values (?,?,?,?,?,?,?)");
		q.setParameters(pTipoEstante, pVolumen,pId,pPeso,pNivelAbastecimiento,pDireccionSucursal,pCiudad);
		return (long) q.executeUnique();
	} 
	
	//TODO RFC3 - Mostrar el indice de ocupacion de cada estante
	public List<Estante> buscarEstantes(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlEstante());
		q.setResultClass(Estante.class);
		return (List<Estante>) q.executeList();
	}
}