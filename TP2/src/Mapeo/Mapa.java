package Mapeo;

import java.util.ArrayList;
import java.util.HashSet;
import CaminoMinimo.*;

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
	
	public int totalCiudades(){
		
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
	
	public int totalVecinos(int ciudad){
		
		chequearCiudad(ciudad,"grado");
		
		return _ciudades.get(ciudad).getCantVecinos();
	}
	
	public boolean existeRuta(int i, int j){
		
		chequearRuta(i,j,"ver si existe");
		
		return _ciudades.get(i).existeRuta(_ciudades.get(j));
	}
	
	public Grafo graficador(int maxPeajes){
		
		chequearPeajes(maxPeajes);
		
		int l = totalCiudades();
		int lim = l + (l*maxPeajes);
		
		Grafo ret = new Grafo();
		
		ArrayList<Nodo> nodos = new ArrayList<>();
		
		for(int i=0;i<lim;i++){
			
			Nodo n = new Nodo(""+i);
			
			nodos.add(n);
		}
		
		for(int i=0;i<lim;i++){
			
			int h = i;
			
			int cont = 0;
			while(h>=l){ h-=l; cont++;}
			
			for (int j : getVecinos(h)){
				
				for(Ruta r : _ciudades.get(h)._rutas.get(_ciudades.get(j))){
					
					if(i>=l)		j+=(l*cont);
					
					if(r._peaje){	
						
						if(j+l<lim){
							
							j+=l;
							nodos.get(i).agregarArista(nodos.get(j), r._distancia);
						}
					}
					
					else nodos.get(i).agregarArista(nodos.get(j), r._distancia);
				}
			}
		}
		
		ret.setNodos(nodos);
		
		return ret;
	}
	
	
	private void chequearRuta(int i,int j,String accion){
		
		if( i < 0 || i >= totalCiudades() )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con una ciudad inexistente! i = " + i);
			
		if( j < 0 || j >= totalCiudades() )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con una ciudad inexistente! j = " + j);
			
		if( i == j )
			throw new IllegalArgumentException("Se intentó " + accion + " una ruta con dos ciudades iguales! i, j = " + i);
	}
	
	private void chequearCiudad(int c, String accion){
		
		if( c < 0 || c >= totalCiudades())
			throw new IllegalArgumentException("Se intentó consultar " + accion + " de un vértice inexistente! i = " + c);
		
	}
	
	private void chequearPeajes(int p){
		
		if(p<0){
			throw new IllegalArgumentException("No se puede crear un Grafo con una cantidad de peajes negativa "+p);
		}
	}
}

