package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.superAndes.negocio.Empresa;
import uniandes.isis2304.superAndes.negocio.Producto;

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
	public List<Empresa> buscarEmpresas(PersistenceManager manager)
	{
		Query q = manager.newQuery(SQL, "SELECT * FROM " + persistencia.getSqlEmpresa());
		q.setResultClass(Empresa.class);
		return (List<Empresa>) q.executeList();
	}
	public long eliminarEmpresa(PersistenceManager manager, String pCorreo)
	{
		Query add = manager.newQuery(SQL, "DELETE FROM " + persistencia.getSqlEmpresa()+ " WHERE pCorreo = ?");
		add.setParameters(pCorreo);
		return (long) add.executeUnique();
	}
}