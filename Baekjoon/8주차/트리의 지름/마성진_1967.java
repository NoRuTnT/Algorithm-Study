import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static List<Node>[] tree;
	public static int result;
	public static boolean[] visited;

	public static class Node {
		int c;
		int cost;

		public Node(int c, int cost) {
			this.c = c;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<>();
		}
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			tree[p].add(new Node(c, cost));
			tree[c].add(new Node(p, cost));
		}
		result = 0;
		for (int i = 1; i <= N; i++) {
			visited = new boolean[N + 1];
			visited[i] = true;
			dfs(i, 0);
		}
		System.out.println(result);
	}

	public static void dfs(int v, int tot) {
		for (Node node : tree[v]) {
			if (!visited[node.c]) {
				visited[node.c] = true;
				dfs(node.c, tot + node.cost);
			}
		}
		result = Math.max(result, tot);
	}

}
