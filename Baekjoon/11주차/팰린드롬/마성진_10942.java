import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static int[] nums;
	public static int M;
	public static boolean[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		nums = new int[N + 1];
		dp = new boolean[N + 1][N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		check();

		M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			if (dp[start][end]) {
				sb.append("1\n");
			} else {
				sb.append("0\n");
			}
		}

		System.out.println(sb);

	}

	public static void check() {
		for (int i = 1; i <= N; i++) {
			dp[i][i] = true;
		}
		for (int i = 1; i < N; i++) {
			if (nums[i] == nums[i + 1]) {
				dp[i][i + 1] = true;
			}
		}
		for (int i = 2; i < N; i++) {
			for (int j = 1; j <= N - i; j++) {
				if (nums[j] == nums[j + i] && dp[j + 1][j + i - 1]) {
					dp[j][j + i] = true;
				}
			}
		}
	}

}
