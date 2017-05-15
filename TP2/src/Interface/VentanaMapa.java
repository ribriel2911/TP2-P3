package Interface;

import java.awt.Color;
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
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

public class VentanaMapa {

	protected JInternalFrame _frame;
	private JMapViewer _map;
	private MapMarkerDot _posicion;
	private JTextField _textLat;
	private JTextField _textLon;
	private Datos _d;


	public VentanaMapa(Datos d) {
		
		_d = d;
		_textLat = _d._textLat;
		_textLon = _d._textLon;
	
		_frame = new JInternalFrame();
		_frame.setBounds(0, 0, 400, 362);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		((BasicInternalFrameUI) _frame.getUI()).setNorthPane(null);
		
		_map = _d._jmap;
		_map.setBounds(0,0,400,400);
		_map.setDisplayPositionByLatLon(-34.5043031,-58.6363941, 10);
		
		_posicion = new MapMarkerDot(_map.getPosition());
		_posicion.setVisible(false);
		_map.addMapMarker(_posicion);
		
		dibujarMapa();
		
		_frame.getContentPane().add(_map);
		
		_map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getButton() == MouseEvent.BUTTON1){
					
					if(_d._rbAgregar.isSelected()){
											
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
	
	private void dibujarMapa(){
		
		for(Coordinate c : _d._mapa.getCoordenadas()){
			
			MapMarkerDot ciudad = new MapMarkerDot(c);
			ciudad.setBackColor(Color.BLUE);
			_map.addMapMarker(ciudad);
		}
		
		for(int i=0;i<_d._mapa.totalCiudades();i++){
			
			for(int j : _d._mapa.getVecinos(i)){
				
				MapPolygon p = new MapPolygonImpl(_d._mapa.getCoordenadas(i),_d._mapa.getCoordenadas(j),_d._mapa.getCoordenadas(i));
				_map.addMapPolygon(p);
			}
		}
	}

}
