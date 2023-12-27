import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static int N, V, E;
	public static int[] dist, loc;
	public static List<List<Node>> graph = new ArrayList<>();

	public static class Node implements Comparable<Node> {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		loc = new int[N];
		dist = new int[V + 1];

		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			loc[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, l));
			graph.get(b).add(new Node(a, l));
		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			dijkstra(loc[i]);
			if (dist[A] == Integer.MAX_VALUE) {
				result += -1;
			} else {
				result += dist[A];
			}
			if (dist[B] == Integer.MAX_VALUE) {
				result += -1;
			} else {
				result += dist[B];
			}
		}

		System.out.println(result);

	}

	private static void dijkstra(int start) {
		Arrays.fill(dist, Integer.MAX_VALUE);
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[V + 1];
		dist[start] = 0;
		pq.offer(new Node(start, dist[start]));

		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (!visited[now.v]) {
				visited[now.v] = true;
			}

			for (Node next : graph.get(now.v)) {
				if (!visited[next.v] && dist[next.v] > dist[now.v] + next.w) {
					dist[next.v] = dist[now.v] + next.w;
					pq.offer(new Node(next.v, dist[next.v]));
				}
			}

		}

	}

}
