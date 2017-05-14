package Interface;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class VentanaMapa {

	protected JInternalFrame _frame;
	protected JMapViewer _map;
	protected MapMarkerDot _posicion;
	private JTextField _textLat;
	private JTextField _textLon;


	public VentanaMapa(Datos d) {
		
		_textLat = d._textLat;
		_textLon = d._textLon;
	
		_frame = new JInternalFrame();
		_frame.setBounds(0, 0, 400, 362);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		((BasicInternalFrameUI) _frame.getUI()).setNorthPane(null);
		
		_map = d._jmap;
		_map.setBounds(0,0,400,400);
		_posicion = new MapMarkerDot(_map.getPosition());
		_posicion.setVisible(false);
		_map.addMapMarker(_posicion);
		
		_frame.getContentPane().add(_map);
		
		_map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getButton() == MouseEvent.BUTTON1){
					
					if(d._rbAgregar.isSelected()){
											
						_textLat.setText(""+_map.getPosition(e.getPoint()).getLat());
						_posicion.setLat(_map.getPosition(e.getPoint()).getLat());
						
						_textLon.setText(""+_map.getPosition(e.getPoint()).getLon());
						_posicion.setLon(_map.getPosition(e.getPoint()).getLon());
						
						_posicion.setVisible(true);
						_map.updateUI();
					}
				}
			}
		});
	}

}
