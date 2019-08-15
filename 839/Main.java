import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static BufferedReader br;
	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		while(N-->0) {
			br.readLine();
			int result = calculate(br.readLine().trim().split(" +"));
			if(result==-1) {
				System.out.println("NO");
			}
			else {
				System.out.println("YES");
			}
			if(N>0) {
				System.out.println();
			}
		}
	}

	private static int calculate(String[] WlDlWrDr) throws IOException {
		int Wl=Integer.parseInt(WlDlWrDr[0]);
		int Dl=Integer.parseInt(WlDlWrDr[1]);
		int Wr=Integer.parseInt(WlDlWrDr[2]);
		int Dr=Integer.parseInt(WlDlWrDr[3]);
		if(Wl==0) {
			Wl=calculate(br.readLine().trim().split(" +"));
		}
		if(Wr==0) {
			Wr=calculate(br.readLine().trim().split(" +"));
		}
		if((Wl*Dl)!=Wr*Dr || Wl==-1 || Wr==-1) {
			return -1;
		}
		return Wl+Wr;
	}
}