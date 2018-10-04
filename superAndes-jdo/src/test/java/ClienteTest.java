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
import uniandes.isis2304.superAndes.negocio.VOCliente;

public class ClienteTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ClienteTest.class.getName());
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
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
	 * 			Métodos de prueba para la tabla Cliente - Creación y borrado
	 *****************************************************************/
	/**
	 * Método que prueba las operaciones sobre la tabla Cliente
	 * 1. Adicionar un tipo de bebida
	 * 2. Listar el contenido de la tabla con 0, 1 y 2 registros insertados
	 * 3. Borrar un tipo de bebida por su identificador
	 * 4. Borrar un tipo de bebida por su nombre
	 */
    @Test
	public void CRDClienteTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando las operaciones CRD sobre Cliente");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de CRD de Cliente incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRD de Cliente incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
    	try
		{
			// Lectura de los proveedores con tabla vacía
			List <VOCliente> lista = superAndes.darVOClientes();
			assertEquals ("No debe haber tipos de bebida creados!!", 0, lista.size ());

			// Lectura de los proveedores con un proveedor en la lista
			String correo1 = "mj.ocampov@uniandes.edu.co";
			VOCliente cliente1 = superAndes.adicionarCliente(correo1, "María Ocampo");
			lista = superAndes.darVOClientes();
			assertEquals ("Debe haber un cliente creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", cliente1, lista.get (0));

			// Lectura de los tipos de bebida con dos tipos de bebida adicionados
			String correo2 = "lj.romero@uniandes.edu.co";
			VOCliente cliente2 = superAndes.adicionarCliente(correo2, "Leidy Romero");
			lista = superAndes.darVOClientes();
			assertEquals ("Debe haber dos clientes creados !!", 2, lista.size ());
			assertTrue ("El primer cliente adicionado debe estar en la tabla", cliente1.equals (lista.get (0)) || cliente1.equals (lista.get (1)));
			assertTrue ("El segundo cliente adicionado debe estar en la tabla", cliente2.equals (lista.get (0)) || cliente2.equals (lista.get (1)));

			// Prueba de eliminación de un tipo de bebida, dado su identificador
			long tbEliminados = superAndes.eliminarClientePorCorreo(correo1);
			assertEquals ("Debe haberse eliminado cliente !!", 1, tbEliminados);
			lista = superAndes.darVOClientes();
			assertEquals ("Debe haber un solo tipo de bebida !!", 1, lista.size ());
			assertFalse ("El primer cliente adicionado NO debe estar en la tabla", cliente1.equals (lista.get (0)));
			assertTrue ("El segundo cliente adicionado debe estar en la tabla", cliente2.equals (lista.get (0)));
			
			// Prueba de eliminación de un tipo de bebida, dado su identificador
			tbEliminados = superAndes.eliminarClientePorCorreo(correo2);
			assertEquals ("Debe haberse eliminado un cliente !!", 1, tbEliminados);
			lista = superAndes.darVOClientes();
			assertEquals ("La tabla debió quedar vacía !!", 0, lista.size ());
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Cliente.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas sobre la tabla Cliente");
		}
		finally
		{
//			superAndes.limpiarParranderos ();
//    		superAndes.cerrarUnidadPersistencia ();    		
		}
	}

    /**
     * Método de prueba de la restricción de unicidad sobre el nombre de Cliente
     */
	@Test
	public void unicidadClienteTest() 
	{
    	// Probar primero la conexión a la base de datos
		try
		{
			log.info ("Probando la restricción de UNICIDAD del nombre del tipo de bebida");
			superAndes = new SuperAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			log.info ("Prueba de UNICIDAD de Cliente incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de UNICIDAD de Cliente incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}
		
		// Ahora si se pueden probar las operaciones
		try
		{
			// Lectura de los tipos de bebida con la tabla vacía
			List <VOCliente> lista = superAndes.darVOClientes();
			assertEquals ("No debe haber clientes creados!!", 0, lista.size ());

			// Lectura de los tipos de bebida con un tipo de bebida adicionado
			String correo1 = "mj.ocampov@uniandes.edu.co";
			VOCliente cliente1 = superAndes.adicionarCliente(correo1, "María Ocampo");
			lista = superAndes.darVOClientes();
			assertEquals ("Debe haber un cliente creado !!", 1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", cliente1, lista.get (0));

			VOCliente proveedor2 = superAndes.adicionarCliente(correo1, "Leidy Romero");
			assertNull ("No puede adicionar dos clientes con el mismo correo !!", proveedor2);
		}
		catch (Exception e)
		{
//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de UNICIDAD sobre la tabla Cliente.\n";
			msg += "Revise el log de superAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

    		fail ("Error en las pruebas de UNICIDAD sobre la tabla Cliente");
		}    				
		finally
		{
//			superAndes.limpiarParranderos ();
//    		superAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
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
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "ClienteTest", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }	
}
