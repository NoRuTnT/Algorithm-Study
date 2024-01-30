import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, result, mapNum;
	static int[][] map;
	static boolean[][] mapCheck;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		result = Integer.MAX_VALUE;
		mapNum = 0;
		map = new int[N][N];
		mapCheck = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!mapCheck[i][j] && map[i][j] != 0) {
					mapNum++;
					mapCheck[i][j] = true;
					map[i][j] = mapNum;
					mapSetting(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] != 0) {
					bfs(i, j, map[i][j]);
				}
			}
		}

		System.out.println(result);

	}

	private static void mapSetting(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int nr = r + dr[i];
			int nc = c + dc[i];
			if (isValid(nr, nc) && !mapCheck[nr][nc] && map[nr][nc] != 0) {
				map[nr][nc] = mapNum;
				mapCheck[nr][nc] = true;
				mapSetting(nr, nc);
			}
		}
	}

	private static void bfs(int r, int c, int num) {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		que.add(new int[] {r, c});
		visited[r][c] = true;
		int dist = 0;
		while (!que.isEmpty()) {
			int length = que.size();
			if(dist >= result) {
				return;
			}
			for (int l = 0; l < length; l++) {
				int[] arr = que.poll();
				for (int i = 0; i < 4; i++) {
					int nr = arr[0] + dr[i];
					int nc = arr[1] + dc[i];
					if (isValid(nr, nc)) {
						if (map[nr][nc] != num && map[nr][nc] != 0) {
							result = Math.min(result, dist);
						}
						if (!visited[nr][nc] && map[nr][nc] == 0) {
							que.add(new int[] {nr, nc});
							visited[nr][nc] = true;
						}
					}
				}
			}
			dist++;
		}
	}

	private static boolean isValid(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < N;
	}

}
