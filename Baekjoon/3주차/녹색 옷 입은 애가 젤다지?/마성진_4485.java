import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static int[][] map;
	public static int[][] dist;
	public static int[] dc = { -1, 0, 1, 0 };
	public static int[] dr = { 0, 1, 0, -1 };

	public static class Point implements Comparable<Point> {

		int col, row, cost;

		public Point(int col, int row, int cost) {
			this.col = col;
			this.row = row;
			this.cost = cost;
		}

		@Override
		public int compareTo(Point o) {
			return this.cost - o.cost;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int cnt = 0;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			map = new int[N][N];
			dist = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					dist[i][j] = Integer.MAX_VALUE;
				}
			}
			cnt++;
			sb.append("Problem " + cnt + ": " + dijkstra() + "\n");
		}
		System.out.println(sb);
	}

	public static int dijkstra() {
		PriorityQueue<Point> pq = new PriorityQueue<Point>();
		dist[0][0] = map[0][0];
		pq.offer(new Point(0, 0, map[0][0]));

		while (!pq.isEmpty()) {
			Point p = pq.poll();
			for (int i = 0; i < 4; i++) {
				int nc = p.col + dc[i];
				int nr = p.row + dr[i];
				if (nc >= 0 && nr >= 0 && nc < N && nr < N) {
					if (dist[nc][nr] > dist[p.col][p.row] + map[nc][nr]) {
						dist[nc][nr] = dist[p.col][p.row] + map[nc][nr];
						pq.offer(new Point(nc, nr, dist[nc][nr]));
					}
				}
			}
		}
		return dist[N - 1][N - 1];
	}

}
