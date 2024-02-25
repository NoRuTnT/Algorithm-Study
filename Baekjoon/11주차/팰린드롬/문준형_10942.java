package gold;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class b10942 {
	static int n,m;
	static int[] num;
	static int[][] dp;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		num = new int[n+1]; 
		dp = new int[n+1][n+1];		
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			num[i]=Integer.parseInt(st.nextToken());
		}
		makedp();
		m = Integer.parseInt(br.readLine());
		for(int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			bw.write(answer(s,e)+"\n");
		}
		bw.flush();
		br.close();
		bw.close();
		
	}
	//dp배열초기화
	private static void makedp() {
		for(int i=1;i<=n;i++) {
			Arrays.fill(dp[i],-1);
			dp[i][i]=1; //수가 1개일때는 무조건회문구조
		}
		for(int i=1;i<n;i++) {
			if(num[i]==num[i+1]) { //2개일때는 모두같은수일때 회문구조 
				dp[i][i+1]=1;
			}else {
				dp[i][i+1]=0;
			}
		}		
	}
	
	private static int answer(int i, int j) {		
		if(dp[i][j]!=-1) {
			return dp[i][j]; //이미dp배열에 저장된곳이면 꺼내서사용
		}		
		if(num[i]==num[j] && answer(i+1,j-1)==1 ) { //맨앞뒤가 같고 그사이의수가 회문구조면 
			dp[i][j]=1; //i부터j도 회문구조, dp배열에저장
			return 1;
		}else {
			dp[i][j]=0;
			return 0;			
		}
	}

}
