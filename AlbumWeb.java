import java.util.Random;

public class AlbumWeb extends Album{
	private int codigoPromocional;

	AlbumWeb(String []equipos, int lugaresPorPais) {
		super(equipos, lugaresPorPais);
		Random random = new Random();
		this.codigoPromocional = (random.nextInt(10000));	
		
	}
	
	protected int consultarCodigoPromocional() {
		return codigoPromocional;
	}
}
