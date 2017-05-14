package Interface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import Mapeo.Mapa;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal {

	private JFrame frame;
	private VentanaMapa frameMap;
	private Mapa map;
	private JTextField textName;
	protected JTextField textLat;
	protected JTextField textLon;

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
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textName = new JTextField();
		textName.setBounds(455, 11, 119, 20);
		frame.getContentPane().add(textName);
		textName.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(400, 14, 78, 14);
		frame.getContentPane().add(lblNombre);
		
		textLat = new JTextField();
		textLat.setBounds(455, 42, 119, 20);
		frame.getContentPane().add(textLat);
		textLat.setColumns(10);
		
		JLabel lblLatitud = new JLabel("Latitud");
		lblLatitud.setBounds(400, 45, 78, 14);
		frame.getContentPane().add(lblLatitud);
		
		textLon = new JTextField();
		textLon.setBounds(455, 73, 119, 20);
		frame.getContentPane().add(textLon);
		textLon.setColumns(10);
		
		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(400, 76, 78, 14);
		frame.getContentPane().add(lblLongitud);
		
		frameMap = new VentanaMapa(textLat,textLon);
		
		JInternalFrame internalFrame = frameMap._frame;
		frame.getContentPane().add(internalFrame);	
		internalFrame.setVisible(true);
	}
}
