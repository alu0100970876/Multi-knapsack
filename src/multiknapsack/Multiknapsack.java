package multiknapsack;

import java.util.ArrayList;

public class Multiknapsack {
	public static void main(String args[]) {
		Solucion sol1 = new Solucion("MK100_01.txt");
		System.out.println("Solucion 1 inicial: " + " Valor: " + sol1.valorTotal());
		sol1.greedySolve();
		System.out.println("Solucion 1 final: "  + " Valor: " + sol1.valorTotal());
		System.out.println("FIN");
		Solucion sol2 = new Solucion("MK100_01.txt");
		System.out.println("Solucion 2 inicial: " + " Valor: " + sol2.valorTotal());
		sol2.GRASP(10, 1);
		System.out.println("Solucion 2 final: "  + " Valor: " + sol2.valorTotal() + " " + sol2.isValid());
		System.out.println("FIN");
		Solucion sol3 = new Solucion("MK100_01.txt");
    System.out.println("Solucion3 inicial: " + " Valor: " + sol3.valorTotal());
    sol3.GRASP(10, 2);
    System.out.println("Solucion 3 final: "  + " Valor: " + sol3.valorTotal() + " " + sol2.isValid());
    System.out.println("FIN");
	}
}
