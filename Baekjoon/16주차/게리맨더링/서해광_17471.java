// 17471. 게리맨더링
// 비트마스크로 visited를 표시하고 싶었지만 실패했다.
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, ans;
	public static int[] p;
	public static int[][] graph;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		ans = Integer.MAX_VALUE;
		N = Integer.parseInt(st.nextToken());
		p = new int[N];
		graph = new int[N][N];
		st = new StringTokenizer(br.readLine());
		for(int i=0 ; i<N ; i++) {
			p[i] = Integer.parseInt(st.nextToken());
		}
		for(int i=0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			for(int j=0 ; j<size ; j++) {
				int a = Integer.parseInt(st.nextToken());
				graph[i][a-1]=1;
			}
		}
		for(int i=1 ; i<(1<<N)-1 ; i++) {
			solution(i);
		}
		System.out.println(ans==Integer.MAX_VALUE?-1:ans);
	} // main
	public static void solution(int bitmask) {
		boolean[] oneRegion = new boolean[N];
		boolean[] zeroRegion = new boolean[N];
		for(int i=0 ; i<N ; i++) {
			if((bitmask&(1<<i))>0) oneRegion[N-1-i]=true;
			else zeroRegion[N-1-i]=true;
		}
//		System.out.println(Arrays.toString(oneRegion));
//		System.out.println(Arrays.toString(zeroRegion));
		int a = bfs(oneRegion, 1);
		int b = bfs(zeroRegion, 0);
//		System.out.println("-------------");
//		System.out.println("a:"+a);
//		System.out.println("b:"+b);
		if(a!=-1 && b!=-1) {
			ans = Math.min(ans, Math.abs(a-b));
		}
	}
	public static int bfs(boolean[] region, int type) {
		int start = 0;
		for(int i=0 ; i<N ; i++) {
			if(region[i]) {
				start = i;
				break;
			}
		}
		int sum = 0;
		region[start] = false;
		Queue<Integer> q= new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) {
			int curr = q.remove();
			sum+=p[curr];
			for(int i=0 ; i<N ; i++) {
				if(graph[curr][i]>0 && region[i]) {
					q.add(i);
					region[i] = false;
				}
			}
		}
		for(int i=0 ; i<N ; i++) {
			if(region[i]) return -1; // true가 남아있다는 건 아직 방문하지 못한 노드가 남아있음(끊어진 그래프)
		}
		return sum;
	}
}
