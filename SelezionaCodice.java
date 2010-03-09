import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.awt.*;
import util.*;
import java.util.*;


/*Creare l'ascoltatore per del
 *cambiare il ruolo di pre "codice attuale"-><nome codice>*/

public class SelezionaCodice implements ActionListener{

	private JLabel num,cod;
	static private JFrame F;
	static private JRadioButtonMenuItem[] jrb;

	public SelezionaCodice(JLabel cod,JLabel num,JFrame F,JRadioButtonMenuItem[] jrb){
		this.num=num;
		SelezionaCodice.F=F;
		SelezionaCodice.jrb=jrb;
		this.cod=cod;}

	public static void main(String st[]){
		MyF frame=new MyF("Seleziona Codice");
		Container c=frame.getContentPane();
		JLabel s=new JLabel();
		s.setText("W la figa");
		JLabel i=new JLabel();
		i.setText("9");
		c.add(new Pannello(s,i,frame));
		frame.pack();
		frame.setVisible(true);}

	public void actionPerformed(ActionEvent eve){
		MyF frame=new MyF("Seleziona Codice");
		Container c=frame.getContentPane();
		Pannello pan=new Pannello(cod,num,frame);
		c.add(pan);
		frame.pack();
		frame.setVisible(true);}

	private static class Pannello extends JPanel implements ActionListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 952599973802280536L;
		private JComboBox combo;
		private JLabel num,cod;
		private JButton nuovo,done,del;
		private JTextField pre;
		private Box ver1,ver2,ori1,ori2;
		private JFrame f;
		private InputStream in;
		private Vector<String[]> v;

		public void actionPerformed(ActionEvent eve){
			if(eve.getSource().equals(combo)){
				String[] s=v.get(combo.getSelectedIndex());
				pre.setText(s[1]);
				done.setEnabled(true);
				del.setEnabled(true);
			}
			else{
				cod.setText((String)combo.getSelectedItem());
				num.setText(""+cod.getText().length());
				CalcoRTL c=new CalcoRTL(jrb);
				jrb[4].setSelected(true);
				c.action(cod.getText());
				f.setVisible(false);
				F.pack();
				}
			}

		public Pannello(JLabel cod,JLabel num,JFrame f){

			this.num=num;
			this.f=f;
			this.cod=cod;
			setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
			ver1=new Box(BoxLayout.Y_AXIS);
			ver2=new Box(BoxLayout.Y_AXIS);
			ori1=new Box(BoxLayout.X_AXIS);
			ori2=new Box(BoxLayout.X_AXIS);
			nuovo=new JButton("Aggiungi Nuovo");
			nuovo.addActionListener(new MenuL(nuovo,cod,num,f,F,jrb));
			done=new JButton("Fatto!");
			done.setEnabled(false);
			done.addActionListener(this);
			del=new JButton("Elimina");
			del.setEnabled(false);
			pre=new JTextField("Codice attuale: "+cod.getText(),12);
			pre.setEditable(false);
			combo=new JComboBox();
			v=new Vector<String[]>();
			try{
				File fi=new File("code.dat");
				if(fi.exists())
					in=new FileInputStream(fi);
				else
					in=getClass().getResourceAsStream("resources/code.dat");}
			catch(Exception e){
				Dialogo d=new Dialogo("Errore","Da controllare");
				d.show();}
			DataInputStream data=new DataInputStream(in);
			try{
				while(true){
					String[] s=new String[2];
					s[0]="";
					s[1]="";
					char i=data.readChar();
					int j=0;
					while(true){
						if(j==0&&i==' '){
							j++;
							i=data.readChar();
							continue;}
						if(i=='\n')
							break;
						s[j]=s[j]+i;
						i=data.readChar();}
				v.add(s);
				combo.addItem(s[0]);
				}
			}
			catch(EOFException exe){}
			catch(IOException exc){
				Dialogo d=new Dialogo("Errore di IO","Da controllare");
				d.show();}
			del.addActionListener(new EraseCod(combo,v));
			combo.addActionListener(this);
			ver1.add(pre);
			ver1.add(Box.createVerticalStrut(1));
			ver1.add(combo);
			ori1.add(nuovo);
			ori1.add(Box.createHorizontalStrut(20));
			ori2.add(done);
			ori2.add(del);
			ver2.add(ori1);
			ver2.add(Box.createVerticalStrut(1));
			ver2.add(ori2);
			add(ver1);
			add(ver2);
			}
		}
	}
