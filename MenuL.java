import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import util.*;

public class MenuL implements ActionListener{
	
	private JLabel codice,numero;
	private JFrame f;
	private JButton b;
	public MenuL(JLabel codice,JLabel numero){
		this(null,codice,numero,null,null,null);}
		
	public MenuL(JButton b,JLabel codice,JLabel numero,JFrame f,JFrame F,JRadioButtonMenuItem[] jrb){
		this.codice=codice;
		this.f=f;
		this.b=b;
		this.numero=numero;}
	
	public void actionPerformed(ActionEvent e){
		if(b==e.getSource()){
			f.setVisible(false);
		}
		else{
			MyF fr=new MyF("Immetti la nuova base");
			Container C=fr.getContentPane();
			Base b=new Base(fr,numero,codice);
			C.add(b);
			fr.pack();
			fr.setVisible(true);}}
		}