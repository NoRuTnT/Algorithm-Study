import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int M, N;
	public static int[][] map;
	public static int[][] dp;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = -1;
			}
		}
		dfs(0, 0);
		System.out.println(dp[0][0]);
	}

	public static int dfs(int x, int y) {
		if (x == M - 1 && y == N - 1) {
			return 1;
		}
		if (dp[x][y] != -1) {
			return dp[x][y];
		}
		dp[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nextI = x + iDir[i];
			int nextJ = y + jDir[i];
			if (nextI >= 0 && nextJ >= 0 && nextI < M && nextJ < N && map[x][y] > map[nextI][nextJ]) {
				dp[x][y] += dfs(nextI, nextJ);
			}
		}
		return dp[x][y];
	}

}
