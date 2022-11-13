
public class FiguritaTop10 extends Figurita {
	private String mundial;
	private int balon;
	
	FiguritaTop10(String pais, int numJugador, int valorBase, String mundial, int balon ) {
		super(pais, numJugador, valorBase);
		this.mundial = mundial;
		this.balon = balon;	
	}
	
	String consultarMundial() {
		return this.mundial;
	}
	
	int consultarBalon() {
		return this.balon;
	}
	
	@Override
	double consultarValor(){
		if (esBalonOro(this.balon)) {
		return this.consultarValor() * 1.2 ;
		}
		return this.consultarValor() * 1.1;
	}
//metodos auxiliares de la clase
	private boolean esBalonOro(int balon) {
		return (balon == 0);
	}
}