/*Problema di scrittura su file*/

import util.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class EraseCod implements ActionListener{

	private JComboBox jcb;
	Vector v;
	public EraseCod(JComboBox jcb,Vector v){
		this.jcb=jcb;
		this.v=v;
	}

	public void actionPerformed(ActionEvent ev){
		int indi=jcb.getSelectedIndex();
		jcb.removeItemAt(indi);
		String[] s=new String[2];
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
			DataOutputStream d=new DataOutputStream(new FileOutputStream(fi));
			for(int i=0;i<v.size();i++){
				if(i==indi)
					continue;
				s=(String[])v.get(i);
				d.writeChars(s[0]);
				d.writeChar(' ');
				d.writeChars(s[1]);
				d.writeChar('\n');}
			d.flush();
			d.close();
		}
		catch(IOException ex){
			Dialogo d=new Dialogo("Errore","EraseCod in scrittura");
			d.show();
		}
	}
}