package Interface;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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

}
