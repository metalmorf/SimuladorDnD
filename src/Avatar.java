import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class Avatar{
	

	public static JPanel P;
	public JFrame F;
	public int tamanhoFramex;
	public int tamanhoFramey;
	public boolean reorganiza;
	public String acumulador_log="";
	public  static ArrayList<Character> C=new ArrayList<Character>();
	public  static ArrayList<Character> TodosChars=new ArrayList<Character>();
	public ArrayList<Integer>ordem=new ArrayList<Integer>();
	public ArrayList<Duracaoefeito>efeitos=new ArrayList<Duracaoefeito>();
	public JTextPane log=new JTextPane();
	//public static JTextArea log_duracao=new JTextArea();;
	public JScrollPane bar= new JScrollPane(anotacao);
	public JScrollPane barra= new JScrollPane(log);
	public static JTextPane anotacao=new JTextPane();
	public adiar adia[];
	//Textfield
	public JTextField[] Lv;
	public JTextField[] Hp;
	public JTextField[] Ataque;
	public JTextField[] Dano;
	public JTextField[] CA;
	public JTextField[] Fortitude;
	public JTextField[] Reflexos;
	public JTextField[] Vontade;
	public JTextField[] Forca;
	public JTextField[] Destreza;
	public JTextField[] Constituicao;
	public JTextField[] Inteligencia;
	public JTextField[] Sabedoria;
	public JTextField[] Carisma;
	public JTextField[] Iniciativa;
	public JProgressBar[] Vida;
	public static Play player;
	//LABEL
    public JLabel[] NomeLB;
	public JLabel[] LvLB;
	public JLabel[] HpLB;
	public JLabel[] AtaqueLB;
	public JLabel[] DanoLB;
	public JLabel[] CALB;
	public JLabel[] FortitudeLB;
	public JLabel[] ReflexosLB;
	public JLabel[] VontadeLB;
	public JLabel[] ForcaLB;
	public JLabel[] DestrezaLB;
	public JLabel[] ConstituicaoLB;
	public JLabel[] InteligenciaLB;
	public JLabel[] SabedoriaLB;
	public JLabel[] CarismaLB;
	public JLabel[] IniciativaLB;
	public JLabel jLabelImagem;
	
	//
	JRadioButton checkedChar[];
	public JComboBox musicas=new JComboBox();
	public JComboBox personagens=new JComboBox();
	public JButton[] botao_adiar_acao;
	public JButton[] botao_atacar;
	public JButton passarturno;
	public JButton voltarturno;
	public JButton CharacterAdd;
	public JButton adicionarduracaoefeito;
	int rodada=-1;
	int posy=0;
	int posx=0;
	int contadortotalturnos=0;
	int contadorRodadaCompleta=0;
	int boxchecked=-1;
	int ataquex=-1;
	int danox=-1;
	
				
/////////////////////////////////////////////////////////////////////////////////////CONSTRUTOR	
public Avatar(ArrayList<Character> chars,JFrame F,ArrayList<Character>TodosChars){
player= new Play();
C=chars;
this.TodosChars=TodosChars;
log.setContentType("text/html"); 
anotacao.setContentType("text/html");
this.F=F;
tamanhoFramex=F.getHeight();
tamanhoFramey=F.getWidth();
voltarturno = new JButton("<< VOLTAR TURNO");  //inicia a ferramenta
voltarturno.addActionListener(new botaovoltar());
voltarturno.setForeground(new Color(255,0,0));
CharacterAdd = new JButton("Add");  //inicia a ferramenta
CharacterAdd.addActionListener(new Character_Add());
passarturno = new JButton("PRÓXIMO TURNO >>");  //inicia a ferramenta
passarturno.addActionListener(new PassarTurno());
passarturno.setForeground(new Color(255,0,0));
adia=new adiar[50];
P= new JPanel(null);
P.setBackground(new Color(220,220,220));
botao_atacar=new JButton [50];
botao_adiar_acao=new JButton [50];
checkedChar=new JRadioButton[50];
NomeLB=new JLabel [50];
//label
LvLB=new JLabel [50];
HpLB=new JLabel [50];
AtaqueLB=new JLabel [50];
DanoLB=new JLabel [50];
CALB=new JLabel [50];
FortitudeLB=new JLabel [50];
ReflexosLB=new JLabel [50];
VontadeLB=new JLabel [50];
ForcaLB=new JLabel [50];
DestrezaLB=new JLabel [50];
ConstituicaoLB=new JLabel [50];
InteligenciaLB=new JLabel [50];
SabedoriaLB=new JLabel [50];
CarismaLB=new JLabel [50];
IniciativaLB=new JLabel [50];


//textfield
Lv=new JTextField [50];
Hp=new JTextField [50];
Ataque=new JTextField [50];
Dano=new JTextField [50];
CA=new JTextField [50];
Fortitude=new JTextField [50];
Reflexos=new JTextField [50];
Vontade=new JTextField [50];
Forca=new JTextField [50];
Destreza=new JTextField [50];
Constituicao=new JTextField [50];
Inteligencia=new JTextField [50];
Sabedoria=new JTextField [50];
Carisma=new JTextField [50];
Iniciativa=new JTextField [50];
Vida=new JProgressBar[50];
barra.setWheelScrollingEnabled(true);	
barra.setAutoscrolls(true);
P.setVisible(true);	

P.add(musicas);

adicionarduracaoefeito=new JButton("Efeito de Magia");
adicionarduracaoefeito.setForeground(new Color(0,0,255));
P.add(adicionarduracaoefeito);
adicionarduracaoefeito.addActionListener(new efeito());

//inicializa combobox personagens
for(int i=0;i<TodosChars.size();i++)
	personagens.addItem (TodosChars.get(i).Nome);



for(int i=0;i<C.size();i++)
{ 
	//botoes
	if(C.get(i).player)
		botao_atacar[i]=new JButton("Dano");
	else
	botao_atacar[i]=new JButton("Atk");
	
	botao_atacar[i].setEnabled(false);
	botao_atacar[i].addActionListener(new ataque(i));	
	botao_adiar_acao[i]=new JButton("Adiar");
	botao_adiar_acao[i].setEnabled(false);
	botao_adiar_acao[i].addActionListener(adia[i]=new adiar(i)); 
    //calcula a iniciativa
    try{
    	int init=0;	
    if(C.get(i).player)
    	init=Iniciativa(C.get(i).Nome);
    else	
    	init= (Integer.parseInt(C.get(i).Iniciativa)+Dice(20));
	
    C.get(i).Iniciativa=Integer.toString(init);
    log(C.get(i).Nome+" Rolou iniciativa: "+init,"black");
    }catch (Exception e) {	JOptionPane.showMessageDialog(null, "Erro na ficha "+C.get(i).Nome+"\nCampo Iniciativa invï¿½lido");}
    //LABEL  
	NomeLB[i]=new JLabel (C.get(i).Nome); //troca o nome do Label conforme o char
	LvLB[i]=new JLabel ("Level");
	HpLB[i]=new JLabel ("Hp    "+C.get(i).Hp+"/");
	AtaqueLB[i]=new JLabel ("Ataque");
	DanoLB[i]=new JLabel ("Dano");
	CALB[i]=new JLabel ("CA");
	FortitudeLB[i]=new JLabel ("Fort");
	ReflexosLB[i]=new JLabel ("Refl");
	VontadeLB[i]=new JLabel ("Vont");
	ForcaLB[i]=new JLabel ("For");
	DestrezaLB[i]=new JLabel ("Des");
	ConstituicaoLB[i]=new JLabel ("Con");
	InteligenciaLB[i]=new JLabel ("Int");
	SabedoriaLB[i]=new JLabel ("Sab");
	CarismaLB[i]=new JLabel ("Car");  
	IniciativaLB[i]=new JLabel ("Inic"); 
    //TextField
	Lv[i]=new JTextField ();
	Hp[i]=new JTextField ();
	Ataque[i]=new JTextField ();
	Dano[i]=new JTextField ();
	CA[i]=new JTextField ();
	Fortitude[i]=new JTextField ();
	Reflexos[i]=new JTextField ();
	Vontade[i]=new JTextField ();
	Forca[i]=new JTextField ();
	Destreza[i]=new JTextField ();
	Constituicao[i]=new JTextField ();
	Inteligencia[i]=new JTextField ();
	Sabedoria[i]=new JTextField ();
	Carisma[i]=new JTextField ();
	Iniciativa[i]=new JTextField ();
	checkedChar[i]=new JRadioButton();
	checkedChar[i].setSelected(false);
	 Lv[i].setText(C.get(i).Lv+"");
	 Hp[i].setText(C.get(i).Hp);
	 Ataque[i].setText(C.get(i).Ataque+"");
	 Dano[i].setText(C.get(i).Dano+"");
	 CA[i].setText(C.get(i).CA+"");
	 Fortitude[i].setText(C.get(i).Fortitude+"");
	 Reflexos[i].setText(C.get(i).Reflexos+"");
	 Vontade[i].setText(C.get(i).Vontade+"");
	 Forca[i].setText("FOR: "+C.get(i).Forca+"");
	 Destreza[i].setText(C.get(i).Destreza);
	 Constituicao[i].setText(C.get(i).Constituicao);
	 Inteligencia[i].setText(C.get(i).Inteligencia);
	 Sabedoria[i].setText(C.get(i).Sabedoria);
	 Carisma[i].setText(C.get(i).Carisma);
	 Iniciativa[i].setText(C.get(i).Iniciativa);	

	checkedChar[i]=new JRadioButton();
	checkedChar[i].addActionListener(new CheckedChar(i));
	
	Vida[i] = new JProgressBar(0,Integer.parseInt(Hp[i].getText()));
	Vida[i].setValue(Integer.parseInt(Hp[i].getText()));
	Vida[i].setStringPainted(true);
	Vida[i].setVisible(true);
	
	//P.add(Lv[i]);
	P.add(Hp[i]);
	P.add(Ataque[i]);
	P.add(Dano[i]);
	P.add(CA[i]);
	P.add(Fortitude[i]);
	P.add(Reflexos[i]);
	P.add(Vontade[i]);
	P.add(Iniciativa[i]);
	P.add(checkedChar[i]);
	P.add(barra);
	P.add(NomeLB[i]); 
	P.add(HpLB[i]);
	P.add(AtaqueLB[i]);
	P.add(DanoLB[i]);
	P.add(CALB[i]);
	P.add(FortitudeLB[i]);
	P.add(ReflexosLB[i]);
	P.add(VontadeLB[i]);
	P.add(IniciativaLB[i]);
	P.add(passarturno);
	P.add(botao_atacar[i]);
	//P.add(botaovoltar);
	P.add(botao_adiar_acao[i]);
	P.add(bar);
	P.add(personagens);
	P.add(CharacterAdd);
	P.add(Vida[i]);
	
	
		 
}
setFont();
P.add(voltarturno);
//new engine();	
//P.validate();
ordem=ordemchars();
reorganiza=true;
System.out.println(ordem);
rodada(1,true);
engine t=new engine();
t.start();

	}	
public void setFont() {
	
for(int i=0;i<C.size();i++)
{
	
	 passarturno.setFont(new Font("Dialog", Font.PLAIN, 11));
	 Vida[i].setFont(new Font("Dialog", Font.BOLD, 12));
	 //Fontes
	 NomeLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 HpLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 LvLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 AtaqueLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 DanoLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 CALB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 FortitudeLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 ReflexosLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 VontadeLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 IniciativaLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
	 botao_atacar[i].setFont(new Font("Dialog", Font.PLAIN, 11));
	 botao_adiar_acao[i].setFont(new Font("Dialog", Font.PLAIN, 11));
	 log.setFont(new Font("Dialog", Font.PLAIN, 13)); 
	
	 
	 
	 Lv[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Hp[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Ataque[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Dano[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		CA[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Fortitude[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Reflexos[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Vontade[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Forca[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Destreza[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Constituicao[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Inteligencia[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Sabedoria[i].setFont(new Font("Dialog", Font.PLAIN, 13));
		Carisma[i].setFont(new Font("Dialog", Font.PLAIN, 13));
















}
	
	
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////botaovoltar
public class botaovoltar extends Thread  implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
		rodada--;
		if(rodada<0)
			rodada=C.size()-1;
		rodada(0,true);
		
		
		
		}

 

}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Efeito
public class efeito extends Thread  implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			efeitos.add(carregaEfeito());
			
			
		
		}
//////////////////////////////////////////////////////////////////////////////////////Ataques Player     
		public Duracaoefeito carregaEfeito()  
		{  
		 int rodadas;
			  String descricaoEfeito = javax.swing.JOptionPane.showInputDialog("Descrição do Efeito:");  
		  
			  String numrodadas = javax.swing.JOptionPane.showInputDialog("Informe o número de rodadas:");  
			  
			  try{
			  rodadas=Integer.parseInt(numrodadas);  
		    }catch (Exception e) {return null;}   
		
		    
		    
		    return new Duracaoefeito(rodadas+1,rodada,descricaoEfeito); 
		}  



}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ataque
public class ataque implements ActionListener{

	int atacante;
	int defensor;
	public  ataque(int atacante)
    {
	this.atacante=atacante;	
    }
		public void actionPerformed(ActionEvent arg0){
			defensor=boxchecked;
			if(defensor==-1)
				JOptionPane.showMessageDialog(null, "Selecione um inimigo para atacar");
			else
			if(C.get(atacante).player)
			{
			//int atk=Ataque(C.get(atacante).Nome);
			//log(C.get(atacante).Nome+"   Atacou   "+C.get(defensor).Nome+"    Ataque= "+atk,"Green");
			//if(atk>=Integer.parseInt(CA[defensor].getText()))
			//{
			//log("Acertou , CA= "+CA[defensor].getText()+"   Ataque= "+atk,"green");	
			int dano=Dano(C.get(atacante).Nome);
			int hptemp=Integer.parseInt(Hp[defensor].getText());
			log(C.get(atacante).Nome+" Acerta "+C.get(defensor).Nome,"green");
			log(C.get(defensor).Nome+" Hp: "+hptemp+" - "+dano+"= "+(hptemp-dano),"green");
			Hp[defensor].setText(Integer.toString(Integer.parseInt(Hp[defensor].getText())-dano));	
			}
		//	else{
		//		log("Errou , CA= "+CA[defensor].getText()+"    Ataque= "+atk,"red");
		//	}
			
			//}
			else{
			callAttack();
			}
			}
		public int callAttack() 
			
		
		{
			
			defensor=boxchecked;
			if(defensor==-1)
				JOptionPane.showMessageDialog(null, "Selecione um inimigo para atacar");
			int atk=0;
			String[]atks;
		//inicio npc
			ataquex++;//incrementa para o proximo ataque (caso haja)
			atks=Ataque[atacante].getText().split("/");
		
		
			if(ataquex<atks.length) //verifica se ï¿½ o personagem ainda tem ataques
			atk=Integer.parseInt(atks[ataquex]);
			int sucessoDecisivo=C.get(atacante).sucessoDecisivo;
			int multiplicadorsucessoDecisivo=C.get(atacante).multiplicadorsucessoDecisivo;
			int flagErro=0;
			int ca=Integer.parseInt(CA[defensor].getText());// CA do adversï¿½rio
			boolean golpe_critico=false;
			int dice=Dice(20);
			//atk=atk+dice;
			String corAtaque="blue";
			String corErro="red";
			
			
			if(dice>=sucessoDecisivo) //verifica se é dano critico
			{
			int confirmasucesso=atk+Dice(20);
			if(confirmasucesso>=ca)
				golpe_critico=true;
			}
			if(dice==1)
			{
				flagErro++;
				int dex=Integer.parseInt(Destreza[atacante].getText());
				dex=(dex-10)/2;
				//System.out.println(dex);	
				int d=Dice(20);
				if((d+dex)<10)
					log("Falha critica ,1 no dado e rodou no teste de destreza ("+d+" + "+dex+")","red");
			}
			log(C.get(atacante).Nome+"   Atacou   "+C.get(defensor).Nome+"    Ataque= "+(atk+dice)+" ("+dice+" + "+atk+")","black");
				
			if((atk+dice)>=ca && flagErro==0)
			{
			int dano=Integer.parseInt((Dano[atacante].getText().split("\\+")[1]));	
			//System.out.println((Dano[atacante].getText().split("\\+")[1]));	
			String dado=preg_match(Dano[atacante].getText(),"\\((.*?)\\)")	;
			int dadotemp;
			if(dado.toLowerCase().equals("d4"))
				{
				dadotemp=Dice(4);
				log("Acertou , CA= "+ca+"   Ataque= "+(atk+dice)+"   Dano= "+(dano+dadotemp)+"  ("+dadotemp+" + "+dano+")",corAtaque);		
				dano=dano+dadotemp;	
				}
			else
				if(dado.toLowerCase().equals("2d4"))
				{
					dadotemp=Dice(4);
					int dadotemp2=Dice(4);
				log("Acertou , CA= "+ca+"   Ataque= "+(atk+dice)+"   Dano= "+(dano+dadotemp+dadotemp2)+"  ("+dadotemp+" +" +dadotemp2+" + "+dano+")",corAtaque);
				dano=dano+dadotemp+dadotemp2;
				
				}
				else
				if(dado.toLowerCase().equals("d6"))
				{	
					dadotemp=Dice(6);
					log("Acertou , CA= "+ca+"   Ataque= "+(atk+dice)+"   Dano= "+(dano+dadotemp)+"  ("+dadotemp+" + "+dano+")",corAtaque);			
					dano=dano+dadotemp;	
				}
				else
					if(dado.toLowerCase().equals("d8"))	
					{
						dadotemp=Dice(8);
						log("Acertou , CA= "+ca+"   Ataque= "+(atk+dice)+"   Dano= "+(dano+dadotemp)+"  ("+dadotemp+" + "+dano+")",corAtaque);	
						dano=dano+dadotemp;	
					}
					else	
						if(dado.toLowerCase().equals("d10"))	
						{
							dadotemp=Dice(10);
							log("Acertou , CA= "+ca+"   Ataque= "+(atk+dice)+"   Dano= "+(dano+dadotemp)+"  ("+dadotemp+" + "+dano+")",corAtaque);			
							dano=dano+dadotemp;
						}
						else
							if(dado.toLowerCase().equals("d12"))		
							{
								dadotemp=Dice(12);
								log("Acertou , CA= "+ca+"   Ataque= "+(atk+dice)+"   Dano= "+(dano+dadotemp)+"  ("+dadotemp+" + "+dano+")",corAtaque);
								dano=dano+dadotemp;
							}
							else
								if(dado.toLowerCase().equals("2d6"))
								{
									dadotemp=Dice(6);
									int dadotemp2=Dice(6);
								log("Acertou , CA= "+ca+"   Ataque= "+(atk+dice)+"   Dano= "+(dano+dadotemp+dadotemp2)+"  ("+dadotemp+" +" +dadotemp2+" + "+dano+")",corAtaque);
								dano=dano+dadotemp+dadotemp2;
								
								}
								else
									if(dado.toLowerCase().equals("3d6"))
									{
										dadotemp=Dice(6);
										int dadotemp2=Dice(6);
										int dadotemp3=Dice(6);
									log("Acertou , CA= "+ca+"   Ataque= "+(atk+dice)+"   Dano= "+(dano+dadotemp+dadotemp2+dadotemp3)+"  ("+dadotemp+" +" +dadotemp2+" + "+dadotemp3+" + "+dano+")",corAtaque);
									dano=dano+dadotemp+dadotemp2+dadotemp3;
									
									}
			
			if(golpe_critico)
			{
				log("GOLPE CRÍTICO  Dano= "+dano+" x "+multiplicadorsucessoDecisivo,corErro);
				dano=dano*multiplicadorsucessoDecisivo;
			}
			int hptemp=Integer.parseInt(Hp[defensor].getText());
			log(C.get(defensor).Nome+" Hp: "+hptemp+" - "+dano+"= "+(hptemp-dano),corErro);
			Hp[defensor].setText(Integer.toString(Integer.parseInt(Hp[defensor].getText())-dano));	
				
				
			}
			else
				{
				if(flagErro>0)
				log("Errou , CA= "+ca+"    Ataque= "+(atk+dice)+" ("+dice+" + "+atk+") TIROU 1 No Dado",corErro);	
				else
					log("Errou , CA= "+ca+"    Ataque= "+(atk+dice)+" ("+dice+" + "+atk+")",corErro);
				
				}
		//}
			if(ataquex+1==atks.length)	
			{
				//rodada();
				ataquex=-1;
				//ArrayList<Integer>ordem=ordemchars();
				atualiza_botao_e_corAtacante();
				return 0;
			}	
		return 0;
		}
}


public void log(String s, String cor){
//try{
acumulador_log+="<font color="+cor+">"+contadortotalturnos+"        -         "+s+"</font>" +"<br/>";
log. setText(acumulador_log);	
//}catch (Exception e) {
//	System.out.println(e);
//}	
	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////PassarTurno
public class PassarTurno  implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			
			rodada(1,true);

		
}
		}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////botaovoltar
public class CheckedChar implements ActionListener{
	
	public int num;
	public CheckedChar(int n){
		
	num=n;
		
	}
	
		public void actionPerformed(ActionEvent arg0) {
			boxchecked=num;
			for(int i=0;i<C.size();i++)
				if(num!=i)
				checkedChar[i].setSelected(false);
		
			System.out.println("radiobutton");
		}

   
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////ordem dos chars no combate
public ArrayList<Integer>ordemchars(){

	 ArrayList<objiniciativa>iniciativas=new ArrayList<objiniciativa>();
	 
	    for(int i=0;i<C.size();i++)
	    	iniciativas.add(new objiniciativa(Integer.parseInt(C.get(i).Iniciativa),i));
	 int flag=0;   	
	 while(flag==0)
	 {
		 flag=1;
	 for(int i=0;i<iniciativas.size()-1;i++) 
	    if(iniciativas.get(i).iniciativa<iniciativas.get(i+1).iniciativa)
	    {
	    objiniciativa aux=new objiniciativa(iniciativas.get(i).iniciativa,iniciativas.get(i).posicaoarray);	
	    iniciativas.get(i).iniciativa=iniciativas.get(i+1).iniciativa;
	    iniciativas.get(i).posicaoarray=iniciativas.get(i+1).posicaoarray;
	    iniciativas.get(i+1).iniciativa=aux.iniciativa;
	    iniciativas.get(i+1).posicaoarray=aux.posicaoarray;
	    flag=0;
	    }
	 } 
	  for(int i=0;i<iniciativas.size();i++)
		  {ordem.add(iniciativas.get(i).posicaoarray);
		  }

//for(int i=0;i<ordem.size();i++)	  
//log_duracao.append(ordem.get(i)+"---"+C.get(ordem.get(i)).Nome+"\n");	  
	  
return ordem;	
}
class objiniciativa{
	
public int iniciativa;
public int posicaoarray;
public objiniciativa(int iniciativa,int posicaoarray){
	
this.iniciativa=iniciativa;
this.posicaoarray=posicaoarray;
	
}	
	
}
///////////////////////////////////////////////////////////////DELAY	
public void Delay(int num){
for(int xxx=0;xxx<1000000;xxx++)	
{
	for(int yyy=0;yyy<1000000;yyy++)
	{}
}	
		System.out.println("teste delay");
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////engine
public class engine extends Thread{

		
		
	

    public void run()
    {
    botao_atacar[ordem.get(0)].setEnabled(true);
     while(true){
    
    	try{
    	 ReorganizaAvatars();
    	}catch (Exception e) {	}
    	 for(int i=0;i<C.size();i++)
     		try{
     			Vida[i].setValue(Integer.parseInt(Hp[i].getText()));
     			
     			if(Integer.parseInt(Hp[i].getText())> Integer.parseInt(C.get(i).Hp)/1.2)
     				Vida[i].setForeground(new Color(0,200,0));
     			else
     			if(Integer.parseInt(Hp[i].getText())<= Integer.parseInt(C.get(i).Hp)/1.4 && Integer.parseInt(Hp[i].getText())> Integer.parseInt(C.get(i).Hp)/2.4)
     				Vida[i].setForeground(new Color(200,200,0));
     				else
     					if(Integer.parseInt(Hp[i].getText())<= Integer.parseInt(C.get(i).Hp)/2.4)
     						Vida[i].setForeground(new Color(200,0,0));
     			
     			NomeLB[ordem.get(rodada)].setForeground(new Color(0,0,255));
     	    	 if(Integer.parseInt(Hp[ordem.get(i)].getText())<0 && ordem.get(rodada)!=i)
     	    	 {  
     	    		// Hp[ordem.get(i)].setText("Dead");
     	    		 boolean b=false;
     	    			Lv[ordem.get(i)].setEditable(b);
     	    			//Hp[ordem.get(i)].setEditable(b);
     	    			Ataque[ordem.get(i)].setEditable(b);
     	    			Dano[ordem.get(i)].setEditable(b);
     	    			CA[ordem.get(i)].setEditable(b);
     	    			Fortitude[ordem.get(i)].setEditable(b);
     	    			Reflexos[ordem.get(i)].setEditable(b);
     	    			Vontade[ordem.get(i)].setEditable(b);
     	    			Forca[ordem.get(i)].setEditable(b);
     	    			Destreza[ordem.get(i)].setEditable(b);
     	    			Constituicao[ordem.get(i)].setEditable(b);
     	    			Inteligencia[ordem.get(i)].setEditable(b);
     	    			Sabedoria[ordem.get(i)].setEditable(b);
     	    			Carisma[ordem.get(i)].setEditable(b);
     	    			Iniciativa[ordem.get(i)].setEditable(b); 
     	    		 
     	    		//mortos.add(rodada);
     	    		// mortos=remove_mortos_duplicados(mortos);
 
     	    		
     	    	 }
     	    	// for(int j=0;j<mortos.size();j++)
     	    	 
     	    	 if(Integer.parseInt(Hp[ordem.get(rodada)].getText())<0 && C.get(ordem.get(rodada)).player==false)
     	    		 rodada(1,false); //false para nao atualizar o campo anotacao
     	    	 
     	    	 if(Integer.parseInt(Hp[ordem.get(i)].getText())>=0)
     	    	 {  
     	    		// Hp[ordem.get(i)].setText("Dead");
     	    		 boolean b=true;
     	    			Lv[ordem.get(i)].setEditable(b);
     	    			Hp[ordem.get(i)].setEditable(b);
     	    			Ataque[ordem.get(i)].setEditable(b);
     	    			Dano[ordem.get(i)].setEditable(b);
     	    			CA[ordem.get(i)].setEditable(b);
     	    			Fortitude[ordem.get(i)].setEditable(b);
     	    			Reflexos[ordem.get(i)].setEditable(b);
     	    			Vontade[ordem.get(i)].setEditable(b);
     	    			Forca[ordem.get(i)].setEditable(b);
     	    			Destreza[ordem.get(i)].setEditable(b);
     	    			Constituicao[ordem.get(i)].setEditable(b);
     	    			Inteligencia[ordem.get(i)].setEditable(b);
     	    			Sabedoria[ordem.get(i)].setEditable(b);
     	    			Carisma[ordem.get(i)].setEditable(b);
     	    			Iniciativa[ordem.get(i)].setEditable(b); 
     	    		 
     	    		//mortos.add(rodada);
     	    		// mortos=remove_mortos_duplicados(mortos);
     	    		 
     	    	 }
     	    	 
     	    	 
     	    	}catch(Exception e){};
     			
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	 //passarturno.setBounds(posx+100, 120+posy, 60, 60);
    	
    	 
     	}
    }

}			
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////engine
public class cor_dead extends Thread{
	
    public void run()
    {
    	
    }

}	

//////////////////////////////////////////////////////////////////////////////////////Iniciativas Player     
public int Iniciativa(String Nome)  
{  
 int init;
	  String response = javax.swing.JOptionPane.showInputDialog("Informe a iniciativa de "+Nome);  
  try{
	  init=Integer.parseInt(response);  
    }catch (Exception e) {
     init=Iniciativa(Nome);
	}   
  return init; 
}  
//////////////////////////////////////////////////////////////////////////////////////Ataques Player     
public int Ataque(String Nome)  
{  
 int ataque;
	  String response = javax.swing.JOptionPane.showInputDialog("Informe o ataque de "+Nome);  
  try{
	  ataque=Integer.parseInt(response);  
    }catch (Exception e) {
     ataque=Iniciativa(Nome);
	}   
  return ataque; 
}

//////////////////////////////////////////////////////////////////////////////////////Dano Player     
public int Dano(String Nome)  
{  
 int Dano;
	  String response = javax.swing.JOptionPane.showInputDialog("Informe o Dano de "+Nome);  
  try{
	  Dano=Integer.parseInt(response);  
    }catch (Exception e) {
     Dano=Ataque(Nome);
	}   
  return Dano; 
}
////////////////////////////////////////////////////////////////////DICE
public int Dice(int D){
	
	 double random;    
     
       random = Math.random() * D;    
        
int numero=(int)random;        
 numero++;      
//if(numero==0)
//	return 1;
//else  
return numero;	
}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Adiar turno
///////////////////////////////////////////////////////////////////////////////Class Adiar
public class adiar extends Thread  implements ActionListener{

	 int adiante;
	boolean turnoAdiado;
	int aux=0; 
	public  adiar(int adiante)
    {
	this.adiante=adiante;	
	
	
    }
	
	
	public void actionPerformed(ActionEvent arg0) {
		adiar t=new adiar(adiante);
		//this.turnoAdiado=true;
		//adiaAcao();
		t.start();
		
		
	}
public void run(){
	
	
	//int v[] = new int[ordem.size()+1];
	
	//System.out.println(ordem);
	
	if(botao_adiar_acao[adiante].getText().equals("Adiar"))
	{
		botao_adiar_acao[adiante].setText("Agir");	
	aux=adiante;
	rodada(1,true);
	botao_adiar_acao[adiante].setEnabled(true);
	//ordem.remove(adiante);
	
	//System.out.println("Adiado removido: "+ordem);
	
	}
	else{
		if(botao_adiar_acao[adiante].getText().equals("Agir"))
		{
		botao_adiar_acao[adiante].setText("Adiar");	
			
			System.out.println("Aux=" +aux+"   Adiante= "+adiante+"   Rodada= "+rodada);
	
			for(int i=0;i<ordem.size();i++)
				{if(ordem.get(i)==adiante)
					ordem.remove(i);
				
				}
			rodada--;
			if(rodada==-1)//bugfix
				rodada=ordem.size();
			
				ordem=insere_no_array(adiante, rodada, ordem);
				rodada--;
			
			rodada(1,true);
			
		
		}
		
	}

	
	
}	
	
}
////////////////////////////////////////////////////////////////Rodada
public void rodada(int n,boolean sobrescreve_anotacao) {
	//n=0 significa voltar, N=1 significa avançar
	try{
	if(rodada>=0 && n==1 && sobrescreve_anotacao)// se nao for "voltar rodada" E o personagem estiver de pé HP>0
	{
	C.get(ordem.get(rodada)).HabilidadesDeClasse=anotacao.getText(); //Atualiza o campo com as informações de classe do personagem
	}
	}catch (Exception e) {System.out.println(e);}
	
	for(int i=0;i<efeitos.size();i++)
		if(efeitos.get(i)!=null)
		if(efeitos.get(i).rodadaDeUtilizacao==rodada)
			{
			if(n==1)
			efeitos.get(i).duracaoEmRodadas--;
			
			if(n==0)
			{
				efeitos.get(i).duracaoEmRodadas++;
			
				if(efeitos.get(i).duracaoEmRodadas>=efeitos.get(i).duracaototal)
				{
				log("Efeito "+efeitos.get(i).Descricao+"  REMOVIDO","orange");
				efeitos.remove(i);
				
				}
			}
			try{
			if(efeitos.get(i).duracaoEmRodadas<=efeitos.get(i).duracaototal)	
			log(efeitos.get(i).Descricao+"  restam  "+efeitos.get(i).duracaoEmRodadas+" Rodadas","orange");
			if(efeitos.get(i).duracaoEmRodadas==0)
				{
				JOptionPane.showMessageDialog(null, "O Efeito "+efeitos.get(i).Descricao+" terminou");
				efeitos.remove(i);
				}
			}catch (Exception e) {
		
				System.out.println(e);
			}
			
			
			
			
				}

	ataquex=-1;
	System.out.println("Ordem da rodada: "+ordem);
	reiniciaCheckbox();
	if(n==1)
	contadortotalturnos++;
	else
		{contadortotalturnos--;	
		}
		//ArrayList<Integer>ordem=ordemchars();
	if(n==1)
	rodada++;
	if(rodada>=ordem.size())
	{	restauraBotoes_adiar();
		rodada=0;
		danox=0;
		if(n==1)
			contadorRodadaCompleta++;
		log("*******Fim da Rodada "+contadorRodadaCompleta+"*******","red");		
		

	}
	if(n==0 && rodada==C.size()-1)
	{	contadorRodadaCompleta--;
	log("*******Fim da Rodada "+contadorRodadaCompleta+"*******","red");
		}
	
		atualiza_botao_e_corAtacante();
try{
	log("Turno de "+C.get(ordem.get(rodada)).Nome,"black");
	}catch (Exception e) {
		
	}
	anotacao.setText(C.get(ordem.get(rodada)).HabilidadesDeClasse); //Atualiza o campo com as informações de classe do personagem
	
	}
public void restauraBotoes_adiar(){
	
//for (int i=0;i<C.size();i++)
	//botao_adiar_acao[i].setText("Adiar");	
	
	
}
//atualiza botao e cor do atacante///////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void atualiza_botao_e_corAtacante(){
	//ArrayList<Integer>ordem=ordemchars();
	for(int i=0;i<C.size();i++)
	{
//	try{	
	if(i==ordem.get(rodada))
	{
	botao_atacar[i].setEnabled(true);
	botao_adiar_acao[i].setEnabled(true);
	//botao_adiar_acao[i].setText("Adiar");
	if(botao_adiar_acao[i].getText().equals("Agir"))
	{
	//	botao_adiar_acao[i].setText("Adiar"); 
	//	ordem.add(adia[i].aux);
	//	botao_adiar_acao[i].setEnabled(true);
	}
//	}
//	}catch (Exception e) {
//		ordem=backup_ordem;
//		System.out.println("ordem restaurada"+ordem);
//		if(i==ordem.get(rodada))
//		{
//		botao_atacar[i].setEnabled(true);
//		botao_adiar_acao[i].setEnabled(false);
//		}
	}
NomeLB[i].setForeground(new Color(0,0,0));	
//try{
if(i!=ordem.get(rodada))
	{
	botao_atacar[i].setEnabled(false);
	if(botao_adiar_acao[i].getText().equals("Agir"))	
	botao_adiar_acao[i].setEnabled(true);
	
	if(botao_adiar_acao[i].getText().equals("Adiar"))	
		botao_adiar_acao[i].setEnabled(false);
	//}
	}
	



	}
	
}
////////////////////////////////////////////////////////////////////////////////////////insere novetor
public ArrayList<Integer> insere_no_array(int numero,int posicao,ArrayList<Integer> ord_rodada)//recebe o arraylist com o numero da rodada adiado ja removido
{
	//ord_rodada.add(1);ord_rodada.add(2);ord_rodada.add(3);ord_rodada.add(4);ord_rodada.add(6);ord_rodada.add(7);
	
	int ordemm[]=new int[ord_rodada.size()+1];
	
	for(int i=0;i<ord_rodada.size();i++)
		ordemm[i]=new Integer(ord_rodada.get(i));
	
	
	for(int i=0;i<ordemm.length;i++)
	System.out.print(ordemm[i]+"  ");
		
	
for(int i=ordemm.length-1;i>posicao;i--)
	ordemm[i]=ordemm[i-1];
	ordemm[posicao]=numero;

	
ArrayList<Integer> a=new ArrayList<Integer>();

for(int i=0;i<ordemm.length;i++)
	a.add(ordemm[i]);
	


System.out.println(a);





return a;
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
public void reiniciaCheckbox(){
	boxchecked=-1;
	for(int i=0;i<C.size();i++)
		checkedChar[i].setSelected(false);	
	
}
public ArrayList<Integer> clonaordem() 
{
ArrayList<Integer> aux=new ArrayList<Integer>();
int v[]=new int[ordem.size()];

for(int i=0;i<ordem.size();i++)
	v[i]=ordem.get(i);
	
for(int i=0;i<ordem.size();i++)
	aux.add(v[i]);
return aux;		
}

//CARREGAR IMAGEM
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
public void LoadImg(){	
 String caminho = "imagem.jpg";  
File file = new File(caminho);  
Image img;  
try {  
    img = ImageIO.read(file);  
} catch (IOException ex) {  
    System.err.println("\"" + caminho + "\" (" + file.getAbsolutePath() + ")");  
    ex.printStackTrace();  
    return;  // ou o que for necessário  
}  
jLabelImagem.setIcon(new ImageIcon(img));   

}
// PLAYER MP3
///////////////////////////////////////////////////////////////////////////////
public class Play extends JPanel implements ActionListener, Runnable{
	
	private String DIRETORIO = new Frame().caminho.replace("Fichas/","Trilhas"); //pasta das musicas
	private List<String> listaMusicas;
	private int index;
	private Thread toca = new Thread(this);

	public Play() {
		super(new GridLayout(0, 1));
		this.setBorder(BorderFactory.createTitledBorder("Músicas"));
		adicionaCheckBox();
	}

	private void adicionaCheckBox() {
		listaMusicas = PlayerFacade.getInstance().buscaListaMusicas(DIRETORIO);
		//System.out.println(new Frame().caminho.replace("Fichas/","Trilhas"));
		//System.out.println("/home/evandro/Dropbox/Projetos java/DeD/Trilhas");
		musicas.addItem("SELECIONAR MÚSICA");
		musicas.setSelectedIndex(0);
		for (int i = 0; i < listaMusicas.size(); i++) {
			musicas.addItem(listaMusicas.get(i));
			musicas.addActionListener(this);
			
			musicas.setActionCommand(listaMusicas.get(i));
		
		
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (toca.isAlive()) {
			toca.stop();
			toca = new Thread(this);
		}
		else
			{toca = new Thread(this);}
					index = 0;
					if(!musicas.getSelectedItem().equals("SELECIONAR MÚSICA"))
						toca.start();
					else{toca.stop();}
				
							
	}

	@Override
	public void run() {
		//while(true){
		PlayerFacade.getInstance().tocarMusica(new File(DIRETORIO+File.separator+musicas.getSelectedItem()));
		//}
		}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////CharacterAdd
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////botao
public class Character_Add extends Thread  implements ActionListener{

public void actionPerformed(ActionEvent arg0) {
//botao t=new botao();
//t.start();

String nome=(String)personagens.getSelectedItem();
if(C.size()==50)
	JOptionPane.showMessageDialog(null,"Limite máximo de players atingido 50");

else
for(int i=0;i<TodosChars.size();i++)
{
int flag=1;	

if(nome.equals(TodosChars.get(i).Nome))
{
//
for(int j=0;j<C.size();j++)
if(nome.equals(C.get(j).Nome.substring(0,C.get(j).Nome.indexOf("("))))
{	
flag++;
}
Character temp=Copia_Char(TodosChars.get(i));
C.add(temp);
log("Adicionado "+nome+"("+flag+")","black");
C.get(C.size()-1).Nome=nome+"("+flag+")";


break;

}
}							
for(int i=C.size()-1;i<C.size();i++)
{
	if(C.get(i).player)
		botao_atacar[i]=new JButton("Dano");
	else
		botao_atacar[i]=new JButton("Atk");
	
botao_atacar[i].setEnabled(false);
botao_atacar[i].addActionListener(new ataque(i));
botao_adiar_acao[i]=new JButton("Adiar");
botao_adiar_acao[i].setEnabled(false);
botao_adiar_acao[i].addActionListener(adia[i]=new adiar(i));
NomeLB[i]=new JLabel (C.get(i).Nome); //troca o nome do Label conforme o char
LvLB[i]=new JLabel ("Level");
HpLB[i]=new JLabel ("Hp    "+C.get(i).Hp+"/");
AtaqueLB[i]=new JLabel ("Ataque");
DanoLB[i]=new JLabel ("Dano");
CALB[i]=new JLabel ("CA");
FortitudeLB[i]=new JLabel ("Fort");
ReflexosLB[i]=new JLabel ("Refl");
VontadeLB[i]=new JLabel ("Vont");
ForcaLB[i]=new JLabel ("For");
DestrezaLB[i]=new JLabel ("Des");
ConstituicaoLB[i]=new JLabel ("Con");
InteligenciaLB[i]=new JLabel ("Int");
SabedoriaLB[i]=new JLabel ("Sab");
CarismaLB[i]=new JLabel ("Car");  
IniciativaLB[i]=new JLabel ("Inic"); 
//TextField
Lv[i]=new JTextField ();
Hp[i]=new JTextField ();
Ataque[i]=new JTextField ();
Dano[i]=new JTextField ();
CA[i]=new JTextField ();
Fortitude[i]=new JTextField ();
Reflexos[i]=new JTextField ();
Vontade[i]=new JTextField ();
Forca[i]=new JTextField ();
Destreza[i]=new JTextField ();
Constituicao[i]=new JTextField ();
Inteligencia[i]=new JTextField ();
Sabedoria[i]=new JTextField ();
Carisma[i]=new JTextField ();
Iniciativa[i]=new JTextField ();
checkedChar[i]=new JRadioButton();
checkedChar[i].setSelected(false);
Lv[i].setText(C.get(i).Lv+"");
Hp[i].setText(C.get(i).Hp);
Ataque[i].setText(C.get(i).Ataque+"");
Dano[i].setText(C.get(i).Dano+"");
CA[i].setText(C.get(i).CA+"");
Fortitude[i].setText(C.get(i).Fortitude+"");
Reflexos[i].setText(C.get(i).Reflexos+"");
Vontade[i].setText(C.get(i).Vontade+"");
Forca[i].setText("FOR: "+C.get(i).Forca+"");
Destreza[i].setText(C.get(i).Destreza);
Constituicao[i].setText(C.get(i).Constituicao);
Inteligencia[i].setText(C.get(i).Inteligencia);
Sabedoria[i].setText(C.get(i).Sabedoria);
Carisma[i].setText(C.get(i).Carisma);
Iniciativa[i].setText(C.get(i).Iniciativa);	

checkedChar[i]=new JRadioButton();
checkedChar[i].addActionListener(new CheckedChar(i));

Vida[i] = new JProgressBar(0,Integer.parseInt(Hp[i].getText()));
Vida[i].setValue(Integer.parseInt(Hp[i].getText()));
Vida[i].setStringPainted(true);
Vida[i].setVisible(true);
Vida[i].setFont(new Font("Dialog", Font.BOLD, 12));
Vida[i].setValue(Integer.parseInt(Hp[i].getText()));
P.add(Vida[i]);
P.add(Hp[i]);
P.add(Ataque[i]);
P.add(Dano[i]);
P.add(CA[i]);
P.add(Fortitude[i]);
P.add(Reflexos[i]);
P.add(Vontade[i]);
P.add(Iniciativa[i]);
P.add(checkedChar[i]);
P.add(barra);
P.add(NomeLB[i]); 
P.add(HpLB[i]);
P.add(AtaqueLB[i]);
P.add(DanoLB[i]);
P.add(CALB[i]);
P.add(FortitudeLB[i]);
P.add(ReflexosLB[i]);
P.add(VontadeLB[i]);
P.add(IniciativaLB[i]);
P.add(passarturno);
P.add(botao_atacar[i]);
P.add(botao_adiar_acao[i]);
P.add(bar);

 passarturno.setFont(new Font("Dialog", Font.PLAIN, 11));
 Vida[i].setFont(new Font("Dialog", Font.BOLD, 12));
 //Fontes
 NomeLB[i].setFont(new Font("Dialog", Font.BOLD, 13));
 HpLB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 LvLB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 AtaqueLB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 DanoLB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 CALB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 FortitudeLB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 ReflexosLB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 VontadeLB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 IniciativaLB[i].setFont(new Font("Dialog", Font.BOLD, 12));
 botao_atacar[i].setFont(new Font("Dialog", Font.PLAIN, 10));
 botao_adiar_acao[i].setFont(new Font("Dialog", Font.PLAIN, 10));
 voltarturno.setFont(new Font("Dialog", Font.PLAIN, 11));
// log.setForeground(new Color(255,0,0));
 log.setFont(new Font("Dialog", Font.PLAIN, 13)); 


}
setFont();
ordem.add(ordem.size());
reorganiza=true;
}	
}
//Reorganiza avatars
public void ReorganizaAvatars(){


passarturno.setBounds(F.getWidth()-200, F.getHeight()-130,184, 61);
voltarturno.setBounds(F.getWidth()-200, F.getHeight()-69,184, 31);
barra.setBounds(10, F.getHeight()-190,F.getWidth()-709, 150);
bar.setBounds(F.getWidth()-700, F.getHeight()-161,499, 121);	
musicas.setBounds(F.getWidth()-290, F.getHeight()-190,F.getHeight(), 30);
adicionarduracaoefeito.setBounds(F.getWidth()-200, F.getHeight()-160,184, 30);
CharacterAdd.setBounds(F.getWidth()-350, F.getHeight()-190,60, 30);
personagens.setBounds(F.getWidth()-600, F.getHeight()-190,250, 30);	

if(tamanhoFramex!=F.getHeight() ||tamanhoFramey!=F.getWidth() || reorganiza==true)
{
posx=0;
posy=0;		
tamanhoFramex=F.getHeight();	
tamanhoFramey=F.getWidth();
reorganiza=false;
for(int i=0;i<C.size();i++)
{
	
	//Label
	NomeLB[i].setBounds(posx+20, 0+posy, 170, 20);	
	checkedChar[i].setBounds(posx+0, 0+posy, 20, 20);
	LvLB[i].setBounds(posx+10, 20+posy, 170, 20);
	Vida[i].setBounds(posx+10, 20+posy, 160, 15);
	HpLB[i].setBounds(posx+10, 40+posy, 170, 20);	
	AtaqueLB[i].setBounds(posx+10, 60+posy, 170, 20);	
	DanoLB[i].setBounds(posx+10, 80+posy, 170, 20);	
	CALB[i].setBounds(posx+10, 100+posy, 170, 20);	
	FortitudeLB[i].setBounds(posx+10, 120+posy, 40, 20);	
	ReflexosLB[i].setBounds(posx+10, 140+posy, 40, 20);	
	VontadeLB[i].setBounds(posx+85, 120+posy, 40, 20);
	IniciativaLB[i].setBounds(posx+85,140+posy, 170, 20);
	barra.setBounds(10, 500, 800, 100);
	//Textfield
 //Nome[i].setBounds(posx+60, 0+posy, 170, 20);	
 Lv[i].setBounds(posx+60, 20+posy, 20, 19);	
 Hp[i].setBounds(posx+65, 41+posy, 55, 19);
 Ataque[i].setBounds(posx+60, 60+posy, 60, 19);	
 botao_atacar[i].setBounds(posx+120, 60+posy, 60, 19);
 botao_adiar_acao[i].setBounds(posx+90, 100+posy, 70, 19);
 Dano[i].setBounds(posx+60, 80+posy, 100, 19);	
 CA[i].setBounds(posx+60, 100+posy, 30, 19);	
 Fortitude[i].setBounds(posx+45, 120+posy, 30, 19);	
 Reflexos[i].setBounds(posx+45, 140+posy, 30, 19);	
 Vontade[i].setBounds(posx+130, 120+posy, 30, 19);
 Iniciativa[i].setBounds(posx+130, 140+posy, 30, 19);
 passarturno.setBounds(posx+100, 120+posy, 60, 60);
 passarturno.setFont(new Font("Dialog", Font.PLAIN, 11));
 Vida[i].setFont(new Font("Dialog", Font.BOLD, 12));
 
	posx+=192;	
	if(posx>=F.getWidth()-150)
	{
	posx=0;
	posy+=165;
	}
}
}	
}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////Copia_Char
public Character Copia_Char(Character C) {


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


}
