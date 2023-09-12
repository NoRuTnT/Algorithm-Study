// 15824. 너 봄에는 캡사이신이 맛있단다 
// 부분점수 획득
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	public static long[] dp = new long[300001]; // 거듭제곱을 저장하기 위한 배열
	public static void main(String[] args) {
		dp[0]=1;
		dp[1]=2;
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long ans = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for(int i=0 ; i<N ; i++) {
			list.add(sc.nextInt());
		}
		Collections.sort(list); // 최대 최소의 차이를 구하는 문제이므로 정렬이 효과적
		for(int i=0 ; i<N-1 ; i++) {
			for(int j=i+1; j<N ; j++) {
				ans+=(list.get(j)-list.get(i))*power(j-i-1)%1000000007; 
        // 정렬후 시작지점(i)과 끝 부분(j)을 잡고 그 사이에 j-i-1개 원소가 들어갈 수 있으므로
        // 최대최소의 차이와 들어갈 수 있는 개수의 곱을 더해주기
			}
		}
		System.out.println(ans%1000000007);
	} // main
  // dp로 2의 거듭제곱을 구하는 메서드
	public static long power(int x) {
		if(x<2) return dp[x];
		if(dp[x]==0) {
			dp[x]=2*dp[x-1]%1000000007;
		}
		return dp[x];
	}
}
