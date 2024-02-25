import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();

		int dp[] = new int[str.length() + 1];
		dp[0] = 1;

		for (int i = 1; i <= str.length(); i++) {
			int num1 = str.charAt(i - 1) - '0';
			if (num1 != 0) {
				dp[i] += dp[i - 1];
				dp[i] %= 1000000;
			}

			if (i == 1)
				continue;

			int num2 = str.charAt(i - 2) - '0';

			if (num2 == 0)
				continue;

			int num3 = num2 * 10 + num1;

			if (num3 >= 10 && num3 <= 26) {
				dp[i] += dp[i - 2];
				dp[i] %= 1000000;
			}
		}
		System.out.println(dp[str.length()]);
	}
}
