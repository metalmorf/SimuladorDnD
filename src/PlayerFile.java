

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.player.Player;

public class PlayerFile {

	private static PlayerFile instance;

	public static PlayerFile getInstance(){
		if (instance == null) {
			return new PlayerFile();
		}
		return instance;
	}

	private PlayerFile(){}
	
	private Player player;

	/**
	 * Recupera uma lista de músicas.
	 * @param dir - Pasta onde se encontram as músicas.
	 */
	public List<String> buscaListaMusicas(String dir){
		List<String> listaMusicas = new ArrayList<String>();
		File diretorio = new File(dir);
		File fList[] = diretorio.listFiles();

		for (File file : fList) {
			String extencao = file.getName().substring(file.getName().length()-3, file.getName().length());
			boolean adiciona = extencao.equals("mp3") ? true : false;
			if (adiciona) {
				listaMusicas.add(file.getName());
			}
		}

		return listaMusicas;
	}
	
	public void tocarMusica(File mp3){
		try {
            FileInputStream fis = new FileInputStream(mp3);
            BufferedInputStream bis = new BufferedInputStream(fis);
            player = new Player(bis);
            player.play();
        }
        catch (Exception e) {
            System.out.println("Problema ao tocar " + mp3);
            e.printStackTrace();
        }
	}
}
