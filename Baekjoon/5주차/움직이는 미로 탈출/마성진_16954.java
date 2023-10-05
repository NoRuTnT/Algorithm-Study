import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static int N;
	public static char[][] chessMap;
	public static boolean[][] visited;
	public static int startI, startJ, result;
	public static int[] iDir = { -1, -1, 0, 1, 1, 1, 0, -1, 0 };
	public static int[] jDir = { 0, 1, 1, 1, 0, -1, -1, -1, 0 };

	public static void main(String[] args) throws IOException {
		input();
		bfs(startI, startJ);
		System.out.println(result);
	}

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 8;
		chessMap = new char[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				chessMap[i][j] = str.charAt(j);
			}
		}
		startI = 7;
		startJ = 0;
		result = 0;
	}

	private static void bfs(int x, int y) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { x, y });
		loop: while (!que.isEmpty()) {
			int r = que.size();
			for (int round = 0; round < r; round++) {
				visited = new boolean[N][N];
				int[] now = que.poll();
				if (chessMap[now[0]][now[1]] == '#') {
					continue;
				}
				for (int i = 0; i < 9; i++) {
					int nextI = now[0] + iDir[i];
					int nextJ = now[1] + jDir[i];
					if (nextI == 0 && nextJ == 7) {
						result = 1;
						break loop;
					}
					if (isVaild(nextI, nextJ)) {
						que.add(new int[] { nextI, nextJ });
						visited[nextI][nextJ] = true;
					}
				}
			}
			moveWall();
		}
	}

	public static boolean isVaild(int x, int y) {
		if (x >= 0 && y >= 0 && x < N && y < N && !visited[x][y] && chessMap[x][y] != '#') {
			return true;
		}
		return false;
	}

	private static void moveWall() {
		for (int i = N - 1; i >= 0; i--) {
			for (int j = 0; j < N; j++) {
				if (chessMap[i][j] == '#') {
					chessMap[i][j] = '.';
					if (i + 1 < 8) {
						chessMap[i + 1][j] = '#';
					}
				}
			}
		}
	}

}
