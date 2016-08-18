import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;



public class Frame extends JFrame{

	public static JFrame frame= new JFrame();
	public static JButton Ok;
	public static JButton CharacterAdd;
	public static JComboBox  personagens;
	public  JTextArea  Log=new JTextArea();;
	ArrayList <Character> C=new ArrayList <Character>();
	public static ArrayList<Character>Personagens_em_batalha=new ArrayList<Character>();
	public JScrollPane barra= new JScrollPane(Log);
	public static JPanel panel= new JPanel(null);
	public static Avatar avatar;
	//public static String caminho="D:/meus documentos/Dropbox/Projetos java/DeD/Fichas/"; //pc casa
	public String caminho="/home/metalmorf/Dropbox/DeD/Fichas/"; //linux lab
	//public String caminho="Fichas/";
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////executaFrame	
public void executaFrame(){

	
//	this.setSize(300,500);
//	this.setVisible(true);
//	this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
//
//	this.add(new Player());
	//this.validate();
//	
	
	personagens=new JComboBox();
	personagens.setVisible(true);
	frame.setTitle("Simulador de Batalha do Mestre v1.7.1   <Desenvolvido por Evandro Fonseca <metalmorphy@gmail.com>>");  //titulo do frame
	//Dimension dx=new Dimension(600,555);
	frame.setSize(1200,700);
	//frame.setMinimumSize(dx);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	panel.setVisible(true);
	frame.add(panel);
	frame.setResizable(true);
	
	int posx=120,posy=30;
	

	 try {
		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (InstantiationException e) {
		
		e.printStackTrace();
	} catch (IllegalAccessException e) {
	
		e.printStackTrace();
	} catch (UnsupportedLookAndFeelException e) {
		
		e.printStackTrace();	
	}
	CarregaFichas();
	Ok = new JButton("Ok");  //inicia a ferramenta
	Ok.addActionListener(new Ok());
	CharacterAdd = new JButton("Add");  //inicia a ferramenta
	CharacterAdd.addActionListener(new Character_Add());
	

	
	//setbounds
	personagens.setBounds(10, 0+posy, 200, 20);
	CharacterAdd.setBounds(230, 0+posy, 70, 20);
	Ok.setBounds(300, 0+posy, 70, 20);	
	barra.setBounds(50, 100+posy, 300, 140);	
	
	
	panel.add(Ok);
	panel.add(CharacterAdd);
	panel.add(personagens);
	//panel.add(Log);
	panel.add(barra);
	panel.validate();
	
	
	
	//this.validate();
	
}	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////botao
	public class botao extends Thread  implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
			botao t=new botao();
			t.start();
			}

	    public void run()
	    {
	     
}

	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Ok
	public class Ok extends Thread  implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
			
				avatar=new Avatar(Personagens_em_batalha,frame,C);//C= todos os personagens
				frame.add(avatar.P);
				panel.setVisible(false);
				
			}



	}	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////botao
	public class Character_Add extends Thread  implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
			//botao t=new botao();
			//t.start();
				
			String nome=(String)personagens.getSelectedItem();
			
			for(int i=0;i<C.size();i++)
			{
				int flag=1;	
				
				if(nome.equals(C.get(i).Nome))
				{
//				
				for(int j=0;j<Personagens_em_batalha.size();j++)
					if(nome.equals(Personagens_em_batalha.get(j).Nome.substring(0,Personagens_em_batalha.get(j).Nome.indexOf("("))))
					{	
					flag++;
					}
				Character temp=Copia_Char(C.get(i));
				Personagens_em_batalha.add(temp);
				Log.append("Adicionado "+nome+"("+flag+")\n");
				Personagens_em_batalha.get(Personagens_em_batalha.size()-1).Nome=nome+"("+flag+")";
				
				
				break;
				
				}
			}							
			System.out.println("\n\n\n");
			for(int j=0;j<Personagens_em_batalha.size();j++)
				System.out.println(Personagens_em_batalha.get(j).Nome);
			
			}




	}	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Copia_Char
	private Character Copia_Char(Character C) {
		
		
		 String Nome=C.Nome;
		 String Lv=C.Lv;
		 String Hp=C.Hp;
		 String CA=C.CA;
		 String Ataque=C.Ataque;
		 String Dano=C.Dano;
		 String Iniciativa=C.Iniciativa;
		 String Fortitude=C.Fortitude;
		 String Reflexos=C.Reflexos;
		 String Vontade=C.Vontade;
		 String Forca=C.Forca;
		 String Destreza=C.Destreza;
		 String Constituicao=C.Constituicao;
		 String Inteligencia=C.Inteligencia;
		 String Sabedoria=C.Sabedoria;
		 String Carisma=C.Carisma;
		 int sucessoDecisivo=C.sucessoDecisivo;
		 int multiplicadorsucessoDecisivo=C.multiplicadorsucessoDecisivo;
		 boolean player=C.player;
		 String HabilidadesDeClasse=C.HabilidadesDeClasse;
		
	
	return new Character( Nome, Lv, Hp,CA, Ataque, Dano, Iniciativa, Fortitude, Reflexos, Vontade, Forca, Destreza, Constituicao,Inteligencia, Sabedoria, Carisma,sucessoDecisivo,multiplicadorsucessoDecisivo,player,HabilidadesDeClasse);
	}	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Carrega Fichas
public ArrayList<Character>CarregaFichas(){
	
File dir;
String[] fichas;

dir= new File(caminho);
fichas= dir.list();	
Arrays.sort(fichas);// Ordena as fichas

for(int i=0;i<fichas.length;i++)
	if(!fichas[i].contains("xml~"))
{
	

String personagem=le_arquivo(caminho+fichas[i]); //devolve cada atributo em um indice
	
//atributos=FiltraString(atributos);// remove marca��es deixando apenas numeros	

String Nome=preg_match(personagem,"nome<(.*?)>");
String Level=preg_match(personagem,"lv<(.*?)>");
String Hp=preg_match(personagem,"hp<(.*?)>");
String Ca=preg_match(personagem,"ca<(.*?)>");
String Ataque=preg_match(personagem,"ataque<(.*?)>");
Ataque=Ataque.replace("BR/","");//bugfix
String Dano=preg_match(personagem,"dano<(.*?)>");
String Iniciativa=preg_match(personagem,"iniciativa<(.*?)>");
String Fortitude=preg_match(personagem,"fortitude<(.*?)>");
String Reflexos=preg_match(personagem,"reflexos<(.*?)>");
String Vontade=preg_match(personagem,"vontade<(.*?)>");
String For=preg_match(personagem,"for<(.*?)>");
String Des=preg_match(personagem,"des<(.*?)>");
String Con=preg_match(personagem,"con<(.*?)>");
String Int=preg_match(personagem,"int<(.*?)>");
String Sab=preg_match(personagem,"sab<(.*?)>");
String Car=preg_match(personagem,"car<(.*?)>");
String player=preg_match(personagem,"player<(.*?)>");
int sucessoDecisivo; 
int multiplicadorsucessoDecisivo;
boolean jogador=false;
String HabilidadesDeClasse=preg_match(personagem,"HabilidadesClasse<>(.*?)HabilidadesClasse</>");
//System.out.println(HabilidadesDeClasse+"LOL");
//System.out.println(preg_match(personagem,"SucessoDecisivo<(.*?)>"));

try{
sucessoDecisivo=Integer.parseInt(preg_match(personagem,"SucessoDecisivo<(.*?)>").split("x")[0]);
multiplicadorsucessoDecisivo=Integer.parseInt(preg_match(personagem,"SucessoDecisivo<(.*?)>").split("x")[1]);
}catch (Exception e) {
	JOptionPane.showMessageDialog(null,"Erro: parametro \"SucessoDecisivo\" n�o reconhecido na Ficha "+Nome);
	sucessoDecisivo=21; 
	multiplicadorsucessoDecisivo=1;
}




if(player.toLowerCase().equals("true"))
	jogador=true;
else
	if(player.toLowerCase().equals("false"))
	jogador=false;
	else
		JOptionPane.showMessageDialog(null, "Erro na ficha de "+Nome+"\nCampo player deve conter true ou false.\nplayer<"+player+">???");


//System.out.println(Nome+"\nCampo player="+player);
	//				Nome	Level	HP	  CA          Ataque			Dano	  iniciativa   Fortitude   Reflexos	Vontade	Forca	 Destreza	Constituicao   Inteligencia  Sabedoria	Carisma			
C.add(new Character(Nome,Level,Hp,Ca,Ataque,Dano,Iniciativa,Fortitude,Reflexos,Vontade,For,Des,Con,Int,Sab,Car,sucessoDecisivo,multiplicadorsucessoDecisivo,jogador,HabilidadesDeClasse));
//System.out.println(Nome);
//System.out.println(Nome+Level+Hp+Ca+Ataque+Dano+Iniciativa+Fortitude+Reflexos+Vontade+For+Des+Con+Int+Sab+Car);
//C.add(new Character(atributos[0],atributos[1],atributos[2],atributos[3],atributos[4],atributos[5],atributos[6],atributos[7],atributos[8],atributos[9],atributos[10],atributos[11],atributos[12],atributos[13], atributos[14],atributos[15]));
	
}

for(int i=0;i<C.size();i++)
	personagens.addItem (C.get(i).Nome);

return C;	
}

//PREG_MATCH///////////////////////////////////////////////////////////////////////////////////////////////////////////////
public String preg_match(String html,String expressao)
	{


Console console = System.console();
String v="";
Pattern p = Pattern.compile(expressao);
Matcher m = p.matcher(html);
boolean b = m.matches();

// Mostra as similaridades
try{
while(m.find())
{

   v+=m.group(1);
}
}catch(Exception e)
{ System.out.println(e); }
return v;



}
	
	
	
	
	
	
//LE_ARQUIVO()///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public String le_arquivo(String c)
{
	String linha=null;
	String conteudo="";
	try{
		FileReader arquivo = new FileReader(c);
		BufferedReader leitor = new BufferedReader(arquivo);
		while((linha = leitor.readLine())!= null) //Le o arquivo linha a linha
		{
		conteudo+=linha;
		}

	}catch (Exception e){JOptionPane.showMessageDialog(null, "Erro: "+e);}

	return conteudo;
}	
	
	
	
	
	
	
	
	
	
	}