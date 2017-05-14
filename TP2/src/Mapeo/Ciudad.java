package Mapeo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Ciudad {
	
	protected	int								_id;
	protected	String							_nombre;
	protected 	Coordinate						_coordenadas;
	protected	HashMap<Ciudad,HashSet<Ruta>>	_rutas;
	
	public Ciudad(int id, String nombre, Coordinate cord){
		
		_id = id;
		_nombre = nombre;
		_coordenadas = cord;
		_rutas = new HashMap<Ciudad,HashSet<Ruta>>();
	}
	
	public Ruta encontrarRuta(Ciudad c,int distancia,boolean peaje){
		
		if(existeRuta(c)){
		
			for(Ruta r : _rutas.get(c)){
				
				if(	r._distancia	==	distancia
				&&	r._peaje		==	peaje)
					
					return r;
			}
		}
		return null;	
	}
	
	public void construirRuta(Ciudad destino,int distancia,boolean peaje){
		
		if(!existeRuta(destino))	{_rutas.put(destino, new HashSet<Ruta>());}
		
		_rutas.get(destino).add(new Ruta(distancia,peaje));
	}
	
	public void destruirRuta(Ciudad destino, int distancia, boolean peaje)	{
		
		Ruta r = encontrarRuta(destino,distancia,peaje);
		
		if(r!=null && _rutas.get(destino).remove(r)){
		
			if(_rutas.get(destino).size()==0){
				_rutas.remove(destino);
			}
		}
		
		else{
		throw new IllegalArgumentException("No se pudo eliminar la ruta con los parametros\n"
				+ "Origen: Ciudad "+_id+"\n"
				+ "Destino: Ciudad "+destino._id+"\n"
				+ "Distancia: "+distancia+"\n"
				+ "Peaje:" + peaje);
		}
	}
	
	public boolean existeRuta(Ciudad destino)	{return _rutas.containsKey(destino);}
	
	public int cantRutas(Ciudad destino)		{return _rutas.get(destino).size();}
	
	public Set<Ciudad> getVecinos()				{return _rutas.keySet();}
	
	public int getCantVecinos()					{return _rutas.size();}
	
	public Set<Ruta> getRutas(Ciudad destino)	{return _rutas.get(destino);}
	
	public Coordinate getCoordenadas()			{return _coordenadas;}
}
