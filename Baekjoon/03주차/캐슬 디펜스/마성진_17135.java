import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N, M, D;
	public static int[][] originalMap;
	public static int[][] copyMap;
	public static int[][] shoots;
	public static int result = Integer.MIN_VALUE;
	public static int kill;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		originalMap = new int[N + 1][M];
		copyMap = new int[N + 1][M];
		shoots = new int[3][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				originalMap[i][j] = Integer.parseInt(st.nextToken());
				if (originalMap[i][j] == 1) {
				}
			}
		}

		comb(0, 0);
		System.out.println(result);
	}

	private static void comb(int depth, int start) {
		if (depth == 3) {
			for (int i = 0; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					copyMap[i][j] = originalMap[i][j];
				}
			}
			kill = 0;
			gameStart();
			result = Math.max(result, kill);
			return;
		}
		for (int i = start; i <= M - 3 + depth; i++) {
			originalMap[N][i] = 2;
			comb(depth + 1, i + 1);
			originalMap[N][i] = 0;
		}

	}

	private static void gameStart() {
		for (int round = 0; round < N; round++) {
			int location = 0;
			for (int i = 0; i < M; i++) {
				if (copyMap[N][i] == 2) {
					findFrom(i, location++);
				}
			}
			shoot();
			move();
		}
	}

	private static void move() {
		for (int i = N - 2; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				copyMap[i + 1][j] = copyMap[i][j];
			}
		}
		for (int i = 0; i < M; i++) {
			copyMap[0][i] = 0;
		}
	}

	private static void shoot() {
		for (int i = 0; i < 3; i++) {
			if (shoots[i][0] == -1 && shoots[i][1] == -1) {
				continue;
			}
			if(copyMap[shoots[i][0]][shoots[i][1]] == 1) {
				copyMap[shoots[i][0]][shoots[i][1]] = 0;
				kill++;
			}
		}
	}

	private static void findFrom(int v, int location) {
		int minDistance = 11;
		int distance = 0;
		boolean flag = false;
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				distance = Math.abs(i - N) + Math.abs(j - v);
				if (distance > D) {
					continue;
				}
				if (copyMap[i][j] == 1) {
					if (distance < minDistance) {
						minDistance = distance;
						shoots[location][0] = i;
						shoots[location][1] = j;
						flag = true;
					} else if (distance == minDistance) {
						if (shoots[location][1] > j) {
							shoots[location][0] = i;
							shoots[location][1] = j;
							flag = true;
						}
					}
				}
			}
		}
		if (!flag) {
			shoots[location][0] = -1;
			shoots[location][1] = -1;
		}
	}

}
