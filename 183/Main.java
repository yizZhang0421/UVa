import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static String encode_result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] rect_declear = br.readLine().split(" +");
		while(!rect_declear[0].equals("#")) {
			if(rect_declear[0].equals("D")) {
				System.out.println("B"+String.format("%4s", rect_declear[1])+String.format("%4s", rect_declear[2]));
			}
			else {
				System.out.println("D"+String.format("%4s", rect_declear[1])+String.format("%4s", rect_declear[2]));
			}
			int rows = Integer.parseInt(rect_declear[1]);
			int columns = Integer.parseInt(rect_declear[2]);
			String[][] bitmap = new String[rows][columns];
			if(rect_declear[0].equals("B")) {
				String bits = "";
				rect_declear = br.readLine().split(" +");
				while(rect_declear.length==1 && !rect_declear[0].equals("#")) {
					bits+=rect_declear[0];
					rect_declear = br.readLine().split(" +");
				}
				for(int i=0;i<bits.length();i++) {
					bitmap[i/columns][i%columns]=bits.charAt(i)+"";
				}
				encode_result="";
				encode(bitmap, 0, rows-1, 0, columns-1);
				if(encode_result.length()!=0) {
					System.out.println(encode_result);
				}
			}
			else {
				String command="";
				rect_declear = br.readLine().split(" +");
				while(rect_declear.length==1 && !rect_declear[0].equals("#")) {
					command+=rect_declear[0];
					rect_declear = br.readLine().split(" +");
				}
				decode_command_index=0;
				decode(bitmap, command, 0, rows-1, 0, columns-1);
				String result="";
				for(String[] i : bitmap) {
					result+=String.join("", i);
					while(result.length()>=50) {
						System.out.println(result.substring(0, 50));
						result=result.substring(50, result.length());
					}
				}
				if(result.length()!=0) {
					System.out.println(result);
				}
			}
		}
	}
	public static int decode_command_index=0;
	private static void decode(String[][] bitmap, String command, int row_f, int row_l, int col_f, int col_l) {
		if(row_f>row_l || col_f>col_l | decode_command_index>=command.length()) {
			return;
		}
		if(command.charAt(decode_command_index)=='0') {
			decode_command_index++;
			for(int r=row_f;r<=row_l;r++) {
				for(int c=col_f;c<=col_l;c++) {
					bitmap[r][c]="0";
				}
			}
		}
		else if(command.charAt(decode_command_index)=='1') {
			decode_command_index++;
			for(int r=row_f;r<=row_l;r++) {
				for(int c=col_f;c<=col_l;c++) {
					bitmap[r][c]="1";
				}
			}
		}
		else {
			decode_command_index++;
			int rows=(row_l-row_f+1);
			int columns=(col_l-col_f+1);
			decode(bitmap, command, row_f, (rows%2==0)?row_f+((rows/2)-1):row_f+(rows/2), col_f, (columns%2==0)?col_f+((columns/2)-1):col_f+(columns/2));
			decode(bitmap, command, row_f, (rows%2==0)?row_f+((rows/2)-1):row_f+(rows/2), (columns%2==0)?col_f+(columns/2):col_f+((columns/2)+1), col_l);
			decode(bitmap, command, (rows%2==0)?row_f+(rows/2):row_f+((rows/2)+1), row_l, col_f, (columns%2==0)?col_f+((columns/2)-1):col_f+(columns/2));
			decode(bitmap, command, (rows%2==0)?row_f+(rows/2):row_f+((rows/2)+1), row_l, (columns%2==0)?col_f+(columns/2):col_f+((columns/2)+1), col_l);
		}
	}
	private static void encode(String[][] bitmap, int row_f, int row_l, int col_f, int col_l) {
		if(row_f>row_l || col_f>col_l) {
			return;
		}
		int rows=(row_l-row_f+1);
		int columns=(col_l-col_f+1);
		int sum=0;
		for(int r=row_f;r<=row_l;r++) {
			for(int c=col_f;c<=col_l;c++) {
				sum+=Integer.parseInt(bitmap[r][c]);
			}
		}
		if(sum==0) {
			encode_result+="0";
			if(encode_result.length()>=50) {
				System.out.println(encode_result.substring(0, 50));
				encode_result=encode_result.substring(50, encode_result.length());
			}
		}
		else if(sum==rows*columns) {
			encode_result+="1";
			if(encode_result.length()>=50) {
				System.out.println(encode_result.substring(0, 50));
				encode_result=encode_result.substring(50, encode_result.length());
			}
		}
		else {
			encode_result+="D";
			if(encode_result.length()>=50) {
				System.out.println(encode_result.substring(0, 50));
				encode_result=encode_result.substring(50, encode_result.length());
			}
			encode(bitmap, row_f, (rows%2==0)?row_f+((rows/2)-1):row_f+(rows/2), col_f, (columns%2==0)?col_f+((columns/2)-1):col_f+(columns/2));
			encode(bitmap, row_f, (rows%2==0)?row_f+((rows/2)-1):row_f+(rows/2), (columns%2==0)?col_f+(columns/2):col_f+((columns/2)+1), col_l);
			encode(bitmap, (rows%2==0)?row_f+(rows/2):row_f+((rows/2)+1), row_l, col_f, (columns%2==0)?col_f+((columns/2)-1):col_f+(columns/2));
			encode(bitmap, (rows%2==0)?row_f+(rows/2):row_f+((rows/2)+1), row_l, (columns%2==0)?col_f+(columns/2):col_f+((columns/2)+1), col_l);
		}
	}
}