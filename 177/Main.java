import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine().trim());
		while(n!=0) {
			ArrayList<Crease> dragon_curve = new ArrayList<>();
			dragon_curve.add(new Crease(Crease.IDENTIFER_HORIZON, Crease.MOVEMENT_L2R, 0, 0));
			min_row_position=0;
			min_column_position=0;
			max_row_position=0;
			max_column_position=0;
			recursive(n, dragon_curve);
			String[][] diagram = new String[max_row_position-min_row_position+1][max_column_position-min_column_position+1];
			for(String[] i : diagram) {
				Arrays.fill(i, " ");
			}
			for(Crease i : dragon_curve) {
				diagram[i.row+Math.abs(min_row_position)][i.column+Math.abs(min_column_position)]=i.get_identifer_string();
			}
			for(String[] i : diagram) {
				System.out.println(String.join("", i).replaceAll("( +$)", ""));
			}
			System.out.println("^");
			n = Integer.parseInt(br.readLine().trim());
		}
	}
	public static int min_row_position=0;
	public static int min_column_position=0;
	public static int max_row_position=0;
	public static int max_column_position=0;
	private static void recursive(int layer, ArrayList<Crease> current_curve) {
		if(layer==0) {
			return;
		}
		ArrayList<Crease> tmp = (ArrayList<Crease>) current_curve.clone();
		for(int i=0;i<tmp.size();i++) {
			int mirror_move = ~tmp.get(i).move_ment;
			Crease new_crease = null;
			if(mirror_move==Crease.MOVEMENT_R2L) {
				new_crease=new Crease(1-tmp.get(i).identifer, Crease.MOVEMENT_B2T);
			}
			else if(mirror_move==Crease.MOVEMENT_L2R) {
				new_crease=new Crease(1-tmp.get(i).identifer, Crease.MOVEMENT_T2B);
			}
			else if(mirror_move==Crease.MOVEMENT_T2B) {
				new_crease=new Crease(1-tmp.get(i).identifer, Crease.MOVEMENT_R2L);
			}
			else if(mirror_move==Crease.MOVEMENT_B2T) {
				new_crease=new Crease(1-tmp.get(i).identifer, Crease.MOVEMENT_L2R);
			}
			if(current_curve.get(0).move_ment==Crease.MOVEMENT_L2R) {
				if(new_crease.move_ment==Crease.MOVEMENT_T2B) {
					new_crease.row=current_curve.get(0).row+1;
					new_crease.column=current_curve.get(0).column+1;
				}
				else {
					new_crease.row=current_curve.get(0).row;
					new_crease.column=current_curve.get(0).column+1;
				}
			}
			else if(current_curve.get(0).move_ment==Crease.MOVEMENT_R2L) {
				if(new_crease.move_ment==Crease.MOVEMENT_T2B) {
					new_crease.row=current_curve.get(0).row+1;
					new_crease.column=current_curve.get(0).column-1;
				}
				else {
					new_crease.row=current_curve.get(0).row;
					new_crease.column=current_curve.get(0).column-1;
				}
			}
			else if(current_curve.get(0).move_ment==Crease.MOVEMENT_B2T) {
				if(new_crease.move_ment==Crease.MOVEMENT_L2R) {
					new_crease.row=current_curve.get(0).row-1;
					new_crease.column=current_curve.get(0).column+1;
				}
				else {
					new_crease.row=current_curve.get(0).row-1;
					new_crease.column=current_curve.get(0).column-1;
				}
			}
			else {
				if(new_crease.move_ment==Crease.MOVEMENT_L2R) {
					new_crease.row=current_curve.get(0).row;
					new_crease.column=current_curve.get(0).column+1;
				}
				else {
					new_crease.row=current_curve.get(0).row;
					new_crease.column=current_curve.get(0).column-1;
				}
			}
			if(new_crease.row<min_row_position) {
				min_row_position=new_crease.row;
			}
			if(new_crease.column<min_column_position) {
				min_column_position=new_crease.column;
			}
			if(new_crease.row>max_row_position) {
				max_row_position=new_crease.row;
			}
			if(new_crease.column>max_column_position) {
				max_column_position=new_crease.column;
			}
			current_curve.add(0, new_crease);
		}
		recursive(layer-1, current_curve);
	}
}
class Crease{
	static int IDENTIFER_VERTICAL = 1;
	static int IDENTIFER_HORIZON = 0;
	static int MOVEMENT_L2R=0;
	static int MOVEMENT_R2L=-1;
	static int MOVEMENT_B2T=1;
	static int MOVEMENT_T2B=-2;
	int identifer;
	int move_ment;
	int row;
	int column;
	public Crease(int identifer, int movement, int x, int y) {
		this.identifer=identifer;
		this.move_ment=movement;
		this.row=x;
		this.column=y;
	}
	public Crease(int identifer, int movement) {
		this.identifer=identifer;
		this.move_ment=movement;
	}
	String get_identifer_string (){
		if(this.identifer==IDENTIFER_HORIZON) {
			return "_";
		}
		else {
			return "|";
		}
	}
}