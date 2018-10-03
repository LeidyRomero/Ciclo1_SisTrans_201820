package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DialogoRegistrarBodega extends JDialog implements ActionListener{
	/**
	 * Comando para agregar un producto
	 */
	private final static String REGISTRAR = "REGISTRAR";

	/**
	 * Comando para cancelar la operación
	 */
	private final static String CANCELAR = "CANCELAR";
	private InterfazSuperAndesApp principal;
	/**
	 * Etiqueta tipo
	 */
	private JLabel lbTipo;

	/**
	 * Etiqueta volumen
	 */
	private JLabel lbVolumen;

	/**
	 * Etiqueta peso
	 */
	private JLabel lbPeso;
	/**
	 * Etiqueta direccion
	 */
	private JLabel lbDireccionBodega;

	/**
	 * Etiqueta direccionSucursal
	 */
	private JLabel lbDireccionSucursal;


	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtTipo;

	/**
	 * Campo de texto para mostrar la presentacion
	 */
	private JTextField txtVolumen;
	/**
	 * Campo de texto para mostrar la marca
	 */
	private JTextField txtPeso;

	/**
	 * Campo de texto para mostrar el precio unitario
	 */
	private JTextField txtDireccionBodega;

	/**
	 * Campo de texto para mostrar el precio unidad medida
	 */
	private JTextField txtDireccionSucursal;

	/**
	 * Botón registrar
	 */
	private JButton btnRegistrar;

	/**
	 * Botón cancelar
	 */
	private JButton btnCancelar;

	/**
	 * Etiqueta ciudad
	 */
	private JLabel lbCiudad;

	/**
	 * Campo de texto para mostrar el nombre
	 */
	private JTextField txtCiudad;


	// --------------------------------------------------------
	// Constructores
	// --------------------------------------------------------

	/**
	 * Construye un nuevo dialogo para agregar un crédito
	 * @param principalP es la referencia al panel padre del diálogo
	 */
	public DialogoRegistrarBodega( InterfazSuperAndesApp principalP )
	{
		principal = principalP;

		setLayout( new GridLayout( 7, 2 ) );
		this.setSize( 600, 200 );
		setTitle( "Agregar bodega" );

		lbTipo = new JLabel( "Tipo:" );
		txtTipo = new JTextField( );

		add( lbTipo );
		add( txtTipo );

		lbVolumen = new JLabel( "Volumen:" );
		txtVolumen = new JTextField( );

		add( lbVolumen );
		add( txtVolumen );

		lbPeso = new JLabel( "Peso:" );
		txtPeso = new JTextField( );

		add( lbPeso );
		add( txtPeso );

		lbDireccionBodega = new JLabel( "Direccion de la bodega:" );
		txtDireccionBodega = new JTextField( );

		add( lbDireccionBodega );
		add( txtDireccionBodega );

		lbDireccionSucursal = new JLabel( "Direccion de la sucursal:" );
		txtDireccionSucursal = new JTextField( );

		add( lbDireccionSucursal );
		add( txtDireccionSucursal );

		lbCiudad = new JLabel( "Ciudad:" );
		txtCiudad = new JTextField( );

		add( lbCiudad );
		add( txtCiudad );

		btnRegistrar = new JButton( "Registrar" );
		btnRegistrar.setActionCommand( REGISTRAR );
		btnRegistrar.addActionListener( this );

		btnCancelar = new JButton( "Cancelar" );
		btnCancelar.setActionCommand( CANCELAR );
		btnCancelar.addActionListener( this );

		add( btnRegistrar );
		add( btnCancelar );

		setModal( true );
		setLocationRelativeTo( null );
	}
	public void actionPerformed(ActionEvent e) {
		if( e.getActionCommand( ).equals( REGISTRAR ) )
		{
			try
			{
				double peso = Double.parseDouble(txtPeso.getText());
				double volumen = Double.parseDouble(txtVolumen.getText());
				principal.adicionarBodega2(txtTipo.getText(),volumen,peso,txtDireccionBodega.getText(),txtDireccionSucursal.getText(),txtCiudad.getText());
				this.dispose();
			}
			catch(NumberFormatException ex)
			{
				JOptionPane.showMessageDialog (this, "Valores ingresados no validos", "Agregar bodega: no exitoso", JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			this.dispose();
		}
	}
}
