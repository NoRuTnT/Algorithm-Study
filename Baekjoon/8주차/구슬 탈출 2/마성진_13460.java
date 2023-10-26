import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static char[][] map;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };
	public static boolean[][][] visited;
	public static int redI, redJ, blueI, blueJ, holeI, holeJ;
	public static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'B') {
					blueI = i;
					blueJ = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'R') {
					redI = i;
					redJ = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'O') {
					holeI = i;
					holeJ = j;
				}
			}
		}
		visited = new boolean[N][M][3];
		result = 11;
		bfs();
		if (result == 11) {
			result = -1;
		}
		System.out.println(result);
	}

	public static void bfs() {
		Queue<int[][]> que = new LinkedList<>();
		que.add(new int[][] { { redI, redJ }, { blueI, blueJ }, { 0 } });
		while (!que.isEmpty()) {
			int[][] now = que.poll();
			for (int i = 0; i < 4; i++) {
				int nri = now[0][0];
				int nrj = now[0][1];
				int nbi = now[1][0];
				int nbj = now[1][1];
				int cnt = now[2][0];
				if (cnt >= result) {
					break;
				}

				if (map[nri + iDir[i]][nrj + jDir[i]] != '#' || map[nbi + iDir[i]][nbj + jDir[i]] != '#') {
					boolean flag = true;
					boolean find = false;

					while (map[nri][nrj] == '.') {
						nri += iDir[i];
						nrj += jDir[i];
						if (map[nri][nrj] == 'O') {
							find = true;
						}
					}
					while (map[nbi][nbj] == '.') {
						nbi += iDir[i];
						nbj += jDir[i];
						if (map[nbi][nbj] == 'O') {
							flag = false;
						}
					}
					nri -= iDir[i];
					nrj -= jDir[i];
					nbi -= iDir[i];
					nbj -= jDir[i];
					if (nri == nbi && nrj == nbj) {
						if (i == 0) {
							if (now[0][0] > now[1][0]) {
								nri -= iDir[i];
							} else {
								nbi -= iDir[i];
							}
						} else if (i == 1) {
							if (now[0][1] > now[1][1]) {
								nbj -= jDir[i];
							} else {
								nrj -= jDir[i];
							}
						} else if (i == 2) {
							if (now[0][0] > now[1][0]) {
								nbi -= iDir[i];
							} else {
								nri -= iDir[i];
							}
						} else {
							if (now[0][1] > now[1][1]) {
								nrj -= jDir[i];
							} else {
								nbj -= jDir[i];
							}
						}
					}
					if (flag && !find) {
						que.add(new int[][] { { nri, nrj }, { nbi, nbj }, { cnt + 1 } });
					} else if (flag && find) {
						result = Math.min(result, cnt + 1);
					}

				}

			}
		}
	}

}
