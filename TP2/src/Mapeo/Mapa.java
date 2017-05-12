package Mapeo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

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
		
		int ciudades = totalCiudades();
		int vertices = ciudades + (ciudades*maxPeajes);
		
		Grafo ret = new Grafo();
		
		ArrayList<Nodo> nodos = Nodo.crearNodos(vertices);
		
		for(int vertice=0;vertice<vertices;vertice++){
			
			int verticeRelativo = vertice;
			
			int cont = 0;
			while(verticeRelativo>=ciudades){
				verticeRelativo-=ciudades; 
				cont++;
				}
			
			for (int vecino : getVecinos(verticeRelativo)){
				
				for(Ruta r : _ciudades.get(verticeRelativo)._rutas.get(_ciudades.get(vecino))){
					
					if(vertice>=ciudades)		vecino+=(ciudades*cont);
					
					if(r._peaje){	
						
						if(vecino+ciudades<vertices){
							
							vecino+=ciudades;
							nodos.get(vertice).agregarArista(nodos.get(vecino), r._distancia);
						}
					}
					
					else nodos.get(vertice).agregarArista(nodos.get(vecino), r._distancia);
				}
			}
		}
		
		ret.setNodos(nodos);
		
		return ret;
	}
	
	public Grafo caminoCorto(int desde, int peajes){
		
		chequearCiudad(desde, "los caminos origen");
		chequearPeajes(peajes);
		
		Grafo ret = new Grafo();
		
		Grafo mapa = graficador(peajes);
		
		Grafo caminosCortos = Dijkstra.calcularCaminoMinimo(mapa, mapa.getNodo(desde));
		
		for(int i=0;i<totalCiudades();i++){
			
			Nodo n = caminoA(i,caminosCortos);
			
			ret.agregarNodo(n);
		}
		
		return ret;
	}
	
	protected Nodo caminoA(int i, Grafo g){
		
		chequearCiudad(i,"el camino");
		
		chequearGrafo(g);
		
		Nodo ret = new Nodo(i);
		
		LinkedList<Integer> camino = new LinkedList<>();
		
		int distanciaOptima = Integer.MAX_VALUE;
		
		int NodoOptimo = i;
		
		int aux = i;
		
		while(aux<g.getVertices()){
			
			int posible = g.getNodo(aux).getDistancia();
			
			if(posible<distanciaOptima){
				
				distanciaOptima = posible;
				NodoOptimo = aux;
			}
			
			aux+=totalCiudades();
		}
		
		for(Integer n : g.getNodo(NodoOptimo).getCaminoMasCorto()){
			
			int id = n;
			
			while(id>=totalCiudades()){ id-=totalCiudades();}
			
			camino.add(id);
		}
		
		
		camino.add(i);
		
		ret.setDistancia(distanciaOptima);
		ret.setCaminoMasCorto(camino);
		
		return ret;
	}
	
	private void chequearGrafo(Grafo g) {
		if(g.getVertices()%totalCiudades()!=0){
			throw new IllegalArgumentException("El grafo ingresado no corresponde a este mapa");
		}
	}

	private void chequearRuta(int i,int j,String accion){
		
		if( i < 0 || i >= totalCiudades() )
			throw new IllegalArgumentException("Se intent� " + accion + " una ruta con una ciudad inexistente! i = " + i);
			
		if( j < 0 || j >= totalCiudades() )
			throw new IllegalArgumentException("Se intent� " + accion + " una ruta con una ciudad inexistente! j = " + j);
			
		if( i == j )
			throw new IllegalArgumentException("Se intent� " + accion + " una ruta con dos ciudades iguales! i, j = " + i);
	}
	
	private void chequearCiudad(int c, String sujeto){
		
		if( c < 0 || c >= totalCiudades())
			throw new IllegalArgumentException("Se intent� consultar " + sujeto + " de un v�rtice inexistente! i = " + c);
	}
	
	private void chequearPeajes(int p){
		
		if(p<0){
			throw new IllegalArgumentException("No se puede crear un Grafo con una cantidad de peajes negativa "+p);
		}
	}
}

