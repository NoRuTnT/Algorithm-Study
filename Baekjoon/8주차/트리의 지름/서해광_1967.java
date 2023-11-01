// 1967. 트리의 지름
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static int ans = 0;
	static class Node{
		int next;
		int weight;
		public Node(int next, int weight) {
			this.next = next;
			this.weight = weight;
		}
		
	}
	public static boolean[] visited;
	public static ArrayList<Node>[] tree;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		tree = new ArrayList[N];
		for(int i=0 ; i<N ; i++) {
			tree[i] = new ArrayList<Node>();
		}
		for(int i=0 ; i<N-1 ; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1; // 1부터 시작하는 인덱스 보정
			int b = Integer.parseInt(st.nextToken())-1;
			int w = Integer.parseInt(st.nextToken());
			tree[a].add(new Node(b, w));
			tree[b].add(new Node(a, w)); // 간선이동의 양방향 가능성 고려
		}
		for(int i=0 ; i<N ; i++) { 
      // 찾을 수 있는 모든 경로를 탐색해서 간선 가중치 합의 최대값을 구하는 방식
			visited = new boolean[N];
			dfs(i, 0);
		}
		System.out.println(ans);
	}
	public static void dfs(int node, int tempSum) {
		visited[node] = true;
		ans = Math.max(tempSum, ans);
		for(int i=0 ; i<tree[node].size() ; i++) {
			if(!visited[tree[node].get(i).next])
			dfs(tree[node].get(i).next, tempSum+tree[node].get(i).weight);
		}
	}
}
