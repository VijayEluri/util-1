/**-------------------------
*Versione aggiornata.
*Da inserire in 666 1.3 final version.
*/
import javax.swing.event.*;
import javax.swing.*;

public class BaseL implements DocumentListener{
	
	private JTextField in,out,n;
	
	public BaseL(JTextField in,JTextField out,JTextField n){
		this.n=n;
		this.out=out;
		this.in=in;
		}
	
	public void insertUpdate(DocumentEvent e){
		int s=0;
		if(e.getDocument().getLength()<2){
			if(out.getText().indexOf(""+in.getText())<0&&!in.getText().equals(" ")){
				out.setText(out.getText()+in.getText());
				try{s=Integer.parseInt(n.getText());}
				catch(IllegalArgumentException d){System.out.println("ERRORE");}
				n.setText(""+(++s));}}
		else{
			char c=in.getText().charAt(1);
			if(out.getText().indexOf(""+c)<0&&c!=' '){
				out.setText(out.getText()+c);
				try{s=Integer.parseInt(n.getText());}
				catch(IllegalArgumentException d){System.out.println("ERRORE");}
				n.setText(""+(++s));}}
		in.select(0,1);}
	
	public void removeUpdate(DocumentEvent e){}
	
	public void changedUpdate(DocumentEvent e){}
}