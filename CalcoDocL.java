/*In scrittura i bottoni devono avere il codice intero
 **/
import java.util.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;

public class CalcoDocL implements DocumentListener{

	private int i;
	private JTextField cod,out,doc;
	private JRadioButton rb;
	private Util sei,nove;
	private JLabel set;

	public CalcoDocL(JTextField txt,JTextField g,JTextField b,JRadioButton rb,JRadioButton[] jrb){
		out=txt;
		cod=g;
		doc=b;
		this.rb=rb;
	}

	public CalcoDocL(int i,JTextField out,JTextField g,JTextField b,JRadioButton rb,JRadioButton[] jrb){
		this(out,g,b,rb,jrb);
		this.i=i;
	}

	public void insertUpdate(DocumentEvent e){
		set=new JLabel(cod.getText()+doc.getText());
		if(!(rb.isSelected()&&i>set.getText().length())){
			nove=new Util(new JLabel(cod.getText()),new JLabel(""+cod.getText().length()));//è il personale
			if(i==0) //è un personale
				sei=nove;
			else //Bottoni classici
				sei=new Util(set,new JLabel(""+i));
			Document d=e.getDocument();
			StringTokenizer stream=null;
			try{
				stream=new StringTokenizer(d.getText(0,d.getLength()));}
			catch(BadLocationException ble){out.setText("-----");}
			if(rb.isSelected()){
				String temp="";
				//while(stream.hasMoreTokens()){
					try{
						int t=nove.leggiN(stream.nextToken(),true);
						temp=temp+sei.scr(t);
					}
					catch(NumberFormatException nfe){
						temp="-----";
					}//}
					out.setText(temp);
			}
			else{
				//while(stream.hasMoreTokens()){
					try{
						int indi=sei.leggiN(stream.nextToken(),true);
						out.setText(""+indi);
					}
					catch(NumberFormatException nfe){
						out.setText("-----");
					}
				//}
			}
		}
		else
			out.setText("-----");
	}

	public void removeUpdate(DocumentEvent e){
		insertUpdate(e);
		if(e.getDocument().getLength()==0){
			out.setText("-----");}
	}
	public void changedUpdate(DocumentEvent e){
		insertUpdate(e);}

	public static void main(String argv[]){
		Calcolo.main(argv);
	}
}