import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while ((line = br.readLine()) != null) {
			if(line.equals("end")) {
				break;
			}
			System.out.print(line+" ");
			String[][] map = new String[10][10];
			for (int i = 0; i < 10; i++) {
				map[i] = br.readLine().split("");
			}
			int min_times=Integer.MAX_VALUE;
			int times = 0;
			boolean solved = false;
			for (int c = 0; c < (1 << 10) ; c++) {
				times = 0;
				String[][] map_tmp = new String[10][10];
				for (int i = 0; i < 10; i++) {
					map_tmp[i] = map[i].clone();
				}
				int for_divide = c;
				int index_of_row = 0;
				while (for_divide != 0) {
					if (for_divide % 2 == 1) {
						press(0, index_of_row, map_tmp);
						times++;
					}
					for_divide /= 2;
					index_of_row++;
				}
				for (int i = 1; i < 10; i++) {
					for (int j = 0; j < 10; j++) {
						if (map_tmp[i - 1][j].equals("O")) {
							press(i, j, map_tmp);
							times++;
						}
					}
				}
				if (String.join("", map_tmp[9]).indexOf("O") < 0 && times<=100) {
					solved = true;
					if(times<min_times) {
						min_times=times;
					}
				}
			}
			if (solved) {
				System.out.println(min_times);
			} else {
				System.out.println(-1);
			}
		}
	}

	private static void press(int i, int j, String[][] map) {
		map[i][j] = (map[i][j].equals("#")) ? "O" : "#";
		try {
			map[i - 1][j] = (map[i - 1][j].equals("#")) ? "O" : "#";
		} catch (Exception e) {}
		try {
			map[i + 1][j] = (map[i + 1][j].equals("#")) ? "O" : "#";
		} catch (Exception e) {}
		try {
			map[i][j - 1] = (map[i][j - 1].equals("#")) ? "O" : "#";
		} catch (Exception e) {}
		try {
			map[i][j + 1] = (map[i][j + 1].equals("#")) ? "O" : "#";
		} catch (Exception e) {}
	}
}