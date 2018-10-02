package uniandes.isis2304.superAndes.interfazApp;
/**
 * Clase principal de la interfaz
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.datanucleus.store.types.wrappers.Date;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.superAndes.interfazApp.PanelDatos;
import uniandes.isis2304.superAndes.negocio.*;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.VOProveedor;
import uniandes.isis2304.superAndes.negocio.VOSucursal;

@SuppressWarnings("serial")
public class InterfazSuperAndesApp extends JFrame implements ActionListener{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	/**
	 * Logger para escribir la traza de la ejecuci�n
	 */
	private static Logger log = Logger.getLogger(InterfazSuperAndesApp.class.getName());
	/**
	 * Ruta al archivo de configuraci�n de la interfaz
	 */
	private static final String CONFIGURACION_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
	/**
	 * Ruta al archivo de configuraci�n de los nombres de tablas de la base de datos
	 */
	private static final String CONFIGURACION_TABLAS = "./src/main/resources/config/TablasBD_A.json";
	// -----------------------------------------------------------------
	// Atributos 
	// -----------------------------------------------------------------
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociaci�n a la clase principal del negocio.
	 */
	private SuperAndes superAndes;
	// -----------------------------------------------------------------
	// Atributos de la interfaz
	// -----------------------------------------------------------------
	/**
	 * Objeto JSON con la configuraci�n de interfaz de la app.
	 */
	private JsonObject guiConfig;
	/**
	 * Panel de despliegue de interacci�n para los requerimientos
	 */
	private PanelDatos panelDatos;
	/**
	 * Men� de la aplicaci�n
	 */
	private JMenuBar menuSA;
	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------
	/**
	 * Construye la ventana principal de la aplicaci�n. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazSuperAndesApp()
	{
		// Carga la configuraci�n de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIGURACION_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gr�fica
		configurarFrame ( );

		if (guiConfig != null) 
		{
			crearMenu( guiConfig.getAsJsonArray("menu") );
		}

		String path = guiConfig.get("bannerPath").getAsString();

		tableConfig = openConfig ("Tablas BD", CONFIGURACION_TABLAS);
		superAndes = new SuperAndes(tableConfig);


		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );   
	}
	// -----------------------------------------------------------------
	// Configuracion de la interfaz
	// -----------------------------------------------------------------
	/**
	 * Lee datos de configuraci�n para la aplicacion, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuraci�n deseada
	 * @param archConfig - Archivo Json que contiene la configuraci�n
	 * @return Un objeto JSON con la configuraci�n del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			File aja =new File(archConfig);
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontr� un archivo de configuraci�n v�lido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontr� un archivo de configuraci�n v�lido "+ e);			
			JOptionPane.showMessageDialog(null, "No se encontr� un archivo de configuraci�n de interfaz v�lido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * M�todo para configurar el frame principal de la aplicaci�n
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuraci�n por defecto" );			
			titulo = "SuperAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuraci�n indicada en el archivo de configuraci�n" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}
	/**
	 * M�todo para crear el men� de la aplicaci�n con base em el objeto JSON le�do
	 * Genera una barra de men� y los men�s con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los men�s deseados
	 */
	private void crearMenu(JsonArray jsonMenu )
	{    	
		// Creaci�n de la barra de men�s
		menuSA = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creaci�n de cada uno de los men�s
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creaci�n de cada una de las opciones del men�
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuSA.add( menu );
		}        
		setJMenuBar ( menuSA );	
	}
	// -----------------------------------------------------------------
	// M�todos de interacci�n
	// -----------------------------------------------------------------
	/**
	 * M�todo para la ejecuci�n de los eventos que enlazan el men� con los m�todos de negocio
	 * Invoca al m�todo correspondiente seg�n el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazSuperAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	//--------------------------------------------------------------------------------------
	//* 			M�todos administrativos
	//--------------------------------------------------------------------------------------/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecuci�n
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecuci�n de la operaci�n y recolecci�n de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el n�mero de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecuci�n de la demo y recolecci�n de los resultados
			//TODO Terminar SuperAndes
			//			long eliminados [] = superAndes.limpiarParranderos();
			//			
			//			// Generaci�n de la cadena de caracteres con la traza de la ejecuci�n de la demo
			//			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			//			resultado += eliminados [0] + " Gustan eliminados\n";
			//			resultado += eliminados [1] + " Sirven eliminados\n";
			//			resultado += eliminados [2] + " Visitan eliminados\n";
			//			resultado += eliminados [3] + " Bebidas eliminadas\n";
			//			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			//			resultado += eliminados [5] + " Bebedores eliminados\n";
			//			resultado += eliminados [6] + " Bares eliminados\n";
			//			resultado += "\nLimpieza terminada";
			//   
			//			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		//TODO Poner el modelo conceptual 
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		//TODO Esquema BD Parranderos
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}

	/**
	 * Muestra el script de creaci�n de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		//TODO Script de BD
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		//TODO Arquitectura Referencia
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentaci�n Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		//TODO Javadoc �?
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la informaci�n acerca del desarrollo de esta apicaci�n
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogot�	- Colombia)\n";
		resultado += " * Departamento	de	Ingenier�a	de	Sistemas	y	Computaci�n\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versi�n 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: SuperAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Leidy Romero\n";
		resultado += " * @author Mar�a Ocampo\n";
		resultado += " * Octubre de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Leidy Romero, Mar�a Ocampo\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}

	//--------------------------------------------------------------------------------------
	//* 			M�todos privados para la presentaci�n de resultados y otras operaciones
	//--------------------------------------------------------------------------------------

	/**
	 * Genera una cadena de caracteres con la descripci�n de la excepcion e, haciendo �nfasis en las excepcionsde JDO
	 * @param e - La excepci�n recibida
	 * @return La descripci�n de la excepci�n, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicaci�n
	 * @param e - La excepci�n generada
	 * @return La cadena con la informaci�n de la excepci�n y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecuci�n\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para m�s detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como par�metro con la aplicaci�n por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//
	//        METODOS
	//

	//-------------------------------------------------------------------------------
	//  Metodos para manejar PRODUCTOS
	//-------------------------------------------------------------------------------
	public void adicionarProducto()
	{
		DialogoRegistrarProducto registrar = new DialogoRegistrarProducto(this);
		registrar.setVisible( true );
	}
	public void adicionarProducto2(String pNombre, String pMarca, double pPrecioUnitario, String pPresentacion, double pPrecioUnidadMedida, int pCantidadPresentacion, String pUnidadMedida, String pEspecificacion, int pCodigoBarras, String pCalidad, String pFechaVencimiento)
	{
		try
		{
			if(!pNombre.equals("") && !pMarca.equals("") && !pPresentacion.equals("") && pPrecioUnitario>0 && !pPresentacion.equals("")&&pPrecioUnidadMedida>0 && pCantidadPresentacion>0 && !pUnidadMedida.equals("") && pCodigoBarras>=0 )//TODO continuar validaciones
			{
				superAndes.adicionarProducto(pNombre, pMarca, pPresentacion, pUnidadMedida, pEspecificacion, pCalidad, pPrecioUnitario, pPrecioUnidadMedida,pCantidadPresentacion, pCodigoBarras,formatoFecha(pFechaVencimiento));
			}
			else
			{
				JOptionPane.showMessageDialog (this, "Valores obligatorios no ingresados", "Agregar producto: no exitoso", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e)
		{

		}
	}
	public Date formatoFecha( String cadena ) throws ParseException
	{
		SimpleDateFormat formato1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
		return (Date)formato1.parse( cadena );
	}
	//-------------------------------------------------------------------------------
	//  Metodos para manejar CATEGORIA
	//-------------------------------------------------------------------------------
	public void adicionarCategoria(String pNombre)
	{
		Categoria encontro = superAndes.adicionarCategoria(pNombre);
		if(encontro!=null)
			JOptionPane.showMessageDialog(this, "La categoria ya existe","Registro no exitoso", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this, "Registro exitoso","Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
	}
	//-------------------------------------------------------------------------------
	//  Metodos para manejar TIPO
	//-------------------------------------------------------------------------------
	public void adicionarTipo(String pNombre, String pCategoria)
	{
		TipoProducto encontro = superAndes.adicionarTipoProducto(pNombre, pCategoria);
		if(encontro!=null)
			JOptionPane.showMessageDialog(this, "El tipo ya existe","Registro no exitoso", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this, "Registro exitoso","Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
	}
	//-------------------------------------------------------------------------------
	//  Metodos para manejar PRODUCTO CATEGORIA
	//-------------------------------------------------------------------------------
	public void adicionarProductoCategoria(String pNombreCategoria, String pCodigoBarras)
	{
		ProductoCategoria encontro = superAndes.adicionarProductoCategoria(pNombreCategoria,Integer.parseInt( pCodigoBarras));
		if(encontro!=null)
			JOptionPane.showMessageDialog(this, "El producto ya esta registrado en esta categoria","Registro no exitoso", JOptionPane.ERROR_MESSAGE);
		else
			JOptionPane.showMessageDialog(this, "Registro exitoso","Registro exitoso", JOptionPane.INFORMATION_MESSAGE);
	}
	//-------------------------------------------------------------------------------
	//  Metodos para manejar BODEGA
	//-------------------------------------------------------------------------------
	public void adicionarBodega()
	{
		DialogoRegistrarBodega registrar = new DialogoRegistrarBodega(this);
		registrar.setVisible( true );

	}
	public void adicionarBodega2(String pTipo,double pVolumen, double pPeso, String pDireccionBodega, String pDireccionSucursal, String pCiudad)
	{
		try
		{
			if(!pTipo.equals("") && !pDireccionBodega.equals("") && !pDireccionSucursal.equals("") && !pCiudad.equals("") && pVolumen>0 && pPeso >0)//TODO continuar validaciones
			{
				superAndes.adicionarBodega(pTipo,pVolumen,pPeso, pDireccionBodega, pDireccionSucursal, pCiudad);
			}
			else
			{
				JOptionPane.showMessageDialog (this, "Valores obligatorios no ingresados", "Agregar bodega: no exitoso", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e)
		{

		}
	}
	public void buscarIndiceBodega()
	{
		superAndes.buscarIndiceBodega();
	}
	//-------------------------------------------------------------------------------
	//  Metodos para manejar ESTANTE
	//-------------------------------------------------------------------------------
	public void adicionarEstante()
	{
		DialogoRegistrarEstante registrar = new DialogoRegistrarEstante(this);
		registrar.setVisible( true );
	}
	public void adicionarEstante2(String pTipoEstante,double pVolumen,long pId, double pPeso, double pNivelAbastecimiento, String pDireccionSucursal,String pCiudad)
	{
		try
		{
			if(!pTipoEstante.equals("") && !pDireccionSucursal.equals("")&& !pCiudad.equals("")&& pVolumen>0 && pPeso>0 && pId>=0 && pNivelAbastecimiento>0)//TODO continuar validaciones
			{
				superAndes.adicionarEstante(pTipoEstante,pVolumen, pId,pPeso, pNivelAbastecimiento, pDireccionSucursal, pCiudad);
			}
			else
			{
				JOptionPane.showMessageDialog (this, "Valores obligatorios no ingresados", "Agregar estante: no exitoso", JOptionPane.ERROR_MESSAGE);
			}
		}
		catch(Exception e)
		{

		}
	}

	//-------------------------------------------------------------------------------
	//  Metodos para manejar SUCURSAL
	//-------------------------------------------------------------------------------
	public void adicionarSucursal()
	{
		DialogoRegistrarSucursal registrar = new DialogoRegistrarSucursal(this);
		registrar.setVisible( true );
	}
	public void adicionarSucursal2(String tamanio, String direccion, String ciudad, String nombre)
	{
		try
		{
			if(!tamanio.equals("") && !direccion.equals("") && !ciudad.equals("") && !nombre.equals(""))
			{
				VOSucursal sc = superAndes.adicionarSucursal(tamanio, direccion, ciudad, nombre);
				if(sc == null)
				{
					throw new Exception ("No se pudo crear una sucursal con direccion: " + direccion + ", "+ciudad);
				}
				String resultado = "En adicionarSucursal\n\n";
				resultado += "Sucursal adicionada exitosamente: " + sc;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
		}
		catch(Exception e)
		{

		}
	}

	//-------------------------------------------------------------------------------
	//  Metodos para manejar PROVEEDOR
	//-------------------------------------------------------------------------------
	public void adicionarProveedor()
	{
		DialogoRegistrarProveedor registrar = new DialogoRegistrarProveedor(this);
		registrar.setVisible( true );
	}
	public void adicionarProveedor2(int nitProveedor, String nombreProveedor)
	{
		try
		{
			if(nitProveedor > 0 && !nombreProveedor.equals(""))
			{
				System.out.println("Pre negocio");
				VOProveedor pv = superAndes.adicionarProveedor(nitProveedor, nombreProveedor);
				if(pv == null)
				{
					throw new Exception ("No se pudo crear un proveedor con nit: " + nitProveedor);
				}
				String resultado = "En adicionarProveedor\n\n";
				resultado += "Proveedor adicionado exitosamente: " + pv;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
		}
		catch(Exception e)
		{

		}
	}

	//TODO CRUD Productos
	//TODO CRUD Clientes
	//TODO CRUD Sucursal
	//TODO CRUD Bodega
	//TODO CRUD Estante
	//TODO CRUD Promoci�n
	//TODO CRUD Pedidos
	//TODO CRUD Ventas
	public void buscarIndiceEstante()
	{
		superAndes.buscarIndiceEstante();
	}
	// -----------------------------------------------------------------
	// Programa principal
	// -----------------------------------------------------------------
	/**
	 * Este m�todo ejecuta la aplicaci�n, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por l�nea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazSuperAndesApp interfaz = new InterfazSuperAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}


}
