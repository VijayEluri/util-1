import javax.swing.*;

public class UtilL{

	private char[] car;
	//private int n;
	
	/**Crae l'arrey di caratteri dal file code*/
	public UtilL(JLabel cod,JLabel num){
		car=cod.getText().toCharArray();
		/*try{
			n=Integer.parseInt(num.getText());
			if(n<2 || n>car.length)
				throw new NumberFormatException();
		}
		catch(NumberFormatException ex){
			Dialogo d=new Dialogo("Errore Generale","Errore da UtilL");
			d.show();}*/
	}
	
	/**Trova i caratteri contenuti nel file di code*/
	public char Xes(int i){
		return car[i];}
	
	/**Trova gli indici dei caratteri contenuti nel file di code*/
	public int Xes(char c){
		int i;
		for(i=0;i<car.length;i++){
			if(car[i]==c)
				return i;}
		return i;}
}