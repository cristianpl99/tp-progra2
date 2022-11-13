import java.util.Random;

public class AlbumTradicional extends Album {
private String []premios;

AlbumTradicional(String[] equipos, int lugaresPorPais, String[] premios) {
	super(equipos, lugaresPorPais);
	this.premios = premios;
}

final String aplicarSorteoInstantaneo() {
	if (this.premios == null) {
		return "El sorteo ya fue hecho previamente";
	}
	Random random = new Random();
	String premio = this.premios [random.nextInt(3)];
	this.premios = null;
	
		return premio;
		}
	}
		

	


