import javax.swing.*;
import java.awt.event.*;
import util.*;
import java.util.StringTokenizer;

public class ScrL implements ActionListener{
	
	private JTextField in;
	private JTextArea out;
	private JLabel cod,num;
	private JCheckBoxMenuItem cb;
	private JFrame f;
	private Util sei;
	
	public ScrL(JCheckBoxMenuItem cb,JTextField in,JTextArea out,JLabel cod,JLabel num,JFrame f){
		this.in=in;
		this.f=f;
		this.out=out;
		this.cod=cod;
		this.cb=cb;
		this.num=num;}
		
	public void actionPerformed(ActionEvent a){
		sei=new Util(cod,num);
		if(!in.getText().equals("")){
			if(!cb.isSelected()){
				StringTokenizer stream =new StringTokenizer(in.getText());
				while(stream.hasMoreTokens()){
					String s=stream.nextToken();
					effe(s);
				}
			}
			else{
				int i=sei.getMax().length();
				String s=in.getText();
				while(!s.equals("")){
					String temp=s.substring(0,i);
					s=s.substring(i);
					effe(temp);
				}
			}
			out.append("\n");
			in.setText("");
			in.requestFocus();
		}
	}
	
	private void effe(String s){
		try{
			char temp=sei.leggiC(s);
			out.append(""+temp);}
		catch(NumberFormatException nfe){
			Dialogo d=new Dialogo("Errore","Carattere non valido:"+s,null,f);
			d.show();
		}
	}
}