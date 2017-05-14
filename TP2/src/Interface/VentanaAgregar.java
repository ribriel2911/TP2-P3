package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class VentanaAgregar {

	protected	JInternalFrame		_frame;
	private		JTextField			_textName;
	private 	JLabel				_lblNombre;
	private 	JTextField			_textLat;
	private 	JLabel				_lblLatitud;
	private 	JTextField			_textLon;
	private 	JLabel				_lblLongitud;
	private 	JButton				_btnAgregar;
	private		JRadioButton		_rbAgregar;
	
	public VentanaAgregar(Datos d){
		
		_frame = new JInternalFrame();
		_frame.setBounds(400, 0, 200, 200);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		_frame.setBorder(null);
		((BasicInternalFrameUI) _frame.getUI()).setNorthPane(null);
		
		_lblNombre = new JLabel("Nombre");
		_lblNombre.setBounds(5, 14, 78, 14);
		_frame.getContentPane().add(_lblNombre);
		
		_textName = d._textName;
		_textName.setBounds(60, 11, 110, 20);
		_frame.getContentPane().add(_textName);
		_textName.setColumns(10);
		
		_lblLatitud = new JLabel("Latitud");
		_lblLatitud.setBounds(5, 45, 78, 14);
		_frame.getContentPane().add(_lblLatitud);
		
		_textLat = d._textLat;
		_textLat.setBounds(60, 42, 110, 20);
		_frame.getContentPane().add(_textLat);
		_textLat.setColumns(10);
		
		_lblLongitud = new JLabel("Longitud");
		_lblLongitud.setBounds(5, 76, 78, 14);
		_frame.getContentPane().add(_lblLongitud);
		
		_textLon = d._textLon;
		_textLon.setBounds(60, 73, 110, 20);
		_frame.getContentPane().add(_textLon);
		_textLon.setColumns(10);
		
		_rbAgregar = d._rbAgregar;
		_rbAgregar.setBounds(5, 105, 20, 20);
		_frame.getContentPane().add(_rbAgregar);
		
		_btnAgregar = new JButton("Agregar Ciudad");
		_btnAgregar.setBounds(25, 105, 150, 23);
		_frame.getContentPane().add(_btnAgregar);
		
		_btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}
}
