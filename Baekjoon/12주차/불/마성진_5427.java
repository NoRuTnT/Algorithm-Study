import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int W, H;
	public static char[][] building;
	public static int result;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static Queue<int[]> fireSpot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < T; tc++) {
			st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			building = new char[H][W];
			fireSpot = new LinkedList<>();

			int startI = 0;
			int startJ = 0;

			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					building[i][j] = str.charAt(j);
					if (building[i][j] == '@') {
						startI = i;
						startJ = j;
					} else if (building[i][j] == '*') {
						fireSpot.add(new int[] { i, j });
					}
				}
			}

			bfs(startI, startJ);
		}

	}

	public static void bfs(int startI, int startJ) {
		Queue<int[]> que = new LinkedList<>();
		boolean[][] visited = new boolean[H][W];
		que.add(new int[] { startI, startJ });
		visited[startI][startJ] = true;
		result = 0;
		while (!que.isEmpty()) {
			int R = que.size();
			result++;
			fire();
			for (int r = 0; r < R; r++) {
				int[] now = que.poll();
				for (int i = 0; i < 4; i++) {
					int nextI = now[0] + iDir[i];
					int nextJ = now[1] + jDir[i];
					if (nextI < 0 || nextI >= H || nextJ < 0 || nextJ >= W) {
						System.out.println(result);
						return;
					}
					if (building[nextI][nextJ] != '#' && building[nextI][nextJ] != '*' && !visited[nextI][nextJ]) {
						que.add(new int[] { nextI, nextJ });
						visited[nextI][nextJ] = true;
					}
				}
			}
		}
		System.out.println("IMPOSSIBLE");
	}

	public static void fire() {

		int R = fireSpot.size();

		for (int i = 0; i < R; i++) {
			int[] fire = fireSpot.poll();
			for (int j = 0; j < 4; j++) {
				int fireI = fire[0] + iDir[j];
				int fireJ = fire[1] + jDir[j];
				if (fireI >= 0 && fireI < H && fireJ >= 0 && fireJ < W && building[fireI][fireJ] != '#'
						&& building[fireI][fireJ] != '*') {
					fireSpot.add(new int[] { fireI, fireJ });
					building[fireI][fireJ] = '*';
				}
			}
		}

	}

}
