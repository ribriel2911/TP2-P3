package Interface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import Mapeo.Mapa;

public class Datos {
	
	protected	JMapViewer											_jmap;
	protected	HashMap<Integer,HashMap<Integer,MapPolygonImpl>>	_rutas;
	protected 	ArrayList<MapMarkerDot>								_ciudades;
	protected 	Mapa	 											_mapa;
	protected 	JTextField											_textName;
	protected 	JTextField											_textLat;
	protected 	JTextField											_textLon;
	protected 	JTextField											_textDesde;
	protected 	JTextField											_textHasta;
	protected 	JTextField											_textPeajes;
	protected 	JRadioButton										_rbAgregar;
	protected 	JRadioButton										_rbBuscar;
	protected	ButtonGroup 										_bgSelector;
	protected 	boolean	 											_textSelector;
	
	public Datos(){
		
		_jmap			= new JMapViewer();
		_ciudades 		= new ArrayList<>();		
		_rutas 			= new HashMap<>();
		
		_mapa			= new Mapa();
		
		_mapa.agregarCiudad("SanFernando", new Coordinate(-34.4442,-58.5775));
		_mapa.agregarCiudad("Tigre", new Coordinate(-34.4167, -58.5833));
		_mapa.agregarCiudad("San Isidro", new Coordinate(-34.4708, -58.5286));
		_mapa.agregarCiudad("UNGS", new Coordinate(-34.522186431469976, -58.70001554489136));
		_mapa.agregarCiudad("Don Torcuato", new Coordinate(-34.5,-58.6333));
		
		_mapa.agregarRuta(3, 1, false);
		_mapa.agregarRuta(3, 0, true);
		_mapa.agregarRuta(3, 4, true);
		_mapa.agregarRuta(1, 2, false);
		_mapa.agregarRuta(0, 2, false);
		_mapa.agregarRuta(4, 2, true);
		
		_textName		= new JTextField();
		_textLat		= new JTextField();
		_textLon		= new JTextField();
		
		_textDesde		= new JTextField();
		_textHasta		= new JTextField();
		_textPeajes		= new JTextField();
		
		_rbAgregar = new JRadioButton();
		_rbAgregar.setSelected(true);
		
		_rbBuscar = new JRadioButton();
		_rbBuscar.setSelected(false);
		
		_bgSelector = new ButtonGroup();
		_bgSelector.add(_rbAgregar);
		_bgSelector.add(_rbBuscar);
		
		_textSelector	= false;
	}
	
	public void repintarRutas(){
		
		for(int i : _rutas.keySet()){
			
			_ciudades.get(i).setBackColor(Color.BLUE);
			
			for(int j : _rutas.get(i).keySet()){
				
				if(_mapa.hayPeaje(i, j))	_rutas.get(i).get(j).setColor(Color.red);
				else						_rutas.get(i).get(j).setColor(Color.blue);
			}
		}
	}
}
