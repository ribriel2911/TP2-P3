package Mapeo;

import java.util.ArrayList;

import CaminoMinimo.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class MapaGraficadorTest {
	
	@Test
	
	public void mapaVacio(){
		
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
	
	@Test
	public void sinPeajesA3(){
		
		Mapa m = MapaFeliz();
		
		Grafo reducido = m.caminoCorto(0, 0);
		
		Nodo tres = reducido.getNodo(3);
		
		assertTrue(tres.getCaminoMasCorto().get(0)==0);
		assertTrue(tres.getCaminoMasCorto().get(1)==1);
		assertTrue(tres.getCaminoMasCorto().get(2)==3);
		assertTrue(tres.getDistancia()==12);
	}
	
	private Mapa MapaFeliz(){
	
		Mapa map = new Mapa(4);
		map.agregarRuta(0, 1, 6, false);
		map.agregarRuta(0, 2, 4, true);
		map.agregarRuta(0, 3, 10, true);
		map.agregarRuta(1, 3, 6, false);
		map.agregarRuta(2, 3, 4, true);
		
		return map;
		
	}
	
	
	
}
