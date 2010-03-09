import java.awt.*;
import java.io.*;
import util.*;
import javax.swing.*;
import java.awt.event.*;

public class StampaListener implements ActionListener{
	
	private JTextArea area;
	private String nssm;
	private Component dove;
	private PrintWriter fuori;
	private JFileChooser chooser;
	private JLabel cod,num;
	private boolean bool;
	
	public StampaListener(JTextArea area,Component dove,JLabel cod,JLabel num,boolean bool){
		this.area=area;
		this.dove=dove;
		this.bool=bool;
		this.cod=cod;
		this.num=num;
		}
		
	public StampaListener(JTextArea area,Component dove,JLabel cod,JLabel num){
		this(area,dove,cod,num,false);}
		
	public void actionPerformed(ActionEvent e){
		chooser=new JFileChooser();
		if(bool){
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.addChoosableFileFilter(new ExampleFileFilter("jna", "File JNA"));}
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		int retval = chooser.showSaveDialog(dove);
		if (retval == JFileChooser.APPROVE_OPTION) {
		try{
			if(bool&&!chooser.getSelectedFile().getName().endsWith(".jna")){
				fuori=new PrintWriter(new FileWriter(chooser.getSelectedFile().getAbsolutePath()+".jna"),true);}
			else
				fuori=new PrintWriter(new FileWriter(chooser.getSelectedFile()),true);}
		catch(IOException ec){Dialogo d=new Dialogo("Errore","Problemi di stampa\n--------\nSi consiglia di riavviare il programma.");
		d.show();}
		nssm=area.getText();
		if(fuori!=null){
			fuori.print(Integer.parseInt(num.getText()));
			fuori.print("|"+cod.getText()+" ");
			fuori.print(nssm);
			fuori.flush();}}}
	}