import java.io.*;

public interface Code{
	/*Leggi classico da stringa codificata al char decodificata*/
	public char leggiC(String s);
	/*Leggi per i file da stringa a byte*/
	public byte[] leggiB(String s);
	/*Leggi per i numeri da stringa a int*/
	public int leggiN(String s);
	/*Leggi con tipo da stringa a Object\npuò essere un char,byte o un int*/
	public Object leggi(String s,int type);
	/*Scrivi da Stringa a Stringa*/
	public String scr(String s);
	/*Scrivi da InputFile a Stringa*/
	public String scr(InputStream in);
	/*Scrivi da int a Stringa*/
	public String scr(int i);
}