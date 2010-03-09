/**
*-------------------------
*In caso di sessione non salvata al primo cambio di codice l'area deve tornare grigia
*e il menu deve rimanere disabilitato fino a quel momento
*-------------------------*/
import javax.swing.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import util.*;

public class PriciPane extends JPanel implements Externalizable,ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7456654439967239289L;
	private JButton b,trn,cpy;
	private JTextField in;
	private JTextArea out;
	private JLabel codice,numero;
	private JRadioButton scr,dir,nulla;
	private JRadioButtonMenuItem[] jrbmi;
	private static JCheckBoxMenuItem comp;
	private ButtonGroup gnum,bot;
	private JMenuBar menu;
	private JMenu B,azioni,file,proprieta,M;
	private JMenuItem load,calco,gnu,shor,co,salva,chiudi,code,warning,about;
	private Box radio,riga,ac,perc,oriz;
	private JScrollPane scroll;
	private JFrame f;

	public static boolean isSel(){
		return comp.isSelected();
	}

	public PriciPane(JFrame f){
		this.f=f;
		URL url=null;
		out=new JTextArea(7,20);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		out.setEditable(false);
		out.setBackground(Color.red);
		codice=new JLabel("codice non immesso");
		numero=new JLabel("0");

	//Inizio menu
		menu=new JMenuBar();

		M=new JMenu("Help");
		about=new JMenuItem("About");
		shor=new JMenuItem("Short's cut");
		url=getClass().getResource("resources/image/help.gif");
		if(url!=null)
			warning=new JMenuItem("Guida",new ImageIcon(url));
		url=getClass().getResource("resources/image/mbsmall.gif");
		if(url!=null)
			co=new JMenuItem("Company",new ImageIcon(url));
		about.addActionListener(new HelpL(0,f));
		warning.addActionListener(new HelpL(1,f));
		shor.addActionListener(new HelpL(2,f));
		co.addActionListener(new HelpL(3,f));
		M.add(warning);
		M.add(about);
		M.add(shor);
		M.add(co);

		azioni=new JMenu("Strumenti");
		azioni.setMnemonic('E');
		url=getClass().getResource("resources/image/cal.gif");
		if(url!=null)
			calco=new JMenuItem("Data Conversion",new ImageIcon(url));
		azioni.add(calco);
		calco.addActionListener(new Calcolo(codice,numero));

		proprieta=new JMenu("Proprietà");
		proprieta.setMnemonic('P');
		B=new JMenu("Cambia base");
		jrbmi=new JRadioButtonMenuItem[5];
		jrbmi[0]=new JRadioButtonMenuItem("Binario");
		jrbmi[1]=new JRadioButtonMenuItem("Ottale");
		jrbmi[2]=new JRadioButtonMenuItem("Standard");
		jrbmi[3]=new JRadioButtonMenuItem("Esadecimale");
		jrbmi[4]=new JRadioButtonMenuItem("Definisci nuova base",true);
		Reset res=new Reset(out);
		ChangeBase cb=new ChangeBase(jrbmi,numero,codice);
		jrbmi[0].addActionListener(res);
		jrbmi[1].addActionListener(res);
		jrbmi[2].addActionListener(res);
		jrbmi[3].addActionListener(res);
		jrbmi[4].addActionListener(res);
		jrbmi[0].addActionListener(cb);
		jrbmi[1].addActionListener(cb);
		jrbmi[2].addActionListener(cb);
		jrbmi[3].addActionListener(cb);
		jrbmi[4].addActionListener(new MenuL(codice,numero));
		B.add(jrbmi[0]);
		B.add(jrbmi[1]);
		B.add(jrbmi[2]);
		B.add(jrbmi[3]);
		B.add(jrbmi[4]);
		gnum=new ButtonGroup();
		gnum.add(jrbmi[0]);
		gnum.add(jrbmi[1]);
		gnum.add(jrbmi[2]);
		gnum.add(jrbmi[3]);
		gnum.add(jrbmi[4]);
		code=new JMenuItem("Cambia set di Caratteri");
		code.addActionListener(new SelezionaCodice(codice,numero,f,jrbmi));
		code.addActionListener(res);
		comp=new JCheckBoxMenuItem("Completamento di cifra",false);
		comp.addActionListener(res);//Da aggiungere le condizioni in giro
		proprieta.add(B);
		proprieta.add(code);
		proprieta.add(comp);

		file=new JMenu("File");
		file.setMnemonic('F');
		url=getClass().getResource("resources/image/new.gif");
		if(url!=null)
			gnu=new JMenuItem("Nuovo",new ImageIcon(url));
		url=getClass().getResource("resources/image/save.gif");
		if(url!=null){
			load=new JMenuItem("Carica",new ImageIcon(url));
			salva=new JMenuItem("Salva",new ImageIcon(url));}
		url=getClass().getResource("resources/image/empty.gif");
		if(url!=null)
			chiudi=new JMenuItem("Salva sezione e Chiudi",new ImageIcon(url));
		chiudi.addActionListener(this);
		file.add(gnu);
		file.add(load);
		file.add(salva);
		file.add(chiudi);

		menu.add(file);
		menu.add(azioni);
		menu.add(proprieta);
		menu.add(M);
		//fine menu

		//Inizio bottoni
		bot=new ButtonGroup();
		nulla=new JRadioButton("null");
		url=getClass().getResource("resources/image/dir.gif");
		if(url!=null){
			dir=new JRadioButton(new ImageIcon(url));
			dir.setPressedIcon(new ImageIcon(url));}
		url=getClass().getResource("resources/image/dira.gif");
		if(url!=null)
			dir.setRolloverIcon(new ImageIcon(url));
		url=getClass().getResource("resources/image/scr.gif");
		if(url!=null){
			scr=new JRadioButton(new ImageIcon(url));
			scr.setPressedIcon(new ImageIcon(url));}
		url=getClass().getResource("resources/image/scra.gif");
		if(url!=null)
			scr.setRolloverIcon(new ImageIcon(url));
		url=getClass().getResource("resources/image/tica.gif");
		if(url!=null){
			scr.setRolloverSelectedIcon(new ImageIcon(url));
			dir.setRolloverSelectedIcon(new ImageIcon(url));}
		url=getClass().getResource("resources/image/tic.gif");
		if(url!=null){
			scr.setSelectedIcon(new ImageIcon(url));
			dir.setSelectedIcon(new ImageIcon(url));}
		dir.setToolTipText("Traduzione Diretta");
		dir.setBorderPainted(false);
		dir.setFocusPainted(false);
		dir.setContentAreaFilled(false);
		scr.setToolTipText("Scrivere");
		scr.setBorderPainted(false);
		scr.setFocusPainted(false);
		scr.setContentAreaFilled(false);
		bot.add(nulla);
		bot.add(scr);
		bot.add(dir);

		url=getClass().getResource("resources/image/trn.gif");
		if(url!=null){
			trn=new JButton(new ImageIcon(url));
			trn.setPressedIcon(new ImageIcon(url));}
		url=getClass().getResource("resources/image/trna.gif");
		if(url!=null)
			trn.setRolloverIcon(new ImageIcon(url));
		url=getClass().getResource("resources/image/cpy.gif");
		if(url!=null){
			cpy=new JButton(new ImageIcon(url));
			cpy.setPressedIcon(new ImageIcon(url));}
		url=getClass().getResource("resources/image/cpya.gif");
		if(url!=null)
			cpy.setRolloverIcon(new ImageIcon(url));
		trn.setToolTipText("Decodifica un file");
		Insets inse=new Insets(0,0,0,0);
		trn.setMargin(inse);
		trn.setBorderPainted(false);
		trn.setFocusPainted(false);
		trn.setContentAreaFilled(false);
		cpy.setToolTipText("Codifica un file");
		cpy.setMargin(inse);
		cpy.setBorderPainted(false);
		cpy.setFocusPainted(false);
		cpy.setContentAreaFilled(false);

		oriz=new Box(BoxLayout.X_AXIS);
		radio=new Box(BoxLayout.X_AXIS);
		radio.add(scr);
		radio.add(dir);
		radio.add(Box.createHorizontalStrut(8));
		radio.add(cpy);
		radio.add(trn);
		//Fino qui ho dato un po' d'ordine
		b=new JButton("Start");
		in=new JTextField(24);
		gnu.addActionListener(res);
		load.addActionListener(res);
		String[] str={"jna"};
		load.addActionListener(new LoadEx(out,codice,numero,f,str,scr));
		salva.addActionListener(new StampaListener(out,this,codice,numero,true));
		in.setEditable(false);
		b.setEnabled(false);
		scroll=new JScrollPane(out);
		perc=new Box(BoxLayout.Y_AXIS);
		ac=new Box(BoxLayout.Y_AXIS);
		riga=new Box(BoxLayout.X_AXIS);
		ac.setMaximumSize(new Dimension(800,40));
		BottoniL rad=new BottoniL(comp,in,out,b,false,codice,numero,f);
		BottoniL radt=new BottoniL(comp,in,out,b,true,codice,numero,f);
		scr.addActionListener(rad);
		dir.addActionListener(radt);
		trn.addActionListener(new NMyP(in,b,false,codice,numero,nulla));
		cpy.addActionListener(new NMyP(in,b,true,codice,numero,nulla));
		scr.setMnemonic('S');
		dir.setMnemonic('D');
		trn.setMnemonic('T');
		cpy.setMnemonic('L');
		b.setMnemonic('\n');
		M.setMnemonic('H');
		oriz.add(codice);
		oriz.add(Box.createHorizontalStrut(100));
		oriz.add(numero);
		riga.add(in);
		riga.add(Box.createHorizontalStrut(4));
		riga.add(b);
		perc.add(oriz);
		perc.add(riga);
		f.setJMenuBar(menu);
		add(Box.createVerticalStrut(5));
		ac.add(radio);
		ac.add(Box.createVerticalStrut(20));
		ac.add(perc);
		add(ac);
		add(Box.createVerticalStrut(30));
		add(scroll);
	}

	public void actionPerformed(ActionEvent event){
		try{
			File f=new File("save.dat");
			this.writeExternal(new ObjectOutputStream(new FileOutputStream(f)));
			System.out.println("\nSalvataggio riuscito\nBye Bye");
			System.exit(0);
		}
		catch(IOException exce){
			System.out.println(exce);
			Dialogo dia=new Dialogo("Errore","Salvataggio dei dati non riuscito");
			dia.show();
		}
	}

	public void writeExternal(ObjectOutput ot) throws IOException{
		try{
			ot.writeInt(Integer.parseInt(numero.getText()));
			}
		catch(NumberFormatException num){
			Dialogo dia=new Dialogo("Errore","Salvataggio dei dati non riuscito\nkissà xchè");
			dia.show();}
		ot.writeChars(codice.getText());
		ot.writeChar('\u2909');
		ot.writeChars(out.getText());
		ot.writeChar('\u2909');
		ActionListener[] action=b.getActionListeners();
		if(action.length!=0)
			ot.writeBoolean(action[0].toString().substring(0,action[0].toString().indexOf('@')).equals("PrinciL"));
		else
			ot.writeBoolean(true);
		ot.writeBoolean(comp.getState());
		for(int i=0;i<4;i++)
			ot.writeBoolean(jrbmi[i].isEnabled());
		ot.flush();
		ot.close();
	}

	public void readExternal(ObjectInput entro)throws IOException,ClassNotFoundException{
		String cod="";
		String testo="";
		int num=entro.readInt();
		char i=entro.readChar();
		while(i!='\u2909'){
			cod=cod+i;
			i=entro.readChar();}
		i=entro.readChar();
		while(i!='\u2909'){
			testo=testo+i;
			i=entro.readChar();}
        codice.setText(cod);
		numero.setText(""+num);
        out.setBackground(Color.lightGray);
        b.setEnabled(true);
        in.setEditable(true);
        if(entro.readBoolean()){
        	b.addActionListener(new PrinciL(comp,b,in,out,codice,numero));
        	scr.setSelected(true);}
        else{
        	b.addActionListener(new ScrL(comp,in,out,codice,numero,f));
        	dir.setSelected(true);}
        out.setText(testo);
        comp.setState(entro.readBoolean());
        for(int j=0;j<4;j++)
        	jrbmi[j].setEnabled(entro.readBoolean());
        entro.close();
	}
}
