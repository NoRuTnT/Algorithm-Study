import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, K;
	public static int cnt;
	public static int result;
	public static int visited[] = new int[100001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		if (N >= K) {
            System.out.println((N-K) + "\n1");
            return;
        }
		cnt = 0;
		result = 1000000;
		bfs();

		System.out.println(result);
		System.out.println(cnt);
	}

	private static void bfs() {
		Queue<Integer> que = new LinkedList<Integer>();

		que.add(N);
		visited[N] = 1;

		while (!que.isEmpty()) {
			int now = que.poll();

			if (result < visited[now])
				return;

			for (int i = 0; i < 3; i++) {
				int next;

				if (i == 0)
					next = now + 1;
				else if (i == 1)
					next = now - 1;
				else
					next = now * 2;

				if (next < 0 || next > 100000)
					continue;

				if (next == K) {
					result = visited[now];
					cnt++;
				}

				if (visited[next] == 0 || visited[next] == visited[now] + 1) {
					que.add(next);
					visited[next] = visited[now] + 1;
				}
			}
		}
	}
}
