import java.util.Scanner;

public class 김태윤_2098 {
	static int[][] dp;
	static int[][] cost;
	static int n;
	static final int MAX=Integer.MAX_VALUE;
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		cost=new int[n][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				cost[i][j]=sc.nextInt();
			}
		}
		
		dp=new int[(1<<n)][n]; //이미 방문한 곳 , 현재 위치
		
		recursion (1,0); // 방문한 곳(비트마스킹), 출발 위치 <- 최소가 되는 경로는 순환하고, 하나라서 어디든 상관없음
		System.out.println(dp[1][0]);
	}
	public static int recursion (int visited, int curr) {
		if(visited==(1<<n)-1) {
			if(cost[curr][0]>0) return cost[curr][0];
			else return 987654321;
		}
		if(dp[visited][curr]>0) return dp[visited][curr];
		dp[visited][curr]=987654321;
		for(int i=0;i<n;i++) { //다음으로 이동할 곳
			if((visited&(1<<i))>0) continue; //이미 방문한 경우
			if(cost[curr][i]==0) continue; // 안 이어진 경우
			dp[visited][curr]=Math.min(dp[visited][curr], recursion(visited|(1<<i), i)+cost[curr][i]);
		}	
		return dp[visited][curr];
	}
}
