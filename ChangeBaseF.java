import javax.swing.*;
import java.awt.*;
import util.*;

public class ChangeBaseF{

	public static void main(String s[]){
		JLabel l=new JLabel();
		l.setText("012345");
		JLabel n=new JLabel();
		n.setText("6");}

	public ChangeBaseF(JLabel codici,JLabel numero,JFrame F,JRadioButtonMenuItem[] jrb){
		/**Titolo della finestra*/
		MyF f=new MyF("Code");
		Container c=f.getContentPane();
		/**Panello da aggiungere*/
		ChangeBasePane p=new ChangeBasePane(f,codici,numero,F,jrb);
		c.add(p);
		f.pack();
		f.setVisible(true);}
	}