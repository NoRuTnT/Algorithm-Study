import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static final int iDir[] = { 0, 0, 1, -1 };
	public static final int jDir[] = { 1, -1, 0, 0 };
	public static int originalMap[][];
	public static int N, M;
	public static int maxSafeZone = Integer.MIN_VALUE;

	public static class Node {
		int x;
		int y;

		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		originalMap = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		perm(0);

		System.out.println(maxSafeZone);
	}

	static void perm(int wallCnt) {
		// 벽이 3개가 설치 된 경우 bfs 탐색 시작
		if (wallCnt == 3) {
			bfs();
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (originalMap[i][j] == 0) {
					originalMap[i][j] = 1;
					perm(wallCnt + 1);
					originalMap[i][j] = 0;
				}
			}
		}
	}

	static void bfs() {
		Queue<Node> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (originalMap[i][j] == 2) {
					q.add(new Node(i, j));
				}
			}
		}

		// 원본 연구소를 바꾸지 않기 위한 카피맵 사용
		int copyMap[][] = new int[N][M];

		for (int i = 0; i < N; i++) {
			copyMap[i] = originalMap[i].clone();
		}

		// BFS 탐색 시작
		while (!q.isEmpty()) {
			Node now = q.poll();
			int x = now.x; // 현재 값
			int y = now.y; //

			for (int k = 0; k < 4; k++) {
				int nx = x + iDir[k];
				int ny = y + jDir[k];

				// 연구소 범위 밖이 아니고 빈칸일 경우에만 바이러스를 퍼트린다.
				if (0 <= nx && nx < N && 0 <= ny && ny < M) {
					if (copyMap[nx][ny] == 0) {
						q.add(new Node(nx, ny));
						copyMap[nx][ny] = 2;
					}
				}
			}
		}

		// SafeZone 확인
		funcSafeZone(copyMap);
	}

	private static void funcSafeZone(int[][] copyMap) {
		int safeZone = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == 0) {
					safeZone++;
				}
			}
		}
		maxSafeZone = Math.max(maxSafeZone, safeZone);
	}

}
