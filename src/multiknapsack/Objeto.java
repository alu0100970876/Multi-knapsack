package multiknapsack;

public class Objeto {
	int beneficio;
	int peso;
	
	Objeto(int beneficio, int peso) {
		setBeneficio(beneficio);
		setPeso(peso);
	}
	
	public int getBeneficio() {
		return beneficio;
	}
	public void setBeneficio(int beneficio) {
		this.beneficio = beneficio;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
}
