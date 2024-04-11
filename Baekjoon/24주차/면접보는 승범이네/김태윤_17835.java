package Week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 김태윤_17835 {
	static class Edge implements Comparable<Edge> {
		int from;
		long value;
		Edge(int from, long value){
			this.from=from;
			this.value=value;
		}

		@Override
		public int compareTo(Edge e) {
			if(this.value-e.value<0) return -1;
			else if(this.value-e.value>0) return 1;
			else return 0;
		}
	}
	static int n, m, k;
	static List<Edge>[] adjList;
	static int[] startPoint;
	static long max=0;
	static int place=100001;
	public static void main(String[] args) throws IOException {
		input();
		findPath();
		System.out.println(place);
		System.out.println(max);
	}
	private static void findPath(){
		PriorityQueue<Edge> pq=new PriorityQueue<>();
		boolean[] isVisited = new boolean[n+1];
		int count=0;
		for(int i=0; i<k; i++){
			int start=startPoint[i];
			for(int j=0; j<adjList[start].size(); j++){
				pq.offer(adjList[start].get(j));
			}
			isVisited[start]=true;
			count++;
			place=Math.min(place, start);
		}
		while(count<n){
			Edge curr=pq.poll();
			int from=curr.from;
			long value=curr.value;
			if(isVisited[from]) continue;
			// 여기 진입하면 최댓값임
			isVisited[from]=true;
			count++;
			if(max==value && place>from){
				place=from;
			}
			else if(max<value){
				max=value;
				place=from;
			}
			for(int i=0; i<adjList[from].size(); i++){
				Edge next=adjList[from].get(i);
				if(isVisited[next.from]) continue;
				pq.offer(new Edge(next.from, value+next.value));
			}
		}
	}
	private static void input() throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		adjList=new ArrayList[n+1];
		startPoint=new int[n+1];

		for(int i=1; i<=n; i++){
			adjList[i]=new ArrayList<>();
		}
		for(int i=0; i<m; i++){
			st=new StringTokenizer(br.readLine());
			int from=Integer.parseInt(st.nextToken());
			int to=Integer.parseInt(st.nextToken());
			long value=Long.parseLong(st.nextToken());
			adjList[to].add(new Edge(from, value));
		}
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<k; i++){
			int start=Integer.parseInt(st.nextToken());
			startPoint[i]=start;
		}
	}
}
