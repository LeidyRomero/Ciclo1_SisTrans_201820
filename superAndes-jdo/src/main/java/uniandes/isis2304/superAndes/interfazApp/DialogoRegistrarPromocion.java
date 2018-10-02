package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;

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
     * Comando para cancelar la operaci�n
     */
    private final static String CANCELAR = "CANCELAR";
    /**
     * 
     */
	private InterfazSuperAndesApp principal;
	/**
     * Etiqueta nombre
     */
    private JLabel lbDescripcion;

    /**
     * Etiqueta direccion
     */
    private JLabel lbUniDisponibles; 
    
	/**
	 * Etiqueta direccion
	 */
	private JLabel lbCodigoBarras;
	
	/**
	 * Etiqueta fecha de inicio
	 */
	private JLabel lbFechaInicio;
	
	/**
	 * Etiqueta fecha de fin
	 */
	private JLabel lbFechaFin;
	
    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtDescripcion;

    /**
     * Campo de texto para mostrar la direccion
     */
    private JTextField txtUniDisponibles;
    
	/**
	 * Campo de texto para mostrar la direccion
	 */
	private JTextField txtCodBarras;
	
	/**
	 * Campo texto fecha inicio
	 */
	private JTextField txtFechaInicio;
	
	/**
	 * Campo texto fecha fin
	 */
	private JTextField txtFechaFin;


    /**
     * Bot�n registrar
     */
    private JButton btnRegistrar;

    /**
     * Bot�n cancelar
     */
    private JButton btnCancelar;

    

    // --------------------------------------------------------
    // Constructores
    // --------------------------------------------------------

    /**
     * Construye un nuevo dialogo para agregar un cr�dito
     * @param principalP es la referencia al panel padre del di�logo
     */
    public DialogoRegistrarPromocion( InterfazSuperAndesApp pPrincipal )
    {
        principal = pPrincipal;

        setLayout( new GridLayout( 7, 2 ) );
        this.setSize( 600, 200 );
        setTitle( "Agregar proveedor" );

        lbDescripcion = new JLabel( "Descripci�n:" );
        txtDescripcion = new JTextField( );

        add( lbDescripcion );
        add( txtDescripcion );

        lbUniDisponibles = new JLabel( "Unidades disponibles:" );
        txtUniDisponibles = new JTextField( );
        
        add( lbUniDisponibles );
        add( txtUniDisponibles );
        
		lbCodigoBarras = new JLabel( "C�digo de barras:" );
		txtCodBarras = new JTextField( );

		add( lbCodigoBarras );
		add( txtCodBarras );
		
		lbFechaInicio = new JLabel("Fecha de inicio: (2018-5-6)");
		txtFechaInicio = new JTextField();
		
		add(lbFechaInicio);
		add(txtFechaInicio);
		
		lbFechaFin = new JLabel("Fecha de fin: (2018-5-6)");
		txtFechaFin = new JTextField();
		
		add(lbFechaFin);
		add(txtFechaFin);
        
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
			principal.adicionarPersona2(txtCodBarras.getText(), txtUniDisponibles.getText(), txtDescripcion.getText());
			this.dispose();
		}
		else
		{
			this.dispose();
		}
	}
}
