package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;
import org.openstreetmap.gui.jmapviewer.interfaces.MapPolygon;

import Mapeo.Ciudad;

public class VentanaMapa {

	protected JInternalFrame _frame;
	private JMapViewer _map;
	private MapMarkerDot _posicion;
	private ArrayList<MapMarkerDot> _ciudades;
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
		
		_ciudades = new ArrayList<>();
		
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
					}
					
					else{
						_posicion.setVisible(false);
						
						MapMarkerDot seleccionado = buscarSercano(e.getPoint());
						
						seleccionado.setBackColor(Color.GREEN);;
						
						d.seleccionado().setText(seleccionado.getName());
						System.out.println(seleccionado.getName());
					}
					
					_map.updateUI();
				}
			}
		});
	}
	
	private void dibujarMapa(){
		
		for(int i=0;i<_d._mapa.totalCiudades();i++){
			
			MapMarkerDot ciudad = new MapMarkerDot(_d._mapa.getCoordenadas(i));
			ciudad.setBackColor(Color.BLUE);
			ciudad.setName(_d._mapa.getCiudad(i));
			ciudad.setFont(null);
			
			_ciudades.add(ciudad);
			_map.addMapMarker(ciudad);
			
			for(int j : _d._mapa.getVecinos(i)){
				
				MapPolygonImpl p = new MapPolygonImpl(_d._mapa.getCoordenadas(i),_d._mapa.getCoordenadas(j),_d._mapa.getCoordenadas(i));
				p.setBackColor(Color.blue);
				_map.addMapPolygon(p);
			}
		}
	}
	
	private double distanciaCoord(Coordinate origen, Coordinate destino) {  
        //double radioTierra = 3958.75;//en millas  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(destino.getLat() - origen.getLat());  
        double dLng = Math.toRadians(destino.getLon() - origen.getLon());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(origen.getLat())) * Math.cos(Math.toRadians(destino.getLat()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia;  
    }  
	
	private MapMarkerDot buscarSercano(Point p){
		
		MapMarkerDot masSercano =_ciudades.get(0);
		
		double distancia = Double.MAX_VALUE;
		
		for(MapMarkerDot m : _ciudades){
			
			m.setBackColor(Color.BLUE);
			
			if(distanciaCoord(_map.getPosition(p),m.getCoordinate())<distancia){
				
				masSercano = m;
				distancia = distanciaCoord(_map.getPosition(p),m.getCoordinate());
			}
		}
		
		return masSercano;
	}

}
