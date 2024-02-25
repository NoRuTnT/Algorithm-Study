import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static class Edge implements Comparable<Edge> {
		int y;
		int cost;

		Edge(int y, int cost) {
			this.y = y;
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

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Edge>[] list = new ArrayList[N + 1];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list[x].add(new Edge(y, cost));
			list[y].add(new Edge(x, cost));
		}

		pq.offer(new Edge(1, 0));

		int total = 0;
		int max = 0;

		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			int x = e.y;
			int cost = e.cost;

			if (visited[x]) {
				continue;
			}

			visited[x] = true;
			total += cost;
			max = Math.max(max, cost);

			for (Edge edge : list[x]) {
				if (!visited[edge.y]) {
					pq.add(edge);
				}
			}

		}

		System.out.println(total - max);

	}
}
