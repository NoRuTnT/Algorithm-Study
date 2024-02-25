import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static int N, M;
	public static ArrayList<Integer>[] list;
	public static int[] degree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		list = new ArrayList[N + 1];
		degree = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list[from].add(to);
			degree[to]++;
		}
		
		Queue<Integer> que = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			if(degree[i] == 0) {
				que.add(i);
			}
		}
		for (int i = 1; i <= N; i++) {
			int n = que.poll();
			sb.append(n).append(" ");
			for (int j = 0; j < list[n].size(); j++) {
				if(--degree[list[n].get(j)] == 0) {
					que.add(list[n].get(j));
				}
			}
		}
		
		System.out.println(sb);
		

	}

}
