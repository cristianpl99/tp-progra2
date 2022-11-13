
public  class Figurita {
	private static int contIdentificador = 1;
	private int numeroIdentificador;
	private String  pais;
	private int numJugador;
	private int valorBase;
	
	Figurita(String pais, int numJugador, int valorBase){
		this.numeroIdentificador = contIdentificador ++;
		this.pais = pais;
		this.numJugador = numJugador;
		this.valorBase = valorBase;
		
	}
		
	String consultarPais() {
		return this.pais;
	}

	int consultarNumJugador() {
		return this.numJugador;
	}
	
	int consultarNumeroIdentificador() {
		return this.numeroIdentificador;
	}

	double consultarValor() {
		return this.valorBase;
	}	
}