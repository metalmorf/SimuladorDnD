

import java.io.File;
import java.util.List;

public class PlayerFacade {
	
	private static PlayerFacade instance;
	
	public static PlayerFacade getInstance(){
		if (instance == null) {
			return new PlayerFacade();
		}
		return instance;
	}
	
	private PlayerFacade(){}

	public List<String> buscaListaMusicas(String diretorio){
		return PlayerFile.getInstance().buscaListaMusicas(diretorio);
	}
	
	public void tocarMusica(File mp3){
		PlayerFile.getInstance().tocarMusica(mp3);
	}
}
