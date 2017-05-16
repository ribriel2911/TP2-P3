package Interface;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

public class MostrarMapa {

	protected JInternalFrame _frame;
	private JMapViewer _map;
	private MapMarkerDot _position;
	private MapMarkerDot _selectedDesde;
	private MapMarkerDot _selectedHasta;
	private ArrayList<MapMarkerDot> _ciudades;
	private	HashMap<Integer,HashMap<Integer,MapPolygonImpl>> _rutas;
	private JTextField _textLat;
	private JTextField _textLon;
	private Datos _d;


	public MostrarMapa(Datos d) {
		
		_d = d;
		_textLat = _d._textLat;
		_textLon = _d._textLon;
	
		_frame = new JInternalFrame();
		_frame.setBounds(0, 0, 400, 442);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		((BasicInternalFrameUI) _frame.getUI()).setNorthPane(null);

		_map = _d._jmap;
		_map.setBounds(0,0,400,440);
		_map.setDisplayPositionByLatLon(45.82879925192134,2.548828125, 4);
		
		_position = new MapMarkerDot(_map.getPosition());
		_position.setVisible(false);
		_map.addMapMarker(_position);
		
		_ciudades = d._ciudades;
		_rutas = d._rutas;
		
		dibujarMapa();
		
		_frame.getContentPane().add(_map);
		
		_map.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (e.getButton() == MouseEvent.BUTTON1){
					
					if(_d._rbAgregarC.isSelected()){
						
						_d.repintarRutas();
											
						_textLat.setText(""+_map.getPosition(e.getPoint()).getLat());
						_position.setLat(_map.getPosition(e.getPoint()).getLat());
						
						_textLon.setText(""+_map.getPosition(e.getPoint()).getLon());
						_position.setLon(_map.getPosition(e.getPoint()).getLon());
						
						_position.setVisible(true);
					}
					
					else{
						_position.setVisible(false);
						
						JTextField desde;
						JTextField hasta;
						
						
						if(_d._rbBuscar.isSelected()){
							desde = d._textDesdeB;
							hasta = d._textHastaB;
						}
						else{
							desde = d._textDesdeA;
							hasta = d._textHastaA;
						}
						
						if(!d._textSelector){
						
							if(_selectedDesde!=null)	_selectedDesde.setBackColor(Color.WHITE);
							_selectedDesde = buscarSercano(e.getPoint());
							_selectedDesde.setBackColor(Color.ORANGE);;
							
							desde.setText(_selectedDesde.getName());
						}
						
						else{
							if(_selectedHasta!=null)	_selectedHasta.setBackColor(Color.WHITE);
							_selectedHasta = buscarSercano(e.getPoint());
							_selectedHasta.setBackColor(Color.ORANGE);;
							
							hasta.setText(_selectedHasta.getName());
						}
					}
					
					_map.updateUI();
				}
			}
		});
	}
	
	private void dibujarMapa(){
		
		for(int i=0;i<_d._mapa.totalCiudades();i++){
			
			MapMarkerDot ciudad = new MapMarkerDot(_d._mapa.getCoordenadas(i));
			ciudad.setBackColor(Color.WHITE);
			ciudad.setName(_d._mapa.getNameCiudad(i));
			
			_ciudades.add(ciudad);
			_map.addMapMarker(ciudad);
			
			_rutas.put(i, new HashMap<>());
			
			for(int j : _d._mapa.getVecinos(i)){
				
				MapPolygonImpl ruta = new MapPolygonImpl(_d._mapa.getCoordenadas(i),_d._mapa.getCoordenadas(j),_d._mapa.getCoordenadas(i));
				
				if(_d._mapa.hayPeaje(i, j))	ruta.setColor(Color.red);
				else						ruta.setColor(Color.blue);
				
				_rutas.get(i).put(j, ruta);
				_map.addMapPolygon(ruta);
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
			
			if(distanciaCoord(_map.getPosition(p),m.getCoordinate())<distancia){
				
				masSercano = m;
				distancia = distanciaCoord(_map.getPosition(p),m.getCoordinate());
			}
		}
		
		return masSercano;
	}

}
