import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	static int N;
	static Stack<Pair> stack = new Stack<>();
	static long result;

	static class Pair {
		int height;
		int cnt;

		Pair(int height, int cnt) {
			this.height = height;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			Pair pair = new Pair(n, 1);

			while (!stack.empty() && stack.peek().height <= n) {
				Pair pop = stack.pop();
				result += pop.cnt;
				if (pop.height == n)
					pair.cnt += pop.cnt;
			}

			if (!stack.empty())
				result++;

			stack.push(pair);
		}

		bw.write(result + "\n");
		bw.flush();
	}

}
