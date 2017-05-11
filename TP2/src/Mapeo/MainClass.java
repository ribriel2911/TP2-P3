package Mapeo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import CaminoMinimo.Dijkstra;
import CaminoMinimo.Grafo;
import CaminoMinimo.Nodo;

public class MainClass {

	public static void main(String[] args) {
		
		Mapa mapa = new Mapa();
		mapa.agregarCiudad("San Miguel");
		mapa.agregarCiudad("Bella Vista");
		mapa.agregarCiudad("Polvorines");
		mapa.agregarCiudad("Jose C. Paz");
		
		
		mapa.agregarRuta(0, 1, 10, true);
		mapa.agregarRuta(1, 2, 10, true);
		mapa.agregarRuta(0, 3, 12, false);
		mapa.agregarRuta(3, 2, 12, false);
		mapa.agregarRuta(0, 2, 22, true);
		
		Grafo nuevo = mapa.graficador(1);
		
		List<Nodo> listaCorrecta = new ArrayList<Nodo>();
		listaCorrecta.add(nuevo.getNodo(0));
		
		Grafo grafoFinal = Dijkstra.calcularCaminoMinimo(nuevo, nuevo.getNodo(0));
		
		List<Nodo> caminoMasCortoParaB = grafoFinal.getNodo(6).getCaminoMasCorto();
		for(Nodo n: caminoMasCortoParaB){
			System.out.println(n.getNombre());
		}
		
	}

}
