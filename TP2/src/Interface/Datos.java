package Interface;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import Mapeo.Mapa;

public class Datos {
	
	protected	JMapViewer		_jmap;
	protected 	Mapa	 		_mapa;
	protected 	JTextField		_textName;
	protected 	JTextField		_textLat;
	protected 	JTextField		_textLon;
	protected 	JTextField		_textDesde;
	protected 	JTextField		_textHasta;
	protected 	JTextField		_textPeajes;
	protected 	JRadioButton	_rbAgregar;
	protected 	JRadioButton	_rbBuscar;
	protected	ButtonGroup 	_bgSelector;
	protected 	boolean	 		_textSelector;
	
	public Datos(){
		
		_jmap			= new JMapViewer();
		
		_mapa			= new Mapa();
		_mapa.agregarCiudad("SanFernando(0)", new Coordinate(-34.4442,-58.5775));
		_mapa.agregarCiudad("Tigre(1)", new Coordinate(-34.4167, -58.5833));
		_mapa.agregarCiudad("San Isidro(2)", new Coordinate(-34.4708, -58.5286));
		_mapa.agregarCiudad("San Miguel(3)", new Coordinate(-34.5239, -58.7794));
		
		_mapa.agregarRuta(3, 1, false);
		_mapa.agregarRuta(3, 0, true);
		_mapa.agregarRuta(1, 2, false);
		_mapa.agregarRuta(0, 2, true);
		
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
	
	public JTextField seleccionado(){
		
		if (!_textSelector){
			
			return _textDesde;
		}
		
		return _textHasta;
	}
}
