package Mapeo;

public class Ruta {
	
	protected	int		_distancia;
	protected	boolean	_peaje;
	
	public Ruta(int distancia,boolean peaje){
		
		chequearDistancia(distancia);
		
		_distancia = distancia;
		_peaje = peaje;
	}
	
	public void chequearDistancia(int dist){
		
		if(dist<0){
			throw new IllegalArgumentException("No se pueden crear rutas con distancias negativas");
		}
	}

}
