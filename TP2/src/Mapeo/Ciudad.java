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
	
	public Ruta encontrarRuta(Ciudad c,boolean peaje){
		
		if(existeRuta(c)){
		
			for(Ruta r : _rutas.get(c)){
				
				if(r._peaje		==	peaje)
					
					return r;
			}
		}
		return null;	
	}
	
	public void construirRuta(Ciudad destino,boolean peaje){
		
		if(!existeRuta(destino))	{_rutas.put(destino, new HashSet<Ruta>());}
		
		_rutas.get(destino).add(new Ruta(distanciaCoord(destino),peaje));
	}
	
	public void destruirRuta(Ciudad destino, boolean peaje)	{
		
		Ruta r = encontrarRuta(destino,peaje);
		
		if(r!=null && _rutas.get(destino).remove(r)){
		
			if(_rutas.get(destino).size()==0){
				_rutas.remove(destino);
			}
		}
		
		else{
		throw new IllegalArgumentException("No se pudo eliminar la ruta con los parametros\n"
				+ "Origen: Ciudad "+_id+"\n"
				+ "Destino: Ciudad "+destino._id+"\n"
				+ "Peaje:" + peaje);
		}
	}
	
	public boolean existeRuta(Ciudad destino)	{return _rutas.containsKey(destino);}
	
	public int cantRutas(Ciudad destino)		{return _rutas.get(destino).size();}
	
	public Set<Ciudad> getVecinos()				{return _rutas.keySet();}
	
	public int getCantVecinos()					{return _rutas.size();}
	
	public Set<Ruta> getRutas(Ciudad destino)	{return _rutas.get(destino);}
	
	public Coordinate getCoordenadas()			{return _coordenadas;}
	
	private double distanciaCoord(Ciudad destino) {  
        //double radioTierra = 3958.75;//en millas  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(destino._coordenadas.getLat() - _coordenadas.getLat());  
        double dLng = Math.toRadians(destino._coordenadas.getLon() - _coordenadas.getLon());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(_coordenadas.getLat())) * Math.cos(Math.toRadians(destino._coordenadas.getLat()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia;  
    }  
}
