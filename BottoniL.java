import java.awt.event.*;
import javax.swing.*;

public class BottoniL implements ActionListener{
	
	private PrinciL ascolto;
	private ScrL liste;
	private JTextArea out;
	private JTextField in;
	private boolean bool;
	private JLabel cod,num;
	private JButton b;
	private JFrame f;
	private JCheckBoxMenuItem cb;
	
	public BottoniL(JCheckBoxMenuItem cb,JTextField in,JTextArea out,JButton b,boolean bool,JLabel cod,JLabel num,JFrame f){
		this.b=b;
		this.cb=cb;
		this.f=f;
		this.cod=cod;
		this.num=num;
		this.in=in;
		this.bool=bool;
		this.out=out;
		}
		
	public void actionPerformed(ActionEvent e){
        b.setEnabled(true);
        in.setEditable(true);
        out.setText("");
        in.setText("");
        in.requestFocus();
        ActionListener[] action=b.getActionListeners();
        for(int i=0;i<action.length;i++){
        	try{b.removeActionListener(action[i]);}
        	catch(Exception ex){}}
		ascolto=new PrinciL(cb,b,in,out,cod,num);
		liste=new ScrL(cb,in,out,cod,num,f);
		if(bool)
			b.addActionListener(liste);
		else
			b.addActionListener(ascolto);
			
		}
	}
	