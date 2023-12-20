// 9251. LCS
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static String strX, strY;
	public static int[][] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		strX = br.readLine();
		strY = br.readLine();
		int X = strX.length();
		int Y = strY.length();
		dp = new int[X+1][Y+1];
		for(int i=1 ; i<=X ; i++) {
			for(int j=1 ; j<=Y ; j++) {
				if(strX.charAt(i-1)==strY.charAt(j-1)) {
					dp[i][j]=dp[i-1][j-1]+1;
					// 같은 문자를 발견하면 바로 이전 dp배열에서 1을 추가해줌
				}else {
					dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
					// 다른 문자를 만나면 X쪽 또는 Y쪽에서 확장시킨 값 둘 중 최대값을 설정
				}
			}
		}
		System.out.println(dp[X][Y]);
	}
}
