package CaminoMinimo;

import java.util.HashSet;
import java.util.Set;

public class Grafo {
	
	private Set<Nodo> _nodos = new HashSet<>();
    
    public void agregarNodo(Nodo nodo) {
        _nodos.add(nodo);
    }

	public Set<Nodo> getNodos() {
		return _nodos;
	}

	public void setNodos(Set<Nodo> nodos) {
		_nodos = nodos;
	}
 
    // getters and setters

}
