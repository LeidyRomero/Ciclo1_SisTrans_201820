package uniandes.isis2304.superAndes.persistencia;

import java.util.List;

import javax.jdo.*;

import uniandes.isis2304.superAndes.negocio.Bodega;

/**
 * 
 * @author lj.romero
 *
 */
class SQLCarrito
{
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaSuperAndes.SQL;
	
	private PersistenciaSuperAndes persistencia;
	
	public SQLCarrito(PersistenciaSuperAndes pPersistencia)
	{
		this.persistencia = pPersistencia;
	}
	//TODO
}