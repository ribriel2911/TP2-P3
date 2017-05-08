package Mapeo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Ciudad {
	
	int _id;
	String _nombre;
	int latitud;
	int longitud;
	HashMap<Ciudad,HashSet<Ruta>> _rutas;
	
	public Ciudad(int id){
		
		_id = id;
		_rutas = new HashMap<Ciudad,HashSet<Ruta>>();
	}
	
	public Ruta encontrarRuta(Ciudad c,int dist,boolean peaje){
		
		for(Ruta r : _rutas.get(c)){
			
			if(	r._distancia	==	dist
			&&	r._peaje		==	peaje)
				
				return r;
		}
		
		return null;
		
	}
	
	public void construirRuta(Ciudad destino,int dist,boolean peaje){
		
		if(!existeRuta(destino)){
			
			_rutas.put(destino, new HashSet<Ruta>());
		}
		
		_rutas.get(destino).add(new Ruta(dist,peaje));
	}
	
	public void destruirRuta(Ciudad destino, int dist, boolean peaje)	{
		
		Ruta r = encontrarRuta(destino,dist,peaje);
		_rutas.get(destino).remove(r);
		
		if(_rutas.get(destino).size()==0){
			_rutas.remove(destino);
		}
	}
	
	public boolean existeRuta(Ciudad destino)	{return _rutas.containsKey(destino);}
	
	public int cantRutas(Ciudad destino)		{return _rutas.get(destino).size();}
	
	public Set<Ciudad> getVecinos()				{return _rutas.keySet();}
	
	public int getCantVecinos()					{return _rutas.size();}
	
	public Set<Ruta> getRutas(Ciudad destino)	{return _rutas.get(destino);}
}
