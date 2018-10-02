package uniandes.isis2304.superAndes.interfazApp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DialogoRegistrarEmpresa extends JDialog implements ActionListener{
	 /**
     * Comando para agregar un producto
     */
    private final static String TERINAR = "TERINAR";

    /**
     * Comando para cancelar la operaci�n
     */
    private final static String CANCELAR = "CANCELAR";
	private InterfazSuperAndesApp principal;
	/**
     * Etiqueta tipo
     */
    private JLabel lbNit;

    /**
     * Etiqueta volumen
     */
    private JLabel lbDireccion;


    /**
     * Campo de texto para mostrar el nombre
     */
    private JTextField txtNit;

    /**
     * Campo de texto para mostrar la presentacion
     */
    private JTextField txtDireccion;
   
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
    public DialogoRegistrarEmpresa( InterfazSuperAndesApp principalP )
    {
        principal = principalP;

        setLayout( new GridLayout( 7, 2 ) );
        this.setSize( 600, 200 );
        setTitle( "Agregar bodega" );

        lbNit = new JLabel( "Tipo:" );
        txtNit = new JTextField( );

        add( lbNit );
        add( txtNit );

        lbDireccion = new JLabel( "Volumen:" );
        txtDireccion = new JTextField( );
        
        add( lbDireccion );
        add( txtDireccion );
        
        btnRegistrar = new JButton( "Terminar" );
        btnRegistrar.setActionCommand( TERINAR );
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
		
	}
}
