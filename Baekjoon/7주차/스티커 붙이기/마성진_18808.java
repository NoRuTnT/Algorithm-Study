import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static int N, M, K;
	public static int[][] notebook;
	public static List<int[][]> stickers;
	public static int result;
	public static int rSize, cSize;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		notebook = new int[N][M];
		stickers = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int[][] tmp = new int[r][c];
			for (int j = 0; j < r; j++) {
				st = new StringTokenizer(br.readLine());
				for (int j2 = 0; j2 < c; j2++) {
					tmp[j][j2] = Integer.parseInt(st.nextToken());
				}
			}
			stickers.add(tmp);
		}
		result = 0;
		start();
		System.out.println(result);
	}

	public static void start() {
		for (int i = 0; i < K; i++) {
			int[][] sticker = stickers.get(i);
			rSize = sticker[0].length;
			cSize = sticker.length;
			rotate: for (int j = 0; j < 4; j++) {
				for (int x = 0; x < N; x++) {
					for (int y = 0; y < M; y++) {
						int flag = check(sticker, x, y);
						if (flag == 0) {
							paste(sticker, x, y);
							break rotate;
						}
					}
				}
				sticker = rotate(sticker);
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				result += notebook[i][j];
			}
		}
	}

	private static int[][] rotate(int[][] sticker) {
		int tmp = rSize;
		rSize = cSize;
		cSize = tmp;
		int[][] stmp = new int[cSize][rSize];
		for (int i = 0; i < rSize; i++) {
			for (int j = 0; j < cSize; j++) {
				stmp[j][rSize - i - 1] = sticker[i][j];
			}
		}
		return stmp;
	}

	private static void paste(int[][] sticker, int x, int y) {
		for (int i = 0; i < cSize; i++) {
			for (int j = 0; j < rSize; j++) {
				if (sticker[i][j] == 0) {
					continue;
				}
				notebook[i + x][j + y] = sticker[i][j];
			}
		}
	}

	public static int check(int[][] sticker, int x, int y) {
		if (cSize + x > N || rSize + y > M) {
			return 2;
		}
		for (int i = 0; i < cSize; i++) {
			for (int j = 0; j < rSize; j++) {
				if (sticker[i][j] == 1 && notebook[i + x][j + y] == 1) {
					return 1;
				}
			}
		}
		return 0;
	}

}
