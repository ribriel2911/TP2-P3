package Mapeo;

import GrafoP3.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class GraficadorTest {
	
	@Test
	
	public void mapaVacio(){
		
		Mapa map = new Mapa();
		
		GrafoPesado test = map.Graficador(2);
		
		assertTrue( test.getVertices()==0);
	}
	
	@Test
	public void tamañoTest()
	{
		Mapa map = MapaFeliz();
		
		GrafoPesado ret = map.Graficador(2);
	
		assertTrue( ret.getVertices() == (map.getCantCiudades()*3) );
	}
	

	@Test
	public void tamañoSinPeajes()
	{
		Mapa map = MapaFeliz();
		
		GrafoPesado ret = map.Graficador(0);
	
		assertTrue	( ret.getVertices() == map.getCantCiudades());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tamañoNegativoTest()
	{
		Mapa map = MapaFeliz();
		map.Graficador(-1);
	}
	
	@Test
	public void aristaSinPeajeTest()
	{
		Mapa map = MapaFeliz();
		
		GrafoPesado ret = map.Graficador(2);
	
		assertTrue( ret.existeArista(0,1) );
		assertTrue( ret.existeArista(1,0) );
	}
	

	@Test
	public void aristaConPeajeTest()
	{
		Mapa map = MapaFeliz();
		
		GrafoPesado ret = map.Graficador(2);
	
		assertFalse	( ret.existeArista(6, 7) );
		assertFalse	( ret.existeArista(7, 6) );
	}
	
	
	@Test
	public void aristaEntreGrafosTest()
	{
		Mapa map = MapaFeliz();
		
		GrafoPesado ret = map.Graficador(2);
	
		assertTrue	( ret.existeArista(4, 11) );
		assertFalse	( ret.existeArista(11, 4) );
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
