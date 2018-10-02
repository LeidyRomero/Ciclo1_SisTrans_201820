package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogoRegistrarProveedor extends JDialog implements ActionListener{
	 /**
     * Comando para agregar un producto
     */
    private final static String REGISTRAR = "REGISTRAR";

    /**
     * Comando para cancelar la operación
     */
    private final static String CANCELAR = "CANCELAR";
    /**
     * 
     */
	private InterfazSuperAndesApp principal;
	/**
     * Etiqueta nombre
     */
    private JLabel lbNombre;

    /**
     * Etiqueta direccion
     */
    private JLabel lbNit; 

    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para mostrar la direccion
     */
    private JTextField txtNit;

    /**
     * Botón registrar
     */
    private JButton btnRegistrar;

    /**
     * Botón cancelar
     */
    private JButton btnCancelar;

    

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo dialogo para agregar un crédito
     * @param principalP es la referencia al panel padre del diálogo
     */
    public DialogoRegistrarProveedor( InterfazSuperAndesApp pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new GridLayout( 7, 2 ) );
        this.setSize( 600, 200 );
        setTitle( "Agregar proveedor" );

        lbNombre = new JLabel( "Nombre:" );
        txtNombre = new JTextField( );

        add( lbNombre );
        add( txtNombre );

        lbNit = new JLabel( "NIT:" );
        txtNit = new JTextField( );
        
        add( lbNit );
        add( txtNit );
        
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
			System.out.println("Pre interfaz");
			principal.adicionarProveedor2(Integer.parseInt(txtNit.getText()), txtNombre.getText());
		}
		else
		{
			this.dispose();
		}
	}
}
