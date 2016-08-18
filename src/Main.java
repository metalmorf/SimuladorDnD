import javax.swing.JOptionPane;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Frame F=new Frame();
		
	try{
		//new Player();
		
		F.executaFrame();
	}
	catch (Exception e) {
		//JOptionPane.showMessageDialog(null, "Erro: "+e);
		JOptionPane.showMessageDialog(null,"Erro ao carregar o diretório das Fichas de Personagens.");
		//F.caminho= javax.swing.JOptionPane.showInputDialog("Digite um novo caminho:")+"/";
		//F.executaFrame();
	//	System.out.println(e);
		
	}
	// TODO Auto-generated method stub

	}

	
	
	
	
	
}
