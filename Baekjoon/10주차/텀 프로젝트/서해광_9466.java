// 9466. 텀 프로젝트
// 사이클을 구성하는 요소를 표시해야 함
// Stack을 이용해서 하나씩 찾아가는 방식(O(N))은 시간초과 발생
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int[] graph;
	public static boolean[] visited;
	public static boolean[] finished;
	public static int T, n, sum;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		T = Integer.parseInt(br.readLine());
		for(int tc=0 ; tc<T ; tc++) {
			n = Integer.parseInt(br.readLine());
			sum = 0;
			graph = new int[n+1];
			visited = new boolean[n+1];
			finished = new boolean[n+1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0 ; i<n ; i++) {
				graph[i+1] = Integer.parseInt(st.nextToken());
			}
			for(int i=1 ; i<=n ; i++) {
				if(!visited[i]) dfs(i);
			}
			sb.append(n-sum).append("\n");
		}
		System.out.println(sb.toString());
	}
	public static void dfs(int curr) {
		visited[curr] = true; 
		int next = graph[curr]; 
		if(!visited[next]) dfs(next); 
		else { // 다음 노드가 방문했다면
			if(!finished[next]) { 
				sum++; 
				while(next != curr) { 
					next = graph[next]; 
					sum++; 
				}
			}
		}
		finished[curr] = true; 
	}
}
