package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b20040 {
	static int n,m;
	static int[] parent;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parent = new int[n];
		for(int i=0;i<n;i++) {
			parent[i] = i;
		}
		int turn=0;
		for(int i=0;i<m;i++) {			
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());			
			if(!union(a,b)) { //사이클만들어지면 turn에 숫자입력
				turn=i+1;
				break;				
			}
		}
		
		System.out.println(turn);
		
	}
	
	public static boolean union(int x, int y) {
		x = find(x);
		y = find(y); 	    
		
		if(x==y) {
			return false;
		}
		
		if(x<=y) {
			parent[y] = x;
		}else {
			parent[x] = y;
		}
		return true;
	}
	
	public static int find(int x) {
		if(parent[x] == x) {
			return x;
		}else {
			return find(parent[x]);
		}
	}
}
