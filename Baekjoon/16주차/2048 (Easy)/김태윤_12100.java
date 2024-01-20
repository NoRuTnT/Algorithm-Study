import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int board[][] = new int[20][20];
	static int n;
	static int max = -1;
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
	}

	public static void recursion(int num, int[][] brd) {
		int[][] tmpbrd = new int[20][20];
		if (num == 5) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (brd[i][j] > max)
						max = brd[i][j];
				}
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < n; j++) {
				tmpbrd[j] = brd[j].clone();
			}
			blank(i,tmpbrd);
			combine(i, tmpbrd);
			blank(i, tmpbrd);
			recursion(num + 1, tmpbrd);
		}
	}

	public static void blank(int act, int[][] brd) {
		switch (act) {
		case 0: // 위로
			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j < n; j++) {
					if (brd[i][j] == 0) {
						for (int k = i; k < n - 1; k++) {
							brd[k][j] = brd[k + 1][j];
							brd[k + 1][j] = 0;
						}
					}
				}
			}
			break;
		case 1: // 우로
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < n; j++) {
					if (brd[i][j] == 0) {
						for (int k = j; k > 0; k--) {
							brd[i][k] = brd[i][k - 1];
							brd[i][k - 1] = 0;
						}
					}
				}
			}
			break;
		case 2: // 아래로
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (brd[i][j] == 0) {
						for (int k = i; k > 0; k--) {
							brd[k][j] = brd[k - 1][j];
							brd[k - 1][j] = 0;
						}
					}
				}
			}
			break;
		case 3: // 좌로
			for (int i = 0; i < n; i++) {
				for (int j = n - 2; j >= 0; j--) {
					if (brd[i][j] == 0) {
						for (int k = j; k < n - 1; k++) {
							brd[i][k] = brd[i][k + 1];
							brd[i][k + 1] = 0;
						}
					}
				}
			}
			break;
		}
	}

	public static void combine(int act, int[][] brd) {
		switch (act) {
		case 0: // 위로 밀기
			for (int i = 1; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (brd[i + dy[act]][j + dx[act]] == brd[i][j]) {
						brd[i + dy[act]][j + dx[act]] *= 2;
						brd[i][j] = 0;
					}
				}
			}
			break;
		case 1: // 우로 밀기
			for (int i = 0; i < n; i++) {
				for (int j = n - 2; j >= 0; j--) {
					if (brd[i + dy[act]][j + dx[act]] == brd[i][j]) {
						brd[i + dy[act]][j + dx[act]] *= 2;
						brd[i][j] = 0;
					}
				}
			}
			break;
		case 2: // 아래로 밀기
			for (int i = n - 2; i >= 0; i--) {
				for (int j = 0; j < n; j++) {
					if (brd[i + dy[act]][j + dx[act]] == brd[i][j]) {
						brd[i + dy[act]][j + dx[act]] *= 2;
						brd[i][j] = 0;
					}
				}
			}
			break;
		case 3:// 좌로 밀기
			for (int i = 0; i < n; i++) {
				for (int j = 1; j < n; j++) {
					if (brd[i + dy[act]][j + dx[act]] == brd[i][j]) {
						brd[i + dy[act]][j + dx[act]] *= 2;
						brd[i][j] = 0;
					}
				}
			}
			break;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		recursion(0, board);
		System.out.println(max);
	}

}
