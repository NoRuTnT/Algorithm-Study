// 1976. 여행 가자
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main {
	public static int N, M;
	public static String ans = "YES";
	public static int[][] arr;
	public static int[] plan;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine()); // 인덱스는 1번부터
		arr = new int[N][N];
		for(int i=0 ; i<N ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0 ; j<N ; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		plan = new int[M];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0 ; i<M ; i++) {
			plan[i] = Integer.parseInt(st.nextToken())-1; // 인덱스 보정
		}
		for(int idx=0 ; idx<M-1 ; idx++) {
			int src = plan[idx];
			int dst = plan[idx+1];
			if(!bfs(src, dst)) {
				ans = "NO";
				break;
			}
		}
		System.out.println(ans);
	}
	public static boolean bfs(int src, int dst) {
		boolean[] visited = new boolean[N];
		visited[src] = true;
		Queue<Integer> q = new LinkedList<>();
		q.add(src);
		while(!q.isEmpty()) {
			int tmp = q.remove();
			if(tmp == dst) return true;
			for(int i=0 ; i<N ; i++) {
				if(arr[tmp][i]==0 || visited[i])
					continue;
				q.add(i);
				visited[i] = true;
			}
		}
		return false;
	}
}
