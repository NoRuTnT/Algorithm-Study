import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static int[][][] dp;
	public static ArrayList<Integer> list;
	public static int size;
	public static int[][] energy = { { 1, 2, 2, 2, 2 }, { 0, 1, 3, 4, 3 }, { 0, 3, 1, 3, 4 }, { 0, 4, 3, 1, 3 },
			{ 0, 3, 4, 3, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		list = new ArrayList<Integer>();
		while (true) {
			int input = Integer.parseInt(st.nextToken());
			if(input == 0) {
				break;
			}
			list.add(input);
		}
		size = list.size();
		dp = new int[size][5][5];
		System.out.println(search(0, 0, 0));
	}

	public static int search(int idx, int left, int right) {
		if (idx == size) {
			return 0;
		}
		if (dp[idx][left][right] != 0) {
			return dp[idx][left][right];
		}
		int next = list.get(idx);
		dp[idx][left][right] = Math.min(search(idx + 1, next, right) + energy[left][next],
				search(idx + 1, left, next) + energy[right][next]);
		return dp[idx][left][right];
	}

}
