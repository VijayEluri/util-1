/**-------------------------
*Versione aggiornata.
*Da inserire in 666 1.0 final version.
*/
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Base extends JPanel{

	private static final long serialVersionUID = -106008339203443483L;
	private Box b;
	private JTextField t;
	public Base(JFrame f,JLabel num,JLabel cod){
		b=new Box(BoxLayout.Y_AXIS);
		int n=cod.getText().length();
		t=new JTextField("Numero da 2 a "+n,20);
		t.select(0,17);
		t.addActionListener(new PrinciL(t,n,f,null,num));
		b.add(t);
		add(b);}
	}