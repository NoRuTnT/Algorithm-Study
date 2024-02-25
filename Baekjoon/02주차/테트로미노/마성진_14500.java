import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[][] map;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static int result;
	public static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, map[i][j]);
				visited[i][j] = false;
			}
		}

		System.out.println(result);

	}

	private static void dfs(int x, int y, int depth, int sum) {
		if (depth == 3) {
			result = Math.max(result, sum);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nextI = x + iDir[i];
			int nextJ = y + jDir[i];
			if (nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) {
				continue;
			}
			if (!visited[nextI][nextJ]) {
				if (depth == 1) {
					visited[nextI][nextJ] = true;
					dfs(x, y, depth + 1, sum + map[nextI][nextJ]);
					visited[nextI][nextJ] = false;
				}
				visited[nextI][nextJ] = true;
				dfs(nextI, nextJ, depth + 1, sum + map[nextI][nextJ]);
				visited[nextI][nextJ] = false;
			}

		}
	}

}
