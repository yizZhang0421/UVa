import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		while(n!=0) {
			int[][] map = new int[n][n];
			ArrayList<String> patterns = new ArrayList<>();
			boolean player_win=false;
			for(int i=0;i<2*n;i++) {
				String[] command = br.readLine().trim().split(" +");
				if(player_win==false) {
					map[Integer.parseInt(command[0])-1][Integer.parseInt(command[1])-1]=command[2].equals("+")?1:0;
					String this_pattern = "";
					for(int[] j : map) {
						for(int k : j) {
							this_pattern+=k;
						}
					}
					if(patterns.contains(this_pattern)) {
						if(i%2==0) {
							System.out.println("Player 2 wins on move "+(i+1));
						}
						else {
							System.out.println("Player 1 wins on move "+(i+1));
						}
						player_win=true;
					}
					for(int j=0;j<4;j++) {
						patterns.add(this_pattern);
						map=rotate(map);
						if(j<3) {
							this_pattern = "";
							for(int[] k : map) {
								for(int l : k) {
									this_pattern+=l;
								}
							}
						}
					}
				}
			}
			if(!player_win) {
				System.out.println("Draw");
			}
			n = Integer.parseInt(br.readLine().trim());
		}
	}
	private static int[][] rotate(int[][] map) {
		int[][] tmp = new int[map.length][map[0].length];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				tmp[j][map.length-1-i]=map[i][j];
			}
		}
		return tmp;
	}
}