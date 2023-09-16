import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static int N, M, K;
	public static int[][] map;
	public static List<int[]> cctvList;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static int[] dir;
	public static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		cctvList = new ArrayList<>();
		K = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6) {
					cctvList.add(new int[] { map[i][j], i, j });
				}
			}
		}
		dir = new int[cctvList.size()];

		comb(0);
		System.out.println(result);

	}

	private static void comb(int depth) {
		if (depth == cctvList.size()) {
			cal();
			return;
		}
		int[] cctv = cctvList.get(depth);
		if (cctv[0] == 1 || cctv[0] == 3 || cctv[0] == 4) {
			for (int j = 0; j < 4; j++) {
				dir[depth] = j;
				comb(depth + 1);
			}
		} else if (cctv[0] == 2) {
			for (int j = 0; j < 2; j++) {
				dir[depth] = j;
				comb(depth + 1);
			}
		} else {
			dir[depth] = 0;
			comb(depth + 1);
		}
	}

	private static void cal() {
		int[][] resultMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				resultMap[i][j] = map[i][j];
			}
		}
		for (int i = 0; i < cctvList.size(); i++) {
			int[] cctv = cctvList.get(i);
			int startI = cctv[1];
			int startJ = cctv[2];
			if (cctv[0] == 1) {
				int nextI = startI + iDir[dir[i]];
				int nextJ = startJ + jDir[dir[i]];
				while (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < M && map[nextI][nextJ] != 6) {
					resultMap[nextI][nextJ] = 7;
					nextI += iDir[dir[i]];
					nextJ += jDir[dir[i]];
				}
			} else if (cctv[0] == 2) {
				for (int j = 0; j < 4; j += 2) {
					int direction = (dir[i] + j) % 4;
					int nextI = startI + iDir[direction];
					int nextJ = startJ + jDir[direction];
					while (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < M && map[nextI][nextJ] != 6) {
						resultMap[nextI][nextJ] = 7;
						nextI += iDir[direction];
						nextJ += jDir[direction];
					}
				}
			} else if (cctv[0] == 3 || cctv[0] == 4 || cctv[0] == 5) {
				int round = cctv[0] - 1;
				for (int j = 0; j < round; j++) {
					int direction = (dir[i] + j) % 4;
					int nextI = startI + iDir[direction];
					int nextJ = startJ + jDir[direction];
					while (nextI >= 0 && nextJ >= 0 && nextI < N && nextJ < M && map[nextI][nextJ] != 6) {
						resultMap[nextI][nextJ] = 7;
						nextI += iDir[direction];
						nextJ += jDir[direction];
					}
				}
			}
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (resultMap[i][j] == 0)
					cnt++;
			}
		}
		result = Math.min(cnt, result);
	}

}
