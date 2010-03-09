import java.awt.event.*;
import javax.swing.*;

import util.*;

public class ChangeBaseL implements ActionListener{
	
	private JTextField cha,num,in;
	private MyF f;
	private JFrame F;
	private JButton b;
	private JLabel codice,numero;
	private Codici c;
	private CalcoRTL cal;
	private JRadioButtonMenuItem jrb;
	
	public ChangeBaseL(JButton b,JTextField num,JTextField cha,MyF f,JLabel codici,JLabel numero,JFrame F,JRadioButtonMenuItem[] jrb){
		this(num,cha,null,f);
		this.numero=numero;
		this.b=b;
		this.jrb=jrb[4];
		cal=new CalcoRTL(jrb);
		this.F=F;
		this.codice=codici;}
	
	public ChangeBaseL(JTextField num,JTextField cha,JTextField in,MyF f){
		this.cha=cha;
		this.num=num;
		this.f=f;
		this.in=in;
		}
		
	public void actionPerformed(ActionEvent e){
		String s=cha.getText();
		int n=Integer.parseInt(num.getText());
		if(b==e.getSource()){
			if(n>1){
				c=new Codici();
				String inputValue = JOptionPane.showInputDialog("Nomina questo set di caratteri");
				if(inputValue!=null)
					if(!inputValue.equals("")){
						c.f(inputValue,s);
						codice.setText(s.substring(0,n));
						numero.setText(""+n);
						jrb.setSelected(true);
						cal.action(codice.getText());
						F.pack();
						f.setVisible(false);}}
			else{
				Dialogo d=new Dialogo("Errore","Il set minimo di caratteri non può essere più corto di 2",null,f);
				d.show();
			}
		}
		else{
			if(n>0){
				char[] arr=s.toCharArray();
				s="";
				n--;
				for(int i=0;i<n;i++){
					s=""+s+arr[i];}
				num.setText(""+n);
				cha.setText(s);
				in.requestFocus();
			}
		}
	}
}