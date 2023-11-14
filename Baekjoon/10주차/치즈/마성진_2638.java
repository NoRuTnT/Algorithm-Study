import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M, time;
	public static int cheeze[][];
	public static Queue<int[]> que;
	public static boolean[][] visited;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cheeze = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				cheeze[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		time = 0;
		while (true) {
			Queue<int[]> q = new LinkedList<>();
			q.add(new int[] { 0, 0 });
			cheeze[0][0] = -1;
			while (!q.isEmpty()) {
				int[] now = q.poll();
				for (int i = 0; i < 4; i++) {
					int nextI = now[0] + iDir[i];
					int nextJ = now[1] + jDir[i];
					if (nextI < 0 || nextJ < 0 || nextI >= N || nextJ >= M) {
						continue;
					}
					if (cheeze[nextI][nextJ] == 0) {
						cheeze[nextI][nextJ] = -1;
						q.add(new int[] { nextI, nextJ });
					}
				}
			}
			Queue<int[]> deleteList = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int cnt = 0;
					if (cheeze[i][j] == 1) {
						for (int d = 0; d < 4; d++) {
							int nowI = i + iDir[d];
							int nowJ = j + jDir[d];
							if (nowI < 0 || nowJ < 0 || nowI >= N || nowJ >= M) {
								continue;
							}

							if (cheeze[nowI][nowJ] == -1) {
								cnt++;
								if (cnt >= 2)
									break;
							}
						}
						if (cnt >= 2) {
							deleteList.add(new int[] { i, j });
						}
					}
				}
			}
			if (deleteList.isEmpty()) {
				System.out.println(time);
				break;
			}

			time++;
			while (!deleteList.isEmpty()) {
				int[] idx = deleteList.poll();
				cheeze[idx[0]][idx[1]] = 0;
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (cheeze[i][j] == -1) {
						cheeze[i][j] = 0;
					}
				}
			}
		}
	}
}
