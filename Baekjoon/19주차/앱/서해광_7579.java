// 7579. 앱
// 설명을 위해 주석을 남긴 버전
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int ans=Integer.MAX_VALUE;
	static int[] A, c;
	static int[][][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		A=new int[N+1];
		c=new int[N+1];
		dp=new int[N+1][10000+1][2]; // 100x100
		st = new StringTokenizer(br.readLine());
		for(int i=1 ; i<=N ; i++) {
			A[i]=Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=1 ; i<=N ; i++) {
			c[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=1 ; i<=N ; i++) {
			// j=1 => j=0
			for(int j=0 ; j<=10000 ; j++) {
				if(j<c[i]) {
					dp[i][j][0]=dp[i-1][j][0]; // [0] 그 칸의 c
					dp[i][j][1]=dp[i-1][j][1]; // [1] 그 칸의 m
				}else {
					dp[i][j][0]=Math.max(dp[i-1][j][0], dp[i-1][j-c[i]][0]+c[i]);
					dp[i][j][1]=Math.max(dp[i-1][j][1], dp[i-1][j-c[i]][1]+A[i]);
				}
			}
		}
		// i=1 => i=0
		for(int i=0 ; i<=10000 ; i++) {
			if(dp[N][i][1]>=M) {
				ans=i;
				break;
			}
		}
		System.out.println(ans);
	} // main
	
}
// 하나의 요소를 선택하고 안 선택하고에 따라 퍼져 나가는 dp
// 같은 dp 칸에서는 거기까지 오기까지 최소 값을 배열에 담으면 된다.

// 각 배열에 들어있는 성분에 해당하는 메모리용량은 Max로 집계해야 됨.
//       1   2   3   4   5   6   7   8   9   10
//0 (0)  0   0   0   0   0   0   0   0   0   0
//30(3)  0   0   30  30  30  30  30  30  30  30
//10(0)  10  10  40  40  40  40  40  40  40  40
//20(3)  10  10  40  40  40  60  60  60  60  60 
//35(5)  10  10  40  40  40 
//40(4)

