import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class node{
	int r;
	int c;
	int value;
	node(){}
	node(int r, int c, int value){
		this.r=r;
		this.c=c;
		this.value=value;
	}
}
public class Main {
	static int n;
	static int[][] cave;
	static int[][] ans;
	static boolean[][] isVisited;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb=new StringBuilder();
		int tc=0;
		while(true) {
			n=Integer.parseInt(br.readLine());
			if(n==0) break;
			cave=new int[n][n];
			ans=new int[n][n]; 
			isVisited=new boolean[n][n];
			for(int i=0;i<n;i++) {
				StringTokenizer st=new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					cave[i][j]=Integer.parseInt(st.nextToken());
					ans[i][j]=Integer.MAX_VALUE;
				}
			}
			dijkstra();
			sb.append("Problem ").append(++tc).append(": ").append(ans[n-1][n-1]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	public static void dijkstra() {
		int[] dr= {1,0,-1,0};
		int[] dc= {0,1,0,-1};
		int r=0, c=0;
		ans[r][c]=cave[r][c];
		isVisited[r][c]=true;
		ArrayList<node>list=new ArrayList<>();
		while(r<n-1 || c<n-1) { // r, c 둘다 n-1 도달하면 더이상 탐색 X
			int min=Integer.MAX_VALUE;
			for(int i=0;i<4;i++) {
				if(r+dr[i]<0 || r+dr[i]>=n || c+dc[i]<0 || c+dc[i]>=n) continue; // 경계 조건
				if(isVisited[r+dr[i]][c+dc[i]]) continue;
				list.add(new node(r+dr[i],c+dc[i],ans[r][c]+cave[r+dr[i]][c+dc[i]])); // 갈 수 있는 후보 추가
			}
			int nextidx=0;
			for(int i=list.size()-1;i>=0;i--) {
				if(isVisited[list.get(i).r][list.get(i).c]) {
					list.remove(i); // 이미 지나온 거 지우기, 위처럼 추가하면 중복 가능성 있었음
					continue;
				}
				if(min>list.get(i).value) { // min 값에 해당하는 영역으로 다음에 이동시킨다
					r=list.get(i).r;
					c=list.get(i).c;
					min=list.get(i).value;
				}
			}
			ans[r][c]=min;
			isVisited[r][c]=true;
		}
	}
}
