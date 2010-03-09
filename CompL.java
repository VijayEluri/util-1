import java.awt.event.*;
import javax.swing.*;
public class CompL implements ActionListener{
	public void actionPerformed(ActionEvent e){
		JCheckBoxMenuItem c=(JCheckBoxMenuItem)e.getSource();
		System.out.println(c.getState());
	}
}