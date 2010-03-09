package macrobug.io;

import static java.lang.Math.ceil;
import static java.lang.Math.log;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;

public class CodecWriter extends Writer implements Codec {

	private Writer write;
	private char[] code;

	public CodecWriter(Writer w,String s) {
		write=w;
		code=s.toCharArray();
	}

	public void write(char[] arg0, int arg1, int arg2) throws IOException {
		write.write(arg0, arg1, arg2);
	}

	public void write(char c)throws IOException {
		int enne = c, n = getBase();
		int numero = (enne >= 0) ? enne : 256 + enne;
		int i, resto;
		ArrayList<Character> a=new ArrayList<Character>();
		for (i = 0, resto = 0; numero >= n; i++) {
			resto = numero % n;
			numero = (int) numero / n;
			a.add(0,getValue(resto));
		}
		a.add(0,getValue(numero));
		while(a.size()<getMax())
			a.add(0,getValue(0));
		for(Character ì:a)
			write.write(ì);
	}
	public void flush() throws IOException {
		write.flush();
	}

	public void close() throws IOException {
		write.close();
	}

	public int getBase() {
		return code.length;
	}

	public char getValue(int c) throws CharacterCodingException {
		return code[c];
	}

	public int getMax() {
		return (int) ceil(log(255) / log(getBase()));
	}
	public static void main(String argv[]){
		BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
		try {
			CodecWriter cw=new CodecWriter(new FileWriter("Pw.txt"),"|!\"£$%&/()=?^ì\'0987654321\\*§°ç><òàù+");
			String s="Prova";
			do{
				for(char c:s.toCharArray())
				cw.write(c);
				cw.write('\n');
				s=b.readLine();
			}
			while(s!=null);
			cw.flush();
			cw.close();
			b.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
