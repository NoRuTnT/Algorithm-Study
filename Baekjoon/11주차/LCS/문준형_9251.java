package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class b9251 {
	static int[][]dp;
	static char[] str,str1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toCharArray();
		str1 = br.readLine().toCharArray();
		dp = new int [str.length+1][str1.length+1];
		for(int i=0;i<str.length+1;i++) {
			Arrays.fill(dp[i],-1); //-1로 초기화
		}		
		System.out.println(find(str.length-1,str1.length-1));
	}

	private static int find(int i, int j) {
		if(i<0 || j<0) {
			return 0;
		}
		if(dp[i][j]==-1) { //한번도 방문안한 dp배열이면
			dp[i][j]=0; //+1시키므로 0으로 바꿔줌
			if(str[i]==str1[j]) { //비교문자열이 같을때
				dp[i][j] = find(i-1,j-1)+1; //dp[i-1][j-1]보다 공통수열갯수 1늘려줌
			}else {
				dp[i][j] = Math.max(find(i-1,j), find(i,j-1)); //같지않으면 그냥 저장해두었던것중 가장긴것가져옴
			}	
		}		
		return dp[i][j];
	}

}
