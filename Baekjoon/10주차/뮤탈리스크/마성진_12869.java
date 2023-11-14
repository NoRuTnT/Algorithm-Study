import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N;
	public static int[] scv;
	public static int[][][] dp;
	public static boolean[][][] visited;
	public static int[][] damage = { { 9, 3, 1 }, { 9, 1, 3 }, { 3, 9, 1 }, { 3, 1, 9 }, { 1, 3, 9 }, { 1, 9, 3 } };

	public static class SCV {

		int s1;
		int s2;
		int s3;
		int cnt;

		SCV(int s1, int s2, int s3, int cnt) {
			this.s1 = s1;
			this.s2 = s2;
			this.s3 = s3;
			this.cnt = cnt;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		scv = new int[3];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			scv[i] = Integer.parseInt(st.nextToken());
		}

		bfs();

	}

	public static void bfs() {
		dp = new int[61][61][61];
		visited = new boolean[61][61][61];
		Queue<SCV> que = new LinkedList<>();
		visited[0][0][0] = true;
		que.add(new SCV(0, 0, 0, 0));

		while (!que.isEmpty()) {
			SCV now = que.poll();

			for (int i = 0; i < 6; i++) {
				int nextS1 = now.s1 + damage[i][0];
				int nextS2 = now.s2 + damage[i][1];
				int nextS3 = now.s3 + damage[i][2];
				if (nextS1 > 60) {
					nextS1 = 60;
				}
				if (nextS2 > 60) {
					nextS2 = 60;
				}
				if (nextS3 > 60) {
					nextS3 = 60;
				}
				if (visited[nextS1][nextS2][nextS3]) {
					continue;
				}
				if (nextS1 >= scv[0] && nextS2 >= scv[1] && nextS3 >= scv[2]) {
					System.out.println(now.cnt + 1);
					return;
				}
				visited[nextS1][nextS2][nextS3] = true;
				que.add(new SCV(nextS1, nextS2, nextS3, now.cnt + 1));
			}

		}

	}

}
