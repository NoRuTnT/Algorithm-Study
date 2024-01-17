//1943. 동전 분배
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int N, sum;
	public static boolean[] dp;
	public static int[][] arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc=0 ; tc<3 ; tc++) {
			sum = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][2];
			for(int i=0 ; i<N ; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
				sum+=arr[i][0]*arr[i][1];
				
			}
			if(sum%2!=0) {
				System.out.println(0); // 홀수이면 애초에 불가능
				continue;
			}
//			for(int i=1 ; i<list.size() ; i++) {
//				for(int j=sum/2 ; j>=0 ; j--) {
//					if(dp[j]) continue;
//					if(j-list.get(i)<0) {
//						break;
//					}
//					if(list.get(i)==j || dp[j-list.get(i)]) {
//						dp[j]=true;
//					}
//					if(dp[sum/2]) {
//						flag = true;
//						break label;
//					}
//				}
//			}
			dp = new boolean[sum/2+1];
			dp[0] = true; // 중요
			boolean flag = false;
			label:
			for(int i=0 ; i<N ; i++) {
				for(int j=sum/2 ; j>=0 ; j--) {
					if(dp[j]) continue; // 위치를 바꿔도 괜찮을까?
					for(int k=1 ; k<=arr[i][1] ; k++) {
						if(j-k*arr[i][0]>=0)
							dp[j] = dp[j-k*arr[i][0]];
						if(dp[sum/2]) {
							flag = true;
							break label;
						}
					}
				}
			}
			System.out.println(flag?1:0);
		} // tc
	}
}
