package CaminoMinimo;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class GrafoTest {

	@Test
	public void agregarNodoTest()
	{
		Grafo grafo = new Grafo();
		grafo.agregarNodo(new Nodo(1));
		assertEquals(1, grafo.getVertices());
	}
	
	@Test
	public void getNodoTest()
	{
		Grafo grafo = new Grafo();
		Nodo nodo = new Nodo(1);
		Nodo nodo2 = new Nodo(2);
		grafo.agregarNodo(nodo);
		grafo.agregarNodo(nodo2);
		assertEquals(nodo2, grafo.getNodo(1));	
	}
	
	@Test
	public void getVerticesTest()
	{
		Grafo grafo = new Grafo();
		grafo.agregarNodo(new Nodo(1));
		grafo.agregarNodo(new Nodo(2));
		grafo.agregarNodo(new Nodo(3));
		assertEquals(3, grafo.getVertices());
	}
	
//	@Test
//	public void getNodosTest()
//	{
//		Grafo grafo = grafoDefault();
//		Set<Nodo> nodosGrafo = nodosDefault();
//		assertEquals(grafo.getNodos(), nodosGrafo);
//	}
	
	public Grafo grafoDefault()
	{
		Grafo grafo = new Grafo();
		
		grafo.agregarNodo(new Nodo(1));
		grafo.agregarNodo(new Nodo(2));
		grafo.agregarNodo(new Nodo(3));
		grafo.agregarNodo(new Nodo(4));
		grafo.agregarNodo(new Nodo(5));
		grafo.agregarNodo(new Nodo(6));
		
		return grafo;
	}
	
	public Set<Nodo> nodosDefault()
	{
		HashSet<Nodo> nodosGrafo = new HashSet<Nodo>();
		
		nodosGrafo.add(new Nodo(1));
		nodosGrafo.add(new Nodo(2));
		nodosGrafo.add(new Nodo(3));
		nodosGrafo.add(new Nodo(4));
		nodosGrafo.add(new Nodo(5));
		nodosGrafo.add(new Nodo(6));
		
		return nodosGrafo;
	}

}
