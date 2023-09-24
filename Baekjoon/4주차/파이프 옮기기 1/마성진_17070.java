import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static int[][] map;
	public static Queue<int[]> que;
	public static int[] iDir = { 0, 1, 1 };
	public static int[] jDir = { 1, 1, 0 };
	public static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		if(map[N-1][N-1] != 1) {
			bfs();
		}
		System.out.println(result);

	}

	private static void bfs() {
		que = new LinkedList<>();
		que.add(new int[] { 0, 1, 0 });
		while (!que.isEmpty()) {
			int[] now = que.poll();
			if (now[2] == 0) {
				comb(0, 2, now);
			} else if (now[2] == 1) {
				comb(0, 3, now);
			} else {
				comb(1, 3, now);
			}
		}
	}

	private static void comb(int start, int end, int[] now) {
		for (int i = start; i < end; i++) {
			int nextI = now[0] + iDir[i];
			int nextJ = now[1] + jDir[i];
			if (nextI < N && nextJ < N) {
				boolean flag = false;
				if (i == 0 || i == 2) {
					if (map[nextI][nextJ] == 1) {
						flag = true;
					}
				} else {
					for (int j = 0; j < 3; j++) {
						if (map[now[0] + iDir[j]][now[1] + jDir[j]] == 1) {
							flag = true;
							break;
						}
					}
				}
				if (flag) {
					continue;
				}
				if (nextI == N - 1 && nextJ == N - 1) {
					result++;
					return;
				}
				que.add(new int[] { nextI, nextJ, i });
			}
		}
	}

}
