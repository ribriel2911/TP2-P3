package CaminoMinimo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Nodo 
{	
	private int _id;
    
    private LinkedList<Integer> _caminoMasCorto = new LinkedList<>();
     
    private Integer _distancia = Integer.MAX_VALUE;
     
    Map<Nodo, Integer> _nodosVecinos = new HashMap<>();
 
    public void agregarArista(Nodo destino, int distancia) 
    {
        _nodosVecinos.put(destino, distancia);
    }
  
    public Nodo(int id) 
    {
        _id = id;
    }

	public int getId() {
		return _id;
	}

	public void setId(int id) 
	{
		_id = id;
	}

	public LinkedList<Integer> getCaminoMasCorto() 
	{
		return _caminoMasCorto;
	}

	public void setCaminoMasCorto(LinkedList<Integer> caminoMasCorto) {
		_caminoMasCorto = caminoMasCorto;
	}

	public Integer getDistancia() 
	{
		return _distancia;
	}

	public void setDistancia(Integer distancia) 
	{
		_distancia = distancia;
	}

	public Map<Nodo, Integer> getNodosVecinos() 
	{
		return _nodosVecinos;
	}

	public void setNodosVecinos(Map<Nodo, Integer> nodosVecinos) 
	{
		_nodosVecinos = nodosVecinos;
	} 
	
	public static ArrayList<Nodo> crearNodos(int cantidadNodos) 
	{
		if(cantidadNodos<0)
			throw new IllegalArgumentException("Se intentó crear una cantidad negativa de nodos: " + cantidadNodos);
		
		
		ArrayList<Nodo> nodos = new ArrayList<>();
		
		for(int i=0;i<cantidadNodos;i++)
		{
			Nodo n = new Nodo(i);
			nodos.add(n);
		}
		
		return nodos;
	}
}
