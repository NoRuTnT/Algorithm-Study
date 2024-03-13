import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int[] parent;
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static class God implements Comparable<God>{
		int start;
		int end;
		double len;
		public God(int start, int end, double len) {
			this.start=start;
			this.end=end;
			this.len=len;
			
		}		
		@Override
		public int compareTo(God o) {
			if(this.len - o.len > 0) {
				return 1;
			}else if(this.len == o.len) {
				return 0;
			}else {
				return -1;
			}
		}		
	}
	
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		Node[] arr = new Node[n+1];
		ArrayList<God> god = new ArrayList<>();
		parent = new int[n+1];
		
		for(int i=1;i<=n;i++) {
			parent[i]=i;
		}
		
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new Node(x,y);			
		}		
		
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(i!=j) {
					int x1 = arr[i].x;
					int y1 = arr[i].y;
					int x2 = arr[j].x;
					int y2 = arr[j].y;
					double len = distance(x1,y1,x2,y2);
					god.add(new God(i,j,len));
				}
			}
		}
		Collections.sort(god);
		double total = 0;	
		for(int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			if(union(x,y)) {
				
			}
		}
		
		for(int i=0;i<god.size();i++) {
			int start = god.get(i).start;
			int end = god.get(i).end;
			double len = god.get(i).len;
			if(find(start)!=find(end)) {
				if(union(start,end)) {
					total += len;
				}
			}
		}		
		System.out.println(String.format("%.2f", total));	
		
	}

	private static double distance(int x1, int y1,int x2,int y2) {
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2, 2));
	}

	private static int find(int x) {
		if(parent[x]==x) {
			return x;
		}else {
			return find(parent[x]);
		}
		
	}
	private static boolean union(int x, int y) {
		x = find(x);
		y = find(y);
		
		if(x==y) {
			return false;
		}
		if(x<y) {
			parent[y] =x;
		}else {
			parent[x] =y;
		}
		return true;
	}
}
