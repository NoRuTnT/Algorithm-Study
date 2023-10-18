package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class b1976 {
	
	public static boolean union(int x,int y) { //유니온
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
	
	public static int find(int x) { //파인드
		if(parent[x]==x) {
			return x;
		}else {
			return find(parent[x]);
		}
	}
	static int[] parent; //부모저장배열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for(int i=0;i<n+1;i++) {
			parent[i]=i; //자기자신으로 초기화
		}
		for(int i=1;i<=n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=n;j++) {
				if(Integer.parseInt(st.nextToken())==1) {
					union(i,j);				//노드이어진거확인하고 부모를 바꿔줌	
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		int cnt=0;
		int check = find(Integer.parseInt(st.nextToken())); //처음시작하는곳 저장
		for(int i=0;i<m-1;i++) {
			if(find(Integer.parseInt(st.nextToken()))==check) { //부모가 시작하는곳이면 cnt+1
				cnt++;
			}
		}
		if(cnt==m-1) { //cnt가 모든경우 다더해지면 yes
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
	}

}
