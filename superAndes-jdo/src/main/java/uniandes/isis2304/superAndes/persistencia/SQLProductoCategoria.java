package uniandes.isis2304.superAndes.persistencia;

import javax.jdo.*;

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
	public long agregarProductoCategoria(PersistenceManager manager, String pNombreCategoria, int pCodigoBarras)
	{
		Query add = manager.newQuery(SQL, "INSERT INTO "+persistencia.getSqlProductoCategoria()+" (nombre_categoria, codigo_barras) values (?,?)");
		add.setParameters(pNombreCategoria,pCodigoBarras);
		return (long) add.executeUnique();
	}
	
}