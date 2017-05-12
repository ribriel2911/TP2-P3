package Mapeo;
import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class MapaTest{

	//Tests propios de la clase Grafo trabajada en clase

	@Test
	public void agregarRutaTest(){
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(0, 2,1,false);
		assertTrue( mapa.existeRuta(0, 2));
	}
	
	@Test
	public void agregarIndicesInvertidosTest(){
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(1, 3,1,false);
		assertTrue( mapa.existeRuta(3, 1) );
	}
	
	@Test
	public void rutaInexistenteTest(){
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(1, 3,1,false);
		assertFalse( mapa.existeRuta(1, 2) );
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void construccionErroneaTest(){
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(1, 4,1,false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void idCiudadNegativaTest(){
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(-1, 2,1,false);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void sinLoopsTest(){
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(3, 3,1,false);
	}
	
	@Test
	public void destruirRutaTest(){
		Mapa mapa = new Mapa(4);
		mapa.agregarRuta(1, 2,1,false);

		mapa.eliminarRuta(1, 2,1,false);
		assertFalse( mapa.existeRuta(1, 2) );
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarExcedidoTest(){
		Mapa mapa = new Mapa(4);
		mapa.eliminarRuta(0, 4,1,false);
	}

	@Test(expected = IllegalArgumentException.class)
	public void eliminarNegativoTest(){
		Mapa mapa = new Mapa(4);
		mapa.eliminarRuta(3, -1,1,false);
	}

	@Test
	public void gradoTest(){
		Mapa rueda = construirRueda();
		
		assertEquals(5, rueda.totalVecinos(5));
		assertEquals(3, rueda.totalVecinos(0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void gradoInvalidoTest(){
		Mapa rueda = construirRueda();
		rueda.totalVecinos(-1);	
	}

	@Test(expected = IllegalArgumentException.class)
	public void gradoExcedidoTest(){
		Mapa rueda = construirRueda();
		rueda.totalVecinos(6);		
	}
	
	@Test
	public void vecinosUniversalTest(){
		Mapa rueda = construirRueda();
		Set<Integer> vecinos = rueda.getVecinos(5);
		Assert.coinciden(vecinos, new int[] {0, 1, 2, 3, 4});
	}
	
	@Test
	public void vecinosRegularTest(){
		
		Mapa rueda = construirRueda();
		Set<Integer> vecinos = rueda.getVecinos(3);
		Assert.coinciden(vecinos, new int[] {2, 4, 5});
	}
	
	@Test
	public void aisladoTest(){
		
		Mapa mapa = new Mapa(3);
		Set<Integer> vecinos = mapa.getVecinos(1);
		Assert.coinciden(vecinos, new int[] {});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void vecinosExcedidoTest(){
		
		Mapa mapa = new Mapa(3);
		mapa.getVecinos(3);
	}

	@Test(expected = IllegalArgumentException.class)
	public void vecinosNegativoTest(){
		
		Mapa mapa = new Mapa(3);
		mapa.getVecinos(-1);
	}
	
	private Mapa construirRueda(){
		
		Mapa rueda = new Mapa(6);
		rueda.agregarRuta(0, 1,1,false);
		rueda.agregarRuta(1, 2,1,false);
		rueda.agregarRuta(2, 3,1,false);
		rueda.agregarRuta(3, 4,1,false);
		rueda.agregarRuta(4, 0,1,false);
		rueda.agregarRuta(0, 5,1,false);
		rueda.agregarRuta(1, 5,1,false);
		rueda.agregarRuta(2, 5,1,false);
		rueda.agregarRuta(3, 5,1,false);
		rueda.agregarRuta(4, 5,1,false);
		
		return rueda;		
	}
}








