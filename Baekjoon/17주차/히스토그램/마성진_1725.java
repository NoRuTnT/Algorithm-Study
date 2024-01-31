import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] arr, tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		tree = new int[N * 4];
		init(1, 0, N - 1);
		System.out.println(getMax(0, N - 1));
	}

	static void init(int node, int start, int end) {
		if (start == end) {
			tree[node] = start;
		} else {
			int mid = (start + end) / 2;
			init(node * 2, start, mid);
			init(node * 2 + 1, mid + 1, end);

			if (arr[tree[node * 2]] < arr[tree[node * 2 + 1]]) {
				tree[node] = tree[node * 2];
			} else {
				tree[node] = tree[node * 2 + 1];
			}
		}
	}

	static int getMax(int left, int right) {
		int m = query(0, N - 1, left, right, 1);

		int area = (right - left + 1) * arr[m];
		if (left <= m - 1) {
			int tmp = getMax(left, m - 1);
			if (area < tmp) {
				area = tmp;
			}
		}

		if (m + 1 <= right) {
			int tmp = getMax(m + 1, right);
			if (area < tmp) {
				area = tmp;
			}
		}
		return area;
	}

	static int query(int start, int end, int left, int right, int node) {
		if (left > end || right < start)
			return -1;
		if (left <= start && end <= right) {
			return tree[node];
		}

		int mid = (start + end) / 2;
		int leftNode = query(start, mid, left, right, node * 2);
		int rightNode = query(mid + 1, end, left, right, node * 2 + 1);

		if (leftNode == -1) {
			return rightNode;
		} else if (rightNode == -1) {
			return leftNode;
		} else {
			if (arr[leftNode] <= arr[rightNode])
				return leftNode;
			else
				return rightNode;
		}
	}

}
