package Mapeo;

import java.util.LinkedList;
import CaminoMinimo.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MapaGraficadoTest {
	
	@Test
	public void mapaVacio()
	{
		Mapa map = new Mapa();
		Grafo test = map.graficador(2);
		
		assertTrue( test.getVertices()==0);
	}
	
	@Test
	public void tamanoTest()
	{
		Mapa map = MapaFeliz();
		Grafo ret = map.graficador(1);
	
		assertTrue( ret.getVertices() == (map.totalCiudades()*2) );
	}

	@Test
	public void tamanoSinPeajes()
	{
		Mapa map = MapaFeliz();
		Grafo ret = map.graficador(0);
	
		assertTrue	( ret.getVertices() == map.totalCiudades());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tamanoNegativoTest()
	{
		Mapa map = MapaFeliz();
		
		map.graficador(-1);
	}
	
	@Test
	public void aristaSinPeajeTest()
	{
		Mapa map = MapaFeliz();
		Grafo ret = map.graficador(1);
	
		assertTrue( ret.existeArista(0,1) );
		assertTrue( ret.existeArista(1,0) );
	}

	@Test
	public void aristaConPeajeTest()
	{
		Mapa map = MapaFeliz();
		Grafo ret = map.graficador(1);
	
		assertFalse	( ret.existeArista(2, 3) );
		assertFalse	( ret.existeArista(2, 3) );
	}
	
	@Test
	public void aristaEntreGrafosTest()
	{
		Mapa map = MapaFeliz();
		Grafo ret = map.graficador(1);
	
		assertTrue	( ret.existeArista(0, 7) );
		assertFalse	( ret.existeArista(7, 0) );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CaminoANodoNegativo(){
		
		Mapa m = MapaFeliz();
		Grafo g = toDijkstra(m,1);
		
		@SuppressWarnings("unused")
		Nodo tres = m.caminoA(-1, g);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CaminoANodoExcedido(){
		
		Mapa m = MapaFeliz();
		Grafo g = toDijkstra(m,1);
		
		@SuppressWarnings("unused")
		Nodo tres = m.caminoA(5, g);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CaminoANodoEnGrafoIncorrecto(){
		
		Mapa m = MapaFeliz();
		Grafo x = new Grafo();
		x.agregarNodo(new Nodo(0));
		
		@SuppressWarnings("unused")
		Nodo tres = m.caminoA(0, x);
	}
	
	@Test
	public void CaminoAlNodo3()
	{		
		Mapa m = MapaFeliz();
		Grafo g = toDijkstra(m,1);
		Nodo tres = m.caminoA(3, g);

		assertTrue(tres.getCaminoMasCorto().get(0)==0);
		assertTrue(tres.getCaminoMasCorto().get(1)==3);
		assertTrue(tres.getDistancia()==10);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CaminosConNodoNegativo(){
		
		Mapa m = MapaFeliz();
		
		@SuppressWarnings("unused")
		Grafo reducido = m.caminoCorto(-1, 1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void CaminosConPeajeNegativo(){
		
		Mapa m = MapaFeliz();
		
		@SuppressWarnings("unused")
		Grafo reducido = m.caminoCorto(0, -1);
	}
	
	@Test
	public void DistanciasCortas(){
		
		Mapa m = MapaFeliz();
		Grafo reducido = m.caminoCorto(0, 1);
		
		Nodo cero = reducido.getNodo(0);
		Nodo uno = reducido.getNodo(1);
		Nodo dos = reducido.getNodo(2);
		Nodo tres = reducido.getNodo(3);
		
		assertTrue(cero.getDistancia()==0);
		assertTrue(uno.getDistancia()==6);
		assertTrue(dos.getDistancia()==4);
		assertTrue(tres.getDistancia()==10);
	}
	
	@Test
	public void CaminosCortos(){
		
		Mapa m = MapaFeliz();
		Grafo reducido = m.caminoCorto(0, 1);
		
		Nodo cero = reducido.getNodo(0);
		Nodo uno = reducido.getNodo(1);
		Nodo dos = reducido.getNodo(2);
		Nodo tres = reducido.getNodo(3);
		
		LinkedList<Integer> aux0 = new LinkedList<>();
		aux0.add(0);
		
		LinkedList<Integer> aux1 = new LinkedList<>();
		aux1.add(0);
		aux1.add(1);
		
		LinkedList<Integer> aux2 = new LinkedList<>();
		aux2.add(0);
		aux2.add(2);
		
		LinkedList<Integer> aux3 = new LinkedList<>();
		aux3.add(0);
		aux3.add(3);
		
		assertTrue(cero.getCaminoMasCorto().equals(aux0));
		assertTrue(uno.getCaminoMasCorto().equals(aux1));
		assertTrue(dos.getCaminoMasCorto().equals(aux2));
		assertTrue(tres.getCaminoMasCorto().equals(aux3));
	}
	
	private Mapa MapaFeliz(){
		
		//El metodo agregar ruta calcula la distancia entre las coordenadas automaticamente
		//por eso para realizar este test las modifico manualmente una a una para generarme un
		//caso de prueba
	
		Mapa map = new Mapa(4);
		map.agregarRuta(0, 1, false);
		map._ciudades.get(0)._rutas.get(map._ciudades.get(1)).set_distancia(6);
		map.agregarRuta(0, 2, true);
		map._ciudades.get(0)._rutas.get(map._ciudades.get(2)).set_distancia(4);
		map.agregarRuta(0, 3, true);
		map._ciudades.get(0)._rutas.get(map._ciudades.get(3)).set_distancia(10);
		map.agregarRuta(1, 3, false);
		map._ciudades.get(1)._rutas.get(map._ciudades.get(3)).set_distancia(6);
		map.agregarRuta(2, 3, true);
		map._ciudades.get(2)._rutas.get(map._ciudades.get(3)).set_distancia(4);
		
		return map;
	}
	
	private Grafo toDijkstra(Mapa m, int p){
		
		Grafo g = m.graficador(p);
		
		g = Dijkstra.calcularCaminoMinimo(g, g.getNodo(0));
		
		return g;
	}
}
