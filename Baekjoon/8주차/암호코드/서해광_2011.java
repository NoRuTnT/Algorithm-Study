// 2011. 암호코드
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String code = br.readLine();
		int N = code.length();
		dp = new int[N+1];
		if(code.charAt(0)=='0') {
			System.out.println(0);
			return;
		}
		if(N==1) {
			System.out.println(1);
			return;
		}
		dp[0]=1;
		for(int i=1 ; i<=N ; i++) {
			int n = code.charAt(i-1)-'0';
			if(1<=n && n<=9) {
				dp[i] = (dp[i-1]+dp[i])%1000000;
			}
			if(i == 1) continue;
			int temp = Integer.parseInt(code.substring(i-2,i));
			if(10<=temp && temp<=26) {
				dp[i] = (dp[i-2]+dp[i])%1000000;
			}
		}
		
		System.out.println(dp[N]);
	}
}
