import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Vertex {
		float x;
		float y;

		public Vertex(float x, float y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Edge implements Comparable<Edge> {

		int destination;
		float time;

		public Edge(int destination, float time) {
			this.destination = destination;
			this.time = time;
		}

		@Override
		public int compareTo(Edge o) {
			return (int) (this.time - o.time);
		}
	}

	static Vertex[] vertices;
	static ArrayList<Edge> adjList[];
	static boolean[] visited;
	static float[] times;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		float x = Float.parseFloat(st.nextToken());
		float y = Float.parseFloat(st.nextToken());
		Vertex start = new Vertex(x, y);

		st = new StringTokenizer(br.readLine());
		x = Float.parseFloat(st.nextToken());
		y = Float.parseFloat(st.nextToken());
		Vertex end = new Vertex(x, y);

		int N = Integer.parseInt(br.readLine());

		vertices = new Vertex[N + 2];
		visited = new boolean[N + 2];
		times = new float[N + 2];
		adjList = new ArrayList[N + 2];
		for (int i = 0; i < N + 2; i++) {
			adjList[i] = new ArrayList<>();
		}

		vertices[0] = start;
		vertices[N + 1] = end;
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			x = Float.parseFloat(st.nextToken());
			y = Float.parseFloat(st.nextToken());
			vertices[i] = new Vertex(x, y);
		}

		// 그래프 생성
		// 출발지는 대포가 아니기 때문에 달려가야한다.
		for (int i = 1; i < N + 2; i++) {
			float distance = (float)Math.sqrt(
				Math.pow(vertices[0].x - vertices[i].x, 2) + Math.pow(vertices[0].y - vertices[i].y, 2));
			adjList[0].add(new Edge(i, (float)(distance / 5.0)));
		}

		// 대포에서는 달려가거나, 대로포 발사되는 것 중 빠른 것으로 선택
		for (int i = 1; i < N + 2; i++) {
			for (int j = 0; j < N + 2; j++) {
				float distance = (float)Math.sqrt(
					Math.pow(vertices[i].x - vertices[j].x, 2) + Math.pow(vertices[i].y - vertices[j].y, 2));
				adjList[i].add(new Edge(j, (float)Math.min(distance / 5.0, Math.abs(distance - 50) / 5.0 + 2)));
			}
		}

		dijkstra();

		System.out.println(times[N + 1]);
	}

	private static void dijkstra() {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		Arrays.fill(times, Integer.MAX_VALUE);
		pq.add(new Edge(0, 0));
		times[0] = 0;

		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			int destination = edge.destination;

			if (visited[destination]) {
				continue;
			} else {
				visited[destination] = true;
			}

			for (Edge e : adjList[destination]) {
				if (times[e.destination] >= times[destination] + e.time) {
					times[e.destination] = times[destination] + e.time;
					pq.add(new Edge(e.destination, times[e.destination]));
				}
			}
		}
	}

}
