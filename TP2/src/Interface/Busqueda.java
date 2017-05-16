package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import CaminoMinimo.Grafo;
import Mapeo.Ciudad;

public class Busqueda {
	
	protected	JInternalFrame		_frame;
	private		JTextField			_textDesde;
	private 	JLabel				_lblDesde;
	private 	JTextField			_textHasta;
	private 	JLabel				_lblHasta;
	private 	JTextField			_textPeajes;
	private 	JLabel				_lblPeajes;
	private 	JButton				_btnBuscar;
	private		JRadioButton		_rbBuscar;
	private		Datos				_d;

	public Busqueda(Datos d){
		
		_d = d;
		
		_frame = new JInternalFrame();
		_frame.setBounds(400, 300, 200, 150);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		_frame.setBorder(null);
		((BasicInternalFrameUI) _frame.getUI()).setNorthPane(null);
		
		_lblDesde = new JLabel("Desde");
		_lblDesde.setBounds(5, 5, 78, 14);
		_frame.getContentPane().add(_lblDesde);
		
		_textDesde = _d._textDesdeB;
		_textDesde.setBounds(60, 5, 110, 20);
		_frame.getContentPane().add(_textDesde);
		_textDesde.setColumns(10);
		
		_textDesde.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				if (e.getButton() == MouseEvent.BUTTON1){
				
					_d._textSelector = false;
				}
			}
		});
		
		_lblHasta = new JLabel("Hasta");
		_lblHasta.setBounds(5, 40, 78, 14);
		_frame.getContentPane().add(_lblHasta);
		
		_textHasta = _d._textHastaB;
		_textHasta.setBounds(60, 40, 110, 20);
		_frame.getContentPane().add(_textHasta);
		_textHasta.setColumns(10);
		
		_textHasta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				if (e.getButton() == MouseEvent.BUTTON1){
				
					_d._textSelector = true;
				}
			}
		});
		
		_lblPeajes = new JLabel("Peajes");
		_lblPeajes.setBounds(5, 75, 78, 14);
		_frame.getContentPane().add(_lblPeajes);
		
		_textPeajes = _d._textPeajes;
		_textPeajes.setBounds(60, 75, 110, 20);
		_frame.getContentPane().add(_textPeajes);
		_textPeajes.setColumns(10);
		
		_rbBuscar = _d._rbBuscar;
		_rbBuscar.setBounds(5, 110, 20, 20);
		_frame.getContentPane().add(_rbBuscar);
		
		_btnBuscar = new JButton("Buscar Camino");
		_btnBuscar.setBounds(25, 110, 150, 23);
		_frame.getContentPane().add(_btnBuscar);
		
		_btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(_rbBuscar.isSelected()){
					_d.repintarRutas();
					
					int desde = _d._mapa.getIdCiudad(_textDesde.getText());
					int hasta = _d._mapa.getIdCiudad(_textHasta.getText());
					int peajes = Integer.valueOf(_textPeajes.getText());
					
					Grafo g = _d._mapa.caminoCorto(desde, peajes);
					
					int anterior = -1;
					
					for(int i : g.getNodo(hasta).getCaminoMasCorto()){
						
						_d._ciudades.get(i).setBackColor(Color.GREEN);
							
						if(anterior>=0){
							
							_d._rutas.get(anterior).get(i).setColor(Color.green);
							_d._rutas.get(i).get(anterior).setColor(Color.green);
							
							anterior = i;
						}
						
						else anterior = i;
					}
					
					_d._jmap.updateUI();
				}
			}
		});
	}
}
