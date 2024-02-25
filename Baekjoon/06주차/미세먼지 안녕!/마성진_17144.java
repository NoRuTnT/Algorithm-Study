package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static int R, C, T;
	public static int acuI, acdI;
	public static List<Node> mList;
	public static int[][] map;
	public static int[] iDir = { -1, 0, 1, 0 };
	public static int[] jDir = { 0, 1, 0, -1 };

	public static class Node {
		public int x;
		public int y;
		public int m;

		public Node(int x, int y, int m) {
			this.x = x;
			this.y = y;
			this.m = m;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		acuI = acdI = 0;

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					if (acuI == 0) {
						acuI = i;
					} else {
						acdI = i;
					}
				}
			}
		}

		for (int i = 0; i < T; i++) {
			bfs();
			move();
		}

		int sum = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1) {
					continue;
				}
				sum += map[i][j];
			}
		}

		System.out.println(sum);

	}

	public static void bfs() {
		mList = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != 0 && map[i][j] != -1) {
					int dust = (int) Math.floor(map[i][j] / 5);
					for (int dir = 0; dir < 4; dir++) {
						int nextI = i + iDir[dir];
						int nextJ = j + jDir[dir];
						if (nextI >= 0 && nextJ >= 0 && nextI < R && nextJ < C && map[nextI][nextJ] != -1) {
							mList.add(new Node(nextI, nextJ, dust));
							map[i][j] -= dust;
						}
					}
				}
			}
		}
		int size = mList.size();
		for (int i = 0; i < size; i++) {
			Node node = mList.get(i);
			map[node.x][node.y] += node.m;
		}

	}

	public static void move() {
		int tmpA, tmpB;

		int startI1 = acuI;
		int startJ1 = 1;
		tmpA = map[startI1][startJ1];
		map[startI1][startJ1] = 0;
		int[] dir1 = { 1, 0, 3, 2 };
		for (int i = 0; i < 4; i++) {
			while (true) {
				int nextI = startI1 + iDir[dir1[i]];
				int nextJ = startJ1 + jDir[dir1[i]];
				if (nextI >= 0 && nextJ >= 0 && nextI <= acuI && nextJ < C && map[nextI][nextJ] != -1) {
					tmpB = map[nextI][nextJ];
					map[nextI][nextJ] = tmpA;
					tmpA = tmpB;
					startI1 = nextI;
					startJ1 = nextJ;
				} else {
					break;
				}
			}
		}

		int startI2 = acdI;
		int startJ2 = 1;
		tmpA = map[startI2][startJ2];
		map[startI2][startJ2] = 0;
		int[] dir2 = { 1, 2, 3, 0 };
		for (int i = 0; i < 4; i++) {
			while (true) {
				int nextI = startI2 + iDir[dir2[i]];
				int nextJ = startJ2 + jDir[dir2[i]];
				if (nextI >= acdI && nextJ >= 0 && nextI < R && nextJ < C && map[nextI][nextJ] != -1) {
					tmpB = map[nextI][nextJ];
					map[nextI][nextJ] = tmpA;
					tmpA = tmpB;
					startI2 = nextI;
					startJ2 = nextJ;
				} else {
					break;
				}
			}
		}

	}

}
