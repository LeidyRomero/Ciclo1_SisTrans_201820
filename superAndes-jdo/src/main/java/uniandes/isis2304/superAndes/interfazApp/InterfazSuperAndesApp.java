package uniandes.isis2304.superAndes.interfazApp;
/**
 * Clase principal de la interfaz
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDODataStoreException;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.Timer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.superAndes.negocio.Bodega;
import uniandes.isis2304.superAndes.negocio.Categoria;
import uniandes.isis2304.superAndes.negocio.Estante;
import uniandes.isis2304.superAndes.negocio.Producto;
import uniandes.isis2304.superAndes.negocio.Promocion;
import uniandes.isis2304.superAndes.negocio.SuperAndes;
import uniandes.isis2304.superAndes.negocio.TipoProducto;
import uniandes.isis2304.superAndes.negocio.VOCliente;
import uniandes.isis2304.superAndes.negocio.VOComprados;
import uniandes.isis2304.superAndes.negocio.VOEmpresa;
import uniandes.isis2304.superAndes.negocio.VOOrdenPedido;
import uniandes.isis2304.superAndes.negocio.VOPersonaNatural;
import uniandes.isis2304.superAndes.negocio.VOPromocion;
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
	/**
	 * Segundos 
	 */
	private static double tiempoUltimaOperacion;
	
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
	private static PanelDatos panelDatos;
	/**
	 * Men� de la aplicaci�n
	 */
	private JMenuBar menuSA;

	private JComboBox<String> cbBuscarProductos;
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
		if(!evento.equals("comboBoxChanged"))
		{
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
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}

	/**
	 * Muestra el script de creaci�n de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentaci�n Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
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
			e.printStackTrace();
		}
	}
	//-------------------------------------------------------------------------------
	//  Metodos para manejar PRODUCTOS
	//-------------------------------------------------------------------------------
	public void adicionarProducto()
	{
		DialogoRegistrarProducto registrar = new DialogoRegistrarProducto(this);
		registrar.setVisible( true );
	}
	public void adicionarProducto2(int pCantidadMinima,String pNombre, String pMarca, double pPrecioUnitario, String pPresentacion, double pPrecioUnidadMedida, int pCantidadPresentacion, String pUnidadMedida, String pCodigoBarras, String pCalidad, Timestamp pFechaVencimiento, String pPeso, String pVolumen, String pCategoria)
	{
		try
		{
			if(pCantidadMinima>=0 && !pCategoria.equals("") && !pNombre.equals("") && !pMarca.equals("") && !pPresentacion.equals("") && pPrecioUnitario>0 && !pPresentacion.equals("")&&pPrecioUnidadMedida>0 && pCantidadPresentacion>0 && !pUnidadMedida.equals("") && !pCodigoBarras.equals("") && !pPeso.equals("") && !pVolumen.equals(""))
			{
				Producto registrado = superAndes.adicionarProducto(pCantidadMinima,pNombre, pMarca, pPresentacion, pUnidadMedida, pCalidad, pPrecioUnitario, pPrecioUnidadMedida,pCantidadPresentacion, pCodigoBarras,pFechaVencimiento, pPeso, pVolumen, pCategoria);
				if(registrado!=null)
				{
					JOptionPane.showMessageDialog (this, "Producto registrado", "Agregar producto: exitoso", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "En adicionarProducto\n\n";
					resultado += "Producto adicionado exitosamente: " + registrado.getCodBarras();
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
					JOptionPane.showMessageDialog (this, "Producto no registrado", "Agregar producto: no exitoso", JOptionPane.ERROR_MESSAGE);
			}
			else if(!isHexNumber(pCodigoBarras))
			{
				JOptionPane.showMessageDialog (this, "Codigo de barras invalido", "Agregar producto: no exitoso", JOptionPane.ERROR_MESSAGE);	
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
	private static boolean isHexNumber (String cadena) {
		try {
			Long.parseLong(cadena, 16);
			return true;
		}
		catch (NumberFormatException ex) {
			// Error handling code...
			return false;
		}
	}
	public java.util.Date formatoFecha( String cadena ) throws ParseException
	{
		SimpleDateFormat formato1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm");
		return formato1.parse( cadena );
	}

	//-------------------------------------------------------------------------------
	//  Metodos para manejar CATEGORIA
	//-------------------------------------------------------------------------------
	public void adicionarCategoria()
	{
		Categoria registrado = superAndes.adicionarCategoria(JOptionPane.showInputDialog(this, "Registrar categoria","Categoria:", JOptionPane.QUESTION_MESSAGE));
		if(registrado!=null)
			JOptionPane.showMessageDialog (this, "Categoria registrada", "Agregar categoria: exitoso", JOptionPane.INFORMATION_MESSAGE);
		else
			JOptionPane.showMessageDialog (this, "Categoria no registrada", "Agregar categoria: no exitoso", JOptionPane.ERROR_MESSAGE);

	}
	//-------------------------------------------------------------------------------
	//  Metodos para manejar TIPO
	//-------------------------------------------------------------------------------
	public void adicionarTipo()
	{
		JTextField tipoField = new JTextField(15);
		JTextField categoriaField = new JTextField(15);

		JPanel aux = new JPanel();
		aux.add(new JLabel("Tipo:"));
		aux.add(tipoField);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Categoria:"));
		aux.add(categoriaField);

		int result = JOptionPane.showConfirmDialog(null, aux,"Registrar tipo", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			TipoProducto registrado = superAndes.adicionarTipoProducto(tipoField.getText(), categoriaField.getText());
			if(registrado!=null)
				JOptionPane.showMessageDialog (this, "Tipo registrado", "Agregar tipo: exitoso", JOptionPane.INFORMATION_MESSAGE);
			else
				JOptionPane.showMessageDialog (this, "Tipo no registrado", "Agregar tipo: no exitoso", JOptionPane.ERROR_MESSAGE);

		}
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
			if(!pTipo.equals("") && !pDireccionBodega.equals("") && !pDireccionSucursal.equals("") && !pCiudad.equals("") && pVolumen>0 && pPeso >0)
			{
				Bodega registrado = superAndes.adicionarBodega(pTipo,pVolumen,pPeso, pDireccionBodega, pDireccionSucursal, pCiudad);
				if(registrado!=null)
				{
					JOptionPane.showMessageDialog (this, "Bodega registrado", "Agregar bodega: exitoso", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "En adicionarBodega\n\n";
					resultado += "Bodega adicionada exitosamente: " + registrado.getDireccionBodega();
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
					JOptionPane.showMessageDialog (this, "Bodega no registrado", "Agregar bodega: no exitoso", JOptionPane.ERROR_MESSAGE);
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
		System.out.println("entra");
		JTextField ciudadField = new JTextField(15);
		JTextField direccionField = new JTextField(15);

		JPanel aux = new JPanel();
		aux.add(new JLabel("Ciudad:"));
		aux.add(ciudadField);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Direccion de la sucursal:"));
		aux.add(direccionField);

		int result = JOptionPane.showConfirmDialog(null, aux,"Buscar indice de ocupabilidad bodegas", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			panelDatos.actualizarInterfaz(superAndes.buscarIndiceBodega(direccionField.getText(), ciudadField.getText()));

		}
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
			if(!pTipoEstante.equals("") && !pDireccionSucursal.equals("")&& !pCiudad.equals("")&& pVolumen>0 && pPeso>0 && pId>=0 && pNivelAbastecimiento>0)
			{
				Estante registrado = superAndes.adicionarEstante(pTipoEstante,pVolumen, pId,pPeso, pNivelAbastecimiento, pDireccionSucursal, pCiudad);
				if(registrado!=null)
				{
					JOptionPane.showMessageDialog (this, "Estante registrado", "Agregar estante: exitoso", JOptionPane.INFORMATION_MESSAGE);
					String resultado = "En adicionarEstante\n\n";
					resultado += "Estante adicionada exitosamente: " + registrado.getIdEstante();
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
					JOptionPane.showMessageDialog (this, "Estante no registrado", "Agregar estante: no exitoso", JOptionPane.ERROR_MESSAGE);

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
	public void buscarIndiceEstante()
	{
		JTextField ciudadField = new JTextField(15);
		JTextField direccionField = new JTextField(15);

		JPanel aux = new JPanel();
		aux.add(new JLabel("Ciudad:"));
		aux.add(ciudadField);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Direccion de la sucursal:"));
		aux.add(direccionField);

		int result = JOptionPane.showConfirmDialog(null, aux,"Buscar indice de ocupabilidad estantes", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			panelDatos.actualizarInterfaz(superAndes.buscarIndiceEstante(direccionField.getText(), ciudadField.getText()));
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
			String resultado = "En adicionarProveedor \n\n";
			resultado += e.getMessage();
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	//-------------------------------------------------------------------------------
	//  Metodos para manejar PROMOCION
	//-------------------------------------------------------------------------------
	public void adicionarPromocion()
	{
		String[] opciones = {"Pague X cantidad, lleve Y", "Descuento del P%", "Pague N y lleve el siguiente con D% de descuento", "Pague N unidades, lleve M"};
		JComboBox opcionesPromo = new JComboBox<>(opciones);
		opcionesPromo.addActionListener(this);

		JPanel aux = new JPanel();
		aux.add(new JLabel("Descripci�n:"));
		aux.add(opcionesPromo);

		int result = JOptionPane.showConfirmDialog(null, aux,"Promocion", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			DialogoRegistrarPromocion registrar = new DialogoRegistrarPromocion(this, opcionesPromo.getSelectedItem().toString(), opciones);
			registrar.setVisible( true );
		}
	}
	public void adicionarPromocion2(Timestamp fechaInicio, Timestamp fechaFin, String descripcion, String codBarras, int uniDisponibles, int uniVendidas, String direccionSucursal, String ciudad)
	{
		try
		{
			if(fechaInicio!= null && !descripcion.equals("") && !codBarras.equals("") && !direccionSucursal.equals("") && !ciudad.equals(""))
			{
				VOPromocion pm = superAndes.adicionarPromocion(fechaInicio, fechaFin, descripcion, codBarras, uniDisponibles, uniVendidas, direccionSucursal, ciudad);
				if(pm == null)
				{
					throw new Exception ("No se pudo crear un promoci�n del producto: " + codBarras);
				}
				String resultado = "En adicionarPromocion\n\n";
				resultado += "Promoci�n adicionada exitosamente: " + pm;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
		}
		catch(Exception e)
		{
			String resultado = "En adicionarPromoci�n \n\n";
			resultado += e.getMessage();
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void darPromocionesMasPopulares()
	{
		try
		{
			String resultado = "En consultarPromociones \n\n";
			List<Promocion> proms = superAndes.darPromocionesMasPopulares();
			for(Promocion actual: proms)
			{
				resultado += actual+"\n";
			}

			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
		catch(Exception e)
		{
			String resultado = "En consultarPromociones \n\n";
			resultado += e.getMessage();
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	//-------------------------------------------------------------------------------
	//  Metodos para manejar EMPRESA
	//-------------------------------------------------------------------------------
	public void adicionarEmpresa()
	{
		DialogoRegistrarEmpresa registrar = new DialogoRegistrarEmpresa(this);
		registrar.setVisible( true );
	}
	public void adicionarEmpresa2(String pDireccion, int pNit, String pCorreo, String pNombre)
	{
		try
		{
			if(!pDireccion.equals("") && pNit >= 0 && !pCorreo.equals("") && !pNombre.equals(""))
			{
				VOCliente cl = superAndes.adicionarCliente(pCorreo, pNombre);
				if(cl == null)
				{
					throw new Exception ("No se pudo crear un cliente con correo: " + pCorreo);
				}
				VOEmpresa em = superAndes.adicionarEmpresa(pDireccion, pNit, pCorreo);
				if(em == null)
				{
					throw new Exception ("No se pudo crear una empresa con nit: " + pNit);
				}
				String resultado = "En adicionarEmpresa \n\n";
				resultado += "Empresa adicionada exitosamente: " + em;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
		}
		catch(Exception e)
		{
			String resultado = "En adicionarEmpresa \n\n";
			resultado += e.getMessage();
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	//-------------------------------------------------------------------------------
	//  Metodos para manejar PERSONA NATURAL
	//-------------------------------------------------------------------------------
	public void adicionarPersona()
	{
		DialogoRegistrarPersonaNatural registrar = new DialogoRegistrarPersonaNatural(this);
		registrar.setVisible( true );
	}
	public void adicionarPersona2(String pDocumento, String pCorreo, String pNombre)
	{
		try
		{
			if(!pDocumento.equals("") && !pCorreo.equals("") && !pNombre.equals(""))
			{
				VOCliente cl = superAndes.adicionarCliente(pCorreo, pNombre);
				if(cl == null)
				{
					throw new Exception ("No se pudo crear un cliente con correo: " + pCorreo);
				}
				VOPersonaNatural pn = superAndes.adicionarPersonaNatural(pDocumento, pCorreo);
				if(pn == null)
				{
					throw new Exception ("No se pudo crear una persona natural con documento: " + pDocumento);
				}
				String resultado = "En adicionarPersonaNatural \n\n";
				resultado += "Persona Natural adicionada exitosamente: " + pn;
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
				panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
		}
		catch(Exception e)
		{
			String resultado = "En adicionarPersonaNatural \n\n";
			resultado += e.getMessage();
			resultado += "\n Operaci�n terminada";
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	//-------------------------------------------------------------------------------
	//  Metodos para manejar ORDEN PEDIDO
	//-------------------------------------------------------------------------------
	public void adicionarPedido()
	{
		JTextField txtFechaEspera = new JTextField();
		JTextField txtNitProveedor = new JTextField();
		JTextField txtCiudad = new JTextField();
		JTextField txtDireccionBodega = new JTextField();
		JTextField txtDireccionSucursal = new JTextField();
		JTextField txtCodigoBarras = new JTextField();
		JTextField txtCantidadProducto = new JTextField();
		JTextField txtPrecioAcordado = new JTextField();
		JPanel aux = new JPanel();
		aux.setLayout(new GridLayout(8, 2));
		aux.add(new JLabel("Fecha esperada entrega: (2018-5-6)"));
		aux.add(txtFechaEspera);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Nit del proveedor:"));
		aux.add(txtNitProveedor);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Ciudad de la sucursal:"));
		aux.add(txtCiudad);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Direcci�n de la sucursal:"));
		aux.add(txtDireccionSucursal);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Direcci�n de entrega (bodega):"));
		aux.add(txtDireccionBodega);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("C�digo de barras del producto:"));
		aux.add(txtCodigoBarras);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Cantidad del producto:"));
		aux.add(txtCantidadProducto);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Precio acordado por unidad:"));
		aux.add(txtPrecioAcordado);

		int result = JOptionPane.showConfirmDialog(null, aux,"Orden pedido", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			try
			{
				if(!txtFechaEspera.getText().equals("") && !txtNitProveedor.getText().equals(""))
				{
					Timestamp fechaEsperada = Timestamp.valueOf(txtFechaEspera.getText()+" 00:00:00");
					VOOrdenPedido pd = superAndes.adicionarOrdenPedido(fechaEsperada, Integer.parseInt(txtNitProveedor.getText()), txtCiudad.getText(), txtDireccionSucursal.getText(), txtDireccionBodega.getText(), txtCodigoBarras.getText(), Integer.parseInt(txtCantidadProducto.getText()), Integer.parseInt(txtPrecioAcordado.getText()));
					if(pd == null)
					{
						throw new Exception("No se pudo crear orden de pedido para el producto: "+ txtCodigoBarras.getText());
					}

					String resultado = "En adicionarPedido \n\n";
					resultado += "Pedido adicionado exitosamente: " + pd;
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
					panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");

			}
			catch(Exception e)
			{
				String resultado = "En adicionarPedido \n\n";
				resultado += e.getMessage();
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
	}

	public void registrarLlegadaPedido()
	{
		JTextField minField = new JTextField(15);
		JTextField maxField = new JTextField(15);

		JPanel aux = new JPanel();
		aux.add(new JLabel("Id del pedido:"));
		aux.add(minField);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Calificaci�n:"));
		aux.add(maxField);
		int result = JOptionPane.showConfirmDialog(null, aux,"Llegada pedido", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			try
			{
				if(!minField.getText().equals("") && !maxField.getText().equals(""))
				{
					VOOrdenPedido op = superAndes.llegadaOrdenPedido(Long.parseLong(minField.getText()), maxField.getText());
					if(op == null)
					{
						throw new Exception("No se pudo registrar la llegada del pedido: " + minField.getText());
					}

					String resultado = "En registrarLlegadaPedido \n\n";
					resultado += "Llegada registrada exitosamente: " + op;
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
					panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
			catch(Exception e)
			{
				String resultado = "En registrarLlegadaPedido \n\n";
				resultado += e.getMessage();
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
	}


	//-----------------------------------------------------------------------
	// M�todos para manejar VENTAS
	//-----------------------------------------------------------------------

	public void adicionarVenta()
	{
		JTextField minField = new JTextField(15);
		JTextField maxField = new JTextField(15);
		JTextField corField = new JTextField(15);

		JPanel aux = new JPanel();
		aux.add(new JLabel("C�digo de barras:"));
		aux.add(minField);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Cantidad:"));
		aux.add(maxField);
		aux.add(Box.createHorizontalStrut(15));
		aux.add(new JLabel("Correo cliente:"));
		aux.add(corField);

		int result = JOptionPane.showConfirmDialog(null, aux,"Venta", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			try
			{
				if(!minField.getText().equals("") && !maxField.getText().equals("") && !corField.getText().equals(""))
				{
					VOComprados venta = superAndes.registrarCompra(minField.getText(), Integer.parseInt(maxField.getText()), corField.getText());
					if(venta == null)
					{
						throw new Exception("No se pudo registrar la compra del pedido: " + minField.getText());
					}

					String resultado = "En registrarVenta \n\n";
					resultado += "Venta registrada exitosamente: " + venta;
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
					panelDatos.devolverAbandonarCarrito();
				}
				else
					panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
			catch(Exception e)
			{
				String resultado = "En registrarVenta \n\n";
				resultado += e.getMessage();
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
	}

	public void dineroSucursalEnRango()
	{
		JTextField minField = new JTextField(15);
		JTextField maxField = new JTextField(15);

		JPanel aux = new JPanel();
		aux.add(new JLabel("Fecha inicio: (2018-5-6) "));
		aux.add(minField);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Fecha fin: (2018-5-6) "));
		aux.add(maxField);

		int result = JOptionPane.showConfirmDialog(null, aux,"Venta", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{
			try
			{
				if(!minField.getText().equals("") && !maxField.getText().equals(""))
				{
					List<Object> list = superAndes.dineroSucursalEnRango(Timestamp.valueOf(minField.getText()+" 00:00:00"), Timestamp.valueOf(maxField.getText()+" 00:00:00"));
					if(list == null)
					{
						throw new Exception("No se pudo consultar el dinero de las sucursales en el periodo: " + minField.getText() + ", "+maxField.getText());
					}

					String resultado = "En consultaDinero \n\n";
					for(Object actual: list)
					{
						resultado+= actual+"\n";
					}
					resultado += "\n Operaci�n terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
					panelDatos.actualizarInterfaz("Operaci�n cancelada por el usuario");
			}
			catch(Exception e)
			{
				String resultado = "En consultaDinero \n\n";
				resultado += e.getMessage();
				resultado += "\n Operaci�n terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
		}
	}
	// -----------------------------------------------------------------
	// Carrito ITERACION 2
	// -----------------------------------------------------------------
	public void adicionarCarrito()
	{
		JTextField direccionField = new JTextField(15);
		JTextField ciudadField = new JTextField(15);
		JTextField correoField = new JTextField(15);

		JPanel aux = new JPanel();
		aux.add(new JLabel("Direcci�n sucursal:"));
		aux.add(direccionField);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Ciudad:"));
		aux.add(ciudadField);
		aux.add(Box.createHorizontalStrut(15)); // a spacer
		aux.add(new JLabel("Correo electr�nico:"));
		aux.add(correoField);

		int result = JOptionPane.showConfirmDialog(null, aux,"Registrar carrito", JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) 
		{

			ArrayList<Producto> productos = superAndes.buscarProductosSucursal(direccionField.getText(), ciudadField.getText());
			if(productos!=null)
			{
				JOptionPane.showMessageDialog (this, "Carrito registrado", "Agregar carrito: exitoso", JOptionPane.INFORMATION_MESSAGE);
				panelDatos.actualizar(productos);
				tiempoUltimaOperacion = System.currentTimeMillis();
			}
			else
				JOptionPane.showMessageDialog (this, "Carrito no registrado", "Agregar carrito: no exitoso", JOptionPane.ERROR_MESSAGE);

		}
	}
	public void abandonarCarrito()
	{

	}
	public static void verificarTiempoInactividad()
	{
		double actual = System.currentTimeMillis();
		if((actual-tiempoUltimaOperacion)>=30)
		{
			panelDatos.devolverAbandonarCarrito();
		}
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
			
			Timer timer = new Timer(60000,new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					verificarTiempoInactividad();
				}
			});
			
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}


}
