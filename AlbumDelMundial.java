
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlbumDelMundial implements InterfazPublicaAlbumDelMundial {
	Fabrica fabrica = new Fabrica();
	protected HashMap<Integer, Participante> participantes = new HashMap<>();
	
	@Override
	
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {
		
		if (participantes.containsKey(dni)){
			throw new RuntimeException("El participante ya esta registrado");		
		}
		tipoDeAlbum(tipoAlbum);
		ArrayList<Figurita> coleccion = new ArrayList<>(); 
		Participante participante = new Participante (dni, nombre, tipoDeAlbum(tipoAlbum), coleccion);
		participantes.put(dni, participante);
		int codigoAlbum = participante.consultarAlbum().consultarCodigoAlbum();

		return codigoAlbum;
		}

	@Override
	public void comprarFiguritas(int dni) {
		Participante participante = validarYBuscarParticipante(dni);
		participante.agregarFiguritasAColeccion(fabrica.generarSobre(dni));
	}

	@Override
	public void comprarFiguritasTop10(int dni) {
		Participante participante = validarYBuscarParticipante(dni);
		if (participante.tieneAlbumExtendido()) {
			participante.agregarFiguritasAColeccion(fabrica.generarSobreTop10(dni));
		}
		else{
			throw new RuntimeException("El participante no posee un album extendido");
		}
	}
	@Override
	public void comprarFiguritasConCodigoPromocional(int dni) {
		Participante participante = validarYBuscarParticipante(dni);
		if (participante.tieneAlbumWeb()){
			AlbumWeb album = (AlbumWeb) participante.consultarAlbum();
			if(album.consultarCodigoPromocional()!=-1){
				participante.agregarFiguritasAColeccion(fabrica.generarSobre(dni)); 	
			}
			else {
				throw new RuntimeException("El participante ya uso su codigo");
				}
			}
		else {
			throw new RuntimeException("El participante no posee un album web");
			}
		}

	@Override
	public List<String> pegarFiguritas(int dni){
		Participante participante = validarYBuscarParticipante(dni);
		return participante.pegarFiguritas();
	}
	
	@Override
	public boolean llenoAlbum(int dni) {
		Participante participante = validarYBuscarParticipante(dni);
		participante.consultarAlbumLleno();
			return participantes.get(dni).consultarAlbumLleno();
	}

	@Override
	public String aplicarSorteoInstantaneo(int dni) {
		Participante participante = validarYBuscarParticipante(dni);
		if(participante.tieneAlbumWeb()){
			throw new RuntimeException("El participante no tiene un album tradicional ni un album extendido");	
		}
		StringBuilder sb = new StringBuilder();
		sb.append(participante.aplicarSorteoInstantaneo());
		return sb.toString();
	}
	
	@Override
	public int buscarFiguritaRepetida(int dni) {
		Participante participante = validarYBuscarParticipante(dni);
		return participante.buscarFiguritaRepetida();	
	}
	
	@Override
	public boolean intercambiar(int dni, int codFigurita) {
		Participante participante1 = participantes.get(dni);
		Figurita figurita1 = participante1.consultarPorFigurita(codFigurita);
		if (figurita1 == null) {
				return false;
		}
		for (Participante participante2 : participantes.values()){
			if (participante2.consultarDNI()!= dni && albumesDeLaMismaClase(participante1, participante2)){
				 if (participante2.intentarIntercambio(figurita1, participante1)) {
						return true;
				 }
			}
		}				
		return false;
	}
	
	@Override
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		int codigoFigurita = buscarFiguritaRepetida (dni);
		return intercambiar (dni, codigoFigurita);
	}

	@Override
	public String darNombre(int dni) {
		Participante participante = validarYBuscarParticipante(dni);
		return participante.consultarNombre();
	}

	@Override
	public String darPremio(int dni) {
		Participante participante = validarYBuscarParticipante(dni);
		if (participante.consultarAlbumLleno()) {
			if (participante.tieneAlbumTradicional()) {
				return "Pelota";
			}
			if (participante.tieneAlbumExtendido()) {
				return "Pelota y viaje";
			}
			else {
				return "Camiseta Oficial";
			}
		}
		throw new RuntimeException("El participante no tiene un album tradicional");
	}

	@Override
	public String listadoDeGanadores() {
		StringBuilder sb = new StringBuilder();
		for (Participante participante : participantes.values()) {
			if(participante.consultarAlbumLleno()) {
				sb.append("\t")
				.append(" DNI: ").append(participante.consultarDNI()).append(" ")
				.append(" NOMBRE: ").append(participante.consultarNombre()).append(" ")
				.append(" PREMIO: ").append(darPremio(participante.consultarDNI())).append(" ")
				.append("\n")
				.append("\n");
				}
			}
		return sb.toString();
	}
	@Override
	public List<String> participantesQueCompletaronElPais(String nombrePais) {
		ArrayList<String> participantesQueCompletaronPais = new ArrayList<String>();
		for (Participante participante : participantes.values()) {
			if(participante.completoPais(nombrePais)){
				StringBuilder sb = new StringBuilder();
				sb.append("\t")
				.append(" DNI: ").append(participante.consultarDNI()).append(" ")
				.append(" NOMBRE: ").append(participante.consultarNombre()).append(" ")
				.append(" TIPO DE ALBUM: ").append(participante.consultarAlbum().getClass().getCanonicalName().toString())
				.append("\n")
				.append("\n");
				participantesQueCompletaronPais.add(sb.toString());
				sb = null;
				}
		}
		return participantesQueCompletaronPais;
	}
	
	@Override
	public  String toString() {
		StringBuilder st = new StringBuilder();
		int tradicional = 0;
		int extendido = 0;
		int web=0;
		st.append("\n");
		st.append("------------------------------------------------------------------");
		st.append("\n\n");
		st.append("Hay un total de "+participantes.size() +" participantes que compraron el album del mundial");
		st.append("\n\n");
		st.append("De los cuales: ");
		for( Participante participante : participantes.values()) {
			if(participante.tieneAlbumWeb()) {
				web++;
			}
			else if(participante.tieneAlbumExtendido()) {
				extendido++;
			}
			else if(participante.tieneAlbumTradicional()) {
				tradicional++;
			}		
		}
		st.append("\n\t");
		st.append(tradicional + " participantes tienen un album tradicional ");
		st.append("\n\t");
		st.append(extendido + " participantes tienen un album extendido");
		st.append("\n\t");
		st.append(web + " participantes tienen un album web");
		st.append("\n");
		return st.toString();
	}
	
	//metodos auxiliares
	
	private Album tipoDeAlbum(String tipoAlbum) {
		String tipo = tipoAlbum.toLowerCase();
		Album album;
		if (tipo.equals("tradicional")) {
			album = fabrica.crearAlbumTradicional();
		}
		else if (tipo.equals("extendido")) {
			album = fabrica.crearAlbumExtendido();
		}
		else if (tipo.equals("web")) {
			album = fabrica.crearAlbumWeb();
		}
		else {
			throw new RuntimeException("El tipo de album no es valido");		
		}
		return album;
	}
	
	private Participante validarYBuscarParticipante(int dni) {
		if (participantes.containsKey(dni)) {
			return participantes.get(dni);	
		}
		throw new RuntimeException("El participante no esta registrado");
	}
	
	private boolean albumesDeLaMismaClase(Participante p1, Participante p2) {
		Album album1 = p1.consultarAlbum();
		Album album2 = p2.consultarAlbum();
		return (album1.getClass().equals(album2.getClass()));
	}
	
	

}