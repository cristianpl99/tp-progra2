import java.util.HashMap;


public abstract class Album  {
	private static int contCodigo = 1;
	private int codigo;
	private HashMap <String, Figurita []> selecciones;
	private boolean albumLleno;

	Album(String []equipos, int lugaresPorPais){
		HashMap<String, Figurita[]> selecciones = new HashMap<>();
		for(String pais : equipos) {	
			selecciones.put(pais, new Figurita[lugaresPorPais]);
		}
		this.codigo=contCodigo++;
		this.selecciones = selecciones;
		this.albumLleno = false;
	}
	
	int consultarCodigoAlbum() {
		return this.codigo;
	}
	
	boolean pegarFigurita(Figurita figurita){
			Figurita [] pais = selecciones.get(figurita.consultarPais());
			if (pais[figurita.consultarNumJugador()-1] == null) {
				pais[figurita.consultarNumJugador()-1] = figurita;
				chequearAlbumLleno();
				return true;
			}	
		return false; 
	}

	boolean estaPegada(Figurita figurita) {
		Figurita[] pais = selecciones.get(figurita.consultarPais());
		if(pais[figurita.consultarNumJugador()-1]!=null) {
				return true;
		}
		return false;
	}
	
	boolean albumLleno() {
		for (Figurita[] pais : selecciones.values()){
			for (Figurita figurita : pais){
				if (figurita == null){
					return false;
				}
			}
		}
		return true;
		}

	boolean llenoElAlbum() {
		return this.albumLleno;
	}

	boolean completoPais(String nombrePais) {
		Figurita[] pais = selecciones.get(nombrePais);{
			for (Figurita figurita : pais) {
				if (figurita == null) {
					return false;
				}
			}
		}
		return true;
	}
	
	final void chequearAlbumLleno(){
		this.albumLleno = albumLleno();
	}
}
	

