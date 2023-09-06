import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[][] map;
	public static int cnt1;
	public static int cnt2;
	public static int cnt3;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < map.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		recur(N, 0, 0);
		
		System.out.println(cnt1);
		System.out.println(cnt2);
		System.out.println(cnt3);
		
	}

	public static void recur(int n, int x, int y) {
		int flag = check(n, x, y);
		if(flag == -1) {
			cnt1++;
			return;
		} else if(flag == 0) {
			cnt2++;
			return;
		} else if(flag == 1) {
			cnt3++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				recur(n/3, x + n/3 * i, y + n/3 * j);
			}
		}
		
	}
	
	public static int check(int n, int x, int y) {
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if(map[x][y] != map[i][j]) {
					return 2;
				}
			}
		}
		return map[x][y];
	}

}
