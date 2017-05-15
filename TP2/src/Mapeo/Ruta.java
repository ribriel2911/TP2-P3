package Mapeo;

public class Ruta {
	
	protected	double	_distancia;
	protected	boolean	_peaje;
	
	public Ruta(double distancia,boolean peaje){
		
		chequearDistancia(distancia);
		
		_distancia = distancia;
		_peaje = peaje;
	}
	
	public void chequearDistancia(double dist){
		
		if(dist<0){
			throw new IllegalArgumentException("No se pueden crear rutas con distancias negativas");
		}
	}

	public double get_distancia() {
		return _distancia;
	}

	public void set_distancia(double _distancia) {
		this._distancia = _distancia;
	}

	public boolean is_peaje() {
		return _peaje;
	}

	public void set_peaje(boolean _peaje) {
		this._peaje = _peaje;
	}
	
}
