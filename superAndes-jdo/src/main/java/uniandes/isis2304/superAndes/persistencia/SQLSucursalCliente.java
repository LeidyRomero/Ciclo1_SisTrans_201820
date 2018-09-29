package uniandes.isis2304.superAndes.persistencia;
class SQLSucursalCliente
{
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra ac� para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;

	public SQLSucursalCliente(PersistenciaSuperAndes persistencia) 
	{
		this.persistencia = persistencia;
	}	
}