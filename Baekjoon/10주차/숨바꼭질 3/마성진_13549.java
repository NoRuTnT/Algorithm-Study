import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, K;
	public static boolean[] visited;
	public static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100001];

		bfs();

		System.out.println(min);
	}

	public static void bfs() {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { N, 0 });

		while (!que.isEmpty()) {
			int[] arr = que.poll();
			int now = arr[0];
			int time = arr[1];
			visited[now] = true;
			if (now == K) {
				min = Math.min(min, time);
			}
			if (now * 2 <= 100000 && !visited[now * 2]) {
				que.add(new int[] { now * 2, time });
			}
			if (now + 1 <= 100000 && !visited[now + 1]) {
				que.add(new int[] { now + 1, time + 1 });
			}
			if (now - 1 >= 0 && !visited[now - 1]) {
				que.add(new int[] { now - 1, time + 1 });
			}
		}
	}

}
