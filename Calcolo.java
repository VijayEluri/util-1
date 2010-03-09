/*
 * Calcolo.java
 *
 * Created on 6 marzo 2003, 18.56
 */

/**
 *sono ancora da fare gli ascoltatori per la calcolatrice e
 * quelli per i visualizatori*/

/**
 *
 * @author  SaThot
 */
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.MyF;
import util.Reset;

public class Calcolo extends javax.swing.JPanel implements ActionListener {

	private static final long serialVersionUID = -6449752306834685166L;
	private ButtonGroup group = null;
    private ButtonGroup bgalto = null;
    private static JRadioButton jrb[]=new JRadioButton[5];
    private JRadioButton in = null;
    private JRadioButton out = null;

    private Box[] box = null;

    private JLabel num,cod;

    private JLabel binL = null;
    private JLabel otaL = null;
    private JLabel deciL = null;
    private JLabel esaL = null;
    private JLabel userL = null;

    private JTextField binF = null;
    private JTextField otaF = null;
    private JTextField deciF = null;
    private JTextField esaF = null;
    private JTextField userF = null;

    static private JTextField codG = null;
    static private JTextField codB = null;
    static private JTextField code = null;

    private JTextField operazioni = null;
    private MyF f;

    /** Creates a new instance of Calcolo */
    public Calcolo(){
    	this(new JLabel("0123456789abcdefg"),new JLabel("10"));}

    public Calcolo(JLabel cod,JLabel num){

		this.cod=cod;
		this.num=num;
		f=new MyF("Calcolatrice",ClassLoader.getSystemResource("logo.gif"),false);
		Container c=f.getContentPane();

        box=new Box[12];

        group=new ButtonGroup();
        box[0]=new Box(BoxLayout.Y_AXIS);
        jrb[0]=new JRadioButton("Binario");
        box[0].add(jrb[0]);
        group.add(jrb[0]);
        jrb[1]=new JRadioButton("Ottale");
        box[0].add(jrb[1]);
        group.add(jrb[1]);
        jrb[2]=new JRadioButton("Decimale");
        box[0].add(jrb[2]);
        group.add(jrb[2]);
        jrb[3]=new JRadioButton("Esadecimale");
        box[0].add(jrb[3]);
        group.add(jrb[3]);
        jrb[4]=new JRadioButton("Personalizato");
        box[0].add(jrb[4]);
        group.add(jrb[4]);
        jrb[4].setEnabled(true);
        CalcoRTL crtl=new CalcoRTL(jrb);
        crtl.action(cod.getText());

        box[11]=new Box(BoxLayout.X_AXIS);
        bgalto=new ButtonGroup();
//
        java.net.URL url=getClass().getResource("resources/image/dir.gif");
		if(url!=null){
			out=new JRadioButton(new ImageIcon(url));
			out.setPressedIcon(new ImageIcon(url));}
		url=getClass().getResource("resources/image/dira.gif");
		if(url!=null)
			out.setRolloverIcon(new ImageIcon(url));
		url=getClass().getResource("resources/image/scr.gif");
		if(url!=null){
			in=new JRadioButton(new ImageIcon(url),true);
			in.setPressedIcon(new ImageIcon(url));}
		url=getClass().getResource("resources/image/scra.gif");
		if(url!=null)
			in.setRolloverIcon(new ImageIcon(url));
		url=getClass().getResource("resources/image/tica.gif");
		if(url!=null){
			in.setRolloverSelectedIcon(new ImageIcon(url));
			out.setRolloverSelectedIcon(new ImageIcon(url));}
		url=getClass().getResource("resources/image/tic.gif");
		if(url!=null){
			in.setSelectedIcon(new ImageIcon(url));
			out.setSelectedIcon(new ImageIcon(url));}
//
        in.setHorizontalTextPosition(SwingConstants.LEFT);
        out.setToolTipText("Codifica -> Numero");
		out.setBorderPainted(false);
		out.setFocusPainted(false);
		out.setContentAreaFilled(false);
		in.setToolTipText("Numero -> Codifica");
		in.setBorderPainted(false);
		in.setFocusPainted(false);
		in.setContentAreaFilled(false);
        bgalto.add(in);
        bgalto.add(out);
        box[11].add(in);
        box[11].add(out);

        box[1]=new Box(BoxLayout.X_AXIS);
        codG=new JTextField("",12);
        codG.setEditable(false);
        codG.setHorizontalAlignment(JTextField.RIGHT);
        box[1].add(codG);
        codB=new JTextField("",12);
        codB.setEditable(false);
        codB.setForeground(Color.red);
        box[1].add(codB);
        box[1].add(Box.createHorizontalStrut(5));
        code=new JTextField("",6);
        code.setEditable(false);
        code.setForeground(Color.blue);
        CalcoRL rl=new CalcoRL(jrb);
        for(int i=0;i<jrb.length-1;i++)
        	jrb[i].addActionListener(rl);
        box[1].add(code);

        box[2]=new Box(BoxLayout.X_AXIS);
        box[3]=new Box(BoxLayout.Y_AXIS);
        binL=new JLabel("Binario");
        binF=new JTextField("",5);
        binF.setEditable(false);
        box[3].add(binL);
        box[3].add(binF);
        box[2].add(box[3]);
        box[4]=new Box(BoxLayout.Y_AXIS);
        otaL=new JLabel("Ottale");
        otaF=new JTextField("",5);
        otaF.setEditable(false);
        box[4].add(otaL);
        box[4].add(otaF);
        box[2].add(box[4]);
        box[5]=new Box(BoxLayout.Y_AXIS);
        deciL=new JLabel("Decimale");
        deciF=new JTextField("",5);
        deciF.setEditable(false);
        box[5].add(deciL);
        box[5].add(deciF);
        box[2].add(box[5]);
        box[6]=new Box(BoxLayout.Y_AXIS);
        esaL=new JLabel("Esadecimale");
        esaF=new JTextField("",5);
        esaF.setEditable(false);
        box[6].add(esaL);
        box[6].add(esaF);
        box[2].add(box[6]);
        box[7]=new Box(BoxLayout.Y_AXIS);
        userL=new JLabel("Personale");
        userF=new JTextField("",5);
        userF.setEditable(false);
        box[7].add(userL);
        box[7].add(userF);
        box[2].add(box[7]);

        box[8]=new Box(BoxLayout.Y_AXIS);
        box[8].add(box[11]);
        box[8].add(box[1]);
        operazioni=new JTextField("",25);
        operazioni.setHorizontalAlignment(JTextField.RIGHT);
        operazioni.getDocument().addDocumentListener(new CalcoDocL(2,binF,codG,codB,in,jrb));
        operazioni.getDocument().addDocumentListener(new CalcoDocL(10,deciF,codG,codB,in,jrb));
        operazioni.getDocument().addDocumentListener(new CalcoDocL(16,esaF,codG,codB,in,jrb));
        operazioni.getDocument().addDocumentListener(new CalcoDocL(8,otaF,codG,codB,in,jrb));
        operazioni.getDocument().addDocumentListener(new CalcoDocL(userF,codG,codB,in,jrb));
        code.addActionListener(new CalcoL(codG,codB,code,jrb,in,operazioni));
        Reset res=new Reset(operazioni);
        for(int i=0;i<5;i++)
        	jrb[i].addActionListener(res);
        jrb[4].addActionListener(new CalcoL(codG,codB,code,jrb,in,operazioni));
        CalcoRTL rtl=new CalcoRTL(operazioni,codG,codB,jrb,in);
        in.addActionListener(rtl);
        out.addActionListener(rtl);
        box[8].add(operazioni);

        box[8].add(Box.createVerticalStrut(25));

        box[8].add(box[2]);
        box[9]=new Box(BoxLayout.X_AXIS);
        box[9].add(box[8]);
        box[9].add(box[0]);
        box[10]=new Box(BoxLayout.Y_AXIS);
        box[10].add(box[9]);
        box[10].add(Box.createVerticalStrut(10));
        add(box[10]);

        c.add(this);
		f.pack();
    }

    /**
     * @param args the command line arguments
     */
	public void actionPerformed(ActionEvent eve){
		String codi=cod.getText();
		jrb[4].setSelected(true);
		code.setText(num.getText());
		codG.setText(codi);
		codB.setText("");
		change(code);
		f.setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Calcolo Start");
        JFrame frame=new JFrame("Calcolatrice 1.0");
		Container c=frame.getContentPane();
		JPanel p=new JPanel();
		JButton b=new JButton("Start");
		b.addActionListener(new Calcolo());
		p.add(b);
		c.add(p);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }

    private static boolean isGood(int min,int max,int nuovo) {
        if(nuovo>min&&max>=nuovo)
            return true;
        return false;
    }

    public static boolean change(String s,JTextField n){
    	codG.setText(s);
    	return change(n);
    }

    public static boolean change(JTextField n){
    	try{
			int N=Integer.parseInt(n.getText());
			String codi=codG.getText()+codB.getText();
			if(isGood(1,codi.length(),N)){
				codG.setText(codi.substring(0,N));
				codB.setText(codi.substring(N));}
			else throw new NumberFormatException();
		}
		catch(NumberFormatException ex){
			n.setText("N non valido");
			n.select(0,12);
			return false;
		}
		code.setText(n.getText());
		return true;
    }
}
