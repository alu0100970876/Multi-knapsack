package multiknapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Solucion {	
	ArrayList<Integer> capacidades = new ArrayList<Integer>();
	ArrayList<Integer> beneficios = new ArrayList<Integer>();
	ArrayList<Integer> pesos = new ArrayList<Integer>();
	public ArrayList<Integer> solucion = new ArrayList<Integer>();
	int valortotal;
	
	public Solucion(String filename) {
		readFromFile(filename);
		solucion = new ArrayList<Integer>();
		for(int i = 0; i < beneficios.size(); ++i)
			solucion.add(-1);
		setValortotal(0);
	}
	
	public Solucion(Solucion other) {
		capacidades = other.capacidades;
		beneficios = other.beneficios;
		pesos =  other.pesos;
		solucion = other.solucion;
	}
	
	
	public void readFromFile(String inputfile) {
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

	public  int getCapacidad(int index) {
		return capacidades.get(index);
	}
	
	public  void setCapacidad(int index, int capacidad) {
		capacidades.set(index, capacidad);
	}
	
	public  int getBeneficio(int index) {
		return beneficios.get(index);
	}
	
	public  void setBeneficio(int index, int beneficio) {
		beneficios.set(index, beneficio);
	}
	
	public  int getPeso(int index) {
		return pesos.get(index);
	}

	public  void setPeso(int index, int peso) {
		pesos.set(index, peso);
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
	
	/** Resolucion del problema con aproximación grasp
	 * @param numOfIterations
	 */
	public void GRASP(int numOfIterations) {
		ArrayList<Integer> mejor = solucion;
		for(int i = 0; i < numOfIterations; i++) {
			Solucion temp = new Solucion(this); // se genera una solucion identica y se limpia
			for(int j = 0; j < temp.solucion.size(); j++) {
				temp.solucion.set(j, -1);
			}
			temp.generarSolucion(this.solucion.size() / 2); // se genera una solucion aleatoria
			//System.out.println("Solucion generada:" + temp + " valor: " + temp.valorTotal());
			temp.mov1(true); // se realiza una busqueda local para esa solucion con uno de los movimientos
			if(this.valorTotal() < temp.valorTotal()) {
				this.solucion  = new ArrayList<Integer>(temp.solucion); // si la nueva solucion es mejor, se cambia
			}
		}
	}
	
	/** Movimiento posible 1, quitar elementos delas mochilas e introducir otros. version aleatoria y greede
	 * @todo version greedy
	 * @param type
	 */
	public void mov1( boolean type) {
		if(type) { // versión aleatoria de este movimiento
			Random rand = new Random();
			ArrayList<Integer> posiblesentran =  new ArrayList<Integer>();
			for(int i = 0; i < this.solucion.size(); i++) { // lista de posibles indices a entrar
				if(this.solucion.get(i) == -1) {
					posiblesentran.add(i);
				}
			}
			for(int i = 0; i < posiblesentran.size(); i++) { // para un numero determinado de veces (parametro) se introduce una de las posibilidades en una mochila aleatoria
				int entra = rand.nextInt( posiblesentran.size()); // elemento que entra
				int mochila =  rand.nextInt(this.capacidades.size());// mochila aleatoria
				this.solucion.set(entra, mochila);
				posiblesentran.remove(entra);
				while(!isValid()) {// si la solucion no es valida se eliminan objetos y se ponen en la lista de posibles a entrar
					ArrayList<Integer> posiblessalir =  new ArrayList<Integer>();
					for(int j = 0; j < this.solucion.size(); j++) {// los posibles a salir son los que esten en esa mochila
						if(this.solucion.get(i) == mochila) {
							posiblessalir.add(i);
						}
					}
					if(posiblessalir.size() == 0) {
						break;
					}
					int sale = rand.nextInt(posiblessalir.size());
					this.solucion.set(sale, -1); // se saca un elemento aleatorio de dicha mochila
				}
			}
		}
	}
	
	/** Genera una solucion aleatoria
	 * @param tamanio
	 */
	public void generarSolucion(int tamanio) {
		Random rand = new Random();
		ArrayList<Integer> posibles =  new ArrayList<Integer>();
		for(int i = 0; i < this.solucion.size(); i++) {
			posibles.add(i);
		}	
		for(int i = 0; i < tamanio; i++) {
			int entra = rand.nextInt( this.solucion.size() - 1); // se selecciona un objeto aleatorio (su indice) que referira a los posibles
			this.solucion.set(posibles.get(entra), rand.nextInt( this.capacidades.size()));// el objeto seleccionado entra en una mochila aleatoria
			posibles.remove(entra);// se saca esa posibilidad para que no se repita
			if(!this.isValid()) {
				this.solucion.set(entra, -1);// si al introducir la solucion no es valida, se saca
			}
		}		
	}
}
