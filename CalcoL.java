/**
 *Questo lo avevo già
 *fatto, ma non sò dove
 *l'ho messo, forse l'ho
 *cancellato, chi lo sà;
 *percui sono stato obbligato
 *a rifarlo
 */
import java.awt.event.*;
import javax.swing.*;

public class CalcoL implements ActionListener{
	
	private JRadioButton r,jrb[];
	private JTextField txt,in,out,n;
	
	public CalcoL(JTextField in,JTextField out,JTextField n,JRadioButton[] jrb,JRadioButton r,JTextField txt){
		this.txt=txt;
		this.in=in;
		this.jrb=jrb;
		this.r=r;
		this.out=out;
		this.n=n;
	}
	
	public void actionPerformed(ActionEvent eva){
		if(eva.getSource().equals(n)){
			int a[]={2,8,10,16};
			txt.setEditable(true);
			txt.setText("");
			String s=in.getText()+out.getText();
			if(Calcolo.change(n)){
				n.setEditable(false);
				if(!r.isSelected()){
					for(int i=0;i<jrb.length-1;i++)
						if(s.length()>a[i])
							jrb[i].setEnabled(true);}
				jrb[4].setEnabled(true);}
		}
		else{
			txt.setEditable(false);
			/*for(int i=0;i<jrb.length;i++)
				jrb[i].setEnabled(false);*/
			n.setEditable(true);}
	}

}