import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import util.*;

public class NMyP extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5231341777973065822L;
	private JTextArea sx,out;
	private JMenu menu = null;
	private JMenuItem open,close,pre,trad;
	private MyF frame = null;
	private Box verticale,pane,ori;
	private JScrollPane scroll,perga;
	private JLabel num,cod,mycod,mynum;
	private JFileChooser chooser;
	private boolean testo;
	private JCheckBox jrb;
	private JMenuBar jmb;
	private java.io.File file;
	/**
	 * Method main
	 *
	 *
	 * @param args
	 *
	 */
	public static void main(String[] args) {
		System.out.println("NMyP Start");
		JFrame f=null;
		if(args.length>0)
			f=new MyF(args[0]);
		else
			f=new MyF("Apri File");
		JPanel p=new JPanel();
		JButton b=new JButton("Start");
		b.addActionListener(new NMyP());
		p.add(b);
		f.getContentPane().add(p);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	/**
	 * Method NMyP
	 *
	 *
	 */
	public NMyP() {
		this(null,null,true,new JLabel("10"),new JLabel("0123456789"),null);
	}

	public NMyP(JTextField in,JButton b,boolean tasto,JLabel num,JLabel cod,JRadioButton nulla){
		this.cod=cod;
		this.num=num;
		mynum=new JLabel("0");
		mycod=new JLabel("null");
		this.testo=tasto;
		if(in!=null){
			in.setEditable(false);
			in.setText("");
			nulla.setSelected(true);
			b.setEnabled(false);}
		chooser=new JFileChooser();
		verticale=new Box(BoxLayout.Y_AXIS);
		pane=new Box(BoxLayout.X_AXIS);
		ori=new Box(BoxLayout.X_AXIS);
		out=new JTextArea("",10,35);
		sx=new JTextArea("",10,35);
		out.setEditable(false);
		sx.setEditable(false);
		scroll=new JScrollPane(sx);
		perga=new JScrollPane(out);
		menu=new JMenu("Azioni");
		menu.setMnemonic('A');
		java.net.URL url=getClass().getResource("resources/image/open.gif");
		if(url!=null)
			open=new JMenuItem("Apri",new ImageIcon(url));
		url=getClass().getResource("resources/image/open.gif");
		if(url!=null)
			open=new JMenuItem("Apri",new ImageIcon(url));
		url=getClass().getResource("resources/image/empty.gif");
		if(url!=null)
			close=new JMenuItem("Chiudi",new ImageIcon(url));
		close.addActionListener(this);
		url=getClass().getResource("resources/image/dir.gif");
		if(url!=null){
			pre=new JMenuItem("Anteprima",new ImageIcon(url));
			trad=new JMenuItem("Traduci",new ImageIcon(url));}

		trad.addActionListener(new StampaListener(out,this,this.cod,this.num,tasto));
		pre.addActionListener(new TradECpyL(sx,out,tasto,this.cod,this.num));
		jrb=new JCheckBox("Continuo");
		pre.addActionListener(new Reset(out));
		open.addActionListener(this);
		menu.add(open);
		menu.add(pre);
		menu.add(trad);
		menu.add(close);
		jmb=new JMenuBar();
		jmb.add(menu);
		pane.add(scroll);
		pane.add(perga);
		ori.add(mycod);
		ori.add(Box.createHorizontalStrut(10));
		ori.add(jrb);
		ori.add(Box.createHorizontalStrut(10));
		ori.add(mynum);
		verticale.add(pane);
		verticale.add(ori);
		add(verticale);
		frame=new MyF("Anteprima File");
		frame.setJMenuBar(jmb);
		frame.getContentPane().add(this);
		frame.pack();
	}

	/**
	 * Method actionPerformed
	 *
	 *
	 * @param e
	 *
	 */
	public void actionPerformed(ActionEvent e) {
		jrb.setSelected(PriciPane.isSel());
		mycod.setText(cod.getText());
		mynum.setText(num.getText());
		mynum.setForeground(Color.red);
		mycod.setForeground(Color.red);
		if(e.getSource().equals(close))
			frame.setVisible(false);
		else{
			sx.setText("");
			out.setText("");
			if(!testo){
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.addChoosableFileFilter(new ExampleFileFilter("jna", "File codificato autodecrittante"));
				chooser.addChoosableFileFilter(new ExampleFileFilter("kna", "File codificato puro"));}
			chooser.setDialogType(JFileChooser.OPEN_DIALOG);
			chooser.setDialogTitle("Apri");
			int retval = chooser.showOpenDialog(this);
			if (retval == JFileChooser.APPROVE_OPTION) {
				frame.setVisible(true);
				file=chooser.getSelectedFile();
				if(testo)
					Load.getText(file,sx);
				else
					sx.setText(LoadEx.getText(file,cod,num));
			}
		}
	}
}
