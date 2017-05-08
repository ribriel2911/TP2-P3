package GrafoP3;
import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Test;

public class GrafoTest
{
	@Test
	public void agregarAristaTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.agregarArista(0, 2,1);
		assertTrue( grafo.existeArista(0, 2) );
	}
	
	@Test
	public void aristaInexistenteTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.agregarArista(1, 3,1);
		assertFalse( grafo.existeArista(1, 2) );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregadoErroneoTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.agregarArista(1, 4,1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.agregarArista(-1, 2,1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void sinLoopsTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.agregarArista(3, 3,1);
	}
	
	@Test
	public void eliminarAristaTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.agregarArista(1, 2,1);

		grafo.eliminarArista(1, 2,1);
		assertFalse( grafo.existeArista(1, 2) );
	}

	@Test
	public void eliminarCruzadoTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.agregarArista(1, 2,1);

		grafo.eliminarArista(1, 2,1);
		assertFalse( grafo.existeArista(2, 1) );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void eliminarExcedidoTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.eliminarArista(0, 4,1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarNegativoTest()
	{
		GrafoPesado grafo = new GrafoPesado(4);
		grafo.eliminarArista(3, -1,1);
	}

	@Test
	public void gradoTest()
	{
		GrafoPesado rueda = construirRueda();
		
		assertEquals(5, rueda.getGrado(5));
		assertEquals(3, rueda.getGrado(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void gradoInvalidoTest()
	{
		GrafoPesado rueda = construirRueda();
		rueda.getGrado(-1);		
	}

	@Test(expected = IllegalArgumentException.class)
	public void gradoExcedidoTest()
	{
		GrafoPesado rueda = construirRueda();
		rueda.getGrado(6);		
	}
	
	@Test
	public void vecinosUniversalTest()
	{
		GrafoPesado rueda = construirRueda();
		Set<Integer> vecinos = rueda.getVecinos(5);
		Assert.coinciden(vecinos, new int[] {0, 1, 2, 3, 4});
	}
	
	@Test
	public void vecinosRegularTest()
	{
		GrafoPesado rueda = construirRueda();
		Set<Integer> vecinos = rueda.getVecinos(3);
		Assert.coinciden(vecinos, new int[] {2, 4, 5});
	}
	
	@Test
	public void aisladoTest()
	{
		GrafoPesado grafo = new GrafoPesado(3);
		Set<Integer> vecinos = grafo.getVecinos(1);
		Assert.coinciden(vecinos, new int[] {});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void vecinosExcedidoTest()
	{
		GrafoPesado grafo = new GrafoPesado(3);
		grafo.getVecinos(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void vecinosNegativoTest()
	{
		GrafoPesado grafo = new GrafoPesado(3);
		grafo.getVecinos(-1);
	}
	
	private GrafoPesado construirRueda()
	{
		GrafoPesado rueda = new GrafoPesado(6);
		rueda.agregarArista(0, 1,1);
		rueda.agregarArista(1, 0,1);
		rueda.agregarArista(1, 2,1);
		rueda.agregarArista(2, 1,1);
		rueda.agregarArista(2, 3,1);
		rueda.agregarArista(3, 2,1);
		rueda.agregarArista(3, 4,1);
		rueda.agregarArista(4, 3,1);
		rueda.agregarArista(4, 0,1);
		rueda.agregarArista(0, 4,1);
		rueda.agregarArista(0, 5,1);
		rueda.agregarArista(5, 0,1);
		rueda.agregarArista(1, 5,1);
		rueda.agregarArista(5, 1,1);
		rueda.agregarArista(2, 5,1);
		rueda.agregarArista(5, 2,1);
		rueda.agregarArista(3, 5,1);
		rueda.agregarArista(5, 3,1);
		rueda.agregarArista(4, 5,1);
		rueda.agregarArista(5, 4,1);
		
		return rueda;		
	}
}








