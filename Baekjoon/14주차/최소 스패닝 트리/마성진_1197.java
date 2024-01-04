import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static int V, E;
	public static List<Edge>[] graph;

	public static class Edge implements Comparable<Edge> {
		int w, cost;

		public Edge(int w, int cost) {
			this.w = w;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return this.cost - o.cost;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		graph = new ArrayList[V + 1];

		for (int i = 0; i < graph.length; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Edge(b, c));
			graph[b].add(new Edge(a, c));
		}

		prim();

	}

	public static void prim() {
		boolean[] visited = new boolean[V + 1];

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(1, 0));

		int total = 0;

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int w = edge.w;
			int c = edge.cost;

			if (visited[w])
				continue;

			visited[w] = true;
			total += c;

			for (Edge e : graph[w]) {
				if (!visited[e.w])
					pq.add(e);
			}
		}

		System.out.println(total);

	}

}
