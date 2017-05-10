package CaminoMinimo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Nodo 
{	
	private String _nombre;
    
    private List<Nodo> _caminoMasCorto = new LinkedList<>();
     
    private Integer _distancia = Integer.MAX_VALUE;
     
    Map<Nodo, Integer> _nodosVecinos = new HashMap<>();
 
    public void agregarArista(Nodo destino, int distancia) {
        _nodosVecinos.put(destino, distancia);
    }
  
    public Nodo(String nombre) {
        _nombre = nombre;
    }

	public String getNombre() {
		return _nombre;
	}

	public void setNombre(String nombre) {
		_nombre = nombre;
	}

	public List<Nodo> getCaminoMasCorto() {
		return _caminoMasCorto;
	}

	public void setCaminoMasCorto(List<Nodo> caminoMasCorto) {
		_caminoMasCorto = caminoMasCorto;
	}

	public Integer getDistancia() {
		return _distancia;
	}

	public void setDistancia(Integer distancia) {
		_distancia = distancia;
	}

	public Map<Nodo, Integer> getNodosVecinos() {
		return _nodosVecinos;
	}

	public void setNodosVecinos(Map<Nodo, Integer> nodosVecinos) {
		_nodosVecinos = nodosVecinos;
	} 
}
