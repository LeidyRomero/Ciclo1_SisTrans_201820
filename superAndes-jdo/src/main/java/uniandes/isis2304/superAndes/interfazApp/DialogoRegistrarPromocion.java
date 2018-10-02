package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogoRegistrarPromocion extends JDialog implements ActionListener{
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
    private JLabel lbCorreo; 
    
	/**
	 * Etiqueta direccion
	 */
	private JLabel lbDocumento; 

    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtNombre;

    /**
     * Campo de texto para mostrar la direccion
     */
    private JTextField txtCorreo;
    
	/**
	 * Campo de texto para mostrar la direccion
	 */
	private JTextField txtDocumento;


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
    public DialogoRegistrarPromocion( InterfazSuperAndesApp pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new GridLayout( 7, 2 ) );
        this.setSize( 600, 200 );
        setTitle( "Agregar proveedor" );

        lbNombre = new JLabel( "Nombre:" );
        txtNombre = new JTextField( );

        add( lbNombre );
        add( txtNombre );

        lbCorreo = new JLabel( "Correo:" );
        txtCorreo = new JTextField( );
        
        add( lbCorreo );
        add( txtCorreo );
        
		lbDocumento = new JLabel( "Documento identificación:" );
		txtDocumento = new JTextField( );

		add( lbDocumento );
		add( txtDocumento );
        
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
			principal.adicionarPersona2(txtDocumento.getText(), txtCorreo.getText(), txtNombre.getText());
			this.dispose();
		}
		else
		{
			this.dispose();
		}
	}
}
