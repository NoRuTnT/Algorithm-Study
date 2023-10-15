import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[][] map;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static boolean[][][] visited;

	public static class Node {
		public int x;
		public int y;
		public int cnt;
		public boolean flag;

		public Node(int x, int y, int cnt, boolean flag) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.flag = flag;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		bfs(0, 0);
	}

	public static void bfs(int x, int y) {
		Queue<Node> que = new LinkedList<>();
		visited = new boolean[N][M][2];
		visited[x][y][0] = true;
		Node start = new Node(x, y, 1, false);
		que.add(start);
		while (!que.isEmpty()) {
			Node now = que.poll();
			if (now.x == N - 1 && now.y == M - 1) {
				System.out.println(now.cnt);
				return;
			}
			for (int i = 0; i < 4; i++) {
				int nextI = now.x + iDir[i];
				int nextJ = now.y + jDir[i];
				if (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < M) {
					if (map[nextI][nextJ] == 0) {
						if (!now.flag && !visited[nextI][nextJ][0]) {
							que.add(new Node(nextI, nextJ, now.cnt + 1, false));
							visited[nextI][nextJ][0] = true;
						} else if (now.flag && !visited[nextI][nextJ][1]) {
							que.add(new Node(nextI, nextJ, now.cnt + 1, true));
							visited[nextI][nextJ][1] = true;
						}
					} else {
						if (!now.flag) {
							que.add(new Node(nextI, nextJ, now.cnt + 1, true));
							visited[nextI][nextJ][1] = true;
						}
					}
				}
			}
		}
		System.out.println(-1);
	}

}
