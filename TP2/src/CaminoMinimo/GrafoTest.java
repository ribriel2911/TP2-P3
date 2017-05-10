package CaminoMinimo;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class GrafoTest 
{
	@Test
	public void dijkstraTest()
	{
		Nodo nodoA = new Nodo("A");
		Nodo nodoB = new Nodo("B");
		Nodo nodoC = new Nodo("C");
		Nodo nodeD = new Nodo("D"); 
		Nodo nodoE = new Nodo("E");
		Nodo nodoF = new Nodo("F");
		 
		nodoA.agregarArista(nodoB, 10);
		nodoA.agregarArista(nodoC, 15);
		 
		nodoB.agregarArista(nodeD, 12);
		nodoB.agregarArista(nodoF, 15);
		 
		nodoC.agregarArista(nodoE, 10);
		 
		nodeD.agregarArista(nodoE, 2);
		nodeD.agregarArista(nodoF, 1);
		 
		nodoF.agregarArista(nodoE, 5);
		 
		Grafo grafo = new Grafo();
		 
		grafo.agregarNodo(nodoA);
		grafo.agregarNodo(nodoB);
		grafo.agregarNodo(nodoC);
		grafo.agregarNodo(nodeD);
		grafo.agregarNodo(nodoE);
		grafo.agregarNodo(nodoF);
		 
		grafo = Dijkstra.calcularCaminoMinimo(grafo, nodoA);
		
		 	List<Nodo> caminoMasCortoParaB = Arrays.asList(nodoA);
	        List<Nodo> caminoMasCortoParaC = Arrays.asList(nodoA);
	        List<Nodo> caminoMasCortoParaD = Arrays.asList(nodoA, nodoB);
	        List<Nodo> caminoMasCortoParaE = Arrays.asList(nodoA, nodoB, nodeD);
	        List<Nodo> caminoMasCortoParaF = Arrays.asList(nodoA, nodoB, nodeD);
	        
	        for(Nodo nodo: grafo.getNodos())
	        {
	        	switch (nodo.getNombre())
	        	{
	        		case "B": assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaB));
	        		break;
	        		
	        		case "C": assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaC));
	        		break;
	        		
	        		case "D": assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaD));
	        		break;
	        		
	        		case "E": assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaE));
	        		break;
	        		
	        		case "F": assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaF));
	        	}
	        }
	}
}
