import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Participante {
	private int dni;
	private String nombre;
	private Album album;
	private ArrayList<Figurita> coleccion;
	
	Participante(int dni, String nombre, Album album, ArrayList<Figurita> coleccion){
		this.dni = dni;
		this.nombre = nombre;
		this.album = album;
		this.coleccion = coleccion;
	}
	protected int consultarDNI() {
		return this.dni;
	}

	protected String consultarNombre() {
		return this.nombre;
	}
	
	protected Album consultarAlbum() {
		return this.album;	
	}

	protected boolean consultarAlbumLleno()
	{
		return album.llenoElAlbum();	
	}
	
	protected void agregarFiguritasAColeccion(ArrayList<Figurita>sobre) {
		for(Figurita figurita : sobre) {
			coleccion.add(figurita);	
			}
	}

	protected List<String> pegarFiguritas(){
		ArrayList<String> pegadas = new ArrayList<String>();
		Iterator<Figurita> iterador = coleccion.iterator();
		while(iterador.hasNext())  {
			Figurita figurita = iterador.next();
				if(album.pegarFigurita(figurita)){
					pegadas.add(figurita.toString());
					iterador.remove();
				}
			}
		return pegadas;	
	}
	
	protected String aplicarSorteoInstantaneo() {
		if(this.tieneAlbumTradicional()) {
			AlbumTradicional album = (AlbumTradicional) this.album;
			return album.aplicarSorteoInstantaneo();
		}
		else {
			AlbumExtendido album = (AlbumExtendido) this.album;
			return album.aplicarSorteoInstantaneo();
			}
		}

	protected int buscarFiguritaRepetida() {
		if (coleccion.size() == 0) {
			return -1;
			}
		return coleccion.get(0).consultarNumeroIdentificador();	
	}

	protected boolean tieneFigurita(Figurita figurita) {
		return album.estaPegada(figurita);	
	}
	
	protected boolean intentarIntercambio (Figurita figurita1, Participante participante1){
		if(this.tieneFigurita(figurita1)) {
			return false;
		}
		for( Figurita figurita2 : coleccion) {
			if (!participante1.tieneFigurita(figurita2)){
				if (figurita1.consultarValor()>=figurita2.consultarValor()) {
					this.intercambiarFiguritaEnColeccion(figurita1, figurita2);
					participante1.intercambiarFiguritaEnColeccion(figurita2, figurita1);
					return true;
				}
			}
		}
		return false;	
	}
	
	protected Figurita consultarPorFigurita(int codFigurita) {
		for(Figurita figurita : coleccion) {
			if (figurita.consultarNumeroIdentificador() == codFigurita){
			return figurita;
			}
		}
		return null;	
	}

	protected boolean consultarTieneRepetidas() {
		return !coleccion.isEmpty();
	}

	protected boolean completoPais(String nombrePais) {	
		return this.album.completoPais(nombrePais);
	}

	protected boolean tieneAlbumExtendido() {
		return (this.album instanceof AlbumExtendido);
	}

	protected boolean tieneAlbumWeb() {
		return (this.album instanceof AlbumWeb);
		}

	protected boolean tieneAlbumTradicional() {
		return (this.album instanceof AlbumTradicional);
	}
	
	private void intercambiarFiguritaEnColeccion(Figurita f1, Figurita f2) {
		coleccion.add(f1);
		coleccion.remove(f2);
	}
}