
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
	
	protected String consultarPais() {
		return this.pais;
	}

	protected int consultarNumJugador() {
		return this.numJugador;
	}
	
	protected int consultarNumeroIdentificador() {
		return this.numeroIdentificador;
	}

	protected double consultarValor() {
		return this.valorBase;
	}	
}