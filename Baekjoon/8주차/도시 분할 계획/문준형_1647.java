package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class b1647 {
	
	static class Node implements Comparable<Node>{
		int start, end, dist;
		
		public Node(int start, int end, int dist) {
			this.start = start;
			this.end= end;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
	static int n,m;
	static Node[] list;
	static int[] parent;
	public static boolean union(int x,int y) {
		x = find(x);
		y = find(y);
		
		if(x == y) {
			return false;
		}
		if(x<y) {
			parent[y] =x;
		}else {
			parent[x] =y;
		}
		return true;
	}
	
	public static int find(int x) {
		if(parent[x]==x) {
			return x;
		}else {
			return find(parent[x]);
		}
	}
	//크루스칼사용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		list = new Node[m];
		parent = new int[n+1];
		for(int i=1;i<=n;i++) {
			parent[i]=i;
		}
		
		for( int i=0;i<m;i++) {
			st= new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			int c=Integer.parseInt(st.nextToken());
			list[i] = new Node(a,b,c);
		}
		
		
		System.out.println(kruskal());
		
	}
	private static int kruskal() {
		int res = 0;
		int cnt=0;
		Arrays.sort(list);	
		for(Node e:list) {
			if(union(e.start,e.end)) {
				res+=e.dist;
				if(++cnt == n-2 || n==2) {
					return res;
				}
				
			}
		}
		return res;
	}
	
	



}
