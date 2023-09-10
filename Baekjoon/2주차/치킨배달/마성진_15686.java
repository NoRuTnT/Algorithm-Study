import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class node {
	int x;
	int y;

	public node(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {

	public static int N, M;
	public static int[][] map;
	public static List<node> chicken;
	public static List<node> person;
	public static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		chicken = new ArrayList<>();
		person = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					chicken.add(new node(i, j));
				} else if (map[i][j] == 1) {
					person.add(new node(i, j));
				}
			}
		}
		node[] arr = new node[M];
		boolean[] visited = new boolean[chicken.size()];
		perm(0, 0, arr, visited);

		System.out.println(result);

	}

	public static void perm(int depth, int start, node[] arr, boolean[] visited) {
		if (depth == M) {
			int cnt = 0;
			for (int i = 0; i < person.size(); i++) {
				if (map[person.get(i).x][person.get(i).y] == 1) {
					cnt += count(arr, person.get(i).x, person.get(i).y);
				}
			}
			if (cnt < result) {
				result = cnt;
			}
			return;
		}

		for (int i = start; i < chicken.size(); i++) {
			visited[i] = true;
			arr[depth] = chicken.get(i);
			perm(depth + 1, i + 1, arr, visited);
			visited[i] = false;
		}

	}

	private static int count(node[] arr, int x, int y) {
		int tmp = Integer.MAX_VALUE;
		for (int j2 = 0; j2 < arr.length; j2++) {
			int distance = Math.abs(x - arr[j2].x) + Math.abs(y - arr[j2].y);

			tmp = Math.min(tmp, distance);
		}
		return tmp;

	}

}
