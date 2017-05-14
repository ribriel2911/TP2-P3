package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

public class VentanaBusqueda {
	
	protected	JInternalFrame		_frame;
	private		JTextField			_textDesde;
	private 	JLabel				_lblDesde;
	private 	JTextField			_textHasta;
	private 	JLabel				_lblHasta;
	private 	JTextField			_textPeajes;
	private 	JLabel				_lblPeajes;
	private 	JButton				_btnBuscar;
	private		JRadioButton		_rbAgregar;

	public VentanaBusqueda(Datos d){
		
		_frame = new JInternalFrame();
		_frame.setBounds(400, 200, 200, 200);
		_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		_frame.getContentPane().setLayout(null);
		_frame.setBorder(null);
		((BasicInternalFrameUI) _frame.getUI()).setNorthPane(null);
		
		_lblDesde = new JLabel("Desde");
		_lblDesde.setBounds(5, 14, 78, 14);
		_frame.getContentPane().add(_lblDesde);
		
		_textDesde = d._textDesde;
		_textDesde.setBounds(60, 11, 110, 20);
		_frame.getContentPane().add(_textDesde);
		_textDesde.setColumns(10);
		
		_textDesde.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				if (e.getButton() == MouseEvent.BUTTON1){
				
					d._textSelector = false;
				}
			}
		});
		
		_lblHasta = new JLabel("Hasta");
		_lblHasta.setBounds(5, 45, 78, 14);
		_frame.getContentPane().add(_lblHasta);
		
		_textHasta = d._textHasta;
		_textHasta.setBounds(60, 42, 110, 20);
		_frame.getContentPane().add(_textHasta);
		_textHasta.setColumns(10);
		
		_textHasta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {	
				
				if (e.getButton() == MouseEvent.BUTTON1){
				
					d._textSelector = true;
				}
			}
		});
		
		_lblPeajes = new JLabel("Peajes");
		_lblPeajes.setBounds(5, 76, 78, 14);
		_frame.getContentPane().add(_lblPeajes);
		
		_textPeajes = d._textPeajes;
		_textPeajes.setBounds(60, 73, 110, 20);
		_frame.getContentPane().add(_textPeajes);
		_textPeajes.setColumns(10);
		
		_rbAgregar = d._rbBuscar;
		_rbAgregar.setBounds(5, 105, 20, 20);
		_frame.getContentPane().add(_rbAgregar);
		
		_btnBuscar = new JButton("Buscar Camino");
		_btnBuscar.setBounds(25, 105, 150, 23);
		_frame.getContentPane().add(_btnBuscar);
		
		_btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
	}
}
