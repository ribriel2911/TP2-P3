package Mapeo;
import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Test;

public class MapeoTest
{
	@Test
	public void agregarRutaTest()
	{
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(0, 2,1,false);
		assertTrue( mapa._ciudades.get(0).existeRuta(mapa._ciudades.get(2)) );
	}
	
	@Test
	public void rutaInexistenteTest()
	{
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(1, 3,1,false);
		assertFalse( mapa._ciudades.get(1).existeRuta(mapa._ciudades.get(2)) );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void construccionErroneaTest()
	{
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(1, 4,1,false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void idCiudadNegativaTest()
	{
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(-1, 2,1,false);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void sinLoopsTest()
	{
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(3, 3,1,false);
	}
	
	@Test
	public void destruirRutaTest()
	{
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(1, 2,1,false);

		mapa.eliminarRuta(1, 2,1,false);
		assertFalse( mapa._ciudades.get(1).existeRuta(mapa._ciudades.get(2)) );
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarExcedidoTest()
	{
		Mapa mapa = new Mapa(4);
		mapa.eliminarRuta(0, 4,1,false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarNegativoTest()
	{
		Mapa mapa = new Mapa(4);
		mapa.eliminarRuta(3, -1,1,false);
	}

	@Test
	public void gradoTest()
	{
		Mapa rueda = construirRueda();
		
		assertEquals(5, rueda._ciudades.get(5).getCantVecinos());
		assertEquals(3, rueda._ciudades.get(0).getCantVecinos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void gradoInvalidoTest()
	{
		Mapa rueda = construirRueda();
		rueda._ciudades.get(-1).getCantVecinos();		
	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void gradoExcedidoTest()
//	{
//		Grafo rueda = construirRueda();
//		rueda.getGrado(6);		
//	}
//	
//	@Test
//	public void vecinosUniversalTest()
//	{
//		Grafo rueda = construirRueda();
//		Set<Integer> vecinos = rueda.getVecinos(5);
//		Assert.coinciden(vecinos, new int[] {0, 1, 2, 3, 4});
//	}
//	
//	@Test
//	public void vecinosRegularTest()
//	{
//		Grafo rueda = construirRueda();
//		Set<Integer> vecinos = rueda.getVecinos(3);
//		Assert.coinciden(vecinos, new int[] {2, 4, 5});
//	}
//	
//	@Test
//	public void aisladoTest()
//	{
//		Grafo grafo = new Grafo(3);
//		Set<Integer> vecinos = grafo.getVecinos(1);
//		Assert.coinciden(vecinos, new int[] {});
//	}
//	
//	@Test(expected = IllegalArgumentException.class)
//	public void vecinosExcedidoTest()
//	{
//		Grafo grafo = new Grafo(3);
//		grafo.getVecinos(3);
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void vecinosNegativoTest()
//	{
//		Grafo grafo = new Grafo(3);
//		grafo.getVecinos(-1);
//	}
//	
	private Mapa construirRueda()
	{
		Mapa rueda = new Mapa(6);
		rueda.agregarDobleMano(0, 1,1,false);
		rueda.agregarDobleMano(1, 2,1,false);
		rueda.agregarDobleMano(2, 3,1,false);
		rueda.agregarDobleMano(3, 4,1,false);
		rueda.agregarDobleMano(4, 0,1,false);
		rueda.agregarDobleMano(0, 5,1,false);
		rueda.agregarDobleMano(1, 5,1,false);
		rueda.agregarDobleMano(2, 5,1,false);
		rueda.agregarDobleMano(3, 5,1,false);
		rueda.agregarDobleMano(4, 5,1,false);
		
		return rueda;		
	}
}








