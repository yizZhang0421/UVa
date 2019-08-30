import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;

public class Main {
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().trim().split(" +");
		while(!input[0].equals("0") || !input[1].equals("0")) {
			int h = Integer.parseInt(input[0]);
			int k = Integer.parseInt(input[1]);
			ArrayList<Integer> denominations = new ArrayList<>();
			denominations.add(1);
			int current_continous_reach = max_continuous(denominations, h); 
			int reach = dfs(denominations, current_continous_reach, h, k);
			for(int i=0;i<denominations.size();i++) {
				System.out.print(String.format("%3s", denominations.get(i)));
			}
			System.out.print(" ->");
			System.out.println(String.format("%3s", reach));
			input = br.readLine().trim().split(" +");
		}
	}

	private static void get_all_make(ArrayList<Integer> denominations, int h, HashSet<Integer> all_make, ArrayList<Integer> current_pick){
		if(h==0) {
			int sum=0;
			for(int i : current_pick) {
				sum+=i;
			}
			all_make.add(sum);
			return;
		}
		for(int i : denominations) {
			int index=current_pick.size();
			current_pick.add(i);
			int sum=0;
			for(int j : current_pick) {
				sum+=j;
			}
			all_make.add(sum);
			get_all_make(denominations, h-1, all_make, current_pick);
			current_pick.remove(index);
		}
	}
	private static int max_continuous(ArrayList<Integer> denominations, int h) {
		HashSet<Integer> all_make = new HashSet<>();
		get_all_make(denominations, h, all_make, new ArrayList<>());
		int current_valid=0;
		for(int i=1;;i++) {
			if(all_make.contains(i)) {
				current_valid=i;
			}
			else {
				break;
			}
		}
		return current_valid;
	}

	private static int dfs(ArrayList<Integer> denominations, int current_continous_reach, int h, int k) {
		if(denominations.size()==k) {
			return current_continous_reach;
		}
		int max_reach=current_continous_reach;
		ArrayList<Integer> max_reach_denominations = (ArrayList<Integer>) denominations.clone();
		for(int i=denominations.get(denominations.size()-1)+1; i<=current_continous_reach+1; i++) {
			ArrayList<Integer> tmp = (ArrayList<Integer>) denominations.clone();
			tmp.add(i);
			int reach=max_continuous(tmp, h);
			reach=dfs(tmp, reach, h, k);
			if(reach>max_reach) {
				max_reach=reach;
				max_reach_denominations=(ArrayList<Integer>) tmp.clone();
			}
		}
		for(int i : max_reach_denominations) {
			if(denominations.contains(i)==false) {
				denominations.add(i);
			}
		}
		return max_reach;
		
	}
}