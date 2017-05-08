package Mapeo;

import java.util.ArrayList;


public class Mapa {
	
	ArrayList<Ciudad> _ciudades;
	
	public Mapa(int verticesIniciales){
		
		_ciudades = new ArrayList<Ciudad>();
		
		for(int i=0; i<verticesIniciales; ++i)
			_ciudades.add(new Ciudad(i));
	}
	
	public void agregarDobleMano(int i, int j,int dist,boolean peaje){
		
		agregarRuta(i,j,dist,peaje);
		agregarRuta(j,i,dist,peaje);
	}
	
	
	public void agregarRuta(int i, int j,int dist,boolean peaje){
		
		chequearCiudades(i,j,"agregar");
		
		_ciudades.get(i).construirRuta(_ciudades.get(j),dist,peaje);
	}
	
	public void eliminarRuta(int i,int j,int dist,boolean peaje){
		
		chequearCiudades(i,j,"eliminar");
		
		_ciudades.get(i).destruirRuta(_ciudades.get(j),dist,peaje);
		
	}
	
	public void agregarCiudad(){
		
		int id = _ciudades.size();
		
		_ciudades.add(new Ciudad(id));
	}
	
	public int getCantCiudades(){
		
		return _ciudades.size();
	}
	
	private void chequearCiudades(int i,int j,String accion){
		
		if( i < 0 || i >= getCantCiudades() )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con una ciudad inexistente! i = " + i);
			
		if( j < 0 || j >= getCantCiudades() )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con una ciudad inexistente! j = " + j);
			
		if( i == j )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con dos ciudades iguales! i, j = " + i);
	}
}
