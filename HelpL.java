/**
*Da aggiornare
*Da inserire in 666 1.3
*/
import java.awt.event.*;
import java.net.URL;

import util.*;
import javax.swing.*;

public class HelpL implements ActionListener{
	
	private int n;
	private JFrame f;

	public HelpL(int numeroHelp,JFrame f){
		n=numeroHelp;
		this.f=f;}

	public void actionPerformed(ActionEvent a){
		Dialogo d=null;
		URL u=null;
		u = ClassLoader.getSystemResource("resources/image/bdevil.gif");
		switch(n){
		case 0:{d=new Dialogo("About","Asmodeo\t1.0\nby MacroBug\nScritto da SaThot <dice@inwind.it>\n2005\\12\\28",u,f);break;}
		case 1:{d=new Dialogo("Guida","resources/guida.html",u,f);break;}
									//  ^^Da aggiornare
		case 2:{d=new Dialogo("ShortCuts","resources/sc.html",u,f);break;}
									//     ^^Da aggiornare ai nuovi menu
		case 3:{d=new Dialogo("MacroBug","MacroBug\nvisita il sito ufficiale\nhttp://dices.web.ctonet.it",u,f);break;}}
		d.show();
	}
}