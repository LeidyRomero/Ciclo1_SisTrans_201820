import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOSucursal;

public class SucursalTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(SucursalTest.class.getName());
	
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
	 * 			M�todos de prueba para la tabla Sucursal - Creaci�n y borrado
	 *****************************************************************/
	/**
	 * M�todo que prueba las operaciones sobre la tabla Sucursal
	 * 1. Adicionar un tipo de bebida
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un tipo de bebida por su identificador
	 * 4. Borrar un tipo de bebida por su nombre
	 */
    @Test
	public void CRDSucursalTest() 
	{
    	// Probar primero la conexi�n a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Sucursal");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Sucursal incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Sucursal incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los proveedores con tabla vac�a
			List <VOSucursal> lista = superAndes.darVOSucursales();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los proveedores con un proveedor en la lista
			String direccion1 = "Calle 7 # 5-74";
			String ciudad1 = "Bogot�";
			VOSucursal sucursal1 = superAndes.adicionarSucursal("55", direccion1, ciudad1, "Hola");
			lista = superAndes.darVOSucursales();
			assertEquals ("Debe haber un Sucursal creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", sucursal1.toString(), lista.get (0).toString());

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String direccion2 = "Calle 7 # 6-74";
			String ciudad2 = "Bogot�";
			VOSucursal sucursal2 = superAndes.adicionarSucursal("25", direccion2, ciudad2, "Hola 2");
			lista = superAndes.darVOSucursales();
			assertEquals ("Debe haber dos sucursals creados !!", 2, lista.size ());
			assertTrue ("El primer sucursal adicionado debe estar en la tabla", sucursal1.equals (lista.get (0)) || sucursal1.equals (lista.get (1)));
			assertTrue ("El segundo sucursal adicionado debe estar en la tabla", sucursal2.equals (lista.get (0)) || sucursal2.equals (lista.get (1)));

			// Prueba de eliminaci�n de un tipo de bebida, dado su identificador
			long tbEliminados = superAndes.eliminarSucursal(direccion1, ciudad1);
			assertEquals ("Debe haberse eliminado sucursal !!", 1, tbEliminados);
			lista = superAndes.darVOSucursales();
			assertEquals ("Debe haber un solo tipo de bebida !!", 1, lista.size ());
			assertFalse ("El primer sucursal adicionado NO debe estar en la tabla", sucursal1.equals (lista.get (0)));
			assertTrue ("El segundo sucursal adicionado debe estar en la tabla", sucursal2.equals (lista.get (0)));
			
			// Prueba de eliminaci�n de un tipo de bebida, dado su identificador
			tbEliminados = superAndes.eliminarSucursal(direccion2, ciudad2);
			assertEquals ("Debe haberse eliminado un sucursal !!", 1, tbEliminados);
			lista = superAndes.darVOSucursales();
			assertEquals ("La tabla debi� quedar vac�a !!", 0, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de operaciones sobre la tabla Sucursal.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Sucursal");
		}
		finally
		{
//			superAndes.limpiarParranderos ();
//    		superAndes.cerrarUnidadPersistencia ();    		
		}
	}

    /**
     * M�todo de prueba de la restricci�n de unicidad sobre el nombre de Sucursal
     */
	@Test
	public void unicidadSucursalTest() 
	{
    	// Probar primero la conexi�n a la base de datos
		try
		{
			log.info ("Probando la restricci�n de UNICIDAD del nombre del tipo de bebida");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Sucursal incompleta. No se pudo conectar a la base de datos !!. La excepci�n generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Sucursal incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los tipos de bebida con la tabla vac�a
			List <VOSucursal> lista = superAndes.darVOSucursales();
			assertEquals ("No debe haber sucursales creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String direccion1 = "Calle 7 # 5-74";
			String ciudad1 = "Bogot�";
			VOSucursal sucursal1 = superAndes.adicionarSucursal("55", direccion1, ciudad1, "Hola");
			lista = superAndes.darVOSucursales();
			assertEquals ("Debe haber un Sucursal creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", sucursal1, lista.get (0));
			
			VOSucursal sucursal2 = superAndes.adicionarSucursal("25", direccion1, ciudad1, "Hola 2");
			assertNull ("No puede adicionar dos sucursales con el mismo correo !!", sucursal2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecuci�n de las pruebas de UNICIDAD sobre la tabla Sucursal.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepci�n";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Sucursal");
		}    				
		finally
		{
//			superAndes.limpiarParranderos ();
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
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de tablas v�lido: ", "SucursalTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
}
