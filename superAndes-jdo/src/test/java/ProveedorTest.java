import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.*;

import java.io.FileReader;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOProveedor;


public class ProveedorTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(ProveedorTest.class.getName());
	
	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD tambi�n
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
	/**
	 * La clase que se quiere probar
	 */
    private SuperAndes superAndes;
	
    /* ****************************************************************
	 * 			M�todos de prueba para la tabla Proveedor - Creaci�n y borrado
	 *****************************************************************/
	/**
	 * M�todo que prueba las operaciones sobre la tabla Proveedor
	 * 1. Adicionar un tipo de bebida
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un tipo de bebida por su identificador
	 * 4. Borrar un tipo de bebida por su nombre
	 */
    @Test
	public void CRDProveedorTest() 
	{
    	// Probar primero la conexi�n a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Proveedor");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Proveedor incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Proveedor incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los proveedores con tabla vac�a
			List <VOProveedor> lista = superAndes.darVOProveedores();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los proveedores con un proveedor en la lista
			int nitProveedor1 = 1234567890;
			VOProveedor proveedor1 = superAndes.adicionarProveedor(nitProveedor1, "Alpina");
			lista = superAndes.darVOProveedores();
			assertEquals ("Debe haber un proveedor creado !!", 1, lista.size ());
			//assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", proveedor1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			int nitProveedor2 = 1234567809;
			VOProveedor prpveedor2 = superAndes.adicionarProveedor(nitProveedor2, "Doria");
			lista = superAndes.darVOProveedores();
			assertEquals ("Debe haber dos proveedores creados !!", 2, lista.size ());
			assertTrue ("El primer proveedor adicionado debe estar en la tabla", proveedor1.equals (lista.get (0)) || proveedor1.equals (lista.get (1)));
			assertTrue ("El segundo proveedor adicionado debe estar en la tabla", prpveedor2.equals (lista.get (0)) || prpveedor2.equals (lista.get (1)));

			// Prueba de eliminaci�n de un tipo de bebida, dado su identificador
			long tbEliminados = superAndes.eliminarProveedorPorNit(nitProveedor1);
			assertEquals ("Debe haberse eliminado proveedor !!", 1, tbEliminados);
			lista = superAndes.darVOProveedores();
			assertEquals ("Debe haber un solo tipo de bebida !!", 1, lista.size ());
			assertFalse ("El primer proveedor adicionado NO debe estar en la tabla", proveedor1.equals (lista.get (0)));
			assertTrue ("El segundo proveedor adicionado debe estar en la tabla", prpveedor2.equals (lista.get (0)));
			
			// Prueba de eliminaci�n de un tipo de bebida, dado su identificador
			tbEliminados = superAndes.eliminarProveedorPorNit(nitProveedor2);
			assertEquals ("Debe haberse eliminado un proveedor !!", 1, tbEliminados);
			lista = superAndes.darVOProveedores();
			assertEquals ("La tabla debi� quedar vac�a !!", 0, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de operaciones sobre la tabla Proveedor.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Proveedor");
		}
		finally
		{
//			superAndes.limpiarParranderos();
//    		superAndes.cerrarUnidadPersistencia ();    		
		}
	}

    /**
     * M�todo de prueba de la restricci�n de unicidad sobre el nombre de Proveedor
     */
	@Test
	public void unicidadProveedorTest() 
	{
    	// Probar primero la conexi�n a la base de datos
		try
		{
			log.info ("Probando la restricci�n de UNICIDAD del nombre del proveedor");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Proveedor incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Proveedor incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOProveedor> lista = superAndes.darVOProveedores();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			int nitProveedor1 = 1234567890;
			VOProveedor proveedor1 = superAndes.adicionarProveedor(nitProveedor1, "Alpina");
			lista = superAndes.darVOProveedores();
			assertEquals ("Debe haber un proveedor creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", proveedor1, lista.get (0));

			VOProveedor proveedor2 = superAndes.adicionarProveedor(nitProveedor1, "Doria");
			assertNull ("No puede adicionar dos tipos de bebida con el mismo nombre !!", proveedor2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de UNICIDAD sobre la tabla Proveedor.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Proveedor");
		}    				
		finally
		{
//			superAndes.limpiarParranderos();
//    		superAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/* ****************************************************************
	 * 			M�todos de configuraci�n
	 *****************************************************************/
    /**
     * Lee datos de configuraci�n para la aplicaci�n, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuraci�n deseada
     * @param archConfig - Archivo Json que contiene la configuraci�n
     * @return Un objeto JSON con la configuraci�n del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontr� un archivo de configuraci�n de tablas v�lido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido");			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de tablas v�lido: ", "ProveedorTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	

}
