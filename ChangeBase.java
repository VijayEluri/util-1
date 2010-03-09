import javax.swing.*;
import java.awt.event.*;

public class ChangeBase implements ActionListener{

	private JLabel cod,num;
	private CalcoRTL crtl=null;
	private JRadioButtonMenuItem[] jrb;

	public ChangeBase(JRadioButtonMenuItem[] jrb,JLabel num,JLabel cod){
		this.jrb=jrb;
		crtl=new CalcoRTL(jrb);
		this.cod=cod;
		this.num=num;}

	public void actionPerformed(ActionEvent evento){
		int i,a[]={2,8,10,16};
		for(i=0;i<4;i++)
			if(evento.getSource().equals(jrb[i]))
				break;
		i=a[i];
		crtl.action(cod.getText());
		if(i<cod.getText().length())
			num.setText(""+i);}
	}