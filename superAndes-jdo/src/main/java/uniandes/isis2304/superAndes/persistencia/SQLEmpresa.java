package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * 
 * @author lj.romero
 *
 */
class SQLEmpresa
{
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLEmpresa (PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	// TODO RF3 - Registrar clientes
	public long agregarEmpresa(PersistenceManager manager, String pDireccionEmpres,int pNitEmpresa, String pCorreo )
	{
		Query q = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlEmpresa()+"(nit_empresa, direccion, correo) values (?,?,?)");
		q.setParameters(pNitEmpresa, pDireccionEmpres, pCorreo);
		return (long) q.executeUnique();
	}
}