/**-------------------------
*2Do:
*1 Errori di visualizazione
*	1.0 TextArea									V
*	1.1 JRadioButton								V
*	1.2 Dialoghi
*		1.2.0 Inibire Frame							V
*	1.3 Ridimensionamento finestra					V
*2 Calcolatrice										V
*	2.0 Modificare Menu								V
*	2.1 Creare ascoltatori							V
*3 Funzione Riempimento								V
*	3.0 Gestione errori
*4 Lettura/Scrittura da file
*	4.0 Salvare/Caricare
*	4.1 Codifica File
*5 Aggiornare gli help
*6 Aggiornare Menu
*       6.0 Sostituire menu azioni con strumenti	V
*7 Eliminare ripetizioni finestre
*-------------------------*/
import java.awt.*;
import javax.swing.*;
import java.io.*;
import util.*;

public class Start{

	public static void main(String argv[]){
                MyF.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
		/*try {
       		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
       	}
       	catch (Exception e) {}*/
       	MyF f = new MyF("Asmodeo",ClassLoader.getSystemResource("resources/image/logo.gif"),true,true);
        ObjectInputStream pannello=null;
        File fi=new File("save.dat");
        if(fi.exists()){
		    try{
              pannello=new ObjectInputStream(new FileInputStream("save.dat"));}
		    catch(Exception e){
			    Dialogo d=new Dialogo("Errore","File corrotto\nRinstallare il File");
			    d.show();
			}
		}
		else{
          try{
            pannello=new ObjectInputStream(ClassLoader.getSystemClassLoader().getResourceAsStream("resources/save.dat"));}
            catch(Exception e){
                Dialogo d=new Dialogo("Errore","File corrotto\nRinstallare il File");
                d.show();
            }
		}
		Container c=f.getContentPane();
    	PriciPane p=new PriciPane(f);
    	try{
    		p.readExternal(pannello);}
    	catch(Exception exce){}
    	c.add(p);
    	f.addWindowListener( new End() );
    	f.pack();
    	System.out.println("St… per Partire");
    	f.setVisible(true);}
}
