package CaminoMinimo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra
{
	public static Grafo calcularCaminoMinimo(Grafo grafo, Nodo nodoOrigen) 
	{
	    nodoOrigen.setDistancia(0);
	 
	    Set<Nodo> nodosAsentados = new HashSet<>();
	    Set<Nodo> nodosNoAsentados = new HashSet<>();
	 
	    nodosNoAsentados.add(nodoOrigen);
	 
	    while (nodosNoAsentados.size() != 0) 
	    {
	        Nodo nodoActual = nodoMenorDistancia(nodosNoAsentados);
	        nodosNoAsentados.remove(nodoActual);
	        for (Entry < Nodo, Integer> parVecinos: nodoActual.getNodosVecinos().entrySet()) 
	        {
	            Nodo nodoVecino = parVecinos.getKey();
	            Integer pesoArista = parVecinos.getValue();
	            
	            if (!nodosAsentados.contains(nodoVecino)) 
	            {
	                calcularDistanciaMinima(nodoVecino, pesoArista, nodoActual);
	                nodosNoAsentados.add(nodoVecino);
	            }
	        }
	        
	        nodosAsentados.add(nodoActual);
	    }
	    
	    return grafo;
	}
	
	private static Nodo nodoMenorDistancia(Set < Nodo > nodosNoAsentados)
	{
	    Nodo nodoMinimaDistancia = null;
	    int minimaDistancia = Integer.MAX_VALUE;
	    
	    for (Nodo nodo: nodosNoAsentados)
	    {
	        int distanciaNodo = nodo.getDistancia();
	        
	        if (distanciaNodo < minimaDistancia) 
	        {
	            minimaDistancia = distanciaNodo;
	            nodoMinimaDistancia = nodo;
	        }
	    }
	    
	    return nodoMinimaDistancia;
	}
	
	private static void calcularDistanciaMinima(Nodo nodoEvaluado,Integer pesoArista, Nodo nodoActual) 
	{
			    Integer distanciaActual = nodoActual.getDistancia();
			    
			    if (distanciaActual + pesoArista < nodoEvaluado.getDistancia())
			    {
			        nodoEvaluado.setDistancia(distanciaActual + pesoArista);
			        LinkedList<Integer> caminoMasCorto = new LinkedList<>(nodoActual.getCaminoMasCorto());
			        caminoMasCorto.add(nodoActual.getId());
			        nodoEvaluado.setCaminoMasCorto(caminoMasCorto);
			    }
			}
}
