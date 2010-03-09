import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import util.MyF;

public class ChangeBasePane extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8917640062934206780L;
	/**qui vanno dichiarati i componenti*/
	private JTextField in,out,n;
	private JButton can,start;
	private Box indica,ver,o;
	public ChangeBasePane(MyF f,JLabel codici,JLabel numero,JFrame F,JRadioButtonMenuItem[] jrb){
		indica=new Box(BoxLayout.Y_AXIS);
		o=new Box(BoxLayout.X_AXIS);
		ver=new Box(BoxLayout.Y_AXIS);
		in=new JTextField(6);
		out=new JTextField(6);
		n=new JTextField(10);
		out.setText("");
		in.setText("");
		n.setText("0");
		out.setEditable(false);
		n.setEditable(false);
		in.getDocument().addDocumentListener(new BaseL(in,out,n));
		can=new JButton("Annulla");
		start=new JButton("Pronto");
		can.addActionListener(new ChangeBaseL(n,out,in,f));
		start.addActionListener(new ChangeBaseL(start,n,out,f,codici,numero,F,jrb));
		indica.add(in);
		indica.add(n);
		o.add(indica);
		o.add(Box.createHorizontalStrut(8));
		o.add(can);
		o.add(start);
		ver.add(o);
		ver.add(out);
		add(ver);
	}
}
