import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static int[] arr;
	public static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		
		makeSet();
		
		result = 0;

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = find(Integer.parseInt(st.nextToken()));
			int b = find(Integer.parseInt(st.nextToken()));
			if (a == b) {
				result = i + 1;
				break;
			} else {
				union(a, b);
			}
		}

		System.out.println(result);

	}

	public static void makeSet() {
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
	}

	public static void union(int a, int b) {
		if (a > b) {
			arr[a] = b;
		} else {
			arr[b] = a;
		}
	}

	public static int find(int x) {
		if (arr[x] == x) {
			return x;
		}
		return arr[x] = find(arr[x]);
	}

}
