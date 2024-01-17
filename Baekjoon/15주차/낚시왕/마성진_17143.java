import java.io.*;
import java.util.*;

public class Main {

	static int R, C, M;
	static Shark[][] sharks;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};    // 위 아래 오른쪽 왼쪽

	static class Shark {
		int s, d, z;

		public Shark(int s, int d, int z) {
			this.s = s;
			this.d = d;
			this.z = z;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		sharks = new Shark[R][C];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks[r][c] = new Shark(s, d, z);
		}

		int result = 0;

		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				if (sharks[j][i] != null) {
					result += sharks[j][i].z;
					sharks[j][i] = null;
					break;
				}
			}

			Shark[][] nextSharks = new Shark[R][C];

			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					Shark shark = sharks[j][k];
					if (shark == null) {
						continue;
					}
					int tmp = R;
					if (shark.d > 2) {
						tmp = C;
					}
					int distance = shark.s % ((tmp - 1) * 2);
					int r = j;
					int c = k;
					int d = shark.d;
					for (int l = 0; l < distance; l++) {
						r += dr[d - 1];
						c += dc[d - 1];
						if (r >= 0 && c >= 0 && r < R && c < C) {
							continue;
						}
						if (d == 1 || d == 3) {
							d += 1;
						} else {
							d -= 1;
						}
						r += dr[d - 1] * 2;
						c += dc[d - 1] * 2;
					}

					shark.d = d;

					if (nextSharks[r][c] != null && nextSharks[r][c].z > shark.z) {
						continue;
					}
					nextSharks[r][c] = shark;
				}
			}
			for (int j = 0; j < R; j++) {
				for (int k = 0; k < C; k++) {
					sharks[j][k] = nextSharks[j][k];
				}
			}
		}

		System.out.println(result);

	}

}
