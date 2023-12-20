import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

	public static int N;
	public static int[] nums;
	public static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		nums = new int[N + 1];
		dp = new int[N + 1][N + 1];

		for (int i = 0; i < N; i++) {
			nums[i + 1] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(nums);

		int max = 1;

		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				max = Math.max(max, dp(i, j));
			}
		}

		System.out.println(max);

	}

	public static int dp(int i, int j) {
		if (i == j) {
			return 1;
		}

		if (dp[i][j] != 0) {
			return dp[i][j];
		}

		int target = 2*nums[j] - nums[i];
		int index = findIndex(target, j + 1);

		if (index == -1) {
			dp[i][j] = 2;
			return dp[i][j];
		} else {
			dp[i][j] = dp(j, index) + 1;
			return dp[i][j];
		}

	}

	public static int findIndex(int target, int start) {

		int left = start;
		int right = N;
		int mid = (left + right) / 2;

		while (left < right) {
			mid = (left + right) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		if (left <= N && nums[left] == target) {
			return left;
		}

		if (nums[mid] == target) {
			return mid;
		} else {
			return -1;
		}

	}

}
