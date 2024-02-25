import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int N;
	static List<Integer> nums = new ArrayList<>();
	static List<Character> ops = new ArrayList<>();
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		String t = br.readLine();
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				nums.add(t.charAt(i) - '0');
			} else {
				ops.add(t.charAt(i));
			}
		}
		int start = nums.get(0);
		dfs(0, start);
		System.out.println(max);
	}

	public static void dfs(int now, int sum) {
		if (now >= ops.size()) {
			max = Math.max(max, sum);
			return;
		}
		// 괄호 안치고 진행
		dfs(now + 1, cal(now, sum, nums.get(now + 1)));
		// 괄호 치고 진행
		if (now + 1 < ops.size()) {
			dfs(now + 2, cal(now, sum, cal(now + 1, nums.get(now + 1), nums.get(now + 2))));
		}
	}

	public static int cal(int opIdx, int a, int b) {
		switch (ops.get(opIdx)) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		}
		return 1;
	}
}
