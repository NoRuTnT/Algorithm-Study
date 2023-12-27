// 1799. 비숍
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int[][] arr;
	public static int[] ans;
	public static boolean[]visited1, visited2;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited1 = new boolean[20];
		visited2 = new boolean[20];
		arr = new int[N][N];
		ans = new int[2];
		for(int i=0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0, 0, 0);
		dfs(1, 0, 0, 1);
		System.out.println(ans[0]+ans[1]);
	}
	public static void dfs(int r, int c, int cnt, int isBlack) {
		if(r>=N) {
			c++;
			if(r%2==0) r=1;
			else r = 0;
		}
		if(c>=N) {
			ans[isBlack] = Math.max(ans[isBlack], cnt);
			return;
		}
		if(arr[c][r]==1 && !visited1[r+c+1] && !visited2[r-c+N]) {
			visited1[r+c+1] = true;
			visited2[r-c+N] = true;
			dfs(r+2, c, cnt+1, isBlack);
			visited1[r+c+1] = false;
			visited2[r-c+N] = false;
		}
		dfs(r+2, c, cnt, isBlack);
	}
}
