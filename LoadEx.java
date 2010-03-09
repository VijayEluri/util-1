import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import util.*;

public class LoadEx extends Load implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2088936183039393156L;
	private JLabel cod,num;
	private JRadioButton scr;
	public static final String[] EX={"jna","kna"};

	public LoadEx(JTextArea area,JLabel cod,JLabel num,JFrame f,String[] s,JRadioButton scr){
		super(area,f,s);
		this.cod=cod;
		this.num=num;
		this.scr=scr;
	}

	public LoadEx(JTextArea area,JLabel cod,JLabel num,JFrame f,String[] s){
		this(area,cod,num,f,s,null);
	}

	public LoadEx(JTextArea area,JLabel cod,JLabel num,String[] s){
		this(area,cod,num,null,s);
	}

	public LoadEx(JLabel cod,JLabel num){
		this(null,cod,num,null,EX);
	}

	public static String getText(File f,JLabel cod,JLabel num){
		LoadEx le=new LoadEx(cod,num);
		return le.getText(f);
	}

	public String getText(File f){
		System.out.println("LoadEx");
		FileReader input=null;
		String temp="";
		try{
			input=new FileReader(f);
		}
		catch(IOException exce){
			Dialogo d=new Dialogo("Errore","File Non Trovato\nMa xchè se ho usato il FileChooser?\nchissà");
			d.show();
		}
		BufferedReader buff=new BufferedReader(input);
		try{
			StringTokenizer toki=new StringTokenizer(buff.readLine());
			temp=toki.nextToken(" ");
			cod.setText(temp.substring(temp.indexOf('|')+1,temp.length()));
			num.setText(temp.substring(0,temp.indexOf('|')));
			String t;
			temp="";
			while(toki.hasMoreTokens()){
				t=toki.nextToken();
				temp+=t+" ";}
			temp.trim();
			temp+="\n";
			while(buff.ready()){
				t=buff.readLine();
				temp+=t+"\n";
			}
		}
		catch(IOException dioDog){
			dioDog.printStackTrace();
			Dialogo d=new Dialogo("Errore di Lettura","Non so cosa è successo\nProva a chiudere e riAprire");
			d.show();
		}
		if(scr!=null)
				scr.setSelected(true);
		return temp;
	}

	public static void main(String argv[]){
		JFrame f=new JFrame("Prova LoadEx");
		JPanel p=new JPanel();
		JTextArea a=new JTextArea(20,20);
		JButton b=new JButton("Start");
		JLabel c=new JLabel();
		JLabel d=new JLabel();
		String s[]={"jna"};
		b.addActionListener(new LoadEx(a,c,d,s));
		p.add(a);
		p.add(b);
		p.add(c);
		p.add(d);
		f.getContentPane().add(p);
		f.pack();
		f.setVisible(true);
	}
}