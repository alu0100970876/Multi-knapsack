package multiknapsack;

import java.util.ArrayList;

public class Multiknapsack {
	public static void main(String args[]) {
		Solucion.readFromFile("problema1.txt");
		Solucion sol1 = new Solucion();
		System.out.println(sol1);
		sol1.greedySolve();
		System.out.println(sol1);
	}
}
