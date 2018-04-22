package multiknapsack;

public class Solucion {
	static int capacidades[];
	static double beneficios[];
	int solucion[];
	
	public static int getCapacidad(int index) {
		return capacidades[index];
	}
	public static void setCapacidad(int index, int capacidad) {
		Solucion.capacidades[index] = capacidad;
	}
	public static double getBeneficio(int index) {
		return beneficios[index];
	}
	public static void setBeneficios(int index, double beneficio) {
		Solucion.beneficios[index] = beneficio;
	}
	public int getSolucion(int index) {
		return solucion[index];
	}
	public void setSolucion(int index, int mochila) {
		this.solucion[index] = mochila;
	}
}
