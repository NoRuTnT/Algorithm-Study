// 1766. 문제집
// 위상정렬과 우선순위 큐
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static int N, M;
	public static List[] graph;
	public static int[] count;
	public static PriorityQueue<Integer> pq;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new List[N+1];
		count = new int[N+1]; // 진입차수 저장
		for(int i=0 ; i<graph.length ; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		for(int i=0 ; i<M ; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			count[b]++;
		}
		pq = new PriorityQueue<>();
		for(int i=1 ; i<=N ; i++) {
			if(count[i]==0) {
				pq.add(i);
			}
		}
		while(!pq.isEmpty()) {
			int tmp = pq.remove();
			sb.append(tmp).append(" ");
			for(int i=0 ; i<graph[tmp].size() ; i++) {
				if(--count[(int)(graph[tmp].get(i))]==0) {
					pq.add((int)(graph[tmp].get(i)));
				}
			}
		}
		System.out.println(sb);
	} // main
}
