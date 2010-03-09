package macrobug.io;

import static java.lang.Math.*;

import java.io.*;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;

public class CodecOutputStream extends OutputStream implements Codec{

	private OutputStream out;
	private char[] code;
	public CodecOutputStream(OutputStream out,String code) {
		this.code=code.toCharArray();
		this.out=out;
	}
	public void write(int c) throws IOException {
		int enne = c, n = getBase();
		int numero = /*(enne >= 0) ?*/ enne/* : 256 + enne*/;
		int i, resto;
		ArrayList<Integer> a=new ArrayList<Integer>();
		for (i = 0, resto = 0; numero >= n; i++) {
			resto = numero % n;
			numero = (int) numero / n;
			a.add(0,getValue(resto));
		}
		a.add(0,getValue(numero));
		while(a.size()<getMax())
			a.add(0,getValue(0));
		for(Integer ì:a)
			out.write(ì);
	}

	public int getBase() {
		return code.length;
	}

	public int getValue(int c) throws CharacterCodingException {
		return code[c];
	}

	public int getMax() {
		return (int) ceil(log(255) / log(getBase()));
	}
	public static void main(String argv[]){
		try {
			CodecOutputStream cos=new CodecOutputStream(new FileOutputStream("Prova.txt"),"|!\"£$%&/()=?^ì\'0987654321\\*§°ç><òàù+");
			BufferedReader b=new BufferedReader(new InputStreamReader(System.in));
			String s="@²#";//64 178  
			do{
				for(char c:s.toCharArray())
					cos.write(c);
				cos.write('\n');
				s=b.readLine();
			}
			while(s!=null);
			b.close();
			cos.flush();
			cos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
