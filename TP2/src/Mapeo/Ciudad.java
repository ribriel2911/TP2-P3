package Mapeo;

import java.util.HashMap;
import java.util.Set;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Ciudad {
	
	protected	int								_id;
	protected	String							_nombre;
	protected 	Coordinate						_coordenadas;
	protected	HashMap<Ciudad,Ruta>	_rutas;
	
	public Ciudad(int id, String nombre, Coordinate cord){
		
		_id = id;
		_nombre = nombre;
		_coordenadas = cord;
		_rutas = new HashMap<>();
	}
	
	public void construirRuta(Ciudad destino,boolean peaje){
		
		_rutas.put(destino, new Ruta(distanciaCoord(destino),peaje));
	}
	
	public void destruirRuta(Ciudad destino)	{
		
		if(existeRuta(destino)){
	
				_rutas.remove(destino);
		}
		
		else{
		throw new IllegalArgumentException("No se pudo eliminar la ruta con los parametros\n"
				+ "Origen: Ciudad "+_id+"\n"
				+ "Destino: Ciudad "+destino._id);
		}
	}
	
	public boolean existeRuta(Ciudad destino)	{return _rutas.containsKey(destino);}
	
	public boolean hayPeaje(Ciudad destino)		{return _rutas.get(destino)._peaje;}
		
	public Set<Ciudad> getVecinos()				{return _rutas.keySet();}
	
	public int getCantVecinos()					{return _rutas.size();}
	
	public Ruta getRuta(Ciudad destino)			{return _rutas.get(destino);}
	
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
