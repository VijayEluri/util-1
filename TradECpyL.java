import javax.swing.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class TradECpyL implements ActionListener{

	private boolean b,g=true;
	private JTextArea in,out;
	private JLabel cod,num;

	public TradECpyL(JTextArea in,JTextArea out,boolean b/*,boolean g*/,JLabel cod,JLabel num){
		this.b=b;
		//this.g=g;
		this.in=in;
		this.out=out;
		this.cod=cod;
		this.num=num;
	}

	public void actionPerformed(ActionEvent e){
		Util sei;
		StringTokenizer toki=new StringTokenizer(in.getText());
		sei=new Util(cod,num);
		if(b){
			if(g){
				while(toki.hasMoreTokens())
					out.append(""+sei.scr(toki.nextToken("\n"))+"\n");
			}
			else{

			}
		}
		else{
			String temp=toki.nextToken(" ");
			while(toki.hasMoreTokens()){
				temp=toki.nextToken("\n");
				if(temp.indexOf(' ')==0||temp.indexOf('\n')==0)
					temp=temp.substring(1);
				String uno=temp;
				while(uno.length()!=0){
					out.append(""+sei.leggiC(uno.substring(0,uno.indexOf(' '))));
					if(uno.indexOf(' ')!=uno.lastIndexOf(' '))
						uno=uno.substring(uno.indexOf(' ')+1);
					else
						break;}
				out.append("\n");
				}
			}
	}
}
