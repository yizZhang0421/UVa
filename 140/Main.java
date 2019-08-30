import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] cube = br.readLine().trim().split(";");
		while(!cube[0].equals("#")) {
			TreeMap<Character, Alpha> record = new TreeMap<>();
			for(String i : cube) {
				String[] cube_info = i.split(":");
				char iden=cube_info[0].charAt(0);
				char[] nei=cube_info[1].toCharArray();
				Alpha alpha = record.get(iden)==null?new Alpha(iden):record.get(iden);
				record.put(iden, alpha);
				for(char c : nei) {
					Alpha alpha_nei=record.get(c)==null?new Alpha(c):record.get(c);
					alpha.neighbor.add(alpha_nei);
					record.put(c, alpha_nei);
					alpha_nei.neighbor.add(alpha);
				}
			}
			global_bandwidth=Integer.MAX_VALUE;
			global_permutation=null;
			permutation(record, new ArrayList<Alpha>());
			for(Alpha i : global_permutation) {
				System.out.print(i.id+" ");
			}
			System.out.print("-> ");
			System.out.println(global_bandwidth);
			cube = br.readLine().trim().split(";");
		}
	}

	static int global_bandwidth;
	static ArrayList<Alpha> global_permutation;
	private static void permutation(TreeMap<Character, Alpha> record, ArrayList<Alpha> container) {
		if(container.size()==record.size()) {
			int bandwidth=0;
			for(Alpha i : container) {
				for(Alpha j : i.neighbor) {
					int distance = Math.abs(container.indexOf(i)-container.indexOf(j));
					if(distance>bandwidth) {
						bandwidth=distance;
					}
				}
			}
			if(bandwidth<global_bandwidth) {
				global_bandwidth=bandwidth;
				global_permutation=(ArrayList<Alpha>) container.clone();
			}
			return;
		}
		for(char key : record.keySet()) {
			Alpha alpha = record.get(key);
			if(container.contains(alpha)==false) {
				container.add(alpha);
				permutation(record, container);
				container.remove(alpha);
			}
		}
	}
}
class Alpha{
	char id;
	HashSet<Alpha> neighbor;
	public Alpha(char id) {
		this.id=id;
		this.neighbor=new HashSet<>();
	}
}