import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
class kill{
	int r;
	int c;
	kill(){}
	kill(int r, int c){
		this.r=r;
		this.c=c;
	}
}
public class Main {
	static int n;
	static int m;
	static int d;
	static int[][] field;
	static int killCnt=0;
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		field=new int[n][m];
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++) {
				field[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dfs(0,0,new int[3], new boolean[m]);
		System.out.println(killCnt);
	}
	public static void dfs(int depth, int idx, int[] archer, boolean[] check) {
		if(depth==3) {
			bfs(archer);
			return;
		}
		for(int i=idx;i<m;i++) {
			if(!check[i]) {
				archer[depth]=i;
				check[i]=true;
				dfs(depth+1, i+1, archer, check);
				check[i]=false;
			}
		}
	}
	public static void bfs(int[] archer) {
		int[] dr= {-1,0,0};
		int[] dc= {0,-1,1};
		int r=n;
		int cnt=0;
		boolean[][] check=new boolean[n][m];
		while(r>0) {
			LinkedList<kill> ans=new LinkedList<>();
			for(int i=0;i<3;i++) {
				LinkedList<kill> queue=new LinkedList<>();
				int move=0;
				queue.add(new kill(r, archer[i]));
				while(move<d) {
					int len=queue.size();
					ArrayList<kill>kill=new ArrayList<>();
					for(int j=0;j<len;j++) {
						kill now=queue.poll();
						for(int k=0;k<3;k++) {
							if(now.r+dr[k]<0 || now.r+dr[k]>=r || now.c+dc[k]<0 || now.c+dc[k]>=m) continue;
							if(field[now.r+dr[k]][now.c+dc[k]]==1 && !check[now.r+dr[k]][now.c+dc[k]]) {
								kill.add(new kill(now.r+dr[k],now.c+dc[k]));
							}
							else queue.add(new kill(now.r+dr[k],now.c+dc[k]));
						}
					}
					move++;
					if(!kill.isEmpty()) { // 잡은 게 있으면
						kill niceshot=kill.get(0);
						for(int j=1;j<kill.size();j++) {
							if(niceshot.c>kill.get(j).c) niceshot=kill.get(j);
						}
						ans.add(niceshot);
						break;
					}
				} // move end
			} // i end
			while(!ans.isEmpty()) {
				kill now=ans.poll();
				if(!check[now.r][now.c]) {
					check[now.r][now.c]=true;
					cnt++;
				}
			}
			r--;
		} // r end
		if(cnt>killCnt) {
			killCnt=cnt;
		}
	}
}
