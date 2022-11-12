import java.util.HashMap;

public class AlbumExtendido extends AlbumTradicional {
	private HashMap<String, Figurita[]> mundiales;
	
	AlbumExtendido(String []equipos, int lugaresPorPais, String[] premios, String [] balonYPaisPorMundialTop10){
		super(equipos, lugaresPorPais, premios);
		HashMap<String, Figurita[]> mundiales = new HashMap<>();
		for(String mundial : balonYPaisPorMundialTop10 ) {	
			mundiales.put(mundial, new Figurita[2]);
			this.mundiales = mundiales; 
			}
		}
/*	Metodo con sobrecarga porque hereda pegarFigurita(Figurita figurita) de la clase Figurita, 
  	pero al recibir una FiguritaTop10, utiliza el metodo pegarFigurita propio de esta clase.
*/
	protected String pegarFigurita(FiguritaTop10 figurita){
		String pegada ="";
		FiguritaTop10 top = (FiguritaTop10) figurita;
		Figurita [] pais = mundiales.get(top.consultarMundial());
		if (pais[top.consultarBalon()] == null) {
			pais[top.consultarBalon()] =  top;
			pegada =(top.toString());
		}
		chequearAlbumLleno();
		return pegada;
	}
/*	Metodo con sobrecarga porque hereda estaPegada(Figurita figurita) de la Class AlbumTradicional, 
  	pero al recibir una FiguritaTop10, utiliza el metodo estaPegada propio de esta clase.
*/	
	protected boolean estaPegada(FiguritaTop10 figurita) {
		Figurita[] mundial = mundiales.get(figurita.consultarPais());
			if(mundial[figurita.consultarBalon()]!=null) {
				return true;
		}
		return false;
		}
	
	
	@Override 
	protected boolean albumLleno() {	
		return super.albumLleno() && this.seccionMundialesLlena();	
	}
	
	private boolean seccionMundialesLlena(){
		for (Figurita[] mundial : mundiales.values()){
			for (Figurita figurita : mundial){
				if (figurita == null){
					return false;
				}
			}
		}
		return true;
	}
}


