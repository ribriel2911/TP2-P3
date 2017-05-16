package CaminoMinimo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Grafo
{
	
	protected ArrayList<Nodo> _nodos = new ArrayList<>();
    
    public void agregarNodo(Nodo nodo) 
    {
        _nodos.add(nodo);
    }

	public Set<Nodo> getNodos() 
	{
		HashSet<Nodo> ret = new HashSet<>();
		ret.addAll(_nodos);
		
		return ret;
	}

	public void setNodos(ArrayList<Nodo> nodos) 
	{
		_nodos = nodos;
	}

	public int getVertices() 
	{
		return _nodos.size();
	}
	
	public Nodo getNodo(int i)
	{
		return _nodos.get(i); 
	}

	public boolean existeArista(int i, int j) 
	{
		return _nodos.get(i)._nodosVecinos.containsKey(_nodos.get(j));
	}
}
