import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		int n=Integer.parseInt(br.readLine());
		int[][] rgb=new int[n][3];
		int[][] dp=new int[n][3];
		for(int i=0;i<n;i++) {
			StringTokenizer st=new StringTokenizer(br.readLine());
			rgb[i][0]=Integer.parseInt(st.nextToken());
			rgb[i][1]=Integer.parseInt(st.nextToken());
			rgb[i][2]=Integer.parseInt(st.nextToken());
		}
		dp[0][0]=rgb[0][0];
		dp[0][1]=rgb[0][1];
		dp[0][2]=rgb[0][2];
		for(int i=1;i<n;i++) {
			for(int j=0;j<3;j++) {
				dp[i][j]=rgb[i][j]+Integer.min(dp[i-1][(j+1)%3], dp[i-1][(j+2)%3]);
			}
		}
		int min=Integer.MAX_VALUE;
		for(int i=0;i<3;i++) {
			min=Integer.min(dp[n-1][i], min);
		}
		System.out.println(min);
		br.close();
	}
}
