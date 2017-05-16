package CaminoMinimo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.junit.Test;

public class NodoTest {

	@Test
	public void getIdTest()
	{
		Nodo nodo = new Nodo(1);
		assertEquals(1, nodo.getId());
	}
	
	@Test
	public void setIdTest()
	{
		Nodo nodo = new Nodo(2);
		nodo.setId(5);
		assertEquals(5, nodo.getId());
	}
	
	@Test
	public void agregarAristaTest()
	{
		Nodo nodo = new Nodo(1);
		nodo.agregarArista(new Nodo(2), 10);
		assertEquals(1, nodo._nodosVecinos.size());
	}
	
	@Test
	public void getDistanciaTest()
	{
		Nodo nodo = new Nodo(4);
		nodo.setDistancia(10);
		assertEquals(new Integer(10), nodo.getDistancia());
	}
	
	@Test
	public void getNodosVecinosTest() //creo que es muy largo... hay que verlo..
	{
		Nodo nodo = new Nodo(1);
		Nodo nodo2 = new Nodo(2);
		Nodo nodo3 = new Nodo(3);
		Nodo nodo4 = new Nodo(4);
		
		nodo.agregarArista(nodo2, 10);
		nodo.agregarArista(nodo3, 15);
		nodo.agregarArista(nodo4, 20);
		
		Map<Nodo, Integer> vecinos = new HashMap<Nodo, Integer>();
		
		vecinos.put(nodo2, 10);
		vecinos.put(nodo3, 15);
		vecinos.put(nodo4, 20);
		
		assertEquals(nodo.getNodosVecinos(), vecinos);
	}
	
	@Test
	public void setNodosVecinosTest()
	{
		Nodo nodo = new Nodo(1);
		
		Map<Nodo, Integer> vecinos = new HashMap<Nodo, Integer>();
		vecinos.put(new Nodo(2), 10);
		vecinos.put(new Nodo(3), 15);
		vecinos.put(new Nodo(4), 20);
		
		nodo.setNodosVecinos(vecinos);
		assertEquals(nodo.getNodosVecinos(), vecinos);
	}
	
	@Test
	public void getCaminoMasCortoTest()
	{
		Nodo nodo = new Nodo(5);
		
		LinkedList<Integer> caminoMasCorto = new LinkedList<Integer>();
		caminoMasCorto.add(1);
		caminoMasCorto.add(3);
		caminoMasCorto.add(5);
		
		nodo.setCaminoMasCorto(caminoMasCorto);
		assertEquals(nodo.getCaminoMasCorto(), caminoMasCorto);
	}
	
	@SuppressWarnings("static-access")
	@Test(expected = IllegalArgumentException.class)
	public void crearNodosCantidadNegativaTest()
	{
		Nodo nodo = new Nodo(1);
		nodo.crearNodos(-5);  		
	}
	
	@Test
	public void crearNodosTest()       
	{
		ArrayList<Nodo> nodosAutomatico = Nodo.crearNodos(3);
		
		ArrayList<Nodo> nodosCreados = new ArrayList<Nodo>();
		nodosCreados.add(nodosAutomatico.get(0));
		nodosCreados.add(nodosAutomatico.get(1));
		nodosCreados.add(nodosAutomatico.get(2));
			
		assertTrue(nodosCreados.equals(nodosAutomatico));
	}	
}
