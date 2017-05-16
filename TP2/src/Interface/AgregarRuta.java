package Interface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import CaminoMinimo.Grafo;

public class AgregarRuta {
	
	protected	JInternalFrame		_frame;
	private		JTextField			_textDesde;
	private 	JLabel				_lblDesde;
	private 	JTextField			_textHasta;
	private 	JLabel				_lblHasta;
	private		JCheckBox			chckbxPeaje;
	private 	JLabel				_lblPeaje;
	private 	JButton				_btnBuscar;
	private		JRadioButton		_rbAgregar;
	private		Datos				_d;

	public AgregarRuta(Datos d){
		
		_d = d;
		
		_frame = new JInternalFrame();
		_frame.setBounds(400, 150, 200, 150);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		_frame.setBorder(null);
		((BasicInternalFrameUI) _frame.getUI()).setNorthPane(null);
		
		_lblDesde = new JLabel("Desde");
		_lblDesde.setBounds(5, 5, 78, 14);
		_frame.getContentPane().add(_lblDesde);
		
		_textDesde = _d._textDesdeA;
		_textDesde.setBounds(60, 5, 110, 20);
		_frame.getContentPane().add(_textDesde);
		_textDesde.setColumns(10);
		
		_textDesde.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				if (e.getButton() == MouseEvent.BUTTON1)	{_d._textSelector = false;}
			}
		});
		
		_lblHasta = new JLabel("Hasta");
		_lblHasta.setBounds(5, 40, 78, 14);
		_frame.getContentPane().add(_lblHasta);
		
		_textHasta = _d._textHastaA;
		_textHasta.setBounds(60, 40, 110, 20);
		_frame.getContentPane().add(_textHasta);
		_textHasta.setColumns(10);
		
		_textHasta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				if (e.getButton() == MouseEvent.BUTTON1)	{_d._textSelector = true;}
			}
		});
		
		_lblPeaje = new JLabel("Peaje");
		_lblPeaje.setBounds(5, 75, 78, 14);
		_frame.getContentPane().add(_lblPeaje);
		
		chckbxPeaje = d._chckbxPeaje;
		chckbxPeaje.setBounds(60, 75, 20, 20);
		_frame.getContentPane().add(chckbxPeaje);
		
		_rbAgregar = _d._rbAgregarR;
		_rbAgregar.setBounds(5, 110, 20, 20);
		_frame.getContentPane().add(_rbAgregar);
		
		_btnBuscar = new JButton("Agregar Ruta");
		_btnBuscar.setBounds(25, 110, 150, 23);
		_frame.getContentPane().add(_btnBuscar);
		
		_btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(_d._rbAgregarR.isSelected()){
					
					try{
						int desde = _d._mapa.getIdCiudad(_textDesde.getText());
						int hasta = _d._mapa.getIdCiudad(_textHasta.getText());
						
						if(_d._mapa.agregarRuta(desde, hasta, chckbxPeaje.isSelected()));
							
							MapPolygonImpl ruta = new MapPolygonImpl(_d._mapa.getCoordenadas(desde),_d._mapa.getCoordenadas(hasta),_d._mapa.getCoordenadas(desde));
							if(_d._mapa.hayPeaje(desde, hasta))	ruta.setColor(Color.red);
							else								ruta.setColor(Color.blue);
							
							_d._rutas.get(desde).put(hasta, ruta);
							_d._rutas.get(hasta).put(desde, ruta);
						
							_d._jmap.addMapPolygon(ruta);
							_d._jmap.updateUI();
					}
					
					catch(IllegalArgumentException e){
						
					}
				}
			}
		});
	}
}
