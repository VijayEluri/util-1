import java.io.*;
import util.*;

/*Versione aggiornata per i Jar file (Scrivere, OutputStream)
 *ma purtroppo non funzionano più, da aggiornare*/

public class Codici{

	public Codici(){}

	public static void main(String s[]){
		Codici cod=new Codici();
		cod.f("Logica negata","10");}

	public void f(String ti,String s){
		try{
			File fi=new File("code.dat");
			if(!fi.exists()){
				BufferedInputStream bis=new BufferedInputStream(getClass().getResourceAsStream("resources/code.dat"));
				FileOutputStream fos=new FileOutputStream(fi);
				int i=bis.read();
				while(i>=0){
					fos.write(i);
					i=bis.read();
				}
				bis.close();
				fos.flush();fos.close();
			}
			DataOutputStream d=d=new DataOutputStream(new FileOutputStream(fi,true));
			d.writeChars(s);
			d.writeChar(' ');
			d.writeChars(ti);
			d.writeChar('\n');
			d.flush();
			d.close();
		}
		catch(IOException f){
			Dialogo d=new Dialogo("Errore","Non è stato possibile salvare il nuovo set di caratteri");
			d.show();
		}
	}
}