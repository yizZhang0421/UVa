import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		while(N-->0) {
			br.readLine();
			int n = Integer.parseInt(br.readLine().trim());
			System.out.println("program sort(input,output);");
			System.out.println("var");
			String[] chars = new String[n];
			for(char i='a';i<'a'+n;i++) {
				chars[i-'a']=i+"";
			}
			System.out.println(String.join(",", chars)+" : integer;");
			System.out.println("begin");
			System.out.println("  readln("+String.join(",", chars)+");");
			if(chars.length!=1) {
				ArrayList<String> sort = new ArrayList<>();
				sort.add(chars[0]);
				recursive(chars, sort, 1);
			}
			else {
				System.out.println("  writeln("+String.join(",", chars)+")");
			}
			System.out.println("end.");
			if(N>0) {
				System.out.println();
			}
		}
	}

	public static String tab = "  ";
	private static void recursive(String[] chars, ArrayList<String> current_sort, int layer) {
		for(int i=current_sort.size()-1;i>=-1;i--) {
			for(int j=0;j<layer;j++) {
				System.out.print(tab);
			}
			if(i==current_sort.size()-1) {
				System.out.println("if "+current_sort.get(i)+" < "+chars[layer]+" then");
			}
			else if(i==-1) {
				System.out.println("else");
			}
			else {
				System.out.println("else if "+current_sort.get(i)+" < "+chars[layer]+" then");
			}
			ArrayList<String> new_sort = (ArrayList<String>) current_sort.clone();
			new_sort.add(i+1, chars[layer]);
			if(layer!=chars.length-1) {
				recursive(chars, new_sort, layer+1);
			}
			else {
				for(int j=0;j<layer+1;j++) {
					System.out.print(tab);
				}
				System.out.println("writeln("+String.join(",", new_sort)+")");
			}
		}
		
	}
}