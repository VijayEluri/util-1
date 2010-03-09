import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import util.Dialogo2;
import util.ExampleFileFilter;

public class Load extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5847806971901766053L;
	protected JTextComponent area;
	protected JFileChooser chooser;
	protected JFrame f;
	protected String[] s;

	public Load(JTextComponent area,JFrame f,String[] s){
		this.area=area;
		this.f=f;
		this.s=s;
		chooser=new JFileChooser();
		chooser.setAcceptAllFileFilterUsed(false);
		for(int i=0;i<s.length;i++)
			chooser.addChoosableFileFilter(new ExampleFileFilter(s[i], "File "+s[i]));
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setDialogTitle("Apri");
	}
	public Load(JTextComponent area,String[] s){
		this(area,null,s);
	}

	public Load(){
		super();
	}

	public void actionPerformed(ActionEvent eve){
		int retval = chooser.showOpenDialog(this);
		if (retval == JFileChooser.APPROVE_OPTION) {
			area.setText(getText(chooser.getSelectedFile()));
		}
		if(f!=null)
			f.pack();
	}

	public static void getText(File f,JTextComponent in){
		Load l=new Load();
		in.setText(l.getText(f));
	}

	public String getText(File f){
		System.out.println("Load");
		FileReader input=null;
		String ret="";
		try{
			input=new FileReader(f);
		}
		catch(IOException exce){
			Dialogo2 d=new Dialogo2("Errore","File Non Trovato\nMa xchè se ho usato il FileChooser?\nchissà");
			d.setVisible(true);
		}
		BufferedReader buff=new BufferedReader(input);
		try{
			String temp;
			while(buff.ready()){
				temp=buff.readLine();
				ret+=temp+"\n";
			}
		}
		catch(IOException dioDog){
			Dialogo2 d=new Dialogo2("Errore di Lettura","Boh, non sò cosa è successo\nProva a chiudere e riAprire");
			d.setVisible(true);
		}
		return ret;
	}
}