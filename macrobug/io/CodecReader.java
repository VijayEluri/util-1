package macrobug.io;

import static java.lang.Math.ceil;
import static java.lang.Math.log;
import static java.lang.Math.pow;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.CharacterCodingException;

public class CodecReader extends Reader implements Codec {

	private Reader read;
	private char code[];
	public CodecReader(Reader r,String s) {
		code=s.toCharArray();
		read=r;
	}

	public int read(char[] arg0, int arg1, int arg2) throws IOException {
		return read.read(arg0,arg1,arg2);
	}
	
	public int read() throws IOException {
		char b[]=new char[getMax()];
		if(read.read(b)<0)
			return -1;
		int numero,ntmp,i,k;
		for(i=b.length-1,k=0,numero=0;i>=0;k++,i--){
			ntmp=getValue(b[i]);
			numero+=ntmp*pow(getBase(),k);
		}
		return numero;
	}
	
	private int getValue(char c) throws CharacterCodingException{
		int i;
		for(i=0;i<code.length;i++){
			if(code[i]==c)
				return i;
		}
		throw new CharacterCodingException();}

	public void close() throws IOException {
		read.close();
	}

	public int getBase(){return code.length;}

	public int getMax(){
		return (int)ceil(log(255)/log(getBase()));
	}
	public static void main(String argv[]){
		try {
			CodecReader cr=new CodecReader(new FileReader("Pw.txt"),"|!\"£$%&/()=?^ì\'0987654321\\*§°ç><òàù+");
			int i=cr.read();
			while(i>=0){
				System.out.print((char)i);
				i=cr.read();
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
