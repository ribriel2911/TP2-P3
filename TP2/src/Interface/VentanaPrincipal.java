package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JRadioButton;

public class VentanaPrincipal {

	private JFrame frame;
	private MostrarMapa frameMap;
	private AgregarCiudad frameAgregar;
	private Busqueda frameBusqueda;
	private AgregarRuta frameAgregarR;
	private Datos datos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal window = new VentanaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		datos = new Datos();
		
		frameMap = new MostrarMapa(datos);
		JInternalFrame internalFrameLeft = frameMap._frame;
		frame.getContentPane().add(internalFrameLeft);	
		internalFrameLeft.setVisible(true);
		
		frameAgregar = new AgregarCiudad(datos);
		JInternalFrame internalFrameUpRight = frameAgregar._frame;
		frame.getContentPane().add(internalFrameUpRight);
		internalFrameUpRight.setVisible(true);
		
		frameBusqueda = new Busqueda(datos);
		JInternalFrame internalFrameDownRight = frameBusqueda._frame;
		frame.getContentPane().add(internalFrameDownRight);
		internalFrameDownRight.setVisible(true);
	}
}
