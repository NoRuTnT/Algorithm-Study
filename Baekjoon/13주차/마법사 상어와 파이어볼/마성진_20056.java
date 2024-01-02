import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M, K;
	public static Queue<FireBall>[][] map;
	public static Queue<FireBall>[][] waitMap;
	public static int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static class FireBall {
		int m, s, d;

		public FireBall(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Queue[N][N];
		waitMap = new Queue[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<>();
				waitMap[i][j] = new LinkedList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new FireBall(m, s, d));

		}

		for (int t = 0; t < K; t++) {

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					while (!map[i][j].isEmpty()) {
						FireBall fireBall = map[i][j].poll();
						int nr = (i + N + dr[fireBall.d] * (fireBall.s % N)) % N;
						int nc = (j + N + dc[fireBall.d] * (fireBall.s % N)) % N;
						waitMap[nr][nc].add(fireBall);
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					while (!waitMap[i][j].isEmpty()) {
						map[i][j].add(waitMap[i][j].poll());
					}
					if (map[i][j].size() < 2) {
						continue;
					}
					int m = 0, s = 0, d = 1, size = map[i][j].size(), flag = 0;
					while (!map[i][j].isEmpty()) {
						FireBall fireBall = map[i][j].poll();
						m += fireBall.m;
						s += fireBall.s;
						if (fireBall.d % 2 == 0) {
							flag++;
						}
					}
					if (flag == size || flag == 0) {
						d = 0;
					}
					m /= 5;
					s /= size;
					if (m == 0) {
						continue;
					}
					for (int k = d; k < 8; k += 2) {
						map[i][j].add(new FireBall(m, s, k));
					}
				}
			}

		}

		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				while (!map[i][j].isEmpty()) {
					result += map[i][j].poll().m;
				}
			}
		}

		System.out.println(result);

	}

}
