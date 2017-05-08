package GrafoP3;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class GrafoPesado
{

	private ArrayList<HashMap<Integer,HashSet<Integer>>> _vecinos;

	public GrafoPesado(int verticesIniciales)
	{
		_vecinos = new ArrayList<HashMap<Integer,HashSet<Integer>>>();
		
		for(int i=0; i<verticesIniciales; ++i)
			_vecinos.add(new HashMap<Integer,HashSet<Integer>>());
	}
	

	public void agregarArista(int i, int j,int dist)
	{
		chequearArista(i, j, "agregar");
		
		
		if(!_vecinos.get(i).containsKey(j))	{_vecinos.get(i).put(j, new HashSet<Integer>());}
		_vecinos.get(i).get(j).add(dist);
	}
	

	public void eliminarArista(int i, int j,int dist)
	{
		chequearArista(i, j, "eliminar");
		
		if(existeArista(i,j)
		&& _vecinos.get(i).get(j).remove(dist)){
			
			if(_vecinos.get(i).get(j).size()==0) _vecinos.get(i).remove(j);
		}
	}
	
	
	public boolean existeArista(int i, int j)
	{
		chequearArista(i, j, "consultar");
		return _vecinos.get(i).containsKey(j);
	}

	public void agregarVertice()
	{
		_vecinos.add(new HashMap<Integer,HashSet<Integer>>());
	}

	public int getGrado(int i)
	{
		chequearVertice(i, "el grado");
		return _vecinos.get(i).size();
	}
	
	public Set<Integer> getVecinos(int i)
	{
		chequearVertice(i, "los vecinos");
		return _vecinos.get(i).keySet();
	}

	public int getVertices()
	{
		return _vecinos.size();
	}	

	// Verifica que un índice corresponda a un vértice válido
	private void chequearVertice(int i, String consulta)
	{
		if( i < 0 || i >= getVertices())
			throw new IllegalArgumentException("Se intentó consultar " + consulta + " de un vértice inexistente! i = " + i);
	}

	// Verifica que los vértices puedan corresponder a una arista
	private void chequearArista(int i, int j, String accion)
	{
		if( i < 0 || i >= getVertices() )
			throw new IllegalArgumentException("Se intentó " + accion + " una arista con un vértice inexistente! i = " + i);
		
		if( j < 0 || j >= getVertices() )
			throw new IllegalArgumentException("Se intentó " + accion + " una arista con un vértice inexistente! j = " + j);
		
		if( i == j )
			throw new IllegalArgumentException("Se intentó " + accion + " una arista con dos vertices iguales! i, j = " + i);
	}
}
