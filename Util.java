/**
 *Qui sono stati usati dei trucchi
 *che sono da togliere per rendere
 *più lineare il codice: vedi ScrL*/
import java.io.*;
import util.*;
import java.util.*;
import javax.swing.*;

public class Util implements Code{

	private UtilL s;
	private String max;
	private int n;

	public Util(){}

	public Util(JLabel num,JLabel cod){
		n=Integer.parseInt(num.getText());
		max=cod.getText().substring(0,Integer.parseInt(num.getText()));
		s=new UtilL(cod,num);}


	public String getMax(){
		return scr(255).trim();
	}

	public char leggi(String st,boolean bool){
		int numero,ntmp,i,k;
		if(st.length()>getMax().length())
			throw new NumberFormatException();
		for(i=st.length()-1,k=0,numero=0;i>=0;k++,i--){
			if(max.indexOf(st.charAt(i))!=-1){
				ntmp=s.Xes(st.charAt(i));
				numero+=ntmp*pow(n,k);}
			else{
				numero=63;
				if(!bool){
				Dialogo d=new Dialogo("Errore","è stato inserito un carattere non valido");
				d.show();}
				throw new NumberFormatException();}}
			return (char)numero;}

	public char leggiC(String st){
		return leggi(st,true);}

	public byte[] leggiB(String st){
		StringTokenizer Toki= new StringTokenizer(st);
		String res="";
		byte[] b=new byte[0],temp=new byte[256];
		for(int i=0;res!=null;i++){
			if(i==256){
				b=conc(temp,b);
				i=0;
			}
			try{
				res=Toki.nextToken();
				temp[i]=(byte)leggiN(res,true);
			}
			catch(NoSuchElementException x){break;}
		}
		return b;
	}

	private byte[] conc(byte[] buf,byte[] end){
		byte[] temp=new byte[end.length];
		int i;
		for(i=0;i<end.length;i++)
			temp[i]=end[i];
		end=new byte[end.length+buf.length];
		for(i=0;i<temp.length;i++)
			end[i]=temp[i];
		for(;i<end.length;i++)
			end[i]=buf[i-temp.length];
		return end;
	}
	/*st: Stringa da convertire\ntype: 0 char\n1 byte[]\n2 int\ndefault echo*/
	public Object leggi(String st,int type){
		switch(type){
			case 0:return new Character(leggiC(st));
			case 1:return leggiB(st);
			case 2:return new Integer(leggiN(st));
			default:return st;
		}
	}

	public int leggiN(String st){
		return leggiN(st,false);}

	/*Questo và eliminato serve solo per mascherare gli errori*/
	public int leggiN(String st,boolean bool)throws NumberFormatException{
		int numero,ntmp,i,k;
		for(i=st.length()-1,k=0,numero=0;i>=0;k++,i--){
			if(max.indexOf(st.charAt(i))!=-1){
				ntmp=s.Xes(st.charAt(i));
				numero+=ntmp*pow(n,k);}
			else{
				if(!bool){
				Dialogo d=new Dialogo("Errore","è stato inserito un carattere :"+st.charAt(i)+"\nnon valido");
				d.show();}
				throw new NumberFormatException();}
			}
		return numero;}

	private double pow(int M,int N){
		int c,t;
		if(N==0)
			return 1;
		if(N==1)
			return M;
		t=M;
		for(c=1;c<N;c++)
 			t*=M;
		return t;
		}

	public String scr(InputStream is){
		String temp="";
		try{
			int i=is.read();
			while(i>=0){
				temp+=scr(i);
				i=is.read();}}
		catch(IOException ex){
			Dialogo2 d=new Dialogo2("Errore","Errore nella lettura del file");
			d.setVisible(true);}
		return temp;}

	public String scr(String str,boolean bool){
		if(bool){
			char[] c;
			String t="",temp;
			c=str.toCharArray();
			for(int i=0;i<str.length();i++){
				temp=scrivi(c[i],n);
				if(temp.length()<getMax().length()){
					String zero=scr("\0").trim();
					int z=getMax().length();
					for(int j=temp.length();j<z;j++)
						temp=zero+temp;
				}
				t=t+temp;
			}
			return t;
		}
		else{
			return scr(str);}
	}

	public String scr(String stri){
		char[] c;
		String t="";
		c=stri.toCharArray();
		for(int i=0;i<stri.length();i++)
			t=t+scrivi(c[i],n)+" ";
		return t;
		}

	public String scr(int stri){
		String t="";
		t=""+scrivi((char)stri,n)+" ";
		return t;}

	private String scrivi(char c,int n){
		String temp="";
		int enne=(int)c;
		int numero=(enne>=0)?enne:256+enne;
		int i,resto;
		for(i=0,resto=0;numero>=n;i++){
		resto=numero%n;
		numero=(int)numero/n;
		temp=temp+s.Xes(resto);}
		temp=temp+s.Xes(numero);
		return inverti(temp);}

	private String inverti(String a){
		int i=a.length();
		String temp="";
		i--;
		while(i>=0){
			temp=temp+a.charAt(i);
			i--;}
		return temp;
		}

	public static void main(String argv[]){
		Util e=new Util();
		System.out.println("pow 5-0 "+e.pow(5,0));
		System.out.println("pow 3-1 "+e.pow(3,1));
		System.out.println("pow 4-2 "+e.pow(4,2));
		System.out.println("pow -1-2 "+e.pow(-1,3));
		System.out.println("pow 3-7 "+e.pow(3,7));
		}
	}
