package multiknapsack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Clase solucion para el problema de la multi-mochila.
 * Almacena las capacidades de cada mochila, los beneficios y
 * pesos de los objetos, así como codifica una solución.
 * Además, contiene los algoritmos para mejorar las soluciones
 * y obtener la solución óptima o una cercana a ella (heurísticas). 
 * 
 * La solución se codifica como un array con un elemento entero
 * por cada posible objeto. El valor de la posición i indica la
 * mochila en la que se ha introducido el objeto i. Si el valor es
 * -1, se considera que el objeto no está en ninguna mochila.
 * 
 * @author Miguel Jimenez Gomis
 * @author Alejandro Leon Fernandez
 * @author Javier Esteban Perez Rivas
 * @author Sara Revilla Baez
 *
 */
public class Solucion {	
	private static final int MAX_ITERACIONES_NO_MEJORA = 500;
	ArrayList<Integer> capacidades = new ArrayList<Integer>(); /** Capacidad de cada una de las mochilas. */
	ArrayList<Integer> beneficios = new ArrayList<Integer>();  /** Beneficios de cada uno de los objetos. */
	ArrayList<Integer> pesos = new ArrayList<Integer>();       /** Pesos de cada uno de los objetos. */
	public ArrayList<Integer> solucion = new ArrayList<Integer>();  /** Codificación de la solucion. */
	int valortotal;            /** Funcion objetivo. Valor conjunto de los objetos introducidos en mochilas. */
	
	/**
	 * Construye la solucion a partir de un fichero dado. Formato:
	 * 
	 *  num_mochilas
     *  num_objetos
     *  capacidad_mochila1
     *  capacidad_mochila2
     *  ...
     *  capacidad_mochilaN
     *  beneficio_objeto1 peso_objeto1
     *  beneficio_objeto2 peso_objeto2
     *  ...
     *  beneficio_objetoN peso_objetoN
	 * 
	 * @param filename nombre del fichero donde se encuentra la solución
	 */
	public Solucion(String filename) {
		readFromFile(filename);
		solucion = new ArrayList<Integer>();
		for(int i = 0; i < beneficios.size(); ++i)
			solucion.add(-1);
		setValortotal(0);
		ordenarPorRatioBeneficioPeso();
		/*for(int i = 0; i < beneficios.size(); ++i)
			System.out.println(beneficios.get(i) + " " + pesos.get(i));*/
	}
	
	/**
	 * Construye un objeto solucion como copia de otro
	 * @param other solucion a copiar
	 */
	public Solucion(Solucion other) {
		capacidades = other.capacidades;
		beneficios = other.beneficios;
		pesos =  other.pesos;
		solucion = new ArrayList<Integer> (other.solucion);
		setValortotal(other.getValortotal());
	}
	
	
	/**
	 * Lee la solución de fichero.
	 * @param inputfile
	 */
	public void readFromFile(String inputfile) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(inputfile));
			if(inputfile.equals("problema1.txt")) {		// por compatibilidad con el problema ejemplo inical
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
			else {
				reader.readLine();
				String line[] = reader.readLine().split("\\s+");
				// Tras dividir por espacios la primera posicion del array queda con la cadena vacia
				// Luego en general se recorre el array de la linea empezando por 1
				int nMochilas, objetosPorFila, peso;
				int nFilas = new Integer(line[1]);
				nMochilas =  new Integer(line[2]);
				objetosPorFila = 20;
				for(int i = 0; i < nFilas; ++i) {				  
					line = reader.readLine().split("\\s+");
					peso = new Integer(reader.readLine().split("\\s+")[1]);
					for(int j = 1; j <= objetosPorFila; ++j) {
						beneficios.add(new Integer(line[j]));
						pesos.add(peso);
					}
				}
				
				for(int i = 0; i < Math.ceil(nMochilas / 10D); ++i) {
					line = reader.readLine().split("\\s+");
					for(int j = 1; j <= Math.min(10, nMochilas - 10*i); ++j)
						capacidades.add(new Integer(line[j]));
				}
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
	
	/**
	 * Ordena la lista de objetos según su ratio beneficio peso, colocando
	 * los objetos con un mejor valor al principio del array.
	 */
	public void ordenarPorRatioBeneficioPeso() {
		ArrayList<Objeto> objetos = new ArrayList<Objeto>();
		for(int i = 0; i < beneficios.size(); ++i)
			objetos.add(new Objeto(beneficios.get(i), pesos.get(i)));
		objetos.sort(null);
		
		beneficios.clear();
		pesos.clear();
		for(int i = objetos.size() - 1; i >= 0; --i) {
			beneficios.add(objetos.get(i).getBeneficio());
			pesos.add(objetos.get(i).getPeso());
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
	 * Calcula el valor total de los objetos introducidos en todas las mochilas
	 * @return valor de los objetos introducidos en mochilas
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
	 * @return peso de los objetos en la mochila
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
	 * @return valor de los objetos en la mochila
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
	 * @return true si la solucion es valida, false si no lo es
	 */
	public boolean isValid() {
		for(int i =  0; i < capacidades.size(); i++) {
			if(this.pesoMochila(i) > capacidades.get(i)) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Obtiene la solución óptima utilizando un algoritmo voraz.
	 * Recursivo.
	 */
	public void greedySolve() {
		ArrayList<Integer> beneficiosRestantes = (ArrayList<Integer>) beneficios.clone();
		ArrayList<Integer> pesosRestantes = (ArrayList<Integer>) pesos.clone();
		int i = 0;
		while((i < capacidades.size()) && (!beneficiosRestantes.isEmpty())) {
			greedySolveKnapsack(i, beneficiosRestantes, pesosRestantes);
			//System.out.println(this);
			//System.out.println("beneficios restantes size = " + beneficiosRestantes.size());
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
				if(j - pesos.get(i-1) < 0)
					v[i][j] = left;
				else {
					int up = v[i-1][j - pesos.get(i-1)] + beneficios.get(i-1);
					v[i][j] = Math.max(left, up);
				}
			}
		}
		
		// Obtener indices de los objetos metidos en esta mochila
		// Indices relativos a los objetos restantes
		ArrayList<Integer> indicesRelativos = new ArrayList<Integer>();
		int capacidadRestante = getCapacidad(mochila);
		for(int i = beneficios.size(); i >= 1; --i) {
			if((pesos.get(i-1) <= capacidadRestante) && (v[i-1][capacidadRestante - pesos.get(i-1)]
					+ beneficios.get(i-1) == v[i][capacidadRestante])) {
				indicesRelativos.add(i-1);
				capacidadRestante = capacidadRestante - pesos.get(i-1);
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
	
	/** 
	 * Resolucion del problema con aproximaciÃ³n GRASP
	 * @param numOfIterations
	 */
	public void GRASP(int numOfIterations, int movetype, int tamListaRestringida) {
		ArrayList<Integer> mejor = solucion;
		for(int i = 0; i < numOfIterations; i++) {
			Solucion temp = new Solucion(this); // se genera una solucion identica y se limpia
			for(int j = 0; j < temp.solucion.size(); j++) {
				temp.solucion.set(j, -1);
			}
			temp.generarSolucion((int)(tamListaRestringida)); // se genera una solucion aleatoria
			//System.out.println("Solucion generada:" + temp + " valor: " + temp.valorTotal());
			switch(movetype) {
			  case 1:
			    temp.mov1(true); // se realiza una busqueda local para esa solucion con uno de los movimientos
		      break;
			  case 2:
			    temp.mov2(true);
			    break;
			  case 3:
			    temp.mov3(new Random().nextInt(solucion.size()));
			    break;
			  case 4:
			    temp.mov4(new Random().nextInt(capacidades.size()));
			    break;
			}
			if(this.valorTotal() < temp.valorTotal() && temp.isValid()) {
				this.solucion  = new ArrayList<Integer>(temp.solucion); // si la nueva solucion es mejor, se cambia
			}			
		}
	}
	
	/**
	 * Implementa el algoritmo multiarranque con soluciones aleatorias. Se ejecuta mientras no 
	 * haya MAX_ITERACIONES_NO_MEJORA iteraciones sin cambio
	 */
	public void multiArranque(int movimiento){
		this.generarSolucion(solucion.size());
		Solucion mejorSol = new Solucion(this);
		int iteracionSinMejora = 0;
		while(iteracionSinMejora < MAX_ITERACIONES_NO_MEJORA) {
			switch(movimiento) {
				case 1: this.mov1(true); break;
				case 2: this.mov2(true); break;
				case 3: this.mov3(new Random().nextInt(solucion.size())); break;
				case 4: this.mov4(new Random().nextInt(capacidades.size())); break;
			}
			if(this.valorTotal() > mejorSol.valorTotal()){
				for(int i = 0; i < solucion.size(); i++)
					mejorSol.setSolucion(i, this.solucion.get(i));
				this.generarSolucion(solucion.size());
				iteracionSinMejora = 0;
			}
			else
				iteracionSinMejora++;
		}
		for(int i = 0; i < solucion.size(); i++)
			this.solucion.set(i, mejorSol.getSolucion(i));
		
	}
	
	/** 
	 * Movimiento posible 1, quitar elementos de las mochilas e introducir otros. version aleatoria y greedy
	 * @todo version greedy
	 * @param type
	 */
	public void mov1( boolean type) {
	  while(!this.isValid()) {
  		if(type) { // versiÃ³n aleatoria de este movimiento
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
  					this.solucion.set(posiblessalir.get(sale), -1); // se saca un elemento aleatorio de dicha mochila
  					//posibles.salir
  				}
  			}
  		 }
	  }
	}
	
	/**
	 * Similar al mov 1 pero sacando 2
	 * @param type
	 */
	public void mov2(boolean type) {
	  while(!this.isValid()) {
      if(type) { // versiÃ³n aleatoria de este movimiento
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
          int entra2 = rand.nextInt( posiblesentran.size()); // elemento que entra en
          int mochila2 =  rand.nextInt(this.capacidades.size());// otra michila aleatoria
          this.solucion.set(entra2, mochila2);
          posiblesentran.remove(entra2);
          while(!isValid()) {// si la solucion no es valida se eliminan objetos y se ponen en la lista de posibles a entrar
            ArrayList<Integer> posiblessalir =  new ArrayList<Integer>();
            for(int j = 0; j < this.solucion.size(); j++) {// los posibles a salir son los que esten en esa mochila
              if(this.solucion.get(i) == mochila ||this.solucion.get(i) == mochila2) {
                posiblessalir.add(i);
              }
            }
            if(posiblessalir.size() == 0) {
              break;
            }
            int sale = rand.nextInt(posiblessalir.size());
            this.solucion.set(posiblessalir.get(sale), -1); // se saca un elemento aleatorio de dicha mochila
            sale = rand.nextInt(posiblessalir.size());
            this.solucion.set(posiblessalir.get(sale), -1);
          }
        }
      }
    }
	}
	
	/**
	 * Cambia un objeto de una mochila a otra o, si no estÃ¡ en una mochila, lo mete donde pueda
	 * @param objeto indice del objeto que se quiere mover
	 */
	public void mov3(int objeto) {
		Random rand = new Random();
		int mochila = rand.nextInt(capacidades.size());
		if(solucion.get(objeto) != -1)
			solucion.set(objeto, mochila);
		else {
			int i = mochila;
			do {
				solucion.set(objeto, i);
				if(!isValid())
					solucion.set(objeto, -1);
				else {
					setValortotal(getValortotal() + beneficios.get(objeto));
					break;
				}
				i = (i + 1) % capacidades.size();
			} while(i != mochila);
		}
	}
	
	/**
	 * Mete un objeto en una mochila y saca los necesarios para recuperar la factibilidad
	 * @param mochila indice de la mochila que se quiere modificar
	 */
	public void mov4(int mochila) {
		int objeto = 0;
		while(objeto < solucion.size() && solucion.get(objeto) != -1) objeto++;
		if(solucion.get(objeto) == -1) {
			solucion.set(objeto, mochila);
			setValortotal(getValortotal() + beneficios.get(objeto));
		}
		if(!isValid())
			for(int i = solucion.size() - 1; i >= 0; --i) {
				if(solucion.get(i) == mochila) {
					solucion.set(i, -1);
					setValortotal(getValortotal() - beneficios.get(i));
					if(isValid()) break;
				}
			}
	}
	
	/**
	 * Genera una solucion aleatoria
	 * @param tamanio numero de elementos de la solucion
	 */
	public void generarSolucion(int tamanio) {
		for(int i = 0; i < this.solucion.size(); i++) {
        this.solucion.set(i, -1);
		}
  		Random rand = new Random();
  		ArrayList<Integer> posibles =  new ArrayList<Integer>();
  		for(int i = 0; i < this.solucion.size(); i++) {
  			posibles.add(i);
  		}	
  		for(int i = 0; i < solucion.size(); i++) {
  			int entra = rand.nextInt(Math.min(tamanio, posibles.size() - 1)); // se selecciona un objeto aleatorio (su indice) de la lista restringida
  			this.solucion.set(posibles.get(entra), rand.nextInt( this.capacidades.size()));// el objeto seleccionado entra en una mochila aleatoria
  			if(!this.isValid()) {
  				this.solucion.set(posibles.get(entra), -1);// si al introducir la solucion no es valida, se saca
  				//break;
  				posibles.add(entra);
  			}
  			posibles.remove(entra);// se saca esa posibilidad para que no se repita
  		}
	}
	
	/**
	 * Encuentra una solución buena mediante la búsqueda de soluciones mejores
	 * vecinas. A partir de una solución inicial, busca soluciones mejores en 
	 * un entorno determinado (de 4 posibles) y actualiza la solución iterativamente
	 * hasta que se cumple el criterio de parada (5 saltos).
	 * 
	 * @param numOfIterations
	 * @param tamListaRestringida
	 */
	public void VNS(int numOfIterations, int tamListaRestringida) {
	    for(int i = 0; i < numOfIterations; i++) {
	      Solucion temp = new Solucion(this); // se genera una solucion identica y se limpia
	      for(int j = 0; j < temp.solucion.size(); j++) {
	        temp.solucion.set(j, -1);
	      }
	      temp.generarSolucion((int)(tamListaRestringida)); // se genera una solucion aleatoria
	      int mov = 1;
	      do {
	        switch (mov) {
	          case 1:
	            temp.mov1(true); // se realiza una busqueda local para esa solucion con uno de los movimientos
	            break;
	          case 2: 
	            temp.mov2(true); // se realiza una busqueda local para esa solucion con uno de los movimientos
	            break; 
	          case 3:
	            temp.mov3(new Random().nextInt(solucion.size()));
	            break;
	          case 4:
	            temp.mov4(new Random().nextInt(capacidades.size()));
	          default:
	            break;
	        }
	        if (this.valorTotal() < temp.valorTotal() && temp.isValid()) {
	          this.solucion  = new ArrayList<Integer>(temp.solucion);          
	        } else {
	          mov++;
	        }
	      }while(mov < 5);
	      mov = 1;
	    }
	}
	
    /**
     * Destruye parte de la solución atendiendo a un porcentaje dado.
     * @param percentage porcentaje de la solución a destruir
     */
    private void destroy(double percentage) {
        ArrayList<Integer> assignedObjects = new ArrayList<>();
        for (int i = 0; i < solucion.size(); ++i) {
            if (solucion.get(i) != -1) {
                assignedObjects.add(solucion.get(i));
            }
        }
        
        int j = 0;
        while (j < (int) (assignedObjects.size() * percentage)) {
            int randObj = new Random().nextInt(assignedObjects.size());
            if (assignedObjects.get(randObj) != -1) {
                assignedObjects.set(randObj, -1);
                ++j;
            }
        }
        
        j = 0;
        for (int i = 0; i < solucion.size() && j < assignedObjects.size(); ++i) {
            if (solucion.get(i) != -1) {
                solucion.set(i, assignedObjects.get(j++));
            }
        }
        
    }
    
    /**
     * Reconstruye la solución con el movimiento 3. Coloca los objetos que están 
     * fuera de mochilas hasta que no se pueda introducir ningún otro objeto (es decir,
     * las mochilas estén saturadas).
     */
    private void reconstruct() {
        int stop = 0;
        for (int object = 0; object < solucion.size() && stop < 10; ++object) {
            if (solucion.get(object) == -1) {
                this.mov3(object);
                if (solucion.get(object) == -1) {
                    stop++;
                }
            }
        }
    }
    
    /**
     * Algoritmo Large Neighbourhood Search. A partir de una solución aleatoria,
     * la mejora durante un cierto número de iteraciones mediante destrucción
     * y reconstrucción de la solución.
     * 
     * @param numIteraciones número de veces que se realiza la destrucción-reconstrucción
     * @param tamListaRestringida número de elementos mejores a considerar
     */
    public void LNS(int numIteraciones, int tamListaRestringida) {
        this.generarSolucion(tamListaRestringida);
        Solucion best = new Solucion(this);
        double percentage = 0.1;
        
        for (int i = 0; i < numIteraciones; ++i){
            this.destroy(percentage);
            this.reconstruct();     // la soluciï¿½n es vï¿½lida
            if (this.valorTotal() > best.valorTotal()) {
                best.solucion = new ArrayList<Integer>(this.solucion);
                best.setValortotal(this.getValortotal());
            }
        }
        
        this.solucion = new ArrayList<Integer>(best.solucion);
        setValortotal(best.getValortotal());
    }
	
}
