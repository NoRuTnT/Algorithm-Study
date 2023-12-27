// 20040. 사이클 게임
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int n, m, ans;
	public static int[] set;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		set = new int[n];
		for (int i = 0; i < n; i++) {
			set[i] = i;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (findset(a) != findset(b))
				union(a, b);
			else {
				ans = i + 1;
				break;
			}
		}
		System.out.println(ans);
	}

	public static int findset(int x) {
		if (x != set[x])
			set[x] = findset(set[x]);
		return set[x];
	}

	public static void union(int x, int y) {
		int x_root = findset(x);
		int y_root = findset(y);
		if (x_root < y_root)
			set[y_root] = x_root;
		else
			set[x_root] = y_root;
	}
}
