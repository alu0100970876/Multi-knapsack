package multiknapsack;

import java.util.ArrayList;

public class Multiknapsack {
	public static void main(String args[]) {
		Solucion sol1 = new Solucion("problema1.txt");
		System.out.println("Solucion 1 inicial: " + sol1+ " Valor: " + sol1.valorTotal());
		sol1.greedySolve();
		System.out.println("Solucion 1 final: " + sol1 + " Valor: " + sol1.valorTotal());
		System.out.println("FIN");
		Solucion sol2 = new Solucion("problema1.txt");
		System.out.println("Solucion 2 inicial: " + sol2+ " Valor: " + sol2.valorTotal());
		sol2.GRASP(100000);
		System.out.println("Solucion 2 final: " + sol2 + " Valor: " + sol2.valorTotal());
		System.out.println("FIN");
	}
}
