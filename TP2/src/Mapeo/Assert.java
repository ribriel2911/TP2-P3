package Mapeo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

public class Assert
{
	// Chequea que "valores" y "target" sean iguales como conjuntos
	public static void coinciden(Set<Integer> valores, int[] target)
	{
		assertEquals(target.length, valores.size());
		
		for(Integer valor: target)
			assertTrue(valores.contains(valor));		
	}
}
