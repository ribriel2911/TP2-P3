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
		Nodo nodoA = new Nodo(0);
		Nodo nodoB = new Nodo(1);
		Nodo nodoC = new Nodo(2);
		Nodo nodeD = new Nodo(3); 
		Nodo nodoE = new Nodo(4);
		Nodo nodoF = new Nodo(5);
		 
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
		
		 	List<Integer> caminoMasCortoParaB = Arrays.asList(nodoA.getId());
	        List<Integer> caminoMasCortoParaC = Arrays.asList(nodoA.getId());
	        List<Integer> caminoMasCortoParaD = Arrays.asList(nodoA.getId(), nodoB.getId());
	        List<Integer> caminoMasCortoParaE = Arrays.asList(nodoA.getId(), nodoB.getId(), nodeD.getId());
	        List<Integer> caminoMasCortoParaF = Arrays.asList(nodoA.getId(), nodoB.getId(), nodeD.getId());
	        
	        for(Nodo nodo: grafo.getNodos())
	        {
	        	switch (nodo.getId())
	        	{
	        		case 1: assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaB));
	        		break;
	        		
	        		case 2: assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaC));
	        		break;
	        		
	        		case 3: assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaD));
	        		break;
	        		
	        		case 4: assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaE));
	        		break;
	        		
	        		case 5: assertTrue(nodo.getCaminoMasCorto().equals(caminoMasCortoParaF));
	        	}
	        }
	}
}
