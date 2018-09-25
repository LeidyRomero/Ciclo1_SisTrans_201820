package uniandes.isis2304.superAndes.persistencia;

import com.google.gson.JsonObject;

public class PersistenciaSuperAndes {
	
	/**
	 * Atributo privado que es el �nico objeto de la clase - Patr�n SINGLETON
	 */
	private static PersistenciaSuperAndes instance;
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el �nico objeto PersistenciaParranderos existente - Patr�n SINGLETON
	 */
	public static PersistenciaSuperAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaSuperAndes ();
		}
		return instance;
	}
}
