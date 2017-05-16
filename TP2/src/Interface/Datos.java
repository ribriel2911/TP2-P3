package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
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
	protected 	JTextField											_textDesdeB;
	protected 	JTextField											_textHastaB;
	protected 	JTextField											_textDesdeA;
	protected 	JTextField											_textHastaA;
	protected 	JTextField											_textPeajes;
	protected	JCheckBox											_chckbxPeaje;
	protected 	JRadioButton										_rbAgregarC;
	protected 	JRadioButton										_rbAgregarR;
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
		
		_textDesdeB		= new JTextField();							desactivar(_textDesdeB);
		_textHastaB		= new JTextField();							desactivar(_textHastaB);
		_textPeajes		= new JTextField("0");						desactivar(_textPeajes);
		
		_textDesdeA		= new JTextField();							desactivar(_textDesdeA);
		_textHastaA		= new JTextField();							desactivar(_textHastaA);
		_chckbxPeaje	= new JCheckBox();							_chckbxPeaje.setEnabled(false);
		
		_rbAgregarC = new JRadioButton();
		_rbAgregarC.setSelected(true);
		_rbAgregarC.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(_rbAgregarC.isSelected()){
					
					activar(_textName);								desactivar(_textDesdeB);
					activar(_textLat);								desactivar(_textHastaB);
					activar(_textLon);								desactivar(_textPeajes);
																	desactivar(_textDesdeA);
																	desactivar(_textHastaA);
																	_chckbxPeaje.setEnabled(false);
				}
			}
		});
		
		_rbBuscar = new JRadioButton();
		_rbBuscar.setSelected(false);
		_rbBuscar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(_rbBuscar.isSelected()){
					
					activar(_textDesdeB);							desactivar(_textName);
					activar(_textHastaB);							desactivar(_textLat);
					activar(_textPeajes);							desactivar(_textLon);
																	desactivar(_textDesdeA);
					_textSelector	= false;						desactivar(_textHastaA);
																	_chckbxPeaje.setEnabled(false);
				}
			}
		});
		
		_rbAgregarR = new JRadioButton();
		_rbAgregarR.setSelected(false);
		_rbAgregarR.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				if(_rbAgregarR.isSelected()){
					
					activar(_textDesdeA);							desactivar(_textName);
					activar(_textHastaA);							desactivar(_textLat);
					_chckbxPeaje.setEnabled(true);					desactivar(_textLon);
																	desactivar(_textDesdeB);
					_textSelector	= false;						desactivar(_textHastaB);
																	desactivar(_textPeajes);	
																	
				}
			}
		});
		
		_bgSelector = new ButtonGroup();
		_bgSelector.add(_rbAgregarC);
		_bgSelector.add(_rbBuscar);
		_bgSelector.add(_rbAgregarR);
		
		_textSelector	= false;
	}
	
	public void repintarRutas(){
		
		for(int i : _rutas.keySet()){
			
			_ciudades.get(i).setBackColor(Color.WHITE);
			
			for(int j : _rutas.get(i).keySet()){
				
				if(_mapa.hayPeaje(i, j))	_rutas.get(i).get(j).setColor(Color.red);
				else						_rutas.get(i).get(j).setColor(Color.blue);
			}
		}
	}
	
	private void desactivar(JTextField text){
		
		text.setEnabled(false);
		text.setEditable(false);
	}
	
	private void activar(JTextField text){
		
		text.setEnabled(true);
		text.setEditable(true);
	}
}
