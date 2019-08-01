import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int n = sc.nextInt();
			if(n==0) {
				break;
			}
			int m = sc.nextInt();
			// system of different constraints in linear programming
			// add a vertex to check negative cycle
			int[][] map = new int[n+1+1][n+1+1];
			for(int i=0;i<map.length;i++) {
				for(int j=0;j<map[i].length;j++) {
					if(i!=j) {
						map[i][j]=Integer.MAX_VALUE/3;
					}
					if(i==map.length-1) {
						map[i][j]=0;
					}
				}
			}
			while(m-->0) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				String ope = sc.next();
				int k = sc.nextInt();
				// prefix sum and convert all situation to that linear programming type
				if(ope.equals("lt")) {
					if((k-1)<map[a-1][a+b]) {
						map[a-1][a+b]=k-1;
					}
				}
				else {
					if((-1*k)-1<map[a+b][a-1]) {
						map[a+b][a-1]=(-1*k)-1;
					}
				}
			}
			// use bellman-ford to check negative cycle
			// if true means no solution
			if(bellman_ford(map.length-1, map)) {
				System.out.println("lamentable kingdom");
			}
			else{
				System.out.println("successful conspiracy");
			}
		}
		sc.close();
	}

	private static boolean bellman_ford(int source, int[][] map) {
		int[] shortest_path = map[source].clone();
		HashSet<Integer> converge_tmp = new HashSet<>();
		int[] previous_node = new int[map.length];
		for(int i=0;i<shortest_path.length;i++) {
			if(shortest_path[i]!=Integer.MAX_VALUE/3 && converge_tmp.contains(i)==false) {
				previous_node[i]=source;
			}
		}
		converge_tmp.add(source);
		for(int c=0;c<map.length;c++) {
			ArrayList<Integer> can_expand_node = new ArrayList<>();
			for(int i=0;i<shortest_path.length;i++) {
				if(shortest_path[i]!=Integer.MAX_VALUE/3 && converge_tmp.contains(i)==false) {
					can_expand_node.add(i);
				}
			}
			for(int i : can_expand_node) {
				for(int j=0;j<map.length;j++) {
					if(i!=j && map[i][j]!=Integer.MAX_VALUE/3 && shortest_path[i]+map[i][j]<shortest_path[j]) {
						shortest_path[j]=shortest_path[i]+map[i][j];
						previous_node[j]=i;
						converge_tmp.remove((Object)j);
					}
				}
				converge_tmp.add(i);
			}
		}
		// if source have previous vertex or path length larger than vertexCount-1
		// it contain negative cycle
		boolean check_cycle = true;
		if(previous_node[source]!=source) {
			check_cycle=false;
		}
		else {
			find_cycle:for(int i=1;i<map.length;i++) {
				int current_node = i;
				int count_of_path=0;
				while(current_node!=source) {
					current_node=previous_node[current_node];
					count_of_path++;
					if(count_of_path>map.length-1) {
						check_cycle=false;
						break find_cycle;
					}
				}
			}
		}
		return check_cycle;
	}
}