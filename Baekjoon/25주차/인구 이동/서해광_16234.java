// 16234. 인구 이동
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N,L,R,ans;
	static int[][] A;
	static class Node{
		int r;
		int c;
		int n;
		public Node(int r,int c,int n) {
			this.r=r;
			this.c=c;
			this.n=n;
		}
	}
	static int[]dr={-1,1,0,0};
	static int[]dc={0,0,-1,1};
	static ArrayList<ArrayList> vector;
	static boolean[][]visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		A=new int[N][N];
		for(int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				A[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			boolean find=false;
			visited=new boolean[N][N];
			vector=new ArrayList<ArrayList>();
			int idx=0;
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]) {
						vector.add(new ArrayList<Node>());
						dfs(i,j,idx);
						idx++;
					}
				}
				for(ArrayList<Node> list : vector) {
					if(list.size()>=2) {
						find=true;
						int num = 0;
						for(Node n : list) {
							num+=A[n.r][n.c];
						}
						for(Node n : list) {
							A[n.r][n.c]=num/list.size();
						}
					}
				}
			}
			if(!find)break;
			ans++;
		}
		System.out.println(ans);
	}
	public static void dfs(int r,int c,int idx) {
		vector.get(idx).add(new Node(r, c, A[r][c]));
		visited[r][c]=true;
		for(int d=0;d<4;d++) {
			int nr=r+dr[d];
			int nc=c+dc[d];
			if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc])continue;
			if(chk(A[r][c],A[nr][nc]))
				dfs(nr,nc,idx);
		}
	}
	public static boolean chk(int a,int b) {
		int diff=Math.abs(a-b);
		return L<=diff&&diff<=R;
	}
}
