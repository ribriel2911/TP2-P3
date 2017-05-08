package Mapeo;

import java.util.ArrayList;
import java.util.HashSet;


public class Mapa {
	
	private ArrayList<Ciudad> _ciudades;
	
	public Mapa(int verticesIniciales){
		
		_ciudades = new ArrayList<Ciudad>();
		
		for(int i=0; i<verticesIniciales; ++i)
			_ciudades.add(new Ciudad(i,"Ciudad:"+i));
	}
	
	public Mapa()	{_ciudades = new ArrayList<Ciudad>();}
	
	public void agregarRuta(int i, int j,int dist,boolean peaje){
		
		chequearRuta(i,j,"agregar");
		
		_ciudades.get(i).construirRuta(_ciudades.get(j),dist,peaje);
		_ciudades.get(j).construirRuta(_ciudades.get(i),dist,peaje);
	}
	
	public void eliminarRuta(int i,int j,int dist,boolean peaje){
		
		chequearRuta(i,j,"eliminar");
		
		_ciudades.get(i).destruirRuta(_ciudades.get(j),dist,peaje);
		_ciudades.get(j).destruirRuta(_ciudades.get(i),dist,peaje);
	}
	
	public void agregarCiudad(String nombre){
		
		int id = _ciudades.size();
		
		_ciudades.add(new Ciudad(id,nombre));
	}
	
	public int getCantCiudades(){
		
		return _ciudades.size();
	}
	
	public HashSet<Integer> getVecinos(int ciudad){
		
		chequearCiudad(ciudad, "vecinos");
		
		HashSet<Integer> ret = new HashSet<Integer>();
		
		for(Ciudad c : _ciudades.get(ciudad).getVecinos()){
			
			ret.add(c._id);
		}
		
		return ret;
	}
	
	public int getGrado(int ciudad){
		
		chequearCiudad(ciudad,"grado");
		
		return _ciudades.get(ciudad).getCantVecinos();
	}
	
	public boolean existeRuta(int i, int j){
		
		chequearRuta(i,j,"ver si existe");
		
		return _ciudades.get(i).existeRuta(_ciudades.get(j));
	}
	
	private void chequearRuta(int i,int j,String accion){
		
		if( i < 0 || i >= getCantCiudades() )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con una ciudad inexistente! i = " + i);
			
		if( j < 0 || j >= getCantCiudades() )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con una ciudad inexistente! j = " + j);
			
		if( i == j )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con dos ciudades iguales! i, j = " + i);
	}
	
	private void chequearCiudad(int c, String accion){
		
		if( c < 0 || c >= getCantCiudades())
			throw new IllegalArgumentException("Se intentó consultar " + accion + " de un vértice inexistente! i = " + c);
		
	}
}

