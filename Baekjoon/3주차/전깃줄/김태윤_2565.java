import java.util.Scanner;

/*
 * 참고 블로그 :https://st-lab.tistory.com/138
 * 			 https://chanhuiseok.github.io/posts/algo-49/
 */

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int[][] lines=new int[n+1][2];
		for(int i=1;i<=n;i++) {
			lines[i][0]=sc.nextInt(); // 왼쪽
			lines[i][1]=sc.nextInt(); // 오른쪽
		}
		// 왼쪽 기준으로 정렬
		for(int i=1;i<n;i++) {
			for(int j=i+1;j<=n;j++) {
				if(lines[i][0]>lines[j][0]) {
					for(int k=0;k<=1;k++) {
						int tmp=lines[i][k];
						lines[i][k]=lines[j][k];
						lines[j][k]=tmp;
					}
				}
			}
		}
		// 이제 오른쪽 기준으로 최장 증가 부분 수열 (LIS)의 크기를 구하면 된다
		// 그 결과값은 남아있는 전깃줄의 수와 같다
		int[] dp=new int[n+1];
		// 
		int max=0;
		for(int i=1;i<=n;i++) {
			dp[i]=1;
			for(int j=1;j<i;j++) {
				if(lines[i][1]>lines[j][1]) dp[i]=Math.max(dp[i], dp[j]+1);
			}
			max= (max>dp[i]) ? max:dp[i];
		}
		
		//구해야 하는 답은 전체 전깃줄 수 - 남은 전깃줄 수
		System.out.println(n-max);
	}
}
