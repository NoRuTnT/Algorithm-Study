import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int T, N, K;
	public static int[] degree;
	public static int[] cost;
	public static int[] dp;
	public static List<Integer>[] edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			edgeList = new ArrayList[N + 1];
			degree = new int[N + 1];
			cost = new int[N + 1];
			dp = new int[N + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				edgeList[i] = new ArrayList<>();
				cost[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				edgeList[a].add(b);
				degree[b]++;
			}

			bfs();

			System.out.println(dp[Integer.parseInt(br.readLine())]);
		}
	}

	public static void bfs() {
		Queue<Integer> que = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			dp[i] = cost[i];
			if (degree[i] == 0) {
				que.add(i);
			}
		}

		for (int i = 1; i <= N; i++) {
			int v = que.poll();
			for (int j = 0; j < edgeList[v].size(); j++) {
				int nextV = edgeList[v].get(j);
				dp[nextV] = Math.max(dp[nextV], dp[v] + cost[nextV]);
				if (--degree[nextV] == 0) {
					que.add(nextV);
				}
			}
		}
	}

}
