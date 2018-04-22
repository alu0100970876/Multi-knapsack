package multiknapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Solucion {	
	static ArrayList<Integer> capacidades = new ArrayList<Integer>();
	static ArrayList<Integer> beneficios = new ArrayList<Integer>();
	static ArrayList<Integer> pesos = new ArrayList<Integer>();
	ArrayList<Integer> solucion = new ArrayList<Integer>();
	int valortotal;
	
	Solucion() {
		solucion = new ArrayList<Integer>();
		for(int i = 0; i < beneficios.size(); ++i)
			solucion.add(-1);
		setValortotal(0);
	}
	
	static void readFromFile(String inputfile) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputfile));
			int nMochilas = new Integer(reader.readLine());
			int nObjetos = new Integer(reader.readLine());
			
			for(int i = 0; i < nMochilas; ++i)
				capacidades.add(new Integer(reader.readLine()));
			
			String line[];
			for(int i = 0; i < nObjetos; ++i) {
				line = reader.readLine().split("\\s");
				beneficios.add(new Integer(line[0]));
				pesos.add(new Integer(line[1]));
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int getValortotal() {
		return valortotal;
	}

	public void setValortotal(int valortotal) {
		this.valortotal = valortotal;
	}

	public static int getCapacidad(int index) {
		return capacidades.get(index);
	}
	
	public static void setCapacidad(int index, int capacidad) {
		Solucion.capacidades.set(index, capacidad);
	}
	
	public static int getBeneficio(int index) {
		return beneficios.get(index);
	}
	
	public static void setBeneficio(int index, int beneficio) {
		Solucion.beneficios.set(index, beneficio);
	}
	
	public static int getPeso(int index) {
		return pesos.get(index);
	}

	public static void setPeso(int index, int peso) {
		Solucion.pesos.set(index, peso);
	}

	public int getSolucion(int index) {
		return solucion.get(index);
	}
	
	public void setSolucion(int index, int mochila) {
		this.solucion.set(index, mochila) ;
	}
	
	
	/**
	 * Calcula el valor total de todas las mochilas
	 * @return
	 */
	public int valorTotal() {
		int valor = 0;
		for(int i = 0; i < capacidades.size(); i++) {
			valor += valorMochila(i);
		}
		this.setValortotal(valor);
		return this.getValortotal();
	}
	
	/**
	 * Calcula el peso de la carga para una mochila dada
	 * @param mochila
	 * @return
	 */
	public int pesoMochila(int mochila) {
		int peso = 0;
		for(int i = 0; i < solucion.size(); i++) {
			if(solucion.get(i) == mochila) {
				peso += pesos.get(i);
			}
		}
		return peso;
	}
	
	/**
	 * Calcula el valor de la carga para una mochila dada
	 * @param mochila
	 * @return
	 */
	public int valorMochila(int mochila) {
		int beneficio = 0;
		for(int i = 0; i < solucion.size(); i++) {
			if(solucion.get(i) == mochila) {
				beneficio += beneficios.get(i);
			}
		}
		return beneficio;
	}
	
	/**
	 * Comprueba que una solucion sea valida
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
	
	public void greedySolve() {
		ArrayList<Integer> beneficiosRestantes = (ArrayList<Integer>) beneficios.clone();
		ArrayList<Integer> pesosRestantes = (ArrayList<Integer>) pesos.clone();
		int i = 0;
		while((i < capacidades.size()) && (!beneficiosRestantes.isEmpty())) {
			greedySolveKnapsack(i, beneficiosRestantes, pesosRestantes);
			// System.out.println("beneficios restantes size = " + beneficiosRestantes.size());
			i++;
		}
	}
	
	private void greedySolveKnapsack(int mochila, ArrayList<Integer> beneficios, ArrayList<Integer> pesos) {
		Integer v[][] = new Integer[beneficios.size() + 1][getCapacidad(mochila) + 1];
		for(int j = 0; j <= getCapacidad(mochila); ++j)
			v[0][j] = 0;
		for(int i = 1; i <= beneficios.size(); ++i) {
			for(int j = 0; j <= getCapacidad(mochila); ++j) {
				int left = v[i - 1][j];
				if(j - getPeso(i-1) < 0)
					v[i][j] = left;
				else {
					int up = v[i-1][j - getPeso(i-1)] + getBeneficio(i-1);
					v[i][j] = Math.max(left, up);
				}
			}
		}
		
		// Obtener indices de los objetos metidos en esta mochila
		// Indices relativos a los objetos restantes
		ArrayList<Integer> indicesRelativos = new ArrayList<Integer>();
		int capacidadRestante = getCapacidad(mochila);
		for(int i = beneficios.size(); i >= 1; --i) {
			if((getPeso(i-1) <= capacidadRestante) && (v[i-1][capacidadRestante - getPeso(i-1)]
					+ getBeneficio(i-1) == v[i][capacidadRestante])) {
				indicesRelativos.add(i-1);
				capacidadRestante = capacidadRestante - getPeso(i-1);
			}
		}
		
		// Actualizar la solucion
		// i -> recorrer solucion general
		// j -> recorrer indices relativos (van de mayor a menor)
		// k -> recorrer solucion parcial
		int j = 0, k = beneficios.size() - 1;
		for(int i = solucion.size() - 1; i >= 0; --i) {
			if(getSolucion(i) == -1) {
				if((j < indicesRelativos.size()) && (indicesRelativos.get(j) == k)) {
					// System.out.println("A " + i + " " + mochila);
					setSolucion(i, mochila);	
					j++;
				}
				k--;
			}
		}
		
		// Borrar los objetos metidos de los objetos restantes
		for(int i = 0; i < indicesRelativos.size(); i++) {
			// Cast a int para que lo tome como indice, en lugar de elemento, a borrar
			beneficios.remove((int) (indicesRelativos.get(i)));
			pesos.remove((int) (indicesRelativos.get(i)));
		}
	}
	
	public String toString() {
		String cadena = "";
		
		for(int i = 0; i < solucion.size(); ++i)
			cadena += getSolucion(i) + " ";
			
		return cadena;
	}
}
