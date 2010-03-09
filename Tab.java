/*
 * Tab.java
 *
 * Created on 18 aprile 2004, 14.38
 */

/**
 *
 * @author  Davide
 */
import javax.swing.*;
import java.awt.event.*;
import util.*;

public class Tab extends JPanel implements ActionListener{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 3400475947638641890L;
	private JScrollPane cod=null,str=null;
    private String head[]=new String[16];
    
    public Tab() {
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        for(int i=0;i<head.length;i++)
            head[i]=Integer.toString(i, 16);
        Object o[][]=new Object[2][32];
        cod=new JScrollPane(new JTable(o,head));
        str=new JScrollPane(new JTable(o,head));
        add(cod);add(str);
    }
    
    public void actionPerformed(ActionEvent ae){}
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyF f=new MyF("Prova di JTable");
        f.getContentPane().add(new Tab());
        f.pack();
        f.setVisible(true);
    }
    
}
