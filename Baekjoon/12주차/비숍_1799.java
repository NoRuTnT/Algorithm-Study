import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static int[][] map;
	public static int[] result;
	public static int[] dr = { -1, -1 };
	public static int[] dc = { 1, -1 };
	public static boolean[][] visited, colors;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());

		result = new int[2];
		map = new int[N][N];
		visited = new boolean[N][N];
		colors = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				colors[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 != 0 && j % 2 != 0);
			}
		}

		dfs(-1, true, 0);
		dfs(-1, false, 0);

		System.out.println(result[0] + result[1]);

	}

	public static void dfs(int idx, boolean color, int depth) {
		for (int i = idx + 1; i < N * N; i++) {
			int r = i / N;
			int c = i % N;

			if (colors[r][c] != color || map[r][c] == 0 || !check(r, c)) {
				continue;
			}

			visited[r][c] = true;
			dfs(i, color, depth + 1);
			visited[r][c] = false;
		}

		int tmp = 0;
		if (color) {
			tmp = 1;
		}
		result[tmp] = Math.max(result[tmp], depth);
	}

	public static boolean check(int r, int c) {
		for (int i = 0; i < 2; i++) {
			int nr = r;
			int nc = c;
			while (true) {
				if (nr < 0 || nr >= N || nc < 0 || nc >= N) {
					break;
				}
				if (visited[nr][nc]) {
					return false;
				}
				nr += dr[i];
				nc += dc[i];
			}
		}
		return true;
	}

}
