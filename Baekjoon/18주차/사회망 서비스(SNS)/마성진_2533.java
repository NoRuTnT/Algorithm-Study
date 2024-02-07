import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean[] visited;
	static List<Integer>[] graph;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		dp = new int[N + 1][2];
		visited = new boolean[N + 1];
		graph = new ArrayList[N + 1];

		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		dfs(1);
		bw.write(Math.min(dp[1][0], dp[1][1]) + "");

		bw.flush();
		bw.close();
		br.close();
	}

	static void dfs(int v) {
		visited[v] = true;
		dp[v][0] = 0;    // 얼리어답터가 아닌 경우
		dp[v][1] = 1;    // 얼리어답터인 경우

		for (int nv : graph[v]) {
			if(!visited[nv]) {
				dfs(nv);
				dp[v][0] += dp[nv][1];
				dp[v][1] += Math.min(dp[nv][0], dp[nv][1]);
			}
		}
	}

}
