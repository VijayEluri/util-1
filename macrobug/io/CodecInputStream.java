package macrobug.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.CharacterCodingException;

import static java.lang.Math.*;

public class CodecInputStream extends InputStream implements Codec{

	private char[] code;
	private InputStream in;
	public CodecInputStream(InputStream in,String s) {
		code=s.toCharArray();
		this.in=in;
	}
	public int getBase(){return code.length;}
	public int getValue(byte c) throws CharacterCodingException{
		int i,t=(c>0)?c:256+c;
		for(i=0;i<code.length;i++){
			if(code[i]==t)
				return i;
		}
		throw new CharacterCodingException();
	}
	public int getMax(){
		return (int)ceil(log(255)/log(getBase()));
	}
	public int read() throws IOException {
		byte b[]=new byte[getMax()];
		if(in.read(b)<0)
			return -1;
		int numero,ntmp,i,k;
		for(i=b.length-1,k=0,numero=0;i>=0;k++,i--){
			ntmp=getValue(b[i]);
			numero+=ntmp*pow(getBase(),k);
		}
		return numero;
	}
	public static void main(String s[]){
		try {
			CodecInputStream cis=new CodecInputStream(new FileInputStream("Prova.txt"),"|!\"£$%&/()=?^ì\'0987654321\\*§°ç><òàù+");
			int i=cis.read();
			while(i>0)
			{
				System.out.print((char)i);
				i=cis.read();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
