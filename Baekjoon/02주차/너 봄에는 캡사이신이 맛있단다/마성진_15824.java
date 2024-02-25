import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int divNum = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.parallelSort(arr);
		long answer = 0;
		for (int i = 0; i < N; i++) {
			answer += arr[i] * customPow(2, i);
			answer -= arr[i] * customPow(2, N - i - 1);
			answer %= divNum;
		}
		System.out.println(answer);
	}

	public static long customPow(int num, int x) {
		if (x == 0)
			return 1L;
		long half = customPow(num, x / 2);
		if (x % 2 == 0)
			return half * half % divNum;
		return half * half * num % divNum;
	}

}
