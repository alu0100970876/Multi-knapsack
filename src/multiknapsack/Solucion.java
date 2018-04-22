package multiknapsack;

import java.util.ArrayList;

public class Solucion {	
	static ArrayList<Double> capacidades = new ArrayList<Double>();
	static ArrayList<Double> beneficios = new ArrayList<Double>();
	static ArrayList<Double> pesos = new ArrayList<Double>();
	ArrayList<Integer> solucion = new ArrayList<Integer>();
	double valortotal;
		
	public double getValortotal() {
		return valortotal;
	}

	public void setValortotal(double valortotal) {
		this.valortotal = valortotal;
	}

	public static double getCapacidad(int index) {
		return capacidades.get(index);
	}
	
	public static void setCapacidad(int index, double capacidad) {
		Solucion.capacidades.set(index, capacidad);
	}
	
	public static double getBeneficio(int index) {
		return beneficios.get(index);
	}
	
	public static void setBeneficios(int index, double beneficio) {
		Solucion.beneficios.set(index, beneficio);
	}
	
	public int getSolucion(int index) {
		return solucion.get(index);
	}
	
	public void setSolucion(int index, int mochila) {
		this.solucion.set(index, mochila) ;
	}
	
	
	/** Calcula el peso total de todas las mochilas
	 * @return
	 */
	public double valorTotal() {
		double valor = 0;
		for(int i = 0; i < capacidades.size(); i++) {
			valor += valorMochila(i);
		}
		this.setValortotal(valor);
		return this.getValortotal();
	}
	
	/** Calcula el peso de la carga para una mochila dada
	 * @param mochila
	 * @return
	 */
	public double pesoMochila(int mochila) {
		double peso = 0;
		for(int i = 0; i < solucion.size(); i++) {
			if(solucion.get(i) == mochila) {
				peso += pesos.get(i);
			}
		}
		return peso;
	}
	
	/** Calcula el valor de la carga para una mochila dada
	 * @param mochila
	 * @return
	 */
	public double valorMochila(int mochila) {
		double beneficio = 0;
		for(int i = 0; i < solucion.size(); i++) {
			if(solucion.get(i) == mochila) {
				beneficio += beneficios.get(i);
			}
		}
		return beneficio;
	}
	
	/** comprueba que una solucion sea valida
	 * @return
	 */
	public boolean isValid() {
		for(int i =  0; i < capacidades.size(); i++) {
			if(this.pesoMochila(i) > capacidades.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	
}
