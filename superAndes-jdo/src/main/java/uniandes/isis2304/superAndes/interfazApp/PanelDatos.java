package uniandes.isis2304.superAndes.interfazApp;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Clase de interfaz para mostrar los resultados de la ejecuci�n de las 
 * operaciones realizadas por el usuario
 */
@SuppressWarnings("serial")
public class PanelDatos extends JPanel{
	// -----------------------------------------------------------------
	// Atributos de interfaz
	// -----------------------------------------------------------------
	/**
	 * �rea de texto con barras de deslizamiento
	 * Area donde van a aparecer los resultados
	 */
	private JTextArea textArea;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Construye el panel
	 * 
	 */
	public PanelDatos ()
	{
		setBorder (new TitledBorder ("Panel de informaci�n"));
		setLayout( new BorderLayout() );

		textArea = new JTextArea("Aqu� sale el resultado de las operaciones solicitadas");
		textArea.setEditable(false);
		add (new JScrollPane(textArea), BorderLayout.CENTER);
	}
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------

	/**
	 * Actualiza el panel con la informaci�n recibida por par�metro.
	 * @param texto El texto con el que actualiza el �rea
	 */
	public void actualizarInterfaz (String texto)
	{
		textArea.setText(texto);
	}
}
