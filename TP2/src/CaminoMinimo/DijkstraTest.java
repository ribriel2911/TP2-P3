package CaminoMinimo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DijkstraTest 
{
	
	@Test
	public void caminoMasCortoB()
	{
		Grafo grafo = construirGrafo();
		grafo = Dijkstra.calcularCaminoMinimo(grafo, grafo.getNodo(0));
		List<Integer> caminoMasCortoParaB = Arrays.asList(grafo.getNodo(0).getId());
		
		assertTrue(grafo.getNodo(1).getCaminoMasCorto().equals(caminoMasCortoParaB));
	}
	
	@Test
	public void caminoMasCortoC()
	{
		Grafo grafo = construirGrafo();
		grafo = Dijkstra.calcularCaminoMinimo(grafo, grafo.getNodo(0));
		List<Integer> caminoMasCortoParaC = Arrays.asList(grafo.getNodo(0).getId());
		
		assertTrue(grafo.getNodo(2).getCaminoMasCorto().equals(caminoMasCortoParaC));
	}
	
	@Test
	public void caminoMasCortoD()
	{
		Grafo grafo = construirGrafo();
		grafo = Dijkstra.calcularCaminoMinimo(grafo, grafo.getNodo(0));
		List<Integer> caminoMasCortoParaD = Arrays.asList(grafo.getNodo(0).getId(), grafo.getNodo(1).getId());
		
		assertTrue(grafo.getNodo(3).getCaminoMasCorto().equals(caminoMasCortoParaD));
	}
	
	@Test
	public void caminoMasCortoE()
	{
		Grafo grafo = construirGrafo();
		grafo = Dijkstra.calcularCaminoMinimo(grafo, grafo.getNodo(0));
		List<Integer> caminoMasCortoParaE = Arrays.asList(grafo.getNodo(0).getId(), grafo.getNodo(1).getId(), grafo.getNodo(3).getId());
		
		assertTrue(grafo.getNodo(4).getCaminoMasCorto().equals(caminoMasCortoParaE));
	}
	
	@Test
	public void caminoMasCortoF()
	{
		Grafo grafo = construirGrafo();
		grafo = Dijkstra.calcularCaminoMinimo(grafo, grafo.getNodo(0));
		List<Integer> caminoMasCortoParaF = Arrays.asList(grafo.getNodo(0).getId(), grafo.getNodo(1).getId(), grafo.getNodo(3).getId());
		
		assertTrue(grafo.getNodo(5).getCaminoMasCorto().equals(caminoMasCortoParaF));
	}
	
	public Grafo construirGrafo()
	{
		Nodo nodoA = new Nodo(0);
		Nodo nodoB = new Nodo(1);
		Nodo nodoC = new Nodo(2);
		Nodo nodoD = new Nodo(3); 
		Nodo nodoE = new Nodo(4);
		Nodo nodoF = new Nodo(5);
		 
		nodoA.agregarArista(nodoB, 10);
		nodoA.agregarArista(nodoC, 15);
		 
		nodoB.agregarArista(nodoD, 12);
		nodoB.agregarArista(nodoF, 15);
		 
		nodoC.agregarArista(nodoE, 10);
		 
		nodoD.agregarArista(nodoE, 2);
		nodoD.agregarArista(nodoF, 1);
		 
		nodoF.agregarArista(nodoE, 5);
		 
		Grafo grafo = new Grafo();
		 
		grafo.agregarNodo(nodoA);
		grafo.agregarNodo(nodoB);
		grafo.agregarNodo(nodoC);
		grafo.agregarNodo(nodoD);
		grafo.agregarNodo(nodoE);
		grafo.agregarNodo(nodoF);
		
		return grafo;
	}
}
