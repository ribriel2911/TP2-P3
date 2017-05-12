package Mapeo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import CaminoMinimo.Dijkstra;
import CaminoMinimo.Grafo;
import CaminoMinimo.Nodo;

public class MainClass {

	public static void main(String[] args) {
		
		Mapa mapa = new Mapa();
		mapa.agregarCiudad("0");
		mapa.agregarCiudad("1");
		mapa.agregarCiudad("2");
		mapa.agregarCiudad("3");
		
		
		mapa.agregarRuta(0, 1, 6, false);
		mapa.agregarRuta(0, 2, 4, true);
		mapa.agregarRuta(0, 3, 10, true);
		mapa.agregarRuta(1, 3, 6, false);
		mapa.agregarRuta(2, 3, 4, true);
		
		Grafo nuevo = mapa.graficador(2);
		
		List<Nodo> listaCorrecta = new ArrayList<Nodo>();
		listaCorrecta.add(nuevo.getNodo(0));
		
		Grafo grafoFinal = Dijkstra.calcularCaminoMinimo(nuevo, nuevo.getNodo(0));
		
		LinkedList<Integer> caminoMasCortoParaB = grafoFinal.getNodo(11).getCaminoMasCorto();
		for(Integer n: caminoMasCortoParaB){
			System.out.println(n);
		}
		
		System.out.println(grafoFinal.getNodo(11).getDistancia());
		
	}

}
