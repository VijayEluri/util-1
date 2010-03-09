/*Questa servira per cambiare l'ascoltatore
 *del textfield tra input e output*/
import java.awt.event.*;
import javax.swing.*;

public class CalcoRTL implements ActionListener{
	
	private AbstractButton[] jrb;
	private JTextField txt,g,b;
	
	public CalcoRTL(JTextField txt,JTextField g,JTextField b,AbstractButton[] jrb,JRadioButton in){
		this.jrb=jrb;
		this.txt=txt;
		this.g=g;
		this.b=b;
	}
	
	public CalcoRTL(AbstractButton[] jrb){
		this.jrb=jrb;
	}
	
	public void actionPerformed(ActionEvent eve){
		txt.setText("");
		/*if(eve.getSource().equals(in))
			for(int i=0;i<4;i++)
					jrb[i].setEnabled(false);
		else*/
			action(g.getText()+b.getText());
	}
	
	public void action(String s){
			int a[]={2,8,10,16};
			for(int i=0;i<4;i++){
				if(s.length()<a[i])
					jrb[i].setEnabled(false);
				else
					jrb[i].setEnabled(true);}
	}
}