package Interface;

import java.awt.Color;
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

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

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
	private		Datos				_d;
	
	public VentanaAgregar(Datos d){
		
		_d = d;
		
		_frame = new JInternalFrame();
		_frame.setBounds(400, 0, 200, 200);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		_frame.setBorder(null);
		((BasicInternalFrameUI) _frame.getUI()).setNorthPane(null);
		
		_lblNombre = new JLabel("Nombre");
		_lblNombre.setBounds(5, 14, 78, 14);
		_frame.getContentPane().add(_lblNombre);
		
		_textName = _d._textName;
		_textName.setBounds(60, 11, 110, 20);
		_frame.getContentPane().add(_textName);
		_textName.setColumns(10);
		
		_lblLatitud = new JLabel("Latitud");
		_lblLatitud.setBounds(5, 45, 78, 14);
		_frame.getContentPane().add(_lblLatitud);
		
		_textLat = _d._textLat;
		_textLat.setBounds(60, 42, 110, 20);
		_frame.getContentPane().add(_textLat);
		_textLat.setColumns(10);
		
		_lblLongitud = new JLabel("Longitud");
		_lblLongitud.setBounds(5, 76, 78, 14);
		_frame.getContentPane().add(_lblLongitud);
		
		_textLon = _d._textLon;
		_textLon.setBounds(60, 73, 110, 20);
		_frame.getContentPane().add(_textLon);
		_textLon.setColumns(10);
		
		_rbAgregar = _d._rbAgregar;
		_rbAgregar.setBounds(5, 105, 20, 20);
		_frame.getContentPane().add(_rbAgregar);
		
		_btnAgregar = new JButton("Agregar Ciudad");
		_btnAgregar.setBounds(25, 105, 150, 23);
		_frame.getContentPane().add(_btnAgregar);
		
		_btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				double lat = Double.valueOf(_textLat.getText());
				double lon = Double.valueOf(_textLon.getText());
				
				Coordinate c = new Coordinate(lat,lon);
				
				MapMarkerDot ciudad = new MapMarkerDot(c);
				ciudad.setBackColor(Color.WHITE);
				ciudad.setName(_textName.getText());	
				
				if(_d._mapa.agregarCiudad(_textName.getText(), c)){
					_d._ciudades.add(ciudad);
					_d._jmap.addMapMarker(ciudad);
				}
			}
		});
	}
}
