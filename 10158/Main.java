import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader bi = new BufferedReader(new InputStreamReader(System.in));
		bi.readLine();
		HashMap<Integer, Space> spaces_map = new HashMap<>();
		int c=1, x=1, y=1;
		while(c!=0 || x!=0 || y!=0) {
			String input = bi.readLine();
			String[] input_arr = input.split(" +");
			c = Integer.parseInt(input_arr[0]);
			x = Integer.parseInt(input_arr[1]);
			y = Integer.parseInt(input_arr[2]);
			if (c == 0 && x == 0 && y == 0) {
				break;
			}
			Space x_space = spaces_map.get(x), y_space = spaces_map.get(y);
			if (c == 1 || c == 2) {
				if(x_space==null && y_space==null) {
					Space s = new Space();
					if(c==1) {
						s.AB.put(x, 0);
						s.AB.put(y, 0);
					}
					else {
						s.AB.put(x, 0);
						s.AB.put(y, 1);
					}
					spaces_map.put(x, s);
					spaces_map.put(y, s);
				}
				else if(x_space!=null && y_space==null){
					if(c==1) {
						x_space.AB.put(y, x_space.AB.get(x));
					}
					else {
						x_space.AB.put(y, 1-x_space.AB.get(x));
					}
					spaces_map.put(y, x_space);
				}
				else if(x_space==null && y_space!=null){
					if(c==1) {
						y_space.AB.put(x, y_space.AB.get(y));
					}
					else {
						y_space.AB.put(x, 1-y_space.AB.get(y));
					}
					spaces_map.put(x, y_space);
				}
				else if(x_space!=null && y_space!=null){
					if(x_space.equals(y_space)) {
						if(c==1) {
							if(x_space.AB.get(x) != x_space.AB.get(y)) {
								System.out.println(-1);
							}
						}
						else {
							if(x_space.AB.get(x) == x_space.AB.get(y)) {
								System.out.println(-1);
							}
						}
					}
					else {
						if(c==1) {
							if(x_space.AB.get(x) == y_space.AB.get(y)) {
								x_space.AB.putAll(y_space.AB);
							}
							else {
								y_space.reverse();
								x_space.AB.putAll(y_space.AB);
							}
						}
						else {
							if(x_space.AB.get(x) != y_space.AB.get(y)) {
								x_space.AB.putAll(y_space.AB);
							}
							else {
								y_space.reverse();
								x_space.AB.putAll(y_space.AB);
							}
						}
						for(int i : y_space.AB.keySet()) {
							spaces_map.put(i, x_space);
						}
					}
				}
			}
			else if (c == 3 || c == 4) {
				if(x_space!=null && y_space!=null) {
					if(c==3) {
						if(x_space.equals(y_space)) {
							if(x_space.AB.get(x) == x_space.AB.get(y)) {
								System.out.println(1);
							}
							else {
								System.out.println(0);
							}
						}
						else {
							System.out.println(0);
						}
					}
					else {
						if(x_space.equals(y_space)) {
							if(x_space.AB.get(x) != x_space.AB.get(y)) {
								System.out.println(1);
							}
							else {
								System.out.println(0);
							}
						}
						else {
							System.out.println(0);
						}
					}
				}
				else {
					System.out.println(0);
				}
			}
		}
	}
}
class Space{
	HashMap<Integer, Integer> AB;
	public Space() {
		this.AB=new HashMap<>();
	}
	public void reverse() {
		for(int key : this.AB.keySet()) {
			this.AB.put(key, 1-this.AB.get(key));
		}
	}
}