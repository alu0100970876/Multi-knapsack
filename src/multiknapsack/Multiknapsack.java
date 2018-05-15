package multiknapsack;

import java.util.ArrayList;

public class Multiknapsack {
	public static void main(String args[]) {
	  Clock temporizador = new Clock();
		Solucion sol1 = new Solucion("instancias/MK75_03.txt");
		System.out.println("Solucion 1 inicial: " + " Valor: " + sol1.valorTotal());
		temporizador.start();
		sol1.greedySolve();
		temporizador.stop();
		System.out.println("Solucion 1 final: "  + " Valor: " + sol1.valorTotal() + " " + sol1.isValid() + " Tiempo: " + (temporizador.getElapsedTime())+ "ms");
		System.out.println("FIN");
		Solucion sol2 = new Solucion("instancias/MK75_03.txt");
		System.out.println("Solucion 2 inicial: " + " Valor: " + sol2.valorTotal());
		temporizador.start();
		sol2.GRASP(10, 1, 5);
		temporizador.stop();
		System.out.println("Solucion 2 final: "  + " Valor: " + sol2.valorTotal() + " " + sol2.isValid()+ " Tiempo: " + (temporizador.getElapsedTime())+ "ms");
		System.out.println("FIN");
		Solucion sol3 = new Solucion("instancias/MK75_03.txt");
	  System.out.println("Solucion 3 inicial: " + " Valor: " + sol3.valorTotal());
	  temporizador.start();
	  sol3.GRASP(10, 2, 5);
	  temporizador.stop();
	  System.out.println("Solucion 3 final: "  + " Valor: " + sol3.valorTotal() + " " + sol3.isValid()+ " Tiempo: " + (temporizador.getElapsedTime())+ "ms");
	  System.out.println("FIN");
//.----
	  Solucion sol4 = new Solucion("instancias/MK75_03.txt");
    System.out.println("Solucion 4 inicial: " + " Valor: " + sol4.valorTotal());
    temporizador.start();
    sol4.VNS(10, 5);
    temporizador.stop();
    System.out.println("Solucion 4 final: "  + " Valor: " + sol4.valorTotal() + " " + sol4.isValid()+ " Tiempo: " + (temporizador.getElapsedTime())+ "ms");
    System.out.println("FIN");
    Solucion sol5 = new Solucion("instancias/MK75_03.txt");
    System.out.println("Solucion 5 inicial: " + " Valor: " + sol5.valorTotal());
    temporizador.start();
    sol5.LNS(10, 5);
    temporizador.stop();
    System.out.println("Solucion 5 final: "  + " Valor: " + sol5.valorTotal() + " " + sol5.isValid()+ " Tiempo: " + (temporizador.getElapsedTime())+ "ms");
    System.out.println("FIN");
	}
}
