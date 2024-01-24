import java.io.*;
import java.util.*;

public class Main {

	static int N;
	static int[][] board;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		result = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0);

		System.out.println(result);

	}

	static void dfs(int depth) {
		if (depth == 5) {
			find();
			return;
		}
		int[][] copyBoard = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copyBoard[i][j] = board[i][j];
			}
		}
		for (int i = 0; i < 4; i++) {
			move(i);
			dfs(depth + 1);
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					board[j][k] = copyBoard[j][k];
				}
			}
		}
	}

	static void move(int direction) {
		switch (direction) {
			// 상
			case 0:
				for (int i = 0; i < N; i++) {
					int index = 0;
					int block = 0;
					for (int j = 0; j < N; j++) {
						if (board[j][i] != 0) {
							if (block == board[j][i]) {
								board[index - 1][i] = block * 2;
								block = 0;
								board[j][i] = 0;
							} else {
								block = board[j][i];
								board[j][i] = 0;
								board[index][i] = block;
								index++;
							}
						}
					}
				}
				break;
			// 하
			case 1:
				for (int i = 0; i < N; i++) {
					int index = N - 1;
					int block = 0;
					for (int j = N - 1; j >= 0; j--) {
						if (board[j][i] != 0) {
							if (block == board[j][i]) {
								board[index + 1][i] = block * 2;
								block = 0;
								board[j][i] = 0;
							} else {
								block = board[j][i];
								board[j][i] = 0;
								board[index][i] = block;
								index--;
							}
						}
					}
				}
				break;
			// 좌
			case 2:
				for (int i = 0; i < N; i++) {
					int index = 0;
					int block = 0;
					for (int j = 0; j < N; j++) {
						if (board[i][j] != 0) {
							if (block == board[i][j]) {
								board[i][index - 1] = block * 2;
								block = 0;
								board[i][j] = 0;
							} else {
								block = board[i][j];
								board[i][j] = 0;
								board[i][index] = block;
								index++;
							}
						}
					}
				}
				break;
			// 우
			case 3:
				for (int i = 0; i < N; i++) {
					int index = N - 1;
					int block = 0;
					for (int j = N - 1; j >= 0; j--) {
						if (board[i][j] != 0) {
							if (block == board[i][j]) {
								board[i][index + 1] = block * 2;
								block = 0;
								board[i][j] = 0;
							} else {
								block = board[i][j];
								board[i][j] = 0;
								board[i][index] = block;
								index--;
							}
						}
					}
				}
				break;
		}
	}

	static void find() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				result = Math.max(result, board[i][j]);
			}
		}
	}
}
