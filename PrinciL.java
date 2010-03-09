/**-------------------------
*Versione aggiornata.
*Da inserire in 666 1.0 final version.
*/
import javax.swing.*;
import java.awt.event.*;
import util.*;

public class PrinciL implements ActionListener{

	public int n;
	private JFrame f,tov;
	private JTextArea out;
	private JTextField in;
	private JLabel num,cod;
	private JCheckBoxMenuItem cb;
	private int max;
	private JButton b;

	public PrinciL(JTextField in,int max,JFrame f,JFrame tov,JLabel num){
	this.in=in;
	this.max=max;
	this.num=num;
	this.f=f;
	this.tov=tov;}

	public PrinciL(JCheckBoxMenuItem cb,JButton b,JTextField in,JTextArea out,JLabel cod,JLabel num){
		this.in=in;
		this.b=b;
		this.cb=cb;
		this.out=out;
		this.cod=cod;
		this.num=num;}

	public void actionPerformed(ActionEvent a){
		if(a.getSource()==b){
			if(!in.getText().equals("")){
				if(Integer.parseInt(num.getText())<2){
					Dialogo d=new Dialogo("Errore","Prima imposta un codice\n\nPer evitare questo errore\nsalva la sessione quando esci");
					d.show();
				}
				else{
					Util sei=new Util(cod,num);
					String str=sei.scr(in.getText(),cb.isSelected());
					out.append(str+"\n");
					in.setText("");
					in.requestFocus();
				}
			}
		}
		else{
			try{n=Integer.parseInt(in.getText());
				if(n<2 || n>max)
					throw new IllegalArgumentException();
				else{
					f.setVisible(false);
					JTextField field=(JTextField)a.getSource();
					field.select(0,2);
					field.requestFocus();
					num.setText(""+n);
					if(tov!=null){
						tov.setVisible(true);}}
			}
		catch(Exception e){
			in.setText("Non hai inserito un numero valido");
			in.select(0,33);
			}
		}
	}
}