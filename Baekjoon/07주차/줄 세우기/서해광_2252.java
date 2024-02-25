// 2252. 줄 세우기
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		List<Integer>[] list = new List[N+1];
		for(int i=1 ; i<=N ; i++) {
			list[i] = new ArrayList<>();
		}
		int[] degree = new int[N+1]; // 위상정렬을 위한 진입차수 저장
		for(int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dst = Integer.parseInt(st.nextToken());
			list[src].add(dst);
			degree[dst]++;
		}
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new LinkedList<>();
		for(int i=1 ; i<=N ; i++) {
			if(degree[i]==0)
				q.add(i);
		}
		while(!q.isEmpty()) {
			int tmp = q.remove();
			sb.append(tmp).append(" ");
			visited[tmp] = true;
			for(int i=0 ; i<list[tmp].size() ; i++) {
				if(!visited[list[tmp].get(i)]) {
					if(--degree[list[tmp].get(i)]==0)
						q.add(list[tmp].get(i));
				}
			}
		}
		System.out.println(sb.toString());
	}
}
