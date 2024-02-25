// 거의 1초에 가깝게 풀림
// 1005. ACM Craft
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int T, N, K, W;
	public static int[][] arr;
	public static int[] D, dp;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int tc=0 ; tc<T ; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N]; // 인접행렬
			D = new int[N];
			dp = new int[N];
			st = new StringTokenizer(br.readLine());
			for(int i=0 ; i<N ; i++) {
				D[i] = Integer.parseInt(st.nextToken());
				dp[i] = D[i];
			}
			int[] cnt = new int[N];
			for(int i=0 ; i<K ; i++) {
				st = new StringTokenizer(br.readLine());
				int str = Integer.parseInt(st.nextToken())-1; // 1부터 시작하는 인덱스 보정
				int end = Integer.parseInt(st.nextToken())-1;
				arr[str][end] = 1;
				cnt[end]++; // 진입차수 카운트
			}
			W = Integer.parseInt(br.readLine())-1;
			
			Queue<Integer> q = new LinkedList<>();
			for(int i=0 ; i<N ; i++) {
				if(cnt[i]==0) {
					q.add(i);
				}
			}
			while(!q.isEmpty()) {
				int tmp = q.remove();
				for(int i=0 ; i<N ; i++) {
					if(arr[tmp][i]==1) {
						dp[i] = Math.max(dp[tmp]+D[i], dp[i]);
						cnt[i]--;
						if(cnt[i]==0)
							q.add(i);
					}
				}
			}
			sb.append(dp[W]).append("\n");
		} // tc
		System.out.println(sb.toString());
	}
}
