import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static ArrayList<Integer>[] list;
	public static int[] plan;
	public static String result = "NO";
	public static boolean flag = true;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		list = new ArrayList[N];
		plan = new int[M];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
			list[i].add(i);
		}
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(st.nextToken()) == 1) {
					list[i].add(j);
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		for (int i = 0; i < M - 1; i++) {
			if(plan[i] == plan[i+1]) {
				flag = true;
				result = "YES";
			} else {
				if (flag) {
					bfs(plan[i], plan[i + 1]);
				} else {
					result = "NO";
					break;
				}
			}
		}

		System.out.println(result);

	}

	private static void bfs(int start, int finish) {
		boolean[] visited = new boolean[N];
		Queue<Integer> que = new LinkedList<>();
		que.add(start);
		visited[start] = true;
		flag = false;
		loop: while (!que.isEmpty()) {
			int now = que.poll();
			for (int i = 0; i < list[now].size(); i++) {
				if(visited[list[now].get(i)]) {
					continue;
				}
				visited[list[now].get(i)] = true;
				if (list[now].get(i) == finish) {
					result = "YES";
					flag = true;
					break loop;
				}
				que.add(list[now].get(i));
			}
		}

	}

}
